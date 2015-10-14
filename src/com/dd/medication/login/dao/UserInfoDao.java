package com.dd.medication.login.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.login.model.UserInfoModel;
import com.dd.medication.util.Const;

public class UserInfoDao extends BaseDao{

	/**
	 * ����һ������
	 * */
	public boolean addUser(UserInfoModel userInfo){
			ContentValues cv = new ContentValues();
			cv.put("memberId", userInfo.getMemberId());//memberId
			cv.put("mobile", userInfo.getMobile());//�ֻ��ţ�Ψһ����¼ʹ�ã�
			cv.put("password", userInfo.getPassword());//�û����루MD5���ܣ�
			cv.put("sessionId", userInfo.getSessionId());//�ỰID session ID
			cv.put("nickName", userInfo.getNickName());//�ǳ�
			cv.put("memberName", userInfo.getMemberName());// ����
			cv.put("sex", userInfo.getSex());//�Ա� GGMK_XB_01 �� GGMK_XB_02 Ů GGMK_XB_03 ����
			cv.put("age", userInfo.getAge());//����
			cv.put("openId", userInfo.getOpenId());//������ƽ̨ID
			cv.put("openAccountType", userInfo.getOpenAccountType());//������ƽ̨���ͣ���Դddinfo��
			cv.put("isActivited", userInfo.getIsActivited());//�Ƿ���Ч
			cv.put("status", userInfo.getStatus());//�Ƿ�ɾ��
			cv.put("createUser", userInfo.getCreateUser());//������
			cv.put("createDate", userInfo.getCreateDate());//����ʱ��
			cv.put("lmodifyUser", userInfo.getLmodifyUser());// ���һ���޸���
			cv.put("lmodifyDate", userInfo.getLmodifyDate());//���һ���޸�ʱ��
			
			return insert(Const.DB_NAME,Const.TB_NAME_USERINFO,cv);
	}
	
	
	/**
	 * ����һ������
	 * */
	public boolean updateUser(UserInfoModel userInfo,String whereClause, String[] whereArgs){
		ContentValues cv = new ContentValues();
		cv.put("memberId", userInfo.getMemberId());//memberId
		cv.put("mobile", userInfo.getMobile());//�ֻ��ţ�Ψһ����¼ʹ�ã�
		cv.put("password", userInfo.getPassword());//�û����루MD5���ܣ�
		cv.put("sessionId", userInfo.getSessionId());//�ỰID session ID
		cv.put("nickName", userInfo.getNickName());//�ǳ�
		cv.put("memberName", userInfo.getMemberName());// ����
		cv.put("sex", userInfo.getSex());//�Ա� GGMK_XB_01 �� GGMK_XB_02 Ů GGMK_XB_03 ����
		cv.put("age", userInfo.getAge());//����
		cv.put("openId", userInfo.getOpenId());//������ƽ̨ID
		cv.put("openAccountType", userInfo.getOpenAccountType());//������ƽ̨���ͣ���Դddinfo��
		cv.put("isActivited", userInfo.getIsActivited());//�Ƿ���Ч
		cv.put("status", userInfo.getStatus());//�Ƿ�ɾ��
		cv.put("createUser", userInfo.getCreateUser());//������
		cv.put("createDate", userInfo.getCreateDate());//����ʱ��
		cv.put("lmodifyUser", userInfo.getLmodifyUser());// ���һ���޸���
		cv.put("lmodifyDate", userInfo.getLmodifyDate());//���һ���޸�ʱ��
		
		return update(Const.DB_NAME,Const.TB_NAME_USERINFO,cv, whereClause, whereArgs);
       }
	
