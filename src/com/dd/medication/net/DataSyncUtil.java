package com.dd.medication.net;

import java.net.URLEncoder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dd.medication.util.MD5;

public class DataSyncUtil {

	public static final String WSDL = "http://123.57.50.102/pharmacy/app/";

	public static final String CHECK_VERI_CODE = "getVeriCode?";
	public static final String REGISTER = "register?";
	public static final String SERACH_PRODUCT = "serachProduct?";
	public static final String PRODUCT_DETAIL = "getProductDetail?";
	public static final String SCAN_BARCODE = "scan?";
	

	/**
	 * 根据电话 密码 验证码 进行注册 密码需要进行MD5加密
	 * */
	public static String getregister(String phoneNum, String password,
			String yzm) {
		String resultStr = "";
		try {
			// 这里密码需要MD5加密password
			password = MD5.MD5Encode(password);
			String urlPath = WSDL + REGISTER + "mobile=" + phoneNum
					+ "&authCode=" + yzm + "&password=" + password;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"该手机号已注册"},"action":"register"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**
	 * 根据所输入的电话号码获取短信验证码
	 * */
	public static boolean getVeriCode(String phoneNum) {

		boolean resultStr = false;
		try {
			String urlPath = WSDL + CHECK_VERI_CODE + "mobile=" + phoneNum;
			Log.i("TAG", urlPath);
			String result = NetTool.getTextContent(urlPath);
			// {"result":"true","data":{},"action":"getVeriCode"}
			System.out.println("===result=====" + result);
			resultStr = JsonUtil.getResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**
	 * 获取当前的网络连接状态
	 * 
	 * @param context
	 * @return true表示已经连接，false表示没有连接
	 */
	public static boolean getNetStatus(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
		// .getState();
		NetworkInfo[] netInfos = manager.getAllNetworkInfo();
		for (NetworkInfo netInfo : netInfos) {
			if (netInfo.isAvailable() && netInfo.isConnected()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 手动搜索药品接口
	 * */
	public static String getSerachProduct(String memberId, String deviceUid,
			String keyword, String pageNo, String pageSize, String isFirstSearch/*
																				 */) {
		String resultStr = "";
		try {
			// 这里密码需要MD5加密password
			String keyword1=URLEncoder.encode(keyword , "utf-8");
			String urlPath = WSDL + SERACH_PRODUCT + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&keyword=" + keyword1
					+ "&pageNo=" + pageNo + "&pageSize=" + pageSize
					+ "&isFirstSearch=" + isFirstSearch;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
			// {"result":"true","data":{"pageTotal":"158","pageNo":"1","pageSize":"3","resultList":[{"allProductId":"36","productname":"复方感冒灵颗粒","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"复方感冒灵片","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"感冒清热颗粒","company":"北京同仁堂科技发展股份有限公司制药厂","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
			//===result====={"result":"true","data":{"pageTotal":"48","pageNo":"1","pageSize":"10","resultList":[{"allProductId":"36","productname":"复方感冒灵颗粒","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"复方感冒灵片","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"感冒清热颗粒","company":"北京同仁堂科技发展股份有限公司制药厂","productType":"CP_TYPE_01"},{"allProductId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"142","productname":"感冒清热软胶囊","company":"石药集团欧意药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"156","productname":"感冒清片","company":"华润三九(郴州)制药有限公司","productType":"CP_TYPE_01"},{"allProductId":"203","productname":"感冒疏风片","company":"昆明中药厂有限公司","productType":"CP_TYPE_01"},{"allProductId":"206","productname":"感冒灵胶囊","company":"华润三九医药股份有限公司","productType":"CP_TYPE_01"},{"allProductId":"207","productname":"小儿感冒颗粒","company":"华润三九(枣庄)药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"212","productname":"四季感冒胶囊","company":"湖南东润联合制药有限公司","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**根据id获取 药品详情*/
	public static String getProductDetail(String memberId, String deviceUid,
			String allProductId) {
		String resultStr = "";
		try {
			// 这里密码需要MD5加密password
			String urlPath = WSDL + PRODUCT_DETAIL + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&allProductId=" + allProductId;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
//			===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","specification":"12g/袋；6g/袋","dosage":"颗粒剂","ingredient":"广藿香、菊花、连翘、大青叶、板蓝根、地黄、地骨皮、白薇、薄荷、石膏；辅料为蔗糖、糊精。","functions":"疏风解表，清热解毒。用于小儿风热感冒，症见发热、头胀痛、咳嗽痰黏、咽喉肿痛；流感见上述证候者。","theusage":"开水冲服。一岁以内一次6克，一岁至三岁一次612克，四岁至七岁一次12-18克，八岁至十二岁一次24克，一日2次。","reactions":"尚不明确。","taboo":"尚不明确。","note":"1.忌辛辣、生冷、油腻食物。 2.不宜在服药期间同时服用滋补性中药。 3.婴儿应在医师指导下服用。 4.风寒感冒者不适用。 5.糖尿病患儿、脾虚易腹泻者应在医师指导下服用。 6.发热体温超过38．5℃的患者，应去医院就诊。 7.服药3天症状无缓解，应去医院就诊。 8.对本品过敏者禁用，过敏体质者慎用。 9.本品性状发生改变时禁止使用。 10.儿童必须在成人监护下使用。 11.请将本品放在儿童不能接触的地方。 12.如正在使用其他药品，使用本品前请咨询医师或药师。 13.开水冲泡，搅拌呈混悬状服用。","store":"[02]药品阴凉贮存区(20℃以下)","validity":"36个月","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**
	 * 以搜索到的条形码 搜索相应的药品信息
	 * */
	public static String getScanOfBarcode(String memberId, String deviceUid,
			String barcode) {
		String resultStr = "";
		try {
			// 这里密码需要MD5加密password
			String urlPath = WSDL + SCAN_BARCODE + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&barcode=" + barcode;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
//			===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","specification":"12g/袋；6g/袋","dosage":"颗粒剂","ingredient":"广藿香、菊花、连翘、大青叶、板蓝根、地黄、地骨皮、白薇、薄荷、石膏；辅料为蔗糖、糊精。","functions":"疏风解表，清热解毒。用于小儿风热感冒，症见发热、头胀痛、咳嗽痰黏、咽喉肿痛；流感见上述证候者。","theusage":"开水冲服。一岁以内一次6克，一岁至三岁一次612克，四岁至七岁一次12-18克，八岁至十二岁一次24克，一日2次。","reactions":"尚不明确。","taboo":"尚不明确。","note":"1.忌辛辣、生冷、油腻食物。 2.不宜在服药期间同时服用滋补性中药。 3.婴儿应在医师指导下服用。 4.风寒感冒者不适用。 5.糖尿病患儿、脾虚易腹泻者应在医师指导下服用。 6.发热体温超过38．5℃的患者，应去医院就诊。 7.服药3天症状无缓解，应去医院就诊。 8.对本品过敏者禁用，过敏体质者慎用。 9.本品性状发生改变时禁止使用。 10.儿童必须在成人监护下使用。 11.请将本品放在儿童不能接触的地方。 12.如正在使用其他药品，使用本品前请咨询医师或药师。 13.开水冲泡，搅拌呈混悬状服用。","store":"[02]药品阴凉贮存区(20℃以下)","validity":"36个月","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

}
