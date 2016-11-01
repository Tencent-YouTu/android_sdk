package com.facein;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.common.LoadingDialog;
import com.common.YTServerAPI;
import com.qq.youtu.youtuyundemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class CameraActivity extends Activity
{
    private CameraPreview mCamPreview;
    private String LOG_TAG = CameraActivity.class.getName();

    private Button startButton;
    private TextView validateNumText;
    private Boolean isVedioStart;
    private File mVideoFile;
    private String mValidateNum;

    private LoadingDialog mLoadingDialog;
    private Bitmap mFaceBitmap;
    private String mName;
    private String mIdCard;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Log.d(LOG_TAG, "onCreate");

        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_livecheck);

        Intent intent = getIntent();
        mValidateNum = intent.getStringExtra("validate_data");
        mName = intent.getStringExtra("name");
        mIdCard = intent.getStringExtra("idCard");

        mLoadingDialog = new LoadingDialog(this);
        isVedioStart = false;

        validateNumText = (TextView) findViewById(R.id.validateNumber);
        startButton = (Button) findViewById(R.id.start);
        mCamPreview = (CameraPreview) findViewById(R.id.camPreview);
        mCamPreview.setCameraFrontBack(CameraPreview.CameraFront);

        validateNumText.setText(mValidateNum);

//        camPreview.setOnDrawFrameCallback(new CameraPreview.OnDrawFrameCallback() {
//            @Override
//            public void call(byte[] bytes, final int w, final int h) {
//                if ( !bProcessing )
//                {
////                    faceStatus = facecheck.DoDetectionProcess(bytes, w, h);
//                    sw = w;
//                    sh = h;
//                }
//
//            }
//        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //record video
                isVedioStart = !isVedioStart;
                if (isVedioStart) {
                    startButton.setText("结束");
                    mVideoFile = mCamPreview.startRecordVideo();
                }else {
                    startButton.setText("开始");
                    mCamPreview.stopRecordCamera();
                    uploadVideo();
                }
           }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamPreview.onResume();
    }

    @Override
    public void onPause() {
        mCamPreview.onPause();

        super.onPause();
    }

    @Override
    public void onDestroy(){
        mFaceBitmap = null;
        mCamPreview.releaseRes();

        super.onDestroy();
    }



    private void uploadVideo(){
        if (mVideoFile == null) {
            return;
        }

        try {
            FileInputStream stream = new FileInputStream(mVideoFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();

            final byte[] vedioByte = out.toByteArray();

            final YTServerAPI mServer = new YTServerAPI();
            mServer.setRequestListener(new YTServerAPI.OnRequestListener() {
                @Override
                public void onSuccess(int statusCode, String responseBody) {
                    mLoadingDialog.dismiss();
                    handleData(responseBody);

                }

                @Override
                public void onFailure(int statusCode) {
                    mLoadingDialog.dismiss();
                    Log.d(LOG_TAG, "http request error : " + statusCode);
                    Message msg = new Message();
                    msg.obj = "http request error : " + statusCode;
                    handler.sendMessage(msg);
                }
            });

            mLoadingDialog.setText("正在上传视频");
            mLoadingDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mServer.idcardlivedetectfour(vedioByte, mValidateNum, mName, mIdCard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }catch (Exception e) {
            e.printStackTrace();
            mLoadingDialog.dismiss();
        }
    }

    private void handleData(String responseBody){

        try {
            JSONObject jsonObject = new JSONObject(responseBody.toString());
            int errorCode = jsonObject.getInt("errorcode");
            int compareStatus = jsonObject.getInt("compare_status");
            int liveStatus = jsonObject.getInt("live_status");
            int sim = jsonObject.getInt("sim");
            if (errorCode == 0) {
                String message = "";
                String copmareResult = "";
                String liveDetectStatus = "";
                if(compareStatus == 0)//face comparison succeeded
                {
                    copmareResult = "通过";
                }else {
                    copmareResult = "不通过";
                }

                message = message + "人脸识别：" + copmareResult + "(" + sim + ")";

                liveDetectStatus = "通过";
                switch (liveStatus) {
                    case -5001:
                        liveDetectStatus = "视频文件异常，请重新录制。";
                        break;
                    case -5002:
                        liveDetectStatus = "活体检测失败，请重新录制。";
                        break;
                    case -5007:
                        liveDetectStatus = "视频文件异常，请重新录制。";
                        break;
                    case -5008:
                        liveDetectStatus = "活体检测失败，请重新录制。";
                        break;
                    case -5009:
                        liveDetectStatus = "活体检测失败，请重新录制。";
                        break;
                    case -5010:
                        liveDetectStatus = "活体检测失败，请重新录制。";
                        break;
                    case -5011:
                        liveDetectStatus = "活体检测失败，请重新录制。";
                        break;
                    case -5012:
                        liveDetectStatus = "活体检测失败，请选择安静的环境朗读。";
                        break;
                    case -5013:
                        break;

                    default:
                        break;
                }

                message = message + "\n";
                message = message + "活体检测: " + liveDetectStatus;



                Message msg = new Message();
                msg.obj = message;
                handler.sendMessage(msg);
            }else {
                Message msg = new Message();
                msg.obj = "活体检测失败，请重新录制。 ";
                handler.sendMessage(msg);

            }

        }catch (JSONException e){

        }

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            AlertDialog.Builder builder = new AlertDialog.Builder(CameraActivity.this);
            builder.setTitle("对比结果");
            builder.setMessage(msg.obj.toString());
//            TextView myMsg = new TextView(CameraActivity.this);
//            myMsg.setText(msg.obj.toString());
//            myMsg.setTextSize(16);
//            myMsg.setGravity(Gravity.CENTER);
//            builder.setView(myMsg);
            builder.setPositiveButton("确定", null).create().show();
        }
    };
}
