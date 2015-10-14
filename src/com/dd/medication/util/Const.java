package com.dd.medication.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Environment;

public class Const {

	public static final String PACKAGE_NAME = "com.dd.medication";

	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME;

	public static final String EXT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/";

	/**数据库名称*/
	public static final String DB_NAME = "ddMedication.db";
	/**数据用户表表名*/
	public static final String TB_NAME_USERINFO = "UserInfo";
	/**药品详情*/
	public static final String TB_NAME_MEDICINE_DETAIL = "MedicineDetail";
	/*保健品详情*/
	public static final String TB_NAME_HEALTH_PRODUCT = "HealthProduct";
	public static final String TB_NAME_ALL_PRODUCT = "AllProduct";
	/*用药提醒记录*/
	public static final String TB_NAME_MEDICATION_REMIND = "MedicationRemind";
	/*我的健康记录*/
	public static final String TB_NAME_MY_HEALTHY = "MyHealthy";
	/*身体部位症状常量*/
	public static final String TB_NAME_SYM_PTOMPART = "SymptomPart";
	/*腾讯ID*/
	public static final String APP_ID = "1104894612";
	/*腾讯KEY*/
	public static final String APP_KEY = "i9Kq8DAWOpRpTsyC";
	
}
