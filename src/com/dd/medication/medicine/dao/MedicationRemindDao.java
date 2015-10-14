package com.dd.medication.medicine.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.timeline.model.YearMonthModel;
import com.dd.medication.util.Const;

/*
 * ��ҩ���Ѽ�¼���ݴ���
 * by andy
 * */

public class MedicationRemindDao extends BaseDao {
	/*
	 * ����һ����ҩ�������ݡ�
	 */
	public boolean addMedicationRemind(MedicationRemind medicationRemindInfo) {
		ContentValues cv = new ContentValues();
		cv.put("medicationRemindId",
				medicationRemindInfo.getMedicationRemindId());
		cv.put("allProductId", medicationRemindInfo.getAllProductId());
		cv.put("medicationObjectName",
				medicationRemindInfo.getMedicationObjectName());
		cv.put("medicationName", medicationRemindInfo.getMedicationName());
		cv.put("dosageCategory", medicationRemindInfo.getDosageCategory());
		cv.put("singleDose", medicationRemindInfo.getSingleDose());
		cv.put("units", medicationRemindInfo.getUnits());
		cv.put("alertDay", medicationRemindInfo.getAlertDay());
		cv.put("alertTime", medicationRemindInfo.getAlertTime());
		cv.put("status", medicationRemindInfo.getStatus());
		cv.put("closeTime", medicationRemindInfo.getCloseTime());
		cv.put("lmodifyDate", medicationRemindInfo.getLmodifyDate());
		cv.put("stop", medicationRemindInfo.getStop());
		cv.put("takeMedicinetTimeExplain",
				medicationRemindInfo.getTakeMedicinetTimeExplain());
		cv.put("otherExplain", medicationRemindInfo.getOtherExplain());
		cv.put("year", medicationRemindInfo.getYear());
		cv.put("month", medicationRemindInfo.getMonth());
		cv.put("date", medicationRemindInfo.getDate());
		cv.put("yearMonth", medicationRemindInfo.getYearMonth());
		return insert(Const.DB_NAME, Const.TB_NAME_MEDICATION_REMIND, cv);
	}

