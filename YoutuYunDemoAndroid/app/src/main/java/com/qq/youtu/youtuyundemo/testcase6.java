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

public class testcase6 {

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

	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public  void testcase6(Context context) {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
		try {
			System.out.println("=====================================");
			System.out.println("get groupids");
			System.out.println("-------------------------------------");
			System.out.println("get groupid correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id" + System.currentTimeMillis());
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetGroupIds();
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("=====================================");
			System.out.println("get personids");
			System.out.println("-------------------------------------");
			System.out.println("get personid correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
            Bitmap selectedImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_2);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			faceYoutu.NewPerson(selectedImage2, person_id + "1", group_ids);
			JSONObject respose = faceYoutu.GetPersonIds(group_id);
			System.out.println(respose);
            selectedImage.recycle();
            selectedImage2.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get personid groupid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetPersonIds("not_exist");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get personid groupid is empty");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetPersonIds("");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("=====================================");
			System.out.println("get faceids");
			System.out.println("-------------------------------------");
			System.out.println("get faceid correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetFaceIds(person_id);
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get faceid personid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetFaceIds("not_exist");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get faceid personid is empty");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetFaceIds("");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("=====================================");
			System.out.println("get faceinfo");
			System.out.println("-------------------------------------");
			System.out.println("get faceinfo correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject face_ids1 = faceYoutu.GetFaceIds(person_id);
			JSONObject respose = faceYoutu.GetFaceInfo(face_ids1.getJSONArray("face_ids").getString(0));
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get faceinfo faceid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetFaceInfo("not_exist");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("-------------------------------------");
			System.out.println("get faceinfo faceid is empty");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			String group_id = "group_id"+System.currentTimeMillis();
			group_ids.add(group_id);
			List<String> faces = new ArrayList<String>();
            Bitmap selectedImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.geyou_1);
			faceYoutu.NewPerson(selectedImage, person_id, group_ids);
			JSONObject respose = faceYoutu.GetFaceInfo("");
			System.out.println(respose);
            selectedImage.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream Drawable2InputStream(Drawable d) {
		Bitmap bitmap = this.drawable2Bitmap(d);
		return this.Bitmap2InputStream(bitmap);
	}

	public Bitmap drawable2Bitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}
	public InputStream Bitmap2InputStream(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}
}
