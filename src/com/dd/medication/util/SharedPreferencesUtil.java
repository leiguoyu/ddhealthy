package com.dd.medication.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

	/**
	 * �����û�ע�����Ů��Ϣ  GGMK_XB_01  GGMK_XB_02
	 * */
	public static  void setUserSex(Context contex, String sexStr){
		//ʵ����SharedPreferences���󣨵�һ���� 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		//ʵ����SharedPreferences.Editor���󣨵ڶ����� 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		//��putString�ķ����������� 
		editor.putString("sexStr", sexStr);
		//�ύ��ǰ���� 
		editor.commit(); 	
	}
	
	/**
	 * ��ȡ�û�ע�����Ů��Ϣ  GGMK_XB_01  GGMK_XB_02
	 * */
	public static  String getUserSex(Context contex, String sexStr){
		//ʵ����SharedPreferences���󣨵�һ���� 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		return mySharedPreferences.getString("sexStr", "");
	}
	
	/**
	 * ���ñ����һ��ע����
	 * */
	public static void setFirstLoginSharedPreferences(Context contex,String isFirstLogin,boolean isFirst){
		//ʵ����SharedPreferences���󣨵�һ���� 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		//ʵ����SharedPreferences.Editor���󣨵ڶ����� 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		//��putString�ķ����������� 
		editor.putBoolean("isFirstLogin", isFirst);
		//�ύ��ǰ���� 
		editor.commit(); 
	}
	
	/**
	 * ��ȡע����   true ��ʾע���   false��û�� 
	 * */
	public static boolean getFirstLoginSharedPreferences(Context contex){
		//ͬ�����ڶ�ȡSharedPreferences����ǰҪʵ������һ��SharedPreferences���� 
		SharedPreferences sharedPreferences= contex.getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		// ʹ��getString�������value��ע���2��������value��Ĭ��ֵ 
		boolean isFirst=sharedPreferences.getBoolean("isFirstLogin", false);
		if(isFirst){
			return true;
		}
		return false;
	}
	
	
}
