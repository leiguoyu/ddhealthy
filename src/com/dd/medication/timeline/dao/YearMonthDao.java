package com.dd.medication.timeline.dao;

import java.util.ArrayList;

import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.timeline.model.YearMonthModel;
import com.dd.medication.util.Const;

public class YearMonthDao extends BaseDao{

	/**
	 * 获取用药记录的年月 升序
	 * */
	public ArrayList<YearMonthModel> getYearMonths(){
		ArrayList<YearMonthModel> infos=new ArrayList<YearMonthModel>();
		Cursor cursor = null;
		try {
			String sql = "select yearMonth from "+Const.TB_NAME_MEDICATION_REMIND +" group by yearMonth order by yearMonth";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do {
						YearMonthModel info=new YearMonthModel();
						info.setYearMonth(cursor.getString(cursor.getColumnIndex("yearMonth")));
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
	
}
