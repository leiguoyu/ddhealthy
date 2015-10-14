package com.dd.medication.login.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.login.model.UserInfoModel;
import com.dd.medication.util.Const;

public class UserInfoDao extends BaseDao{

	/**
	 * 插入一条数据
	 * */
	public boolean addUser(UserInfoModel userInfo){
			ContentValues cv = new ContentValues();
			cv.put("memberId", userInfo.getMemberId());//memberId
			cv.put("mobile", userInfo.getMobile());//手机号（唯一、登录使用）
			cv.put("password", userInfo.getPassword());//用户密码（MD5加密）
			cv.put("sessionId", userInfo.getSessionId());//会话ID session ID
			cv.put("nickName", userInfo.getNickName());//昵称
			cv.put("memberName", userInfo.getMemberName());// 姓名
			cv.put("sex", userInfo.getSex());//性别 GGMK_XB_01 男 GGMK_XB_02 女 GGMK_XB_03 保密
			cv.put("age", userInfo.getAge());//年龄
			cv.put("openId", userInfo.getOpenId());//第三方平台ID
			cv.put("openAccountType", userInfo.getOpenAccountType());//第三方平台类型（来源ddinfo）
			cv.put("isActivited", userInfo.getIsActivited());//是否有效
			cv.put("status", userInfo.getStatus());//是否删除
			cv.put("createUser", userInfo.getCreateUser());//创建人
			cv.put("createDate", userInfo.getCreateDate());//创建时间
			cv.put("lmodifyUser", userInfo.getLmodifyUser());// 最后一次修改人
			cv.put("lmodifyDate", userInfo.getLmodifyDate());//最后一次修改时间
			
			return insert(Const.DB_NAME,Const.TB_NAME_USERINFO,cv);
	}
	
	
	/**
	 * 更新一条数据
	 * */
	public boolean updateUser(UserInfoModel userInfo,String whereClause, String[] whereArgs){
		ContentValues cv = new ContentValues();
		cv.put("memberId", userInfo.getMemberId());//memberId
		cv.put("mobile", userInfo.getMobile());//手机号（唯一、登录使用）
		cv.put("password", userInfo.getPassword());//用户密码（MD5加密）
		cv.put("sessionId", userInfo.getSessionId());//会话ID session ID
		cv.put("nickName", userInfo.getNickName());//昵称
		cv.put("memberName", userInfo.getMemberName());// 姓名
		cv.put("sex", userInfo.getSex());//性别 GGMK_XB_01 男 GGMK_XB_02 女 GGMK_XB_03 保密
		cv.put("age", userInfo.getAge());//年龄
		cv.put("openId", userInfo.getOpenId());//第三方平台ID
		cv.put("openAccountType", userInfo.getOpenAccountType());//第三方平台类型（来源ddinfo）
		cv.put("isActivited", userInfo.getIsActivited());//是否有效
		cv.put("status", userInfo.getStatus());//是否删除
		cv.put("createUser", userInfo.getCreateUser());//创建人
		cv.put("createDate", userInfo.getCreateDate());//创建时间
		cv.put("lmodifyUser", userInfo.getLmodifyUser());// 最后一次修改人
		cv.put("lmodifyDate", userInfo.getLmodifyDate());//最后一次修改时间
		
		return update(Const.DB_NAME,Const.TB_NAME_USERINFO,cv, whereClause, whereArgs);
       }
	
	/**
	 * 获取全部用户信息
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
					info.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));//手机号（唯一、登录使用）
					info.setPassword(cursor.getString(cursor.getColumnIndex("password")));//用户密码（MD5加密）
					info.setSessionId(cursor.getString(cursor.getColumnIndex("sessionId")));//会话ID session ID
					info.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));//昵称
					info.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));// 姓名
					info.setSex(cursor.getString(cursor.getColumnIndex("sex")));//性别 GGMK_XB_01 男 GGMK_XB_02 女 GGMK_XB_03 保密
					info.setAge(cursor.getInt(cursor.getColumnIndex("age")));//年龄
					info.setOpenId(cursor.getString(cursor.getColumnIndex("openId")));//第三方平台ID
					info.setOpenAccountType(cursor.getString(cursor.getColumnIndex("openAccountType")));//第三方平台类型（来源ddinfo）
					info.setIsActivited(cursor.getInt(cursor.getColumnIndex("isActivited")));//是否有效
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
					info.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));//手机号（唯一、登录使用）
					info.setPassword(cursor.getString(cursor.getColumnIndex("password")));//用户密码（MD5加密）
					info.setSessionId(cursor.getString(cursor.getColumnIndex("sessionId")));//会话ID session ID
					info.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));//昵称
					info.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));// 姓名
					info.setSex(cursor.getString(cursor.getColumnIndex("sex")));//性别 GGMK_XB_01 男 GGMK_XB_02 女 GGMK_XB_03 保密
					info.setAge(cursor.getInt(cursor.getColumnIndex("age")));//年龄
					info.setOpenId(cursor.getString(cursor.getColumnIndex("openId")));//第三方平台ID
					info.setOpenAccountType(cursor.getString(cursor.getColumnIndex("openAccountType")));//第三方平台类型（来源ddinfo）
					info.setIsActivited(cursor.getInt(cursor.getColumnIndex("isActivited")));//是否有效
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
		return delete(Const.DB_NAME,Const.TB_NAME_USERINFO, whereClause, whereArgs);
	}
	
	
	/**
	 * 根据电话号码所有用户信息
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
