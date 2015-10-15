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
import com.youtu.*;

public class testcase4 {

	static String noface = "./dist/noface.jpg";
	static String noface2 = "./dist/testset/exception/face/scene1.jpg";
	static String pic_not_exist = "./dist/testset/exception/face/scene3.jpg";
	static String sameface1 = "./dist/testset/top5set/same_person/geyou_1.jpg";
	static String sameface2 = "./dist/testset/top5set/same_person/geyou_2.jpg";
	static String multiface = "./dist/testset/exception/face/nose_tip_871_254.jpg";
	static String onface_in_multiface = "./dist/testset/oneface_femal.png";
	static String another_oneface = "./dist/testset/correct/male/m4.jpg";
	static String broken_image1 = "./dist/testset/exception/content/bad.jpg";
	static String broken_image2 = "./dist/testset/exception/content/broken1.jpg";
	static String broken_image3 = "./dist/testset/exception/content/broken2.jpg";
	static String broken_image4 = "./dist/testset/exception/content/empty.jpg";

	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public void testcase4(Context context) {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
		try {
			System.out.println("=====================================");
			System.out.println("add face");
			System.out.println("-------------------------------------");
			System.out.println("addface correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage2, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface personid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage2, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace("not_exist", face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface two image both one face");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m4);
			face_ids.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface two image same");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage2, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
			JSONObject rsp = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(rsp);
            selectedImage.recycle();
            selectedImage2.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface two image A one face B multiface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			face_ids.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface two image A one face B noface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noface);
			face_ids.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("addface two image A one face B broken");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> face_ids = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			face_ids.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.broken1);
			face_ids.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("=====================================");
			System.out.println("delete face");
			System.out.println("-------------------------------------");
			System.out.println("delface correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> faces = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faces.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			faces.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			faceYoutu.AddFace(person_id, faces);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			List<String> face_ids = new ArrayList<String>();
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(0));
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(1));
			JSONObject respose = faceYoutu.DelFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("delface persoonid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> faces = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faces.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			faces.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			faceYoutu.AddFace(person_id, faces);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			List<String> face_ids = new ArrayList<String>();
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(0));
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(1));
			JSONObject respose = faceYoutu.DelFace("not_exist", face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("delface persoonid one face correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<Bitmap> faces = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faces.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			faces.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			faceYoutu.AddFace(person_id, faces);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			List<String> face_ids = new ArrayList<String>();
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(0));
			JSONObject respose = faceYoutu.DelFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("delface persoonid two face one not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<Bitmap> faces = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faces.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			faces.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			faceYoutu.AddFace(person_id, faces);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			List<String> face_ids = new ArrayList<String>();
			face_ids.add(face_ids1.getJSONArray("face_ids").getString(0));
			face_ids.add("not_exist");
			JSONObject respose = faceYoutu.DelFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("delface persoonid two face all not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<Bitmap> faces = new ArrayList<Bitmap>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faces.add(selectedImage);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.multi2);
			faces.add(selectedImage2);
            Bitmap selectedImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage3, person_id, group_ids);
			faceYoutu.AddFace(person_id, faces);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			List<String> face_ids = new ArrayList<String>();
			face_ids.add("not_exist");
			face_ids.add("not_exist1");
			JSONObject respose = faceYoutu.DelFace(person_id, face_ids);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
            selectedImage3.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
