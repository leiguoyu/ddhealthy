package com.dd.medication.base.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dd.medication.util.Const;

/**
 * BaseDao
 * */
public class BaseDao {
	private SQLiteDatabase database;

	public BaseDao() {

	}

	protected Cursor getCursorQuery(String dbName, String sql) {
		Log.i("BaseDao", "dbName = " + dbName + "|sql=" + sql);
		return getDatabase(dbName).rawQuery(sql, null);
	}

	// 我现在不知道界面具体长啥样？按照线框图随便拖动的控件都没配置风格
	public SQLiteDatabase getDatabase(String dbName) {
		if (database == null || !database.isOpen()) {
			database = SQLiteDatabase.openOrCreateDatabase(Const.DB_PATH + "/"
					+ dbName, null);
		}
		return database;
	}

	public void closeDatabase() {

		if (database != null) {
			database.close();
		}
	}

	/**
	 * 鎻掑叆鏁版嵁
	 * 
	 * @param model
	 * @return
	 */
	public boolean insert(String dbName, String tbName, ContentValues values) {

		// Log.i("BaseDao", "insert dbName = " + dbName + "|tbName=" + tbName +
		// "| values= " + values.toString() );
		boolean isSuccess = true;
		try {
			getDatabase(dbName).insert(tbName, null, values);
			// getDatabase(dbName).replace(tbName, null, values);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			closeDatabase();
		}
		return isSuccess;
	}

	/**
	 * 鍒犻櫎鏁版嵁
	 * 
	 * @param model
	 * @return
	 */
	public boolean delete(String dbName, String tbName, String whereClause,
			String[] whereArgs) {
		// Log.i("BaseDao", "delete dbName = " + dbName + "|whereClause=" +
		// whereClause + "| whereArgs= " + whereArgs );
		boolean isSuccess = true;
		try {
			getDatabase(dbName).delete(tbName, whereClause, whereArgs);
			// getDatabase(dbName).replace(tbName, null, values);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			closeDatabase();
		}
		return isSuccess;
	}

	/**
	 * 鍒犻櫎鎵?湁鏁版嵁
	 * 
	 * @return
	 */
	public boolean deleteAll(String dbName, String tbName) {
		getDatabase(dbName).delete(tbName, null, null);
		closeDatabase();
		return true;
	}

	/**
	 * 淇敼鏁版嵁
	 * 
	 * @param model
	 * @return
	 */
	public boolean update(String dbName, String tbName, ContentValues values,
			String whereClause, String[] whereArgs) {
		boolean isSuccess = true;
		try {
			// Log.i("BaseDao", "update dbName = " + dbName + "|tbName=" +
			// tbName + "| values= " + values.toString() + "| whereClause = " +
			// whereClause + " |whereArgs=" + whereArgs);

			getDatabase(dbName).update(tbName, values, whereClause, whereArgs);
			closeDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isSuccess = false;
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * 鎵цsql璇彞
	 * 
	 * @param dbName
	 * @param tbName
	 * @param sql
	 * @return
	 */
	public boolean excuteSQL(String dbName, String sql) {

		// Log.i("BaseDao", "excuteSQL dbName = " + dbName + "|sql=" + sql);
		getDatabase(dbName).execSQL(sql);
		return true;
	}

	public boolean excuteSQL(String dbName, String sql, Object[] bindArgs) {
		this.getDatabase(dbName).execSQL(sql, bindArgs);
		return true;
	}

}
