package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.util.Const;

public class AllProductDao extends BaseDao{

	/**
	 * 插入一条数据
	 * */
	public boolean addAllProduct(AllProductModel info){
			ContentValues cv = new ContentValues();
			cv.put("allProductId", info.getAllProductId());//Id
			cv.put("productName", info.getProductName());//产品名称
			cv.put("company", info.getCompany());//企业名称
			cv.put("barCode", info.getBarCode());//条形码
			cv.put("productType", info.getProductType());//产品类型（来源ddinfo）
			cv.put("reId", info.getReId());// 关联ID
			
			cv.put("status", info.getStatus());//是否删除
			cv.put("createUser", info.getCreateUser());//创建人
			cv.put("createDate", info.getCreateDate());//创建时间
			cv.put("lmodifyUser", info.getLmodifyUser());// 最后一次修改人
			cv.put("lmodifyDate", info.getLmodifyDate());//最后一次修改时间
			
			return insert(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT,cv);
	}
	
	/**
	 * 插入多条数据 
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
	 * 更新一条数据
	 * */
	public boolean updateUser(AllProductModel info,String whereClause, String[] whereArgs){
		ContentValues cv = new ContentValues();
		cv.put("allProductId", info.getAllProductId());//Id
		cv.put("productName", info.getProductName());//产品名称
		cv.put("company", info.getCompany());//企业名称
		cv.put("barCode", info.getBarCode());//条形码
		cv.put("productType", info.getProductType());//产品类型（来源ddinfo）
		cv.put("reId", info.getReId());// 关联ID
		
		cv.put("status", info.getStatus());//是否删除
		cv.put("createUser", info.getCreateUser());//创建人
		cv.put("createDate", info.getCreateDate());//创建时间
		cv.put("lmodifyUser", info.getLmodifyUser());// 最后一次修改人
		cv.put("lmodifyDate", info.getLmodifyDate());//最后一次修改时间
		
		return update(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT,cv, whereClause, whereArgs);
       }
	
	/**
	 * 获取全部用户信息
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
					info.setProductName(cursor.getString(cursor.getColumnIndex("productName")));//产品名称
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));//企业名称
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));//条形码
					info.setProductType(cursor.getString(cursor.getColumnIndex("productType")));//产品类型（来源ddinfo）
					info.setReId(cursor.getInt(cursor.getColumnIndex("reId")));// 关联ID
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));//是否删除
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));//创建人
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));//创建时间
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));// 最后一次修改人
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));//最后一次修改时间
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
	 * 根据电话号码获取一个人的信息
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
					info.setProductName(cursor.getString(cursor.getColumnIndex("productName")));//产品名称
					info.setCompany(cursor.getString(cursor.getColumnIndex("company")));//企业名称
					info.setBarCode(cursor.getString(cursor.getColumnIndex("barCode")));//条形码
					info.setProductType(cursor.getString(cursor.getColumnIndex("productType")));//产品类型（来源ddinfo）
					info.setReId(cursor.getInt(cursor.getColumnIndex("reId")));// 关联ID
					info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));//是否删除
					info.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));//创建人
					info.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));//创建时间
					info.setLmodifyUser(cursor.getString(cursor.getColumnIndex("lmodifyUser")));// 最后一次修改人
					info.setLmodifyDate(cursor.getString(cursor.getColumnIndex("lmodifyDate")));//最后一次修改时间
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
	 * 根据电话号码 删除一条用户信息
	 * **/
	public boolean  deleteUser(String whereClause,String[] whereArgs){
		return delete(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT, whereClause, whereArgs);
	}
	
	
	/**
	 * 根据电话号码所有用户信息
	 * **/
	public boolean deleteAllUser(){
		return deleteAll(Const.DB_NAME,Const.TB_NAME_ALL_PRODUCT);
	}
	
	
}
