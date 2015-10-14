package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.login.model.UserInfoModel;
import com.dd.medication.medicine.model.MedicineDetailModel;
import com.dd.medication.util.Const;

/*
 * ����ҩƷ�������ݴ���
 * by andy
 * */

public class MedicineDetailDao extends BaseDao {
	/*
	 * ����һ��ҩƷ�������ݣ�ͨ��ɨ�������ҩƷ�ӷ�������ȡ���ݺ󱣴汾�ء�
	 */
	public boolean addMedicineDetail(MedicineDetailModel medicineDetailInfo) {
		ContentValues cv = new ContentValues();
		cv.put("medicineDetailId", medicineDetailInfo.getMedicineDetailId());
		cv.put("type", medicineDetailInfo.getType());
		cv.put("productname", medicineDetailInfo.getProductname());
		cv.put("company", medicineDetailInfo.getCompany());
		cv.put("specification", medicineDetailInfo.getSpecification());
		cv.put("barCode", medicineDetailInfo.getBarCode());
		cv.put("dosage", medicineDetailInfo.getDosage());
		cv.put("ingredient", medicineDetailInfo.getIngredient());
		cv.put("functions", medicineDetailInfo.getFunctions());
		cv.put("theusage", medicineDetailInfo.getTheusage());
		cv.put("reactions", medicineDetailInfo.getReactions());
		cv.put("taboo", medicineDetailInfo.getTaboo());
		cv.put("note", medicineDetailInfo.getNote());
		cv.put("store", medicineDetailInfo.getStore());
		cv.put("validity", medicineDetailInfo.getValidity());
		cv.put("indication", medicineDetailInfo.getIndication());
		cv.put("dosageCategory", medicineDetailInfo.getDosageCategory());
		cv.put("usingTime", medicineDetailInfo.getUsingTime());
		cv.put("status", medicineDetailInfo.getStatus());
		cv.put("createUser", medicineDetailInfo.getCreateUser());
		cv.put("createDate", medicineDetailInfo.getCreateDate());
		cv.put("lmodifyUser", medicineDetailInfo.getLmodifyUser());
		cv.put("lmodifyDate", medicineDetailInfo.getLmodifyDate());

		return insert(Const.DB_NAME, Const.TB_NAME_MEDICINE_DETAIL, cv);
	}

	/**
	 * ����һ������
	 * */
	public boolean updateMedicineDetail(MedicineDetailModel medicineDetailInfo,
			String whereClause, String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put("medicineDetailId", medicineDetailInfo.getMedicineDetailId());
		cv.put("type", medicineDetailInfo.getType());
		cv.put("productname", medicineDetailInfo.getProductname());
		cv.put("company", medicineDetailInfo.getCompany());
		cv.put("specification", medicineDetailInfo.getSpecification());
		cv.put("barCode", medicineDetailInfo.getBarCode());
		cv.put("dosage", medicineDetailInfo.getDosage());
		cv.put("ingredient", medicineDetailInfo.getIngredient());
		cv.put("functions", medicineDetailInfo.getFunctions());
		cv.put("theusage", medicineDetailInfo.getTheusage());
		cv.put("reactions", medicineDetailInfo.getReactions());
		cv.put("taboo", medicineDetailInfo.getTaboo());
		cv.put("note", medicineDetailInfo.getNote());
		cv.put("store", medicineDetailInfo.getStore());
		cv.put("validity", medicineDetailInfo.getValidity());
		cv.put("indication", medicineDetailInfo.getIndication());
		cv.put("dosageCategory", medicineDetailInfo.getDosageCategory());
		cv.put("usingTime", medicineDetailInfo.getUsingTime());
		cv.put("status", medicineDetailInfo.getStatus());
		cv.put("createUser", medicineDetailInfo.getCreateUser());
		cv.put("createDate", medicineDetailInfo.getCreateDate());
		cv.put("lmodifyUser", medicineDetailInfo.getLmodifyUser());
		cv.put("lmodifyDate", medicineDetailInfo.getLmodifyDate());

		return update(Const.DB_NAME, Const.TB_NAME_MEDICINE_DETAIL, cv,
				whereClause, whereArgs);
	}

