package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.medicine.model.SymptomPartModel;
import com.dd.medication.util.Const;

public class SymptomPartDao extends BaseDao {

	/**
	 * 插入一条数据
	 * */
	public boolean addSymptomPart(SymptomPartModel info) {
		ContentValues cv = new ContentValues();
		cv.put("symptomPartId", info.getSymptomPartId());
		cv.put("baseParts", info.getBaseParts());
		cv.put("parts", info.getParts());
		cv.put("symptom", info.getSymptom());
		cv.put("clinicalSeverity", info.getClinicalSeverity());
		cv.put("sex", info.getSex());
		return insert(Const.DB_NAME, Const.TB_NAME_SYM_PTOMPART, cv);
	}

	/**
	 * 更新一条数据
	 * */
	public boolean updateSymptomPart(SymptomPartModel info, String whereClause,
			String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put("symptomPartId", info.getSymptomPartId());
		cv.put("baseParts", info.getBaseParts());
		cv.put("parts", info.getParts());
		cv.put("symptom", info.getSymptom());
		cv.put("clinicalSeverity", info.getClinicalSeverity());
		cv.put("sex", info.getSex());
		return update(Const.DB_NAME, Const.TB_NAME_SYM_PTOMPART, cv,
				whereClause, whereArgs);
	}

	/**
	 * 按照部位获取病症 
	 * */
	public ArrayList<String> getSymptomByParts(String parts) {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = null;
		try {
			String sql = "select symptom from " + Const.TB_NAME_SYM_PTOMPART
					+ " where parts= '" + parts + "'" ;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getString(cursor
								.getColumnIndex("symptom")));
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
		return list;
	}
	

	/**
	 * 按照大部位获取小部位  返回症状列表  分男女  0男  1 女
	 * */
	public ArrayList<String> getPartsByBaseParts(String baseParts ,String sex) {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = null;
		try {
//			select * from SymptomPart where baseParts="臀部" group by parts
//			select * from SymptomPart where baseParts="臀部" and sex !="0" group by parts
			String sql = "select parts from " + Const.TB_NAME_SYM_PTOMPART
					+ " where baseParts = '" + baseParts + "'" +" and sex != '"+sex +"'" +" group by parts";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getString(cursor
								.getColumnIndex("parts")));
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
		return list;
	}
	
	/**
	 * 按照大部位获取小部位  返回症状列表  分男女  0男  1 女
	 * */
	public ArrayList<String> getPartsByBaseParts(String baseParts) {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = null;
		try {
//			select * from SymptomPart where baseParts="臀部" group by parts
//			select * from SymptomPart where baseParts="臀部" and sex !="0" group by parts
			String sql = "select parts from " + Const.TB_NAME_SYM_PTOMPART
					+ " where baseParts = '" + baseParts + "'" +" group by parts";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getString(cursor
								.getColumnIndex("parts")));
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
		return list;
	}
	
	
	/**
	 * 按照大部位获取小部位  返回对象列表
	 * */
	public ArrayList<SymptomPartModel> getPartsByBase(String baseParts) {
		ArrayList<SymptomPartModel> list = new ArrayList<SymptomPartModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_SYM_PTOMPART
					+ "where baseParts= '" + baseParts + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						SymptomPartModel info = new SymptomPartModel();
						info.setSymptomPartId(cursor.getString(cursor
								.getColumnIndex("symptomPartId")));
						info.setBaseParts(cursor.getString(cursor
								.getColumnIndex("baseParts")));
						info.setParts(cursor.getString(cursor
								.getColumnIndex("parts")));
						info.setSymptom(cursor.getString(cursor
								.getColumnIndex("symptom")));
						info.setClinicalSeverity(cursor.getString(cursor
								.getColumnIndex("clinicalSeverity")));
						info.setSex(cursor.getString(cursor
								.getColumnIndex("sex")));
						list.add(info);
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
		return list;
	}

	/**
	 * 根据性别获取人体全部身体部位的分类
	 * select * from SymptomPart where  sex !="0" group by parts
	 * */
	public ArrayList<String> getPartsByBody(String sex) {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = null;
		try {
			String sql = "select parts from " + Const.TB_NAME_SYM_PTOMPART
					+ "where sex != '" + sex + "' group by parts";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getString(cursor
								.getColumnIndex("parts")));
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
		return list;
	}
	
	
	/**
	 * 删除一条数据
	 * */
	public boolean deleteUser(String whereClause, String[] whereArgs) {
		return delete(Const.DB_NAME, Const.TB_NAME_SYM_PTOMPART, whereClause,
				whereArgs);
	}

	/**
	 * 删除全部数据
	 * */
	public boolean deleteAllSymptomPart() {
		return deleteAll(Const.DB_NAME, Const.TB_NAME_SYM_PTOMPART);
	}

}
