package com.qq.youtu.youtuyundemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.youtu.*;

public class testcase7_url {
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
	static String multifaceurl2 = "http://n.sinaimg.cn/transform/20150424/Q1vL-cczmvup0266165.jpg";
	static String notpicurl = "http://www.cnblogs.com/end/archive/2012/02/21/2360965.html";
	static String wrongurl = "http://img1.gtimg.com/ent/pics/hv1/189/25kgewkpewmnh952/61910364.jpg";
	static String oneface2url = "http://i0.sinaimg.cn/IT/2011/1215/U5384P2DT20111215041107.png";

	// appid, secretid secretkey�뵽http://open.youtu.qq.com/ע��
	public static final String APP_ID = "10002784";
	public static final String SECRET_ID = "AKIDFb4p8doJtrnfgieKpQlvEV0BE4Sa6F6Z";
	public static final String SECRET_KEY = "cEScbaS7MrKs7boBnKW06PUnpn4ET1P6";

	public static void testcase7_url() {
		//Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_TENCENTYUN_END_POINT);
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, API_YOUTU_END_POINT);
		try {

			System.out.println("=====================================");
			System.out.println("detectFaceUrl");
			System.out.println("-------------------------------------");
			System.out.println("detect oneface url mode 0");
			JSONObject respose = faceYoutu.DetectFaceUrl(onefaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect oneface url mode 1");
			JSONObject respose = faceYoutu.DetectFaceUrl(onefaceurl, 1);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect multiface url mode 0");
			JSONObject respose = faceYoutu.DetectFaceUrl(multifaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect multiface url mode 1");
			JSONObject respose = faceYoutu.DetectFaceUrl(multifaceurl, 1);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect noface url mode 0");
			JSONObject respose = faceYoutu.DetectFaceUrl(nofaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("-------------------------------------");
			System.out.println("detect face url is illegal");
			JSONObject respose = faceYoutu.DetectFaceUrl(notpicurl, 0);
			// get respose
			System.out.println(respose);
			
			JSONObject ret5 = faceYoutu.DetectFaceUrl(wrongurl, 0);
			System.out.println(ret5);
			JSONObject ret2 = faceYoutu.DetectFaceUrl("", 0);
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect image url is empty");
			JSONObject respose = faceYoutu.DetectFaceUrl("", 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("detect image url is space");
			JSONObject respose = faceYoutu.DetectFaceUrl(" ", 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("=====================================");
			System.out.println("faceshapeURL");
			System.out.println("-------------------------------------");
			System.out.println("faceshape mode 0");
			JSONObject respose = faceYoutu.FaceShapeUrl(onefaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape oneface mode 1");
			JSONObject respose = faceYoutu.FaceShapeUrl(onefaceurl, 1);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape oneface mode 0");
			JSONObject respose = faceYoutu.FaceShapeUrl(onefaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape multiface mode 0");
			JSONObject respose = faceYoutu.FaceShapeUrl(multifaceurl, 0);
			System.out.println(respose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape multiface mode 1");
			JSONObject respose = faceYoutu.FaceShapeUrl(multifaceurl, 1);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape noface mode 0");
			JSONObject respose = faceYoutu.FaceShapeUrl(nofaceurl, 0);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("faceshape noface mode 1");
			JSONObject respose = faceYoutu.FaceShapeUrl(nofaceurl, 1);
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("shapeface url is illegal");
			JSONObject respose = faceYoutu.FaceShapeUrl(notpicurl, 0);
			// get respose
			System.out.println(respose);
			
			JSONObject ret5 = faceYoutu.FaceShapeUrl(wrongurl, 0);
			System.out.println(ret5);
			JSONObject ret2 = faceYoutu.FaceShapeUrl("", 0);
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			System.out.println("=====================================");
			System.out.println("FaceCompareURL");
			System.out.println("-------------------------------------");
			System.out.println("FaceCompareURL both face only one");
			JSONObject respose = faceYoutu.FaceCompareUrl(onefaceurl, oneface2url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceCompareURL A multifaceface B multiface");
			JSONObject respose = faceYoutu.FaceCompareUrl(multifaceurl, multifaceurl2);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("-------------------------------------");
			System.out.println("FaceCompareURL A face B no");
			JSONObject respose = faceYoutu.FaceCompareUrl(nofaceurl, oneface2url);
			// get respose
			System.out.println(respose);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			System.out.println("-------------------------------------");
			System.out.println("FaceCompareURL A face B broken");
			JSONObject respose = faceYoutu.FaceCompareUrl(notpicurl, onefaceurl);
			// get respose
			System.out.println(respose);
			
			JSONObject ret5 = faceYoutu.FaceCompareUrl(wrongurl, oneface2url);
			System.out.println(ret5);
			JSONObject ret2 = faceYoutu.FaceCompareUrl("", oneface2url);
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}