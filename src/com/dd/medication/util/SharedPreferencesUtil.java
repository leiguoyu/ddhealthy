package com.dd.medication.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

	/**
	 * 保存用户注册的男女信息  GGMK_XB_01  GGMK_XB_02
	 * */
	public static  void setUserSex(Context contex, String sexStr){
		//实例化SharedPreferences对象（第一步） 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		//用putString的方法保存数据 
		editor.putString("sexStr", sexStr);
		//提交当前数据 
		editor.commit(); 	
	}
	
	/**
	 * 获取用户注册的男女信息  GGMK_XB_01  GGMK_XB_02
	 * */
	public static  String getUserSex(Context contex, String sexStr){
		//实例化SharedPreferences对象（第一步） 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		return mySharedPreferences.getString("sexStr", "");
	}
	
	/**
	 * 设置保存第一册注册标记
	 * */
	public static void setFirstLoginSharedPreferences(Context contex,String isFirstLogin,boolean isFirst){
		//实例化SharedPreferences对象（第一步） 
		SharedPreferences mySharedPreferences=contex. getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		//用putString的方法保存数据 
		editor.putBoolean("isFirstLogin", isFirst);
		//提交当前数据 
		editor.commit(); 
	}
	
	/**
	 * 获取注册标记   true 表示注册过   false则没有 
	 * */
	public static boolean getFirstLoginSharedPreferences(Context contex){
		//同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象 
		SharedPreferences sharedPreferences= contex.getSharedPreferences("phone", 
		Activity.MODE_PRIVATE); 
		// 使用getString方法获得value，注意第2个参数是value的默认值 
		boolean isFirst=sharedPreferences.getBoolean("isFirstLogin", false);
		if(isFirst){
			return true;
		}
		return false;
	}
	
	
}
