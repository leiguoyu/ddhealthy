package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.HealthProductModel;
import com.dd.medication.util.Const;

/*
 * ���ر���Ʒ�������ݴ���
 * by andy
 * */

public class HealthProductDao extends BaseDao{
	/*
	 * ����һ������Ʒ�������ݣ�ͨ��ɨ�������ҩƷ�ӷ�������ȡ���ݺ󱣴汾�ء�
	 * */
	public boolean addHealthProduct(HealthProductModel healthProductInfo){
		ContentValues cv = new ContentValues();
		cv.put("healthProductDetailId", healthProductInfo.getHealthProductDetailId());
		cv.put("type", healthProductInfo.getType());
		cv.put("productname", healthProductInfo.getProductname());
		cv.put("company", healthProductInfo.getCompany());
		cv.put("domesticOrImport", healthProductInfo.getDomesticOrImport());
		cv.put("scgdq", healthProductInfo.getScgdq());
		cv.put("bjgn", healthProductInfo.getBjgn());
		cv.put("gxcfbzxcfhl", healthProductInfo.getGxcfbzxcfhl());
		cv.put("zyyl", healthProductInfo.getZyyl());
		cv.put("bsyrq", healthProductInfo.getBsyrq());
		cv.put("syffjsyl", healthProductInfo.getSyffjsyl());
		cv.put("cpgg", healthProductInfo.getCpgg());
		cv.put("bzq", healthProductInfo.getBzq());
		cv.put("zcff", healthProductInfo.getZcff());
		cv.put("zysx", healthProductInfo.getZysx());
		cv.put("usingTime", healthProductInfo.getUsingTime());
		cv.put("barCode", healthProductInfo.getBarCode());
		cv.put("status", healthProductInfo.getStatus());
		cv.put("createUser", healthProductInfo.getCreateUser());
		cv.put("createDate", healthProductInfo.getCreateDate());
		cv.put("lmodifyUser", healthProductInfo.getLmodifyUser());
		cv.put("lmodifyDate", healthProductInfo.getLmodifyDate());
		
		return insert(Const.DB_NAME, Const.TB_NAME_HEALTH_PRODUCT, cv);
	}
	
