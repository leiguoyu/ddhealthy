package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.util.Const;

public class AllProductDao extends BaseDao{

	/**
	 * ����һ������
	 * */
	public boolean addAllProduct(AllProductModel info){
			ContentValues cv = new ContentValues();
			cv.put("allProductId", info.getAllProductId());//Id
			cv.put("productName", info.getProductName());//��Ʒ����
			cv.put("company", info.getCompany());//��ҵ����
			cv.put("barCode", info.getBarCode());//������
			cv.put("productType", info.getProductType());//��Ʒ���ͣ���Դddinfo��
			cv.put("reId", info.getReId());// ����ID
			
			cv.put("status", info.getStatus());//�Ƿ�ɾ��
			cv.put("createUser", info.getCreateUser());//������
			cv.put("createDate", info.getCreateDate());//����ʱ��
			cv.put("lmodifyUser", info.getLmodifyUser());// ���һ���޸���
			cv.put("lmodifyDate", info.getLmodifyDate());//���һ���޸�ʱ��
			
			return insert(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT,cv);
	}
	
	/**
	 * ����������� 
	 * */
	public boolean addAllProduct(ArrayList<AllProductModel> info){
		if(info!=null && info.size()>0){
			try {
				for (int j = 0; j < info.size(); j++) {
					addAllProduct(info.get(j));
				}	
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
	/**
	 * ����һ������
	 * */
	public boolean updateUser(AllProductModel info,String whereClause, String[] whereArgs){
		ContentValues cv = new ContentValues();
		cv.put("allProductId", info.getAllProductId());//Id
		cv.put("productName", info.getProductName());//��Ʒ����
		cv.put("company", info.getCompany());//��ҵ����
		cv.put("barCode", info.getBarCode());//������
		cv.put("productType", info.getProductType());//��Ʒ���ͣ���Դddinfo��
		cv.put("reId", info.getReId());// ����ID
		
		cv.put("status", info.getStatus());//�Ƿ�ɾ��
		cv.put("createUser", info.getCreateUser());//������
		cv.put("createDate", info.getCreateDate());//����ʱ��
		cv.put("lmodifyUser", info.getLmodifyUser());// ���һ���޸���
		cv.put("lmodifyDate", info.getLmodifyDate());//���һ���޸�ʱ��
		
		return update(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT,cv, whereClause, whereArgs);
       }
	
	/**
	 * ��ȡȫ���û���Ϣ
	 * */
	public ArrayList<AllProductModel>getAllUsers(){
		ArrayList<AllProductModel> infos=new ArrayList<AllProductModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_ALL_PRODUCT;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do {
					AllProductModel info=new AllProductModel();
					info.setAllProductId(cursor.getInt(cursor.getColumnIndex("allProductId")));//Id
					info.setProductName(cursor.getString(cursor.getColumnIndex("productName")));//��Ʒ����
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));//��ҵ����
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));//������
					info.setProductType(cursor.getString(cursor.getColumnIndex("productType")));//��Ʒ���ͣ���Դddinfo��
					info.setReId(cursor.getInt(cursor.getColumnIndex("reId")));// ����ID
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));//�Ƿ�ɾ��
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));//������
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));//����ʱ��
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));// ���һ���޸���
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));//���һ���޸�ʱ��
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
	 * ���ݵ绰�����ȡһ���˵���Ϣ
	 * **/
	public AllProductModel getUser(String mobile){
		AllProductModel info=new AllProductModel();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_ALL_PRODUCT+"where mobile= '"
					+mobile+"'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do{
					info.setAllProductId(cursor.getInt(cursor.getColumnIndex("allProductId")));//Id
					info.setProductName(cursor.getString(cursor.getColumnIndex("productName")));//��Ʒ����
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));//��ҵ����
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));//������
					info.setProductType(cursor.getString(cursor.getColumnIndex("productType")));//��Ʒ���ͣ���Դddinfo��
					info.setReId(cursor.getInt(cursor.getColumnIndex("reId")));// ����ID
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));//�Ƿ�ɾ��
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));//������
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));//����ʱ��
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));// ���һ���޸���
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));//���һ���޸�ʱ��
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
		return info;
	}
	
	/**
	 * ���ݵ绰���� ɾ��һ���û���Ϣ
	 * **/
	public boolean  deleteUser(String whereClause,String[] whereArgs){
		return delete(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT, whereClause, whereArgs);
	}
	
	
	/**
	 * ���ݵ绰���������û���Ϣ
	 * **/
	public boolean deleteAllUser(){
		return deleteAll(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT);
	}
	
	
}
