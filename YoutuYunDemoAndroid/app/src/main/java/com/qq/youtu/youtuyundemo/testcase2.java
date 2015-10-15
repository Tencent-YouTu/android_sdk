package com.qq.youtu.youtuyundemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.youtu.Youtu;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by addingchen on 2015/9/29.
 */
public class testcase2 {

    static String noface = "./dist/noface.jpg";
    static String sameface1 = "./dist/testset/top5set/same_person/geyou_1.jpg";
    static String sameface2 = "./dist/testset/top5set/same_person/geyou_2.jpg";
    static String multiface = "./dist/testset/exception/face/nose_tip_871_254.jpg";
    static String onface_in_multiface = "./dist/testset/oneface_femal.png";
    static String another_oneface = "./dist/testset/correct/male/m4.jpg";
    static String broken_image1 = "./dist/testset/exception/content/bad.jpg";
    static String broken_image2 = "./dist/testset/exception/content/broken1.jpg";
    static String broken_image3 = "./dist/testset/exception/content/broken2.jpg";
    static String broken_image4 = "./dist/testset/exception/content/empty.jpg";
    static String file_not_exist = "./aa.jpg";

    // appid, secretid secretkey请到http://open.youtu.qq.com/注册
    public static final String APP_ID = "10002784";
    public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
    public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

    public void tasecase2(Context context){
        //Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
        Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);

        try {

            System.out.println("=====================================");
            System.out.println("FaceVerify");
            System.out.println("-------------------------------------");
            System.out.println("FaceVerify correct");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceVerify(selectedImage2, person_id);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceVerify image noface");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id"+System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noface);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceVerify(selectedImage2, person_id);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceVerify personid not exist");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id"+System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceVerify(selectedImage2, "not_exist");
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceVerify A broken");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceVerify(selectedImage2, person_id);
            // get respose
            System.out.println(respose);

            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken2);
            JSONObject ret2 = faceYoutu.FaceVerify(selectedImage3, person_id);
            System.out.println(ret2);

            Bitmap selectedImage4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad);
            JSONObject ret3 = faceYoutu.FaceVerify(selectedImage4, person_id);
            System.out.println(ret3);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
            selectedImage4.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("=====================================");
            System.out.println("FaceIdentify");
            System.out.println("-------------------------------------");
            System.out.println("FaceIdentify correct");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id"+System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceIdentify(selectedImage2, group_ids.get(0));
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceIdentify groupid not exist");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id"+System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceIdentify(selectedImage2, "not_exist");
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceIdentify image no face");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id"+System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noface);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceIdentify(selectedImage2, group_ids.get(0));
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("-------------------------------------");
            System.out.println("FaceIdentify A broken");
            String person_id = "person_id" + System.currentTimeMillis();
            List<String> group_ids = new ArrayList<String>();
            group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
            faceYoutu.NewPerson(selectedImage, person_id, group_ids);
            JSONObject respose = faceYoutu.FaceIdentify(selectedImage2, group_ids.get(0));
            // get respose
            System.out.println(respose);

            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken2);
            JSONObject ret2 = faceYoutu.FaceIdentify(selectedImage3, group_ids.get(0));
            System.out.println(ret2);

            Bitmap selectedImage4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad);
            JSONObject ret3 = faceYoutu.FaceIdentify(selectedImage4, group_ids.get(0));
            System.out.println(ret3);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
            selectedImage4.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

