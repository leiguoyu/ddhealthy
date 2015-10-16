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
	 * ���ݵ绰 ���� ��֤�� ����ע�� ������Ҫ����MD5����
	 * */
	public static String getregister(String phoneNum, String password,
			String yzm) {
		String resultStr = "";
		try {
			// ����������ҪMD5����password
			password = MD5.MD5Encode(password);
			String urlPath = WSDL + REGISTER + "mobile=" + phoneNum
					+ "&authCode=" + yzm + "&password=" + password;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"���ֻ�����ע��"},"action":"register"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**
	 * ����������ĵ绰�����ȡ������֤��
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
	 * ��ȡ��ǰ����������״̬
	 * 
	 * @param context
	 * @return true��ʾ�Ѿ����ӣ�false��ʾû������
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
	 * �ֶ�����ҩƷ�ӿ�
	 * */
	public static String getSerachProduct(String memberId, String deviceUid,
			String keyword, String pageNo, String pageSize, String isFirstSearch/*
																				 */) {
		String resultStr = "";
		try {
			// ����������ҪMD5����password
			String keyword1=URLEncoder.encode(keyword , "utf-8");
			String urlPath = WSDL + SERACH_PRODUCT + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&keyword=" + keyword1
					+ "&pageNo=" + pageNo + "&pageSize=" + pageSize
					+ "&isFirstSearch=" + isFirstSearch;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
			// {"result":"true","data":{"pageTotal":"158","pageNo":"1","pageSize":"3","resultList":[{"allProductId":"36","productname":"������ð�����","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"������ð��Ƭ","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"��ð���ȿ���","company":"����ͬ���ÿƼ���չ�ɷ����޹�˾��ҩ��","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
			//===result====={"result":"true","data":{"pageTotal":"48","pageNo":"1","pageSize":"10","resultList":[{"allProductId":"36","productname":"������ð�����","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"������ð��Ƭ","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"��ð���ȿ���","company":"����ͬ���ÿƼ���չ�ɷ����޹�˾��ҩ��","productType":"CP_TYPE_01"},{"allProductId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"142","productname":"��ð��������","company":"ʯҩ����ŷ��ҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"156","productname":"��ð��Ƭ","company":"��������(����)��ҩ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"203","productname":"��ð���Ƭ","company":"������ҩ�����޹�˾","productType":"CP_TYPE_01"},{"allProductId":"206","productname":"��ð�齺��","company":"��������ҽҩ�ɷ����޹�˾","productType":"CP_TYPE_01"},{"allProductId":"207","productname":"С����ð����","company":"��������(��ׯ)ҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"212","productname":"�ļ���ð����","company":"���϶���������ҩ���޹�˾","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**����id��ȡ ҩƷ����*/
	public static String getProductDetail(String memberId, String deviceUid,
			String allProductId) {
		String resultStr = "";
		try {
			// ����������ҪMD5����password
			String urlPath = WSDL + PRODUCT_DETAIL + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&allProductId=" + allProductId;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
//			===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","specification":"12g/����6g/��","dosage":"������","ingredient":"��޽�㡢�ջ������̡�����Ҷ�����������ػơ��ع�Ƥ����ޱ�����ɡ�ʯ�ࣻ����Ϊ���ǡ�������","functions":"��������Ƚⶾ������С�����ȸ�ð��֢�����ȡ�ͷ��ʹ������̵𤡢�ʺ���ʹ�����м�����֤���ߡ�","theusage":"��ˮ�����һ������һ��6�ˣ�һ��������һ��612�ˣ�����������һ��12-18�ˣ�������ʮ����һ��24�ˣ�һ��2�Ρ�","reactions":"�в���ȷ��","taboo":"�в���ȷ��","note":"1.�����������䡢����ʳ� 2.�����ڷ�ҩ�ڼ�ͬʱ�����̲�����ҩ�� 3.Ӥ��Ӧ��ҽʦָ���·��á� 4.�纮��ð�߲����á� 5.���򲡻�����Ƣ���׸�к��Ӧ��ҽʦָ���·��á� 6.�������³���38��5��Ļ��ߣ�ӦȥҽԺ��� 7.��ҩ3��֢״�޻��⣬ӦȥҽԺ��� 8.�Ա�Ʒ�����߽��ã��������������á� 9.��Ʒ��״�����ı�ʱ��ֹʹ�á� 10.��ͯ�����ڳ��˼໤��ʹ�á� 11.�뽫��Ʒ���ڶ�ͯ���ܽӴ��ĵط��� 12.������ʹ������ҩƷ��ʹ�ñ�Ʒǰ����ѯҽʦ��ҩʦ�� 13.��ˮ���ݣ�����ʻ���״���á�","store":"[02]ҩƷ����������(20������)","validity":"36����","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

	/**
	 * ���������������� ������Ӧ��ҩƷ��Ϣ
	 * */
	public static String getScanOfBarcode(String memberId, String deviceUid,
			String barcode) {
		String resultStr = "";
		try {
			// ����������ҪMD5����password
			String urlPath = WSDL + SCAN_BARCODE + "memberId=" + memberId
					+ "&deviceUid=" + deviceUid + "&barcode=" + barcode;
			Log.i("TAG", urlPath);
			resultStr = NetTool.getTextContent(urlPath);
//			===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","specification":"12g/����6g/��","dosage":"������","ingredient":"��޽�㡢�ջ������̡�����Ҷ�����������ػơ��ع�Ƥ����ޱ�����ɡ�ʯ�ࣻ����Ϊ���ǡ�������","functions":"��������Ƚⶾ������С�����ȸ�ð��֢�����ȡ�ͷ��ʹ������̵𤡢�ʺ���ʹ�����м�����֤���ߡ�","theusage":"��ˮ�����һ������һ��6�ˣ�һ��������һ��612�ˣ�����������һ��12-18�ˣ�������ʮ����һ��24�ˣ�һ��2�Ρ�","reactions":"�в���ȷ��","taboo":"�в���ȷ��","note":"1.�����������䡢����ʳ� 2.�����ڷ�ҩ�ڼ�ͬʱ�����̲�����ҩ�� 3.Ӥ��Ӧ��ҽʦָ���·��á� 4.�纮��ð�߲����á� 5.���򲡻�����Ƣ���׸�к��Ӧ��ҽʦָ���·��á� 6.�������³���38��5��Ļ��ߣ�ӦȥҽԺ��� 7.��ҩ3��֢״�޻��⣬ӦȥҽԺ��� 8.�Ա�Ʒ�����߽��ã��������������á� 9.��Ʒ��״�����ı�ʱ��ֹʹ�á� 10.��ͯ�����ڳ��˼໤��ʹ�á� 11.�뽫��Ʒ���ڶ�ͯ���ܽӴ��ĵط��� 12.������ʹ������ҩƷ��ʹ�ñ�Ʒǰ����ѯҽʦ��ҩʦ�� 13.��ˮ���ݣ�����ʻ���״���á�","store":"[02]ҩƷ����������(20������)","validity":"36����","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
			System.out.println("===result=====" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		return resultStr;
	}

}
