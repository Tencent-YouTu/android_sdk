package com.facein;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import com.qq.youtu.youtuyundemo.R;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CameraPreview extends GLSurfaceView implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener
{
    private final String vss =
            "attribute vec2 vPosition;\n" +
                    "attribute vec2 vTexCoord;\n" +
                    "varying vec2 texCoord;\n" +
                    "void main() {\n" +
                    "  texCoord = vTexCoord;\n" +
                    "  gl_Position = vec4 ( vPosition.y, vPosition.x, 0.0, 1.0 );\n" +
                    "}";

    private final String fss_front =
            "#extension GL_OES_EGL_image_external : require\n" +
                    "precision mediump float;\n" +
                    "uniform samplerExternalOES sTexture;\n" +
                    "varying vec2 texCoord;\n" +
                    "void main() {\n" +
                    "  gl_FragColor = texture2D(sTexture,texCoord);\n" +
                    "}";

    private final String fss_back =
            "#extension GL_OES_EGL_image_external : require\n" +
                    "precision mediump float;\n" +
                    "uniform samplerExternalOES sTexture;\n" +
                    "varying vec2 texCoord;\n" +
                    "void main() {\n" +
                    "  vec2 newText = vec2(1.0-texCoord.x, texCoord.y);\n" +
                    "  gl_FragColor = texture2D(sTexture,newText);\n" +
                    "}";


    private static String LOG_TAG = CameraPreview.class.getName();
    private List<Camera.Size> mSupportedPreviewSizes;

    private int[] mTextureId;
    private int[] fbo = new int[] { 0 };

    private SurfaceTexture  mSurfaceTexture;
    private Camera          mCamera;
    private FloatBuffer     pVertex;
    private FloatBuffer     pTexCoord;
    private ByteBuffer      pixelBuffer;
    private MediaRecorder   mMediarecorder;
    private File            mOutputFile;
    private Boolean         isPreviewing;
    private int             nCameraFrontBack; //0: front  1:back
    private int             hProgram;

    private int     bufferWidth         = 240;
    private int     bufferHeight        = 320;
    private int     mWindowWidth        = 320;
    private int     mWindowHeight       = 480;
    private static float   mPreviewRatio       = 640/480.0f;

    public static final int CameraFront = 0;
    public static final int CameraBack = 1;

    private OnTakePicCallBack mOnTakePicCallBack;
    private OnDrawFrameCallback mOnDrawFrameCallback;

    public interface OnDrawFrameCallback {
        void call(byte[] rgba, int w, int h);
    }

    public interface OnTakePicCallBack{
        void onPictureTaken(byte[] data);
    }

    public CameraPreview(Context context) {
        super(context);
        initView();
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.youtuattrs);
        nCameraFrontBack = typedArray.getInteger(R.styleable.youtuattrs_cameraPosition, 0);
        initView();
    }

    private void initView() {
        Log.d(LOG_TAG , "initView");
        float[] vtmp = {1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f};
        float[] ttmp = {1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};
        pVertex = ByteBuffer.allocateDirect(8 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        pVertex.put(vtmp);
        pVertex.position(0);
        pTexCoord = ByteBuffer.allocateDirect(8 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        pTexCoord.put(ttmp);
        pTexCoord.position(0);

        pixelBuffer = ByteBuffer.allocateDirect(bufferWidth * bufferHeight * 4).order(ByteOrder.nativeOrder());

        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);// fixed:
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        getHolder().setFormat(PixelFormat.RGBA_8888);
        if (nCameraFrontBack == CameraFront) {
            openFrontCamera();
        }else{
            openBackCamera();
        }

        Camera.Size size = mCamera.getParameters().getPreviewSize();
        mPreviewRatio = (float)size.width/size.height;
    }

    @Override
    public void onResume()
    {
        Log.d(LOG_TAG , "onResume");
    }

    @Override
    public void onPause()
    {
        Log.d(LOG_TAG , "onPause");
    }

    public void releaseRes(){
        releaseMediaRecorder();

        isPreviewing = false;
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        queueEvent(new Runnable() {
            @Override
            public void run() {
                if (mSurfaceTexture != null){
                    mSurfaceTexture.setOnFrameAvailableListener(null);

                    mSurfaceTexture.release();
                    mSurfaceTexture = null;
                }

                if (hProgram != 0){
                    GLES20.glDeleteProgram(hProgram);
                    hProgram = 0;
                }
                deleteTex();
            }
        });
    }

    public void setOnDrawFrameCallback(OnDrawFrameCallback callback) {
        mOnDrawFrameCallback = callback;
    }

    public void setOnTakePicCallBack(OnTakePicCallBack callBack){
        mOnTakePicCallBack = callBack;
    }


    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        requestRender();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

        super.surfaceDestroyed(holder);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        if (mSurfaceTexture != null) {
            mSurfaceTexture.setOnFrameAvailableListener(null);
            mSurfaceTexture.release();;
            mSurfaceTexture = null;
        }

        if (hProgram != 0) {
            GLES20.glDeleteProgram(hProgram);
            hProgram = 0;
        }

        initTex();
        mSurfaceTexture = new SurfaceTexture(mTextureId[0]);
        mSurfaceTexture.setOnFrameAvailableListener(this);

        try {
            mCamera.stopPreview();
            mCamera.setPreviewTexture(mSurfaceTexture);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nCameraFrontBack == CameraFront) {
            hProgram = loadShader(vss, fss_front);
        }else{
            hProgram = loadShader(vss, fss_back);
        }

        mWindowWidth = width;
        mWindowHeight = height;

        initCamera();
    }

    public File startRecordVideo(){
        mMediarecorder = new MediaRecorder();// 创建mediarecorder对象

        mCamera.unlock();
        mMediarecorder.setCamera(mCamera);
        // 设置录制视频源为Camera(相机)
        mMediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
        mMediarecorder
                .setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // 设置录制的视频编码h263 h264
//        mMediarecorder.setVideoFrameRate(15);
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_TIME_LAPSE_480P);
        mMediarecorder.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight);
        //        mediarecorder.setVideoEncodingBitRate(4000000);
        mMediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mMediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
        //
        //        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        //        mediarecorder.setVideoFrameRate(30);
        mMediarecorder.setOrientationHint(270);

        // 设置视频文件输出的路径
        mOutputFile = getOutputMediaFile();
        if (mOutputFile == null) {
            return null;
        }
        mMediarecorder.setOutputFile(mOutputFile.getPath());
        try {
            // 准备录制
            mMediarecorder.prepare();
            // 开始录制
            mMediarecorder.start();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            e.printStackTrace();
        } catch (IOException e) {
            releaseMediaRecorder();
            e.printStackTrace();
        }

        return mOutputFile;
    }

    public void stopRecordCamera(){
        releaseMediaRecorder();

    }

    public void takePicture(){
        if(isPreviewing && mCamera != null) {
            mCamera.takePicture(mShutterCallback, null, mJpegPictureCallback);
        }
    }

    public void setCameraFrontBack(int n) {
        nCameraFrontBack = n;
    }

    private void openFrontCamera() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mCamera = Camera.open(camIdx);
                return;
            }
        }

        mCamera = Camera.open();
    }

    private void openBackCamera() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mCamera = Camera.open(camIdx);
                return;
            }
        }

        mCamera = Camera.open();
    }

    private void initTex() {
        mTextureId = new int[2];
        GLES20.glGenTextures(2, mTextureId, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureId[0]);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId[1]);
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA,
                bufferWidth, bufferHeight, 0, GLES20.GL_RGBA,
                GLES20.GL_UNSIGNED_BYTE, null);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glGenFramebuffers(1, fbo, 0);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fbo[0]);
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,
                GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D,
                mTextureId[1], 0);
    }

    private void deleteTex() {
        if (mTextureId[0] != 0){
            GLES20.glDeleteTextures(2, mTextureId, 0);
            mTextureId[0] = 0;
            mTextureId[1] = 0;
        }

        if (fbo[0] != 0) {
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fbo[0]);
            GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,
                    GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, 0, 0);
            GLES20.glDeleteFramebuffers(1, fbo, 0);
            fbo[0] = 0;

        }
    }


    private void initCamera() {
        try {
            Camera.Parameters parameters = mCamera.getParameters();

            List<String> focusModes = parameters.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO))
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);

            mCamera.setParameters(parameters);
            mCamera.startPreview();
            isPreviewing = true;

        } catch (Exception e) {
            Log.d(LOG_TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    private void releaseMediaRecorder(){
        if (mMediarecorder != null) {
            // clear recorder configuration
            mMediarecorder.reset();
            // release the recorder object
            mMediarecorder.release();
            mMediarecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
            if (mCamera != null) {
                mCamera.lock();
            }
        }
    }



    private File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return  null;
        }

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "youtu");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()) {
                Log.d("CameraSample", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "VID_"+ timeStamp + ".mp4");
        return mediaFile;
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;

        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.height / size.width;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;

            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        if ( null != mSurfaceTexture){
            mSurfaceTexture.updateTexImage();
        }

        GLES20.glUseProgram(hProgram);

        int ph = GLES20.glGetAttribLocation(hProgram, "vPosition");
        int tch = GLES20.glGetAttribLocation(hProgram, "vTexCoord");
        int th = GLES20.glGetUniformLocation(hProgram, "sTexture");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureId[0]);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        GLES20.glUniform1i(th, 0);

        GLES20.glVertexAttribPointer(ph, 2, GLES20.GL_FLOAT, false, 4 * 2, pVertex);
        GLES20.glVertexAttribPointer(tch, 2, GLES20.GL_FLOAT, false, 4 * 2, pTexCoord);

        GLES20.glEnableVertexAttribArray(ph);
        GLES20.glEnableVertexAttribArray(tch);

        {
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fbo[0]);
            GLES20.glViewport(0, 0, bufferWidth, bufferHeight);

            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
            GLES20.glReadPixels(0, 0, bufferWidth, bufferHeight, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixelBuffer);
            if (mOnDrawFrameCallback != null) {
                mOnDrawFrameCallback.call(pixelBuffer.array(), bufferWidth, bufferHeight);
            }
        }

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        {
            double aspectHeight = mWindowHeight;
            double aspectWidth = mWindowHeight / mPreviewRatio;
            if (mWindowWidth > aspectWidth) {
                aspectWidth = mWindowWidth;
                aspectHeight = mWindowWidth * mPreviewRatio;
            }

            // glViewport(0, 0, width_, height_);
            GLES20.glViewport((int) -(aspectWidth - mWindowWidth) / 2,
                    (int) -(aspectHeight - mWindowHeight) / 2, (int) aspectWidth,
                    (int) aspectHeight);
        }
