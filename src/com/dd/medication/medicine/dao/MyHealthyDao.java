package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.util.Const;

/*
 *我的健康记录数据处理
 * by andy
 * */

public class MyHealthyDao extends BaseDao {
	/*
	 * 插入一条我的健康记录。
	 */
	public boolean addMyHealthy(MyHealthyModel myHealthyInfo) {
		ContentValues cv = new ContentValues();
		cv.put("myHealthyId", myHealthyInfo.getMyHealthyId());
		cv.put("localCreateDay", myHealthyInfo.getLocalCreateDay());
		cv.put("localCreateTime", myHealthyInfo.getLocalCreateTime());
		cv.put("parts", myHealthyInfo.getParts());
		cv.put("symptoms", myHealthyInfo.getSymptoms());
		cv.put("symptomsSeverity", myHealthyInfo.getSymptomsSeverity());
		cv.put("symptomsDetail", myHealthyInfo.getSymptomsDetail());
		cv.put("operateType", myHealthyInfo.getOperateType());
		return insert(Const.DB_NAME, Const.TB_NAME_MY_HEALTHY, cv);
	}

	/**
	 * 获取一条健康记录数据
	 * **/
	public MyHealthyModel getMyHealthyId(int myHealthyId) {
		MyHealthyModel info = new MyHealthyModel();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MY_HEALTHY
					+ "where myHealthyId= '" + myHealthyId + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {

					info.setMyHealthyId(cursor.getInt(cursor
							.getColumnIndex("myHealthyId")));
					info.setLocalCreateDay(cursor.getString(cursor
							.getColumnIndex("localCreateDay")));
					info.setLocalCreateTime(cursor.getString(cursor
							.getColumnIndex("localCreateTime")));
					info.setParts(cursor.getString(cursor
							.getColumnIndex("parts")));
					info.setSymptoms(cursor.getString(cursor
							.getColumnIndex("symptoms")));
					info.setSymptomsSeverity(cursor.getString(cursor
							.getColumnIndex("symptomsSeverity")));
					info.setSymptomsDetail(cursor.getString(cursor
							.getColumnIndex("symptomsDetail")));
					info.setOperateType(cursor.getString(cursor
							.getColumnIndex("operateType")));
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
	 * 更新一条数据
	 * */
	public boolean updateMyHealthy(MyHealthyModel myHealthyInfo,
			String whereClause, String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put("myHealthyId", myHealthyInfo.getMyHealthyId());
		cv.put("localCreateDay", myHealthyInfo.getLocalCreateDay());
		cv.put("localCreateTime", myHealthyInfo.getLocalCreateTime());
		cv.put("parts", myHealthyInfo.getParts());
		cv.put("symptoms", myHealthyInfo.getSymptoms());
		cv.put("symptomsSeverity", myHealthyInfo.getSymptomsSeverity());
		cv.put("symptomsDetail", myHealthyInfo.getSymptomsDetail());
		cv.put("operateType", myHealthyInfo.getOperateType());
		return update(Const.DB_NAME, Const.TB_NAME_MY_HEALTHY, cv, whereClause,
				whereArgs);
	}

	/**
	 * 获取所有我的健康数据
	 * */
	public ArrayList<MyHealthyModel> getAllMyHealthy() {
		ArrayList<MyHealthyModel> infos = new ArrayList<MyHealthyModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MY_HEALTHY;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MyHealthyModel info = new MyHealthyModel();
						info.setMyHealthyId(cursor.getInt(cursor
								.getColumnIndex("myHealthyId")));
						info.setLocalCreateDay(cursor.getString(cursor
								.getColumnIndex("localCreateDay")));
						info.setLocalCreateTime(cursor.getString(cursor
								.getColumnIndex("localCreateTime")));
						info.setParts(cursor.getString(cursor
								.getColumnIndex("parts")));
						info.setSymptoms(cursor.getString(cursor
								.getColumnIndex("symptoms")));
						info.setSymptomsSeverity(cursor.getString(cursor
								.getColumnIndex("symptomsSeverity")));
						info.setSymptomsDetail(cursor.getString(cursor
								.getColumnIndex("symptomsDetail")));
						info.setOperateType(cursor.getString(cursor
								.getColumnIndex("operateType")));
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
	 * 获取历史病症
	 * */
	public ArrayList<String> getHistorySymptom(){
		ArrayList<String> str = new ArrayList<String>();
		Cursor cursor = null;
		try {
			String sql = "select symptoms from " + Const.TB_NAME_MY_HEALTHY
					+ " group by symptoms ";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						str.add(cursor.getString(cursor
								.getColumnIndex("symptoms")));
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
		return str;
	}
	
	/**
	 * 获取某天的健康记录数据. 用于timeline获取每天的健康数据
	 * **/
	public ArrayList<MyHealthyModel> getLocalCreateDay(String localCreateDay) {
		ArrayList<MyHealthyModel> infos = new ArrayList<MyHealthyModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MY_HEALTHY
					+ " where localCreateDay = '" + localCreateDay + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MyHealthyModel info = new MyHealthyModel();
						info.setMyHealthyId(cursor.getInt(cursor
								.getColumnIndex("myHealthyId")));
						info.setLocalCreateDay(cursor.getString(cursor
								.getColumnIndex("localCreateDay")));
						info.setLocalCreateTime(cursor.getString(cursor
								.getColumnIndex("localCreateTime")));
						info.setParts(cursor.getString(cursor
								.getColumnIndex("parts")));
						info.setSymptoms(cursor.getString(cursor
								.getColumnIndex("symptoms")));
						info.setSymptomsSeverity(cursor.getString(cursor
								.getColumnIndex("symptomsSeverity")));
						info.setSymptomsDetail(cursor.getString(cursor
								.getColumnIndex("symptomsDetail")));
						info.setOperateType(cursor.getString(cursor
								.getColumnIndex("operateType")));
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
	 * 删除某条记录
	 * **/
	public boolean deleteHealthRec(String whereClause,String[] whereArgs) {
		return delete(Const.DB_NAME, Const.TB_NAME_MY_HEALTHY,whereClause,whereArgs);
	}
	
	/**
	 * 删除所用药品详情记录
	 * **/
	public boolean deleteAllHealthRec() {
		return deleteAll(Const.DB_NAME, Const.TB_NAME_MY_HEALTHY);
	}

	public int getCount() {
		int count=0;
		Cursor cursor = null;
		try {
			String sql="select count(*) from "+Const.TB_NAME_MY_HEALTHY;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					count = cursor.getInt(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return count;
	}
}