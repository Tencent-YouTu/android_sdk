package com.qq.youtu.youtuyundemo;

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

import com.youtu.Youtu;

public class testcase3 {

	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public void testcase3(Context context) {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
		
		try {
			System.out.println("=====================================");
			System.out.println("NewPerson");
			System.out.println("-------------------------------------");
			System.out.println("personid is empty");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			JSONObject respose = faceYoutu.NewPerson(selectedImage, "", group_ids);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			System.out.println("-------------------------------------");
			System.out.println("groupid is empty");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("");
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			JSONObject respose = faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			JSONObject respose = faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("groupid is repeat");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String groupid = "group_id"+System.currentTimeMillis();
			group_ids.add(groupid);
			group_ids.add(groupid);
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			JSONObject respose = faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("=====================================");
			System.out.println("delete Person");
			System.out.println("-------------------------------------");
			System.out.println("personid exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.DelPerson(person_id);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("personid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.DelPerson("not_exist");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("personid has been delete");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.DelPerson(person_id);
			System.out.println(respose);

			JSONObject ret = faceYoutu.DelPerson(person_id);
			System.out.println(ret);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
