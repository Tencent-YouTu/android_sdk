package com.qq.youtu.youtuyundemo;

/**
 * Created by addingchen on 2015/10/10.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.youtu.*;


public class fuzzycase_etc {
    public final static  String API_YOUTU_END_POINT = "http://api.youtu.qq.com/youtu/";
    public final static String API_TENCENTYUN_END_POINT = "https://youtu.api.qcloud.com/youtu/";

    // appid, secretid secretkey请到http://open.youtu.qq.com/注册
    public static final String APP_ID = "10002784";
    public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
    public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

    public void fuzzycase_etc(Context context){
        //Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
        Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
        try {

            System.out.println("=====================================");
            System.out.println("fuzzy detect");
            System.out.println("-------------------------------------");
            System.out.println("fuzzy detect correct");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.fuzzy);
            JSONObject respose = faceYoutu.FuzzyDetect(selectedImage);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------------------------------");
            System.out.println("fuzzy detect not fuzzy");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.good_pic);
            JSONObject respose = faceYoutu.FuzzyDetect(selectedImage);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------------------------------");
            System.out.println("fuzzy detect pic illegal");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
            JSONObject respose = faceYoutu.FuzzyDetect(selectedImage);
            // get respose
            System.out.println(respose);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken2);
            JSONObject ret2 = faceYoutu.FuzzyDetect(selectedImage2);
            System.out.println(ret2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad);
            JSONObject ret3 = faceYoutu.FuzzyDetect(selectedImage3);
            System.out.println(ret3);

//			JSONObject ret5 = faceYoutu.FuzzyDetect(file_not_exist);
//			System.out.println(ret5);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("=====================================");
            System.out.println("food detect");
            System.out.println("-------------------------------------");
            System.out.println("food detect correct");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.food01);
            JSONObject respose = faceYoutu.FoodDetect(selectedImage);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------------------------------");
            System.out.println("food detect not food");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.good_pic);
            JSONObject respose = faceYoutu.FoodDetect(selectedImage);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------------------------------");
            System.out.println("food detect pic illegal");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
            JSONObject respose = faceYoutu.FoodDetect(selectedImage);
            // get respose
            System.out.println(respose);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken2);
            JSONObject ret2 = faceYoutu.FoodDetect(selectedImage2);
            System.out.println(ret2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad);
            JSONObject ret3 = faceYoutu.FoodDetect(selectedImage3);
            System.out.println(ret3);
//			JSONObject ret5 = faceYoutu.FuzzyDetect(file_not_exist);
//			System.out.println(ret5);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            System.out.println("=====================================");
            System.out.println("image tag ");
            System.out.println("-------------------------------------");
            System.out.println("image tag correct");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.food01);
            JSONObject respose = faceYoutu.ImageTag(selectedImage);
            // get respose
            System.out.println(respose);
            selectedImage.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------------------------------");
            System.out.println("image tag pic illegal");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
            JSONObject respose = faceYoutu.ImageTag(selectedImage);
            // get respose
            System.out.println(respose);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken2);
            JSONObject ret2 = faceYoutu.ImageTag(selectedImage2);
            System.out.println(ret2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad);
            JSONObject ret3 = faceYoutu.ImageTag(selectedImage3);
            System.out.println(ret3);
//			JSONObject ret5 = faceYoutu.FuzzyDetect(file_not_exist);
//			System.out.println(ret5);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