	/**
	 * ����һ������
	 * */
	public boolean updateHealthProduct(HealthProductModel healthProductInfo,String whereClause, String[] whereArgs){
		ContentValues cv = new ContentValues();
		cv.put("healthProductDetailId", healthProductInfo.getHealthProductDetailId());
		cv.put("type", healthProductInfo.getType());
		cv.put("productname", healthProductInfo.getProductname());
		cv.put("company", healthProductInfo.getCompany());
		cv.put("domesticOrImport", healthProductInfo.getDomesticOrImport());
		cv.put("scgdq", healthProductInfo.getScgdq());
		cv.put("bjgn", healthProductInfo.getBjgn());
		cv.put("gxcfbzxcfhl", healthProductInfo.getGxcfbzxcfhl());
		cv.put("zyyl", healthProductInfo.getZyyl());
		cv.put("bsyrq", healthProductInfo.getBsyrq());
		cv.put("syffjsyl", healthProductInfo.getSyffjsyl());
		cv.put("cpgg", healthProductInfo.getCpgg());
		cv.put("bzq", healthProductInfo.getBzq());
		cv.put("zcff", healthProductInfo.getZcff());
		cv.put("zysx", healthProductInfo.getZysx());
		cv.put("usingTime", healthProductInfo.getUsingTime());
		cv.put("barCode", healthProductInfo.getBarCode());
		cv.put("status", healthProductInfo.getStatus());
		cv.put("createUser", healthProductInfo.getCreateUser());
		cv.put("createDate", healthProductInfo.getCreateDate());
		cv.put("lmodifyUser", healthProductInfo.getLmodifyUser());
		cv.put("lmodifyDate", healthProductInfo.getLmodifyDate());
		
		return update(Const.DB_NAME, Const.TB_NAME_HEALTH_PRODUCT, cv, whereClause, whereArgs);
       }
	/**
	 * ��ȡ�����������ı���Ʒ
	 * */
	public ArrayList<HealthProductModel>getAllHealthProduct(){
		ArrayList<HealthProductModel> infos=new ArrayList<HealthProductModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_HEALTH_PRODUCT;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do{
						HealthProductModel info=new HealthProductModel();
					info.setHealthProductDetailId(cursor.getString(cursor.getColumnIndex("healthProductDetailId")));
					info.setType(cursor.getInt(cursor.getColumnIndex("type")));
					info.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));
					info.setDomesticOrImport(cursor.getString(cursor.getColumnIndex("domesticOrImport")));
					info.setScgdq(cursor.getString(cursor.getColumnIndex("scgdq")));
					info.setBjgn(cursor.getString(cursor.getColumnIndex("bjgn")));
					info.setGxcfbzxcfhl(cursor.getString(cursor.getColumnIndex("gxcfbzxcfhl")));
					info.setZyyl(cursor.getString(cursor.getColumnIndex("zyyl")));
					info.setSyrq(cursor.getString(cursor.getColumnIndex("syrq")));
					info.setBsyrq(cursor.getString(cursor.getColumnIndex("bsyrq")));
					info.setSyffjsyl(cursor.getString(cursor.getColumnIndex("syffjsyl")));
					info.setCpgg(cursor.getString(cursor.getColumnIndex("cpgg")));
					info.setBzq(cursor.getString(cursor.getColumnIndex("bzq")));
					info.setZcff(cursor.getString(cursor.getColumnIndex("zcff")));
					info.setZysx(cursor.getString(cursor.getColumnIndex("zysx")));
					info.setUsingTime(cursor.getString(cursor.getColumnIndex("usingTime")));
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));
					infos.add(info);
					} while (cursor.moveToNext());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return infos;
	}
	
	/**
	 * ��ȡͨ��ɨ��or �ֶ�������ȡ���ı���Ʒ���顣 type= 0:ɨ���ȡ�ı���Ʒ����           type= 1���ֶ�������ȡ���ı���Ʒ����
	 * **/
	public ArrayList<HealthProductModel>getType(int type){
		ArrayList<HealthProductModel> infos=new ArrayList<HealthProductModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_HEALTH_PRODUCT+"where type= '"
					+type+"'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do {
						HealthProductModel info=new HealthProductModel();
					info.setHealthProductDetailId(cursor.getString(cursor.getColumnIndex("healthProductDetailId")));
					info.setType(cursor.getInt(cursor.getColumnIndex("type")));
					info.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));
					info.setDomesticOrImport(cursor.getString(cursor.getColumnIndex("domesticOrImport")));
					info.setScgdq(cursor.getString(cursor.getColumnIndex("scgdq")));
					info.setBjgn(cursor.getString(cursor.getColumnIndex("bjgn")));
					info.setGxcfbzxcfhl(cursor.getString(cursor.getColumnIndex("gxcfbzxcfhl")));
					info.setZyyl(cursor.getString(cursor.getColumnIndex("zyyl")));
					info.setSyrq(cursor.getString(cursor.getColumnIndex("syrq")));
					info.setBsyrq(cursor.getString(cursor.getColumnIndex("bsyrq")));
					info.setSyffjsyl(cursor.getString(cursor.getColumnIndex("syffjsyl")));
					info.setCpgg(cursor.getString(cursor.getColumnIndex("cpgg")));
					info.setBzq(cursor.getString(cursor.getColumnIndex("bzq")));
					info.setZcff(cursor.getString(cursor.getColumnIndex("zcff")));
					info.setZysx(cursor.getString(cursor.getColumnIndex("zysx")));
					info.setUsingTime(cursor.getString(cursor.getColumnIndex("usingTime")));
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));
					infos.add(info);
					} while (cursor.moveToNext());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return infos;
	}
	
	/**
	 * ɾ������ҩƷ�����¼
	 * **/
	public boolean deleteAllUser(){
		return deleteAll(Const.DB_NAME,Const.TB_NAME_HEALTH_PRODUCT);
	}
}