	/**
	 * ��ȡ��������������ҩ���顣
	 * */
	public ArrayList<MedicineDetailModel> getAllMedicineDetail() {
		ArrayList<MedicineDetailModel> infos = new ArrayList<MedicineDetailModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICINE_DETAIL;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do{
					MedicineDetailModel info = new MedicineDetailModel();
					info.setMedicineDetailId(cursor.getString(cursor
							.getColumnIndex("medicineDetailId")));
					info.setType(cursor.getInt(cursor.getColumnIndex("type")));
					info.setProductname(cursor.getString(cursor
							.getColumnIndex("productname")));
					info.setCompany(cursor.getString(cursor
							.getColumnIndex("company")));
					info.setSpecification(cursor.getString(cursor
							.getColumnIndex("specification")));
					info.setBarCode(cursor.getString(cursor
							.getColumnIndex("barCode")));
					info.setDosage(cursor.getString(cursor
							.getColumnIndex("dosage")));
					info.setIngredient(cursor.getString(cursor
							.getColumnIndex("ingredient")));
					info.setFunctions(cursor.getString(cursor
							.getColumnIndex("functions")));
					info.setTheusage(cursor.getString(cursor
							.getColumnIndex("theusage")));
					info.setReactions(cursor.getString(cursor
							.getColumnIndex("reactions")));
					info.setTaboo(cursor.getString(cursor
							.getColumnIndex("taboo")));
					info.setNote(cursor.getString(cursor.getColumnIndex("note")));
					info.setStore(cursor.getString(cursor
							.getColumnIndex("store")));
					info.setValidity(cursor.getString(cursor
							.getColumnIndex("validity")));
					info.setIndication(cursor.getString(cursor
							.getColumnIndex("indication")));
					info.setDosageCategory(cursor.getString(cursor
							.getColumnIndex("dosageCategory")));
					info.setUsingTime(cursor.getString(cursor
							.getColumnIndex("usingTime")));
					info.setStatus(cursor.getInt(cursor
							.getColumnIndex("status")));
					info.setCreateUser(cursor.getString(cursor
							.getColumnIndex("createUser")));
					info.setCreateDate(cursor.getString(cursor
							.getColumnIndex("createDate")));
					info.setLmodifyUser(cursor.getString(cursor
							.getColumnIndex("lmodifyUser")));
					info.setLmodifyDate(cursor.getString(cursor
							.getColumnIndex("lmodifyDate")));
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
	 * ��ȡͨ��ɨ��or �ֶ�������ȡ����ҩƷ���顣 type= 0:ɨ���ȡ��ҩƷ���� type= 1���ֶ�������ȡ����ҩƷ����
	 * **/
	public ArrayList<MedicineDetailModel> getType(int type) {
		ArrayList<MedicineDetailModel> infos = new ArrayList<MedicineDetailModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICINE_DETAIL
					+ "where type= '" + type + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do{
					MedicineDetailModel info = new MedicineDetailModel();
					info.setMedicineDetailId(cursor.getString(cursor
							.getColumnIndex("medicineDetailId")));
					info.setType(cursor.getInt(cursor.getColumnIndex("type")));
					info.setProductname(cursor.getString(cursor
							.getColumnIndex("productname")));
					info.setCompany(cursor.getString(cursor
							.getColumnIndex("company")));
					info.setSpecification(cursor.getString(cursor
							.getColumnIndex("specification")));
					info.setBarCode(cursor.getString(cursor
							.getColumnIndex("barCode")));
					info.setDosage(cursor.getString(cursor
							.getColumnIndex("dosage")));
					info.setIngredient(cursor.getString(cursor
							.getColumnIndex("ingredient")));
					info.setFunctions(cursor.getString(cursor
							.getColumnIndex("functions")));
					info.setTheusage(cursor.getString(cursor
							.getColumnIndex("theusage")));
					info.setReactions(cursor.getString(cursor
							.getColumnIndex("reactions")));
					info.setTaboo(cursor.getString(cursor
							.getColumnIndex("taboo")));
					info.setNote(cursor.getString(cursor.getColumnIndex("note")));
					info.setStore(cursor.getString(cursor
							.getColumnIndex("store")));
					info.setValidity(cursor.getString(cursor
							.getColumnIndex("validity")));
					info.setIndication(cursor.getString(cursor
							.getColumnIndex("indication")));
					info.setDosageCategory(cursor.getString(cursor
							.getColumnIndex("dosageCategory")));
					info.setUsingTime(cursor.getString(cursor
							.getColumnIndex("usingTime")));
					info.setStatus(cursor.getInt(cursor
							.getColumnIndex("status")));
					info.setCreateUser(cursor.getString(cursor
							.getColumnIndex("createUser")));
					info.setCreateDate(cursor.getString(cursor
							.getColumnIndex("createDate")));
					info.setLmodifyUser(cursor.getString(cursor
							.getColumnIndex("lmodifyUser")));
					info.setLmodifyDate(cursor.getString(cursor
							.getColumnIndex("lmodifyDate")));
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
	public boolean deleteAllUser() {
		return deleteAll(Const.DB_NAME, Const.TB_NAME_MEDICINE_DETAIL);
	}
}