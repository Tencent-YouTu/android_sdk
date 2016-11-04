# android_sdk

android SDK宗旨：展示如何在android平台上使用优图开放平台开放的图像服务, 不能作为sdk使用。

android SDK包含：
  1. 如何进行鉴权
  2. 如何对参数进行封装

demo展示如何调用优图开放平台API接口，网络请求返回的数据以log形式展示，请开发者用Android studio查看，是根据 http://open.youtu.qq.com/welcome/developer#/api-summary 实现的。

请开发者根据自己的需求，按照SDK中实现方式，封装http://open.youtu.qq.com/welcome/developer#/api-summary 列出的API


如果遇到问题，请按以下步骤解决：
  1. 阅读android SDK源码
  2. 在http://open.youtu.qq.com/welcome/developer#/api-summary 阅读发送参数、返回结果含义
  3. 请联系我们
  
##注意：
	人脸核身相关接口，需要申请权限接入，具体参考http://open.youtu.qq.com/welcome/service#/solution-facecheck
	人脸核身接口包括：
		public JSONObject IdcardOcrVIP(Bitmap bitmap, int cardType) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
		public JSONObject FaceCompareVip(Bitmap bitmapA, Bitmap bitmapB) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException
		public JSONObject IdcardFaceCompare(Bitmap bitmap, String name, String idcard) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
		public JSONObject LivegetFour() throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
		public JSONObject LiveDetectFour(byte[] video, Bitmap bitmap, String validateData, boolean isCompare) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
		public JSONObject IdcardLiveDetectFour(byte[] video, String validateData, String name, String idcard) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;

##名词：
- AppId 平台添加应用后分配的AppId
- SecretId 平台添加应用后分配的SecretId
- SecretKey 平台添加应用后分配的SecretKey
- 签名 接口鉴权凭证，由AppId、SecretId、SecretKey等生成，详见	http://open.youtu.qq.com/welcome/new-authentication


## 使用示例

##### 设置APP 鉴权信息
	Config.java里设置自己申请的 APP_ID, SECRET_ID, SECRET_KEY
	public class Config {
    	public static final String APP_ID = "your appId";   // 替换APP_ID
    	public static final String SECRET_ID = "your secretId";  // 替换SECRET_ID
    	public static final String SECRET_KEY = "your secretkey";// 替换SECRET_KEY
	}
	
##### 根据你使用的平台选择一种初始化方式
	优图开放平台初始化
	Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);

	优图开放平台核身服务初始化（**核身服务目前仅支持核身专有接口,需要联系商务开通**）
	Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
	
##### 调用示例
  	try {
  		Bitmap selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.geyou_1, opts);
        JSONObject respose = faceYoutu.DetectFace(selectedImage, 0);
        Log.d(LOG_TAG, respose.toString());
        if(null != selectedImage) {
                selectedImage.recycle();
           }
        } catch (Exception e) {
              e.printStackTrace();
        }

