package com.qq.youtu.youtuyundemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.youtu.*;

public class testcase9_url {
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

	public static void testcase9_url() {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_YOUTU_END_POINT);
		try {
			System.out.println("=====================================");
			System.out.println("NewPersonURL");
			System.out.println("-------------------------------------");
			System.out.println("NewPersonURL oneface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			JSONObject respose = faceYoutu.NewPersonUrl(onefaceurl, person_id, group_ids);
			System.out.println(respose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("NewPersonURL multiface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			JSONObject respose = faceYoutu.NewPersonUrl(multifaceurl, person_id, group_ids);
			System.out.println(respose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("NewPersonURL noface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			JSONObject respose = faceYoutu.NewPersonUrl(nofaceurl, person_id, group_ids);
			System.out.println(respose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("NewPersonURL illegal");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			JSONObject respose = faceYoutu.NewPersonUrl(wrongurl, person_id, group_ids);
			System.out.println(respose);
			
			JSONObject ret = faceYoutu.NewPersonUrl(notpicurl, person_id, group_ids);
			System.out.println(ret);
			
			JSONObject ret2 = faceYoutu.NewPersonUrl("", person_id, group_ids);
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("=====================================");
			System.out.println("add faceurl");
			System.out.println("-------------------------------------");
			System.out.println("addfaceURL oneface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<String> urls = new ArrayList<String>();
			urls.add(onefaceurl);
			faceYoutu.NewPersonUrl(samefaceurl_withoneface, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFaceUrl(person_id, urls);
			System.out.println(respose);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("addfaceURL multiface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<String> urls = new ArrayList<String>();
			urls.add(multifaceurl);
			faceYoutu.NewPersonUrl(onefaceurl, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFaceUrl(person_id, urls);
			System.out.println(respose);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("addfaceURL noface");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<String> urls = new ArrayList<String>();
			urls.add(nofaceurl);
			faceYoutu.NewPersonUrl(onefaceurl, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFaceUrl(person_id, urls);
			System.out.println(respose);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("addfaceURL illegal");
			String person_id = "person_id" + System.currentTimeMillis();
			List<String> group_ids = new ArrayList<String>();
			group_ids.add("group_id"+System.currentTimeMillis());
			List<String> urls = new ArrayList<String>();
			urls.add(notpicurl);
			urls.add(wrongurl);
			urls.add("");
			urls.add(onefaceurl);
			urls.add(onefaceurl);
			faceYoutu.NewPersonUrl(onefaceurl, person_id, group_ids);
			JSONObject respose = faceYoutu.AddFaceUrl(person_id, urls);
			System.out.println(respose);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
