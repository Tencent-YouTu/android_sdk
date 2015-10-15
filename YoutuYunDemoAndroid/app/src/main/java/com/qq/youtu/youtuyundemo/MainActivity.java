package com.qq.youtu.youtuyundemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.youtu.Youtu;

import org.json.JSONObject;

public class MainActivity extends Activity {

    public static final String APP_ID = "10002784";
    public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
    public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";
    private Bitmap theSelectedImage = null;
    private  BitmapFactory.Options opts = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.localTest);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        button = (Button)findViewById(R.id.remoteTest);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
                            JSONObject respose = faceYoutu.FaceCompareUrl("http://open.youtu.qq.com/content/img/slide-1.jpg", "http://open.youtu.qq.com/content/img/slide-1.jpg");
                            System.out.println(respose);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        opts = new BitmapFactory.Options();
        opts.inDensity = this.getResources().getDisplayMetrics().densityDpi;
        opts.inTargetDensity = this.getResources().getDisplayMetrics().densityDpi;

        testcase1();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                testcase2 case2 = new testcase2();
//                case2.tasecase2(context);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                testcase3 case3 = new testcase3();
//                case3.testcase3(context);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                testcase4 case4 = new testcase4();
//                case4.testcase4(context);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                testcase5 case5 = new testcase5();
//                case5.testcase5(context);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                testcase6 case6 = new testcase6();
//                case6.testcase6(context);
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Context context = getApplicationContext();
//                fuzzycase_etc fuzzycase_etc = new fuzzycase_etc();
//                fuzzycase_etc.fuzzycase_etc(context);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                fuzzycase_etc_url case_url = new fuzzycase_etc_url();
//                case_url.fuzzycase_etc_url();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testcase7_url case7 = new testcase7_url();
//                case7.testcase7_url();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testcase8_url case8 = new testcase8_url();
//                case8.testcase8_url();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testcase9_url case9 = new testcase9_url();
//                case9.testcase9_url();
//            }
//        }).start();
    }

    void testcase1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_TENCENTYUN_END_POINT);
                Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);

                Context context = getApplicationContext();
                Resources res = context.getResources();

                Uri noface = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.noface) + "/"
                        + res.getResourceTypeName(R.drawable.noface) + "/"
                        + res.getResourceEntryName(R.drawable.noface));
                Uri sameface1 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.geyou_1) + "/"
                        + res.getResourceTypeName(R.drawable.geyou_1) + "/"
                        + res.getResourceEntryName(R.drawable.geyou_1));
                Uri sameface2 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.geyou_2) + "/"
                        + res.getResourceTypeName(R.drawable.geyou_2) + "/"
                        + res.getResourceEntryName(R.drawable.geyou_2));
                Uri multiface = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.nose_tip_871_254) + "/"
                        + res.getResourceTypeName(R.drawable.nose_tip_871_254) + "/"
                        + res.getResourceEntryName(R.drawable.nose_tip_871_254));
                Uri multiface2 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.multi1) + "/"
                        + res.getResourceTypeName(R.drawable.multi1) + "/"
                        + res.getResourceEntryName(R.drawable.multi1));
                Uri onface_in_multiface = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.oneface_femal) + "/"
                        + res.getResourceTypeName(R.drawable.oneface_femal) + "/"
                        + res.getResourceEntryName(R.drawable.oneface_femal));
                Uri broken_image1 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.broken1) + "/"
                        + res.getResourceTypeName(R.drawable.broken1) + "/"
                        + res.getResourceEntryName(R.drawable.broken1));
                Uri broken_image2 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.bad) + "/"
                        + res.getResourceTypeName(R.drawable.bad) + "/"
                        + res.getResourceEntryName(R.drawable.bad));
                Uri broken_image3 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.broken2) + "/"
                        + res.getResourceTypeName(R.drawable.broken2) + "/"
                        + res.getResourceEntryName(R.drawable.broken2));
                Uri food = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.food01) + "/"
                        + res.getResourceTypeName(R.drawable.food01) + "/"
                        + res.getResourceEntryName(R.drawable.food01));
                Uri fuzzy_pic = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.fuzzy) + "/"
                        + res.getResourceTypeName(R.drawable.fuzzy) + "/"
                        + res.getResourceEntryName(R.drawable.fuzzy));
                Uri not_fuzzy_pic = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + res.getResourcePackageName(R.drawable.good_pic) + "/"
                        + res.getResourceTypeName(R.drawable.good_pic) + "/"
                        + res.getResourceEntryName(R.drawable.geyou_2));

                try {

                    System.out.println("=====================================");
                    System.out.println("detectFace");
                    System.out.println("-------------------------------------");
                    System.out.println("detect face mode 0");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_1, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 0);
                    System.out.println(respose);
                    selectedImage.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("-------------------------------------");
                    System.out.println("detect face mode 1");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_1, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 1);
                    System.out.println(respose);
                    selectedImage.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("-------------------------------------");
                    System.out.println("detect multiface mode 0");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.nose_tip_871_254, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 0);
                    // get respose
                    System.out.println(respose);
                    selectedImage.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("-------------------------------------");
                    System.out.println("detect multiface mode 1");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.multi2, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 1);
                    // get respose
                    System.out.println(respose);
                    selectedImage.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("-------------------------------------");
                    System.out.println("detect noface mode 0");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.noface, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 0);
                    // get respose
                    System.out.println(respose);
                    selectedImage.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("-------------------------------------");
                    System.out.println("detect face image is illegal");
                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.broken1, opts);
                    JSONObject respose = faceYoutu.DetectFace(selectedImage, 0);
                    // get respose
                    System.out.println(respose);

                    Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.broken2, opts);
                    JSONObject ret2 = faceYoutu.DetectFace(selectedImage2, 0);
                    System.out.println(ret2);

                    Bitmap selectedImage3 = BitmapFactory.decodeResource(getResources(), R.drawable.bad, opts);
                    JSONObject ret3 = faceYoutu.DetectFace(selectedImage3, 0);
                    System.out.println(ret3);
                    selectedImage.recycle();
                    selectedImage2.recycle();
                    selectedImage3.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//
//                try {
//
//                    System.out.println("=====================================");
//                    System.out.println("faceshape");
//                    System.out.println("-------------------------------------");
//                    System.out.println("faceshape mode 0");
//                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_2, , opts);
//                    JSONObject respose = faceYoutu.FaceShape(selectedImage, 0);
//                    // get respose
//                    System.out.println(respose);
//                    selectedImage.recycle();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                try {
//
//                    System.out.println("-------------------------------------");
//                    System.out.println("FaceShape mode 1");
//                    Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_2, opts);
//                    JSONObject respose = faceYoutu.FaceShape(selectedImage, 1);
//                    // get respose
//                    System.out.println(respose);
//                    selectedImage.recycle();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//        try {
//
//            System.out.println("-------------------------------------");
//            System.out.println("shape multiface mode 0");
//            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.multi2, opts);;
//            JSONObject respose = faceYoutu.FaceShape(selectedImage, 0);
//            // get respose
//            System.out.println(respose);
//            selectedImage.recycle();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//            System.out.println("-------------------------------------");
//            System.out.println("shape multiface mode 1");
//            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.multi2, opts);
//            JSONObject respose = faceYoutu.FaceShape(selectedImage, 1);
//            // get respose
//            System.out.println(respose);
//            selectedImage.recycle();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//            System.out.println("-------------------------------------");
//            System.out.println("shape noface mode 0");
//            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.noface, opts);
//            JSONObject respose = faceYoutu.FaceShape(selectedImage, 0);
//            // get respose
//            System.out.println(respose);
//            selectedImage.recycle();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//            System.out.println("-------------------------------------");
//            System.out.println("shape face image is illegal");
//            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.broken1, opts);
//            JSONObject respose = faceYoutu.FaceShape(selectedImage, 0);
//            System.out.println(respose);
//
//            Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.broken2, opts);
//            JSONObject ret2 = faceYoutu.FaceShape(selectedImage2, 0);
//            System.out.println(ret2);
//
//            Bitmap selectedImage3 = BitmapFactory.decodeResource(getResources(), R.drawable.bad, opts);
//            JSONObject ret3 = faceYoutu.FaceShape(selectedImage3, 0);
//            System.out.println(ret3);
//            selectedImage.recycle();
//            selectedImage2.recycle();
//            selectedImage3.recycle();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        try {

            System.out.println("=====================================");
            System.out.println("FaceCompare");
            System.out.println("-------------------------------------");
            System.out.println("FaceCompare both face only one");
            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_2, opts);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_1, opts);
            ;
            JSONObject respose = faceYoutu.FaceCompare(selectedImage, selectedImage2);
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceCompare A multiface face B multiface");
            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.multi2, opts);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.multi3, opts);

            JSONObject respose = faceYoutu.FaceCompare(selectedImage, selectedImage2);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceCompare A face B no");
            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_2, opts);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.noface, opts);

            JSONObject respose = faceYoutu.FaceCompare(selectedImage, selectedImage2);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceCompare A face B broken");
            Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_2, opts);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.broken1, opts);
            JSONObject respose = faceYoutu.FaceCompare(selectedImage, selectedImage2);
            // get respose
            System.out.println(respose);

            Bitmap selectedImage3= BitmapFactory.decodeResource(getResources(), R.drawable.broken2, opts);
            JSONObject ret2 = faceYoutu.FaceCompare(selectedImage, selectedImage3);
            System.out.println(ret2);

            Bitmap selectedImage4 = BitmapFactory.decodeResource(getResources(), R.drawable.bad, opts);
            JSONObject ret3 = faceYoutu.FaceCompare(selectedImage, selectedImage4);
            System.out.println(ret3);
//            selectedImage.recycle();
//            selectedImage2.recycle();
//            selectedImage3.recycle();
//            selectedImage4.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }
            }
        }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            try {
                String path = uri2Path(uri);
                theSelectedImage = getBitmap(path, 1000, 1000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (theSelectedImage != null) {
                            try {
                                Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT);
                                JSONObject respose = faceYoutu.DetectFace(theSelectedImage, 1);
                                System.out.println(respose);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            } catch ( Exception e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String uri2Path(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(1);
        cursor.close();
        return path;
    }

    private Bitmap getBitmap(String path , int maxWidth, int maxHeight){
        //先解析图片边框的大小
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = true;
        ops.inSampleSize = 1;
        int oHeight = ops.outHeight;
        int oWidth = ops.outWidth;

        //控制压缩比
        int contentHeight = maxWidth;
        int contentWidth = maxHeight;
        if(((float)oHeight/contentHeight) < ((float)oWidth/contentWidth)){
            ops.inSampleSize = (int) Math.ceil((float)oWidth/contentWidth);
        }else{
            ops.inSampleSize = (int) Math.ceil((float)oHeight/contentHeight);
        }
        ops.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(path, ops);
        return bm;
    }


}
