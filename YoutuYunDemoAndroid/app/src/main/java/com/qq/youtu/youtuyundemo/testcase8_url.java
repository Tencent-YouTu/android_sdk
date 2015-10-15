package com.qq.youtu.youtuyundemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.youtu.*;

public class testcase8_url {
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
	
	static String nofaceurl = "http://avatar.csdn.net/7/7/A/1_lewsn2008.jpg";
	static String onefaceurl = "http://img1.gtimg.com/ent/pics/hv1/189/25/952/61910364.jpg";
	static String multifaceurl = "http://photos.imageevent.com/sipphoto/samplepictures/large/Cousins%20FE%20crop.jpg";
	static String notpicurl = "http://www.cnblogs.com/end/archive/2012/02/21/2360965.html";
	static String wrongurl = "http://img1.gtimg.com/ent/pics/hv1/189/25kgewkpewmnh952/61910364.jpg";
	static String oneface2url = "http://i0.sinaimg.cn/IT/2011/1215/U5384P2DT20111215041107.png";
	static String samefaceurl_withoneface = "http://img3.douban.com/img/celebrity/medium/37843.jpg";
	
	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public static void testcase8_url() {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_YOUTU_END_POINT);
		try {

			System.out.println("=====================================");
			System.out.println("FaceVerifyURL");
			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL correct");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(onefaceurl, person_id);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL image oneface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(onefaceurl, person_id);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL image mueltiface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(multifaceurl, person_id);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL image noface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(nofaceurl, person_id);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL url is illegal");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(notpicurl, person_id);
			// get respose
			System.out.println(respose);
			
			JSONObject ret5 = faceYoutu.FaceVerifyUrl(wrongurl, person_id);
			System.out.println(ret5);
			JSONObject ret2 = faceYoutu.FaceVerifyUrl("", person_id);
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceVerifyURL with personid not exist");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceVerifyUrl(onefaceurl, "not_exist");
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("=====================================");
			System.out.println("FaceIdentifyURL");
			System.out.println("-------------------------------------");
			System.out.println("FaceIdentifyURL oneface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceIdentifyUrl(onefaceurl, group_ids.get(0));
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceIdentifyURL multiface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceIdentifyUrl(multifaceurl, group_ids.get(0));
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceIdentifyURL noface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceIdentifyUrl(nofaceurl, group_ids.get(0));
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("-------------------------------------");
			System.out.println("FaceIdentify A broken");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.FaceIdentifyUrl(notpicurl, group_ids.get(0));
			// get respose
			System.out.println(respose);

			JSONObject ret2 = faceYoutu.FaceIdentifyUrl("", group_ids.get(0));
			System.out.println(ret2);
			
			JSONObject ret5 = faceYoutu.FaceIdentifyUrl(wrongurl, group_ids.get(0));
			System.out.println(ret5);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}