//        GLES20.glViewport(0, 0, mWindowWidth, mWindowHeight);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(ph);
        GLES20.glDisableVertexAttribArray(tch);
    }


    private static int loadShader(String vss, String fss) {
        int vshader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vshader, vss);
        GLES20.glCompileShader(vshader);
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(vshader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("Shader", "Could not compile vshader");
            Log.v("Shader", "Could not compile vshader:" + GLES20.glGetShaderInfoLog(vshader));
            GLES20.glDeleteShader(vshader);
            vshader = 0;
        }

        int fshader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fshader, fss);
        GLES20.glCompileShader(fshader);
        GLES20.glGetShaderiv(fshader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("Shader", "Could not compile fshader");
            Log.v("Shader", "Could not compile fshader:" + GLES20.glGetShaderInfoLog(fshader));
            GLES20.glDeleteShader(fshader);
            fshader = 0;
        }

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vshader);
        GLES20.glAttachShader(program, fshader);
        GLES20.glLinkProgram(program);

        return program;
    }

    ShutterCallback mShutterCallback = new ShutterCallback()
    {
        public void onShutter() {
            Log.i(LOG_TAG, "myShutterCallback:onShutter...");
        }
    };

    PictureCallback mJpegPictureCallback = new PictureCallback()
    {
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.i(LOG_TAG, "myJpegCallback:onPictureTaken...");
            mCamera.stopPreview();
            isPreviewing = false;
            if (mOnTakePicCallBack != null) {
                mOnTakePicCallBack.onPictureTaken(data);
            }
            mCamera.startPreview();
            isPreviewing = true;
        }
    };


}
