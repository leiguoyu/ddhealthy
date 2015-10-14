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
 * ���ݿ⵼����
 * 
 * @author
 */
public class DBFileImporter {

	/**
	 * ���������ݿ�
	 * **/
	public void importDB(Context context) {
		// �ж��ļ����Ƿ���ڴ�����ֱ�ӵ��벻�����򴴽��ļ���
		File file = new File(Const.DB_PATH + "/" + Const.DB_NAME);
		// �ж����ݿ��Ƿ��Ѿ�����
		if (!file.exists()) {
			importDB(context, Const.DB_NAME);
		}

	}

	/**
	 * �������ݿ�
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
				File file = new File(Const.DB_PATH + "/" + dbName);// �����ļ�
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