	/**
	 * ��ȡȫ���û���Ϣ
	 * */
	public ArrayList<UserInfoModel>getAllUsers(){
		ArrayList<UserInfoModel> infos=new ArrayList<UserInfoModel>();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_USERINFO;
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do {
					UserInfoModel info=new UserInfoModel();
					info.setMemberId(cursor.getInt(cursor.getColumnIndex("memberId")));//memberId
					info.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));//�ֻ��ţ�Ψһ����¼ʹ�ã�
					info.setPassword(cursor.getString(cursor.getColumnIndex("password")));//�û����루MD5���ܣ�
					info.setSessionId(cursor.getString(cursor.getColumnIndex("sessionId")));//�ỰID session ID
					info.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));//�ǳ�
					info.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));// ����
					info.setSex(cursor.getString(cursor.getColumnIndex("sex")));//�Ա� GGMK_XB_01 �� GGMK_XB_02 Ů GGMK_XB_03 ����
					info.setAge(cursor.getInt(cursor.getColumnIndex("age")));//����
					info.setOpenId(cursor.getString(cursor.getColumnIndex("openId")));//������ƽ̨ID
					info.setOpenAccountType(cursor.getString(cursor.getColumnIndex("openAccountType")));//������ƽ̨���ͣ���Դddinfo��
					info.setIsActivited(cursor.getInt(cursor.getColumnIndex("isActivited")));//�Ƿ���Ч
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
	public UserInfoModel getUser(String mobile){
		UserInfoModel info=new UserInfoModel();
		Cursor cursor = null;
		try {
			String sql = "select * from "+Const.TB_NAME_USERINFO+"where mobile= '"
					+mobile+"'";
			cursor = getCursorQuery(Const.DB_NAME, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					do{
					info.setMemberId(cursor.getInt(cursor.getColumnIndex("memberId")));//memberId
					info.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));//�ֻ��ţ�Ψһ����¼ʹ�ã�
					info.setPassword(cursor.getString(cursor.getColumnIndex("password")));//�û����루MD5���ܣ�
					info.setSessionId(cursor.getString(cursor.getColumnIndex("sessionId")));//�ỰID session ID
					info.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));//�ǳ�
					info.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));// ����
					info.setSex(cursor.getString(cursor.getColumnIndex("sex")));//�Ա� GGMK_XB_01 �� GGMK_XB_02 Ů GGMK_XB_03 ����
					info.setAge(cursor.getInt(cursor.getColumnIndex("age")));//����
					info.setOpenId(cursor.getString(cursor.getColumnIndex("openId")));//������ƽ̨ID
					info.setOpenAccountType(cursor.getString(cursor.getColumnIndex("openAccountType")));//������ƽ̨���ͣ���Դddinfo��
					info.setIsActivited(cursor.getInt(cursor.getColumnIndex("isActivited")));//�Ƿ���Ч
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
		return delete(Const.DB_NAME,Const.TB_NAME_USERINFO, whereClause, whereArgs);
	}
	
	
	/**
	 * ���ݵ绰���������û���Ϣ
	 * **/
	public boolean deleteAllUser(){
		return deleteAll(Const.DB_NAME,Const.TB_NAME_USERINFO);
	}
	
	/*
	public boolean isExistUser(String userName,String password){
		int cnt = 0;
		Cursor cursor = null;
		try {
			String sql = "select count(*) from TB_USER where USERNAME= '"
					+ userName
					+ "' and PASSWORD='"+password+"'";
			cursor = getCursorQuery(TB_Const.DB_NAME.DB_BASE, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					cnt = cursor.getInt(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			cnt = 0;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return cnt > 0;
	}
	
	public String getOrgNameByUserNameAndPassword(String userName,String password){
		String orgName = "";
		Cursor cursor = null;
		try {
			String sql = "select ORGNAME from TB_USER where USERNAME= '"
					+ userName
					+ "' and PASSWORD='"+password+"'";
			cursor = getCursorQuery(TB_Const.DB_NAME.DB_BASE, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					orgName = cursor.getString(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			orgName = "";
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return orgName;
	}
 
	public String getDbVersionByDbName(String dbName,String tbName){
		String version = "0";
		Cursor cursor = null;
		try {
			String sql = "select distinct VERSION from "+tbName;
			cursor = getCursorQuery(dbName, sql);
			if(cursor != null){
				if (cursor.moveToFirst()) {
					version = cursor.getString(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			version ="0";
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
		return version;
	}
	
	public boolean resetPassword(String username,String password){
		boolean result = false;
		try {
			String sql = "update tb_user set password = '"+password+"' where username = '"+username+"'";
			result = excuteSQL(TB_Const.DB_NAME.DB_BASE, sql);
			result = true;
		} catch (Exception e) {
			
		}
		
		return result;
	}
	
	
*/}