	/**
	 * ��ȡһ����������
	 * **/
	public MedicationRemind getMedicationRemindId(int medicationRemindId) {
		MedicationRemind info = new MedicationRemind();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ "where medicationRemindId= '" + medicationRemindId + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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
	 * �༭������һ������
	 * */
	public boolean updateMedicationRemind(
			MedicationRemind medicationRemindInfo, String whereClause,
			String[] whereArgs) {
		ContentValues cv = new ContentValues();
		cv.put("medicationRemindId",
				medicationRemindInfo.getMedicationRemindId());
		cv.put("allProductId", medicationRemindInfo.getAllProductId());
		cv.put("medicationObjectName",
				medicationRemindInfo.getMedicationObjectName());
		cv.put("medicationName", medicationRemindInfo.getMedicationName());
		cv.put("dosageCategory", medicationRemindInfo.getDosageCategory());
		cv.put("singleDose", medicationRemindInfo.getSingleDose());
		cv.put("units", medicationRemindInfo.getUnits());
		cv.put("alertDay", medicationRemindInfo.getAlertDay());
		cv.put("alertTime", medicationRemindInfo.getAlertTime());
		cv.put("status", medicationRemindInfo.getStatus());
		cv.put("closeTime", medicationRemindInfo.getCloseTime());
		cv.put("lmodifyDate", medicationRemindInfo.getLmodifyDate());
		cv.put("stop", medicationRemindInfo.getStop());
		cv.put("takeMedicinetTimeExplain",
				medicationRemindInfo.getTakeMedicinetTimeExplain());
		cv.put("otherExplain", medicationRemindInfo.getOtherExplain());
		cv.put("year", medicationRemindInfo.getYear());
		cv.put("month", medicationRemindInfo.getMonth());
		cv.put("date", medicationRemindInfo.getDate());
		cv.put("yearMonth", medicationRemindInfo.getYearMonth());
		return update(Const.DB_NAME, Const.TB_NAME_MEDICATION_REMIND, cv,
				whereClause, whereArgs);
	}

	/**
	 * ��ȡ������ҩ��������
	 * */
	public ArrayList<MedicationRemind> getAllMedicationRemind() {
		ArrayList<MedicationRemind> infos = new ArrayList<MedicationRemind>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MedicationRemind info = new MedicationRemind();
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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
	 * �����ڷ����ȡȫ������
	 * */
	public ArrayList<MedicationRemind> getRecByGroupDate() {
		ArrayList<MedicationRemind> infos = new ArrayList<MedicationRemind>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ " group by alertDay";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MedicationRemind info = new MedicationRemind();
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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
	 * ��ȡĳ�����ҩ��������. ����timeline��ȡÿ�����������
	 * **/
	public ArrayList<MedicationRemind> getAlertDay(String alertDay) {
		ArrayList<MedicationRemind> infos = new ArrayList<MedicationRemind>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ " where alertDay = '" + alertDay + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MedicationRemind info = new MedicationRemind();
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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

	public ArrayList<String> getMedicationNameByDate(String alertDay) {

		ArrayList<String>  medicationNames = new ArrayList<String>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ " where alertDay = '" + alertDay
					+ "' group by medicationName";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						medicationNames.add(cursor.getString(cursor
								.getColumnIndex("medicationName")));
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

		return medicationNames;
	}

	/**
	 * ��ȡĳ��ҩƷ����ҩ��������. ���ڡ��ҡ���ҩƷ��¼��
	 * **/
	public ArrayList<MedicationRemind> getMedicationName(String medicationName) {
		ArrayList<MedicationRemind> infos = new ArrayList<MedicationRemind>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ "where medicationName= '" + medicationName + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MedicationRemind info = new MedicationRemind();
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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
		return deleteAll(Const.DB_NAME, Const.TB_NAME_MEDICATION_REMIND);
	}

	/**
	 * �����·� ��ȡ���µ�����
	 * */
	public ArrayList<MedicationRemind> getYearMonthMedicationRemind(
			String yearMonth) {

		ArrayList<MedicationRemind> infos = new ArrayList<MedicationRemind>();
		Cursor cursor = null;
		try {
			String sql = "select * from " + Const.TB_NAME_MEDICATION_REMIND
					+ " where yearMonth='" + yearMonth + "'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						MedicationRemind info = new MedicationRemind();
						info.setMedicationRemindId(cursor.getInt(cursor
								.getColumnIndex("medicationRemindId")));
						info.setAllProductId(cursor.getInt(cursor
								.getColumnIndex("allProductId")));
						info.setMedicationObjectName(cursor.getString(cursor
								.getColumnIndex("medicationObjectName")));
						info.setMedicationName(cursor.getString(cursor
								.getColumnIndex("medicationName")));
						info.setDosageCategory(cursor.getString(cursor
								.getColumnIndex("dosageCategory")));
						info.setSingleDose(cursor.getString(cursor
								.getColumnIndex("singleDose")));
						info.setUnits(cursor.getString(cursor
								.getColumnIndex("units")));
						info.setAlertDay(cursor.getString(cursor
								.getColumnIndex("alertDay")));
						info.setAlertTime(cursor.getString(cursor
								.getColumnIndex("alertTime")));
						info.setStatus(cursor.getInt(cursor
								.getColumnIndex("status")));
						info.setCloseTime(cursor.getString(cursor
								.getColumnIndex("closeTime")));
						info.setLmodifyDate(cursor.getString(cursor
								.getColumnIndex("lmodifyDate")));
						info.setStop(cursor.getInt(cursor
								.getColumnIndex("stop")));
						info.setTakeMedicinetTimeExplain(cursor.getString(cursor
								.getColumnIndex("takeMedicinetTimeExplain")));
						info.setOtherExplain(cursor.getString(cursor
								.getColumnIndex("otherExplain")));
						info.setYear(cursor.getString(cursor
								.getColumnIndex("year")));
						info.setMonth(cursor.getString(cursor
								.getColumnIndex("month")));
						info.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						info.setYearMonth(cursor.getString(cursor
								.getColumnIndex("yearMonth")));
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
	 * �����������
	 * **/
	public void addMedicationRemind(
			ArrayList<MedicationRemind> medicationRemindList) {
		for (int i = 0; i < medicationRemindList.size(); i++) {
			addMedicationRemind(medicationRemindList.get(i));
		}

	}

}