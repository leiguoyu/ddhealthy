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

	/**���ݿ�����*/
	public static final String DB_NAME = "ddMedication.db";
	/**�����û������*/
	public static final String TB_NAME_USERINFO = "UserInfo";
	/**ҩƷ����*/
	public static final String TB_NAME_MEDICINE_DETAIL = "MedicineDetail";
	/*����Ʒ����*/
	public static final String TB_NAME_HEALTH_PRODUCT = "HealthProduct";
	public static final String TB_NAME_ALL_PRODUCT = "AllProduct";
	/*��ҩ���Ѽ�¼*/
	public static final String TB_NAME_MEDICATION_REMIND = "MedicationRemind";
	/*�ҵĽ�����¼*/
	public static final String TB_NAME_MY_HEALTHY = "MyHealthy";
	/*���岿λ֢״����*/
	public static final String TB_NAME_SYM_PTOMPART = "SymptomPart";
	/*��ѶID*/
	public static final String APP_ID = "1104894612";
	/*��ѶKEY*/
	public static final String APP_KEY = "i9Kq8DAWOpRpTsyC";
	
}
