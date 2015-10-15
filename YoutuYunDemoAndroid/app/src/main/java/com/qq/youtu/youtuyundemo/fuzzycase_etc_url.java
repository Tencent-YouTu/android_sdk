package com.qq.youtu.youtuyundemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.youtu.*;

public class fuzzycase_etc_url {
	public final static  String API_YOUTU_END_POINT = "http://api.youtu.qq.com/youtu/";
	public final static String API_TENCENTYUN_END_POINT = "https://youtu.api.qcloud.com/youtu/";
	
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
	static String fuzzy_pic = "./dist/0948410.jpg";
	static String not_fuzzy_pic = "./dist/good_pic.jpg";
	
	static String nofaceurl = "http://avatar.csdn.net/7/7/A/1_lewsn2008.jpg";
	static String onefaceurl = "http://img1.gtimg.com/ent/pics/hv1/189/25/952/61910364.jpg";
	static String multifaceurl = "http://photos.imageevent.com/sipphoto/samplepictures/large/Cousins%20FE%20crop.jpg";
	static String notpicurl = "http://www.cnblogs.com/end/archive/2012/02/21/2360965.html";
	static String wrongurl = "http://img1.gtimg.com/ent/pics/hv1/189/25kgewkpewmnh952/61910364.jpg";
	static String oneface2url = "http://i0.sinaimg.cn/IT/2011/1215/U5384P2DT20111215041107.png";
	static String samefaceurl_withoneface = "http://img3.douban.com/img/celebrity/medium/37843.jpg";
	static String fuzzy_pic_url = "http://images.51cto.com/files/uploadimg/20121115/0948410.jpg";
	static String not_fuzzy_pic_url = "http://img.bz1111.com/800/2012-8/2012081210378.jpg";
	static String food_url = "http://img.ivsky.com/img/tupian/pre/201009/09/meishidacan-004.jpg";
	
	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public static void fuzzycase_etc_url() {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_YOUTU_END_POINT);
		
		try {

			System.out.println("=====================================");
			System.out.println("fuzzy detect url");
			System.out.println("-------------------------------------");
			System.out.println("fuzzy detect url correct");
			JSONObject respose = faceYoutu.FuzzyDetectUrl(fuzzy_pic_url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("fuzzy detect url not fuzzy");
			JSONObject respose = faceYoutu.FuzzyDetectUrl(not_fuzzy_pic_url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("fuzzy detect url pic illegal");
			JSONObject respose = faceYoutu.FuzzyDetectUrl(notpicurl);
			// get respose
			System.out.println(respose);

			JSONObject ret4 = faceYoutu.FuzzyDetectUrl(wrongurl);
			System.out.println(ret4);
			
			JSONObject ret5 = faceYoutu.FuzzyDetectUrl("");
			System.out.println(ret5);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("=====================================");
			System.out.println("food detect url");
			System.out.println("-------------------------------------");
			System.out.println("food detect url correct");
			JSONObject respose = faceYoutu.FoodDetectUrl(food_url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("food detect url not fuzzy");
			JSONObject respose = faceYoutu.FoodDetectUrl(not_fuzzy_pic_url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("food detect url pic illegal");
			JSONObject respose = faceYoutu.FoodDetectUrl(notpicurl);
			// get respose
			System.out.println(respose);

			JSONObject ret4 = faceYoutu.FoodDetectUrl(wrongurl);
			System.out.println(ret4);
			
			JSONObject ret5 = faceYoutu.FoodDetectUrl("");
			System.out.println(ret5);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("=====================================");
			System.out.println("image tag url detect");
			System.out.println("-------------------------------------");
			System.out.println("image tag url correct");
			JSONObject respose = faceYoutu.ImageTagUrl(food_url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("image tag url pic illegal");
			JSONObject respose = faceYoutu.ImageTagUrl(wrongurl);
			// get respose
			System.out.println(respose);

			JSONObject ret2 = faceYoutu.ImageTagUrl(notpicurl);
			System.out.println(ret2);

			JSONObject ret3 = faceYoutu.ImageTagUrl("");
			System.out.println(ret3);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}