##接口说明

	Youtu构造方法
	public Youtu(String appid, String secret_id, String secret_key,String end_point);
	参数：
	appid 授权appid
	secret_id 授权secret_id
	secret_key 授权secret_key
	end_point  域名
	
	人脸属性分析 检测给定图片(Image)中的所有人脸(Face)的位置和相应的面部属性。位置包括(x, y, w, h)，
	面部属性包括性别(gender), 年龄(age), 表情(expression), 眼镜(glass)和姿态(pitch，roll，yaw).
	public JSONObject DetectFace(Bitmap bitmap,int mode) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException；
	参数：
	bitmap 人脸图片
	mode 检测模式 0/1 正常/大脸模式

	人脸属性分析 检测给定图片(Image)中的所有人脸(Face)的位置和相应的面部属性。位置包括(x, y, w, h)，
	面部属性包括性别(gender), 年龄(age), 表情(expression), 眼镜(glass)和姿态(pitch，roll，yaw).
	public JSONObject DetectFaceUrl(String url, int mode)
	throws IOException, JSONException, KeyManagementException,NoSuchAlgorithmException ；
	参数：
	url 人脸图片url
	mode 检测模式 0/1 正常/大脸模式

	五官定位
	public JSONObject FaceShape(Bitmap bitmap,int mode) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;  
	参数：
	bitmap 人脸图片
	
	五官定位
	public JSONObject FaceShapeUrl(String url,int mode) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	url 人脸图片url

	人脸对比， 计算两个Face的相似性以及五官相似度。
	public JSONObject FaceCompare(Bitmap bitmapA, Bitmap bitmapB)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	bitmapA 第一张人脸图片
	bitmapB 第二张人脸图片

	人脸对比， 计算两个Face的相似性以及五官相似度。
	public JSONObject FaceCompareUrl(String urlA, String urlB)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	urlA 第一张人脸图片url
	urlA 第二张人脸图片url

	人脸验证，给定一个Face和一个Person，返回是否是同一个人的判断以及置信度。
	public JSONObject FaceVerify(Bitmap bitmap, String person_id)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException；
	参数：
	bitmap 需要验证的人脸图片
	person_id 验证的目标person

	人脸验证，给定一个Face和一个Person，返回是否是同一个人的判断以及置信度。
	public JSONObject FaceVerifyUrl(String url, String person_id)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	url 需要验证的人脸图片url
	person_id 验证的目标person

	人脸识别，对于一个待识别的人脸图片，在一个Group中识别出最相似的Top5 Person作为其身份返回，返回的Top5中按照相似度从大到小排列。
	public JSONObject FaceIdentify(Bitmap bitmap, String group_id)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	bitmap 需要验证的人脸图片
	group_id 人脸face组

	人脸识别，对于一个待识别的人脸图片，在一个Group中识别出最相似的Top5 Person作为其身份返回，返回的Top5中按照相似度从大到小排列。
	public JSONObject FaceIdentifyUrl(String url, String group_id)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	url 需要识别的人脸图片url
	group_id 人脸face组

	创建一个Person，并将Person放置到group_ids指定的组当中
	public JSONObject NewPerson(Bitmap bitmap, String person_id,
		List<String> group_ids) throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	bitmap 需要新建的人脸图片
	person_id 指定创建的人脸
	group_ids 加入的group列表
		
	创建一个Person，并将Person放置到group_ids指定的组当中
	public JSONObject NewPersonUrl(String url, String person_id,
		List<String> group_ids) throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	url 需要新建的人脸图片url
	person_id 指定创建的人脸
	group_ids 加入的group列表

	删除一个person下的face，包括特征，属性和face_id
	public JSONObject DelPerson(String person_id) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	person_id 待删除人脸的person ID

	增加一个人脸Face.将一组Face加入到一个Person中。注意，一个Face只能被加入到一个Person中。
	一个Person最多允许包含100个Face。
	public JSONObject AddFace(String person_id, List<Bitmap> bitmap_arr)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	person_id 人脸Face的person id
	bitmap_arr 人脸图片列表

	增加一个人脸Face.将一组Face加入到一个Person中。注意，一个Face只能被加入到一个Person中。
	一个Person最多允许包含100个Face。
	public JSONObject AddFaceUrl(String person_id, List<String> url_arr)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	person_id 人脸Face的person id
	url_arr 人脸图片url列表
	

	删除一个person下的face，包括特征，属性和face_id.
	public JSONObject DelFace(String person_id, List<String> face_id_arr)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	person_id 待删除人脸的person ID
	face_id_arr 删除人脸id的列表
	
	设置Person的name
	public JSONObject SetInfo(String person_name, String person_id)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	person_name 新的name
	person_id 要设置的person id

	获取一个Person的信息, 包括name, id, tag, 相关的face, 以及groups等信息。
	public JSONObject GetInfo(String person_id) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	person_id 待查询个体的ID

	获取一个AppId下所有group列表
	public JSONObject GetGroupIds() throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException ;

	获取一个组Group中所有person列表
	public JSONObject GetPersonIds(String group_id) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	group_id 待查询的组id
	
	获取一个组person中所有face列表
	public JSONObject GetFaceIds(String person_id) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	person_id 待查询的个体id

	获取一个face的相关特征信息
	public JSONObject GetFaceInfo(String face_id) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	face_id 带查询的人脸ID


	判断一个图像的模糊程度
	public JSONObject FuzzyDetect(Bitmap bitmap) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	bitmap 输入图片

	判断一个图像的模糊程度
	public JSONObject FuzzyDetectUrl(String url) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;	
	参数：
	url 输入图片url

	识别一个图像是否为美食图像
	public JSONObject FoodDetect(Bitmap bitmap) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	bitmap 输入图片

	识别一个图像是否为美食图像
	public JSONObject FoodDetectUrl(String url) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	url 输入图片url


	识别一个图像的标签信息,对图像分类
	public JSONObject ImageTag(Bitmap bitmap) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	bitmap 输入图片

	识别一个图像的标签信息,对图像分类
	public JSONObject ImageTagUrl(String url) throws IOException,
	JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	url 输入图片url

	识别一个图像是否为色情图像
	public JSONObject ImagePorn(Bitmap bitmap) throws IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数：
	bitmap 输入图片		

	识别一个图像是否为色情图像
	public JSONObject ImagePornUrl(String url) throws IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数：
	url 输入图片url

	身份证OCR识别
	public JSONObject IdcardOcr(Bitmap bitmap, int cardType) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException参数：
	参数
	bitmap 输入图片
	cardType 身份证图片类型，0-正面，1-反面

	身份证OCR识别
	public JSONObject IdcardOcrUrl(String url, int cardType) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数
	url 输入图片url
	cardType 身份证图片类型，0-正面，1-反面

	名片OCR识别
	public JSONObject NamecardOcr(Bitmap bitmap) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数
	bitmap 输入图片

	名片OCR识别
	public JSONObject NamecardOcrUrl(String url) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数
	url 输入图片url


	身份证OCR识别
	public JSONObject IdcardOcrVIP(Bitmap bitmap, int cardType) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数
	bitmap 输入图片
	cardType 身份证图片类型，0-正面，1-反面

	静态人脸比对:用户自带数据源比对
	public JSONObject FaceCompareVip(Bitmap bitmapA, Bitmap bitmapB) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException
	参数
	bitmapA 第一张人脸图片
	bitmapB 第二张人脸图片
	
	静态人脸比对:使用优图数据源比对
	public JSONObject IdcardFaceCompare(Bitmap bitmap, String name, String idcard) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException ;
	参数
	idcard  用户身份证号码
	name  用户身份证姓名
	bitmap 输入图片


	唇语获取
	public JSONObject LivegetFour() throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;

	视频人脸核身:用户自带数据源核身
	public JSONObject LiveDetectFour(byte[] video, Bitmap bitmap, String validateData, boolean isCompare) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数
	video 需要检测的视频base64编码
	validateDat livegetfour得到的唇语验证数据
	bitmap 输入图片
	isCompare video中的照片和card是否做对比，True做对比，False不做对比

	视频人脸核身:使用优图数据源核身
	public JSONObject IdcardLiveDetectFour(byte[] video, String validateData, String name, String idcard) throws  IOException,
			JSONException, KeyManagementException, NoSuchAlgorithmException;
	参数
	video 需要检测的视频base64编码
	idcard 用户身份证号码
	name 用户身份证姓名
	validateData livegetfour得到的唇语验证数据
