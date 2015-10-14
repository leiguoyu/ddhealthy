package com.dd.medication.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * 数据库导入类
 * 
 * @author
 */
public class DBFileImporter {

	/**
	 * 导入多个数据库
	 * **/
	public void importDB(Context context) {
		// 判断文件夹是否存在存在则直接导入不存在则创建文件夹
		File file = new File(Const.DB_PATH + "/" + Const.DB_NAME);
		// 判断数据库是否已经导入
		if (!file.exists()) {
			importDB(context, Const.DB_NAME);
		}

	}

	/**
	 * 导入数据库
	 * */
	public void importDB(Context context, String dbName) {

		AssetManager assetManager = context.getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open(dbName);

			BufferedInputStream bis = new BufferedInputStream(inputStream);
			ByteArrayBuffer baf = new ByteArrayBuffer(100);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			byte[] data = baf.toByteArray();
			if (data != null) {
				File file = new File(Const.DB_PATH + "/" + dbName);// 保存文件
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data);
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			Log.d("importDB:err", e.getMessage());
		}

	}

}
