package com.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.youtu.sign.Base64Util;
import com.youtu.sign.YoutuSign;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * 
 * @author qingliang
 *
 * 人脸核身相关接口调用
 */
public class YTServerAPI {
	public final static String API_URL = "https://vip-api.youtu.qq.com/youtu/";

	private static final String LOG_TAG = YTServerAPI.class.getName();
	private static int EXPIRED_SECONDS = 2592000;
	private String m_appid;
	private String m_secret_id;
	private String m_secret_key;
	private String m_end_point;
	private boolean m_use_youtu;

	private OnRequestListener mListener;

	public YTServerAPI() {
		m_appid = Config.APP_ID;
		m_secret_id = Config.SECRET_ID;
		m_secret_key = Config.SECRET_KEY;
		m_end_point = API_URL;
		m_use_youtu=!m_end_point.startsWith("https");
	}

	public interface OnRequestListener{
		void onSuccess(int statusCode, String responseBody);
		void onFailure(int statusCode);
	}

	public void setRequestListener(OnRequestListener listener){
		mListener = listener;
	}

	public void getLiveCheckData() throws IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException {

		JSONObject data = new JSONObject();
		if (m_use_youtu) {
			sendHttpRequest(data, "openliveapi/livegetfour");
		}else{
			sendHttpsRequest(data, "openliveapi/livegetfour");;
		}

	}

	public void idcardlivedetectfour(byte[] video, String validateNum, String name, String idCard) throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		String vedioData = Base64.encodeToString(video, Base64.DEFAULT);
		data.put("video", vedioData);
		data.put("app_id", m_appid);
		data.put("validate_data", validateNum);
		data.put("idcard_number", idCard);
		data.put("idcard_name", name);

		if (m_use_youtu) {
			sendHttpRequest(data, "openliveapi/idcardlivedetectfour");
		}else{
			sendHttpsRequest(data, "openliveapi/idcardlivedetectfour");;
		}

	}

	public void idCardOcr(Bitmap bitmap, int cardType)throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException{
		JSONObject data = new JSONObject();

		String imageData = bitmapToBase64(bitmap);
		data.put("image", imageData);
		data.put("app_id", m_appid);
		data.put("card_type", cardType);

		if (m_use_youtu) {
			sendHttpRequest(data, "ocrapi/idcardocr");
		}else{
			sendHttpsRequest(data, "ocrapi/idcardocr");;
		}
	}

	/**
	 * base64转为bitmap
	 * @param base64Data
	 * @return
	 */
	public static Bitmap base64ToBitmap(String base64Data) {
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	
	private JSONObject sendHttpRequest(JSONObject postData, String mothod)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {

		StringBuffer mySign = new StringBuffer("");
		YoutuSign.appSign(m_appid, m_secret_id, m_secret_key,
			System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
			"", mySign);

		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		URL url = new URL(m_end_point + mothod);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("user-agent", "youtu-android-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();

		// POST请求
		DataOutputStream out = new DataOutputStream(
			connection.getOutputStream());

		postData.put("app_id", m_appid);
		Log.d(LOG_TAG, "requst url = " + url.toString());
//		Log.d(LOG_TAG, "data = " + postData.toString());
		out.write(postData.toString().getBytes("utf-8"));
		//out.writeBytes(postData.toString());
		out.flush();
		out.close();
		final int responseCode = connection.getResponseCode();

		// 读取响应
		InputStream isss;
		if(responseCode >= HttpStatus.SC_BAD_REQUEST) {
			isss = connection.getErrorStream();
		}
		else {
			isss = connection.getInputStream();
		}


//		InputStream isss = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				isss));
		String lines;
		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
		// System.out.println(resposeBuffer+"\n");
		reader.close();
		// 断开连接
		connection.disconnect();

		Log.d(LOG_TAG, "responseCode = " + responseCode + "  response data = "+resposeBuffer.toString());
		JSONObject respose = null;
		if (responseCode == HttpsURLConnection.HTTP_OK) {
			if (mListener != null) {
				mListener.onSuccess(responseCode, resposeBuffer.toString());
			}

			respose = new JSONObject(resposeBuffer.toString());

		}else{
			if (mListener != null) {
				mListener.onFailure(responseCode);
			}
		}

		return respose;

	}
	

	private JSONObject sendHttpsRequest(JSONObject postData, String mothod)
	throws NoSuchAlgorithmException, KeyManagementException,
			IOException, JSONException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
			new java.security.SecureRandom());
		
		StringBuffer mySign = new StringBuffer("");
		YoutuSign.appSign(m_appid, m_secret_id, m_secret_key,
			System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
			"", mySign);

		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		
		URL url = new URL(m_end_point + mothod);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setSSLSocketFactory(sc.getSocketFactory());
		connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
     // set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("user-agent", "youtu-android-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();
		
    	// POST请求
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());

		postData.put("app_id", m_appid);
		out.write(postData.toString().getBytes("utf-8"));
		// 刷新、关闭
		out.flush();
		out.close();

		final int responseCode = connection.getResponseCode();
		// 读取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
     	// System.out.println(resposeBuffer+"\n");
		reader.close();
     	// 断开连接
		connection.disconnect();

		if (responseCode == HttpsURLConnection.HTTP_OK) {
			if (mListener != null) {
				mListener.onSuccess(responseCode, resposeBuffer.toString());
			}

		}else{
			if (mListener != null) {
				mListener.onFailure(responseCode);
			}
		}

		JSONObject respose = new JSONObject(resposeBuffer.toString());

		return respose;
	}


	private void GetBase64FromInputStream(InputStream is, StringBuffer base64) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String line = "";
		StringBuffer data = new StringBuffer("");
		while ((line = in.readLine()) != null) {
			data.append(line);
		}
		base64.append(Base64Util.encode(data.toString().getBytes()));
	}

	/**
	 * bitmap转为base64
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap) throws IOException {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return result;
	}


	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}


}