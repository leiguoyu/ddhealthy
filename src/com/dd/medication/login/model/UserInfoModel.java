package com.dd.medication.login.model;

import com.dd.medication.base.model.BaseModel;

public class UserInfoModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 * */
	private int memberId;
	/**
	 * 手机号（唯一、登录使用）
	 * */
	private String mobile;
	/**
	 * 用户密码（MD5加密）
	 * */
	private String password;
	/**
	 * 会话ID session ID
	 * */
	private String sessionId;
	/**
	 * 昵称
	 * */
	private String nickName;
	/**
	 * 姓名
	 * */
	private String memberName;

	/**
	 * 性别 GGMK_XB_01 男 GGMK_XB_02 女 GGMK_XB_03 保密
	 * */
	private String sex;
	/**
	 * 年龄
	 * */
	private int age;
	/**
	 * 第三方平台ID
	 * */
	private String openId;
	/**
	 * 第三方平台类型（来源ddinfo）
	 * */
	private String openAccountType;
	/**
	 * 是否有效
	 * */
	private int isActivited;
	/**
	 * 是否删除
	 * */
	private int status;
	/**
	 * 创建人
	 * */
	private String createUser;
	/**
	 * 创建时间
	 * */
	private String createDate;
	/**
	 * 最后一次修改人
	 * */
	private String lmodifyUser;
	/**
	 * 最后一次修改时间
	 * */
	private String lmodifyDate;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenAccountType() {
		return openAccountType;
	}

	public void setOpenAccountType(String openAccountType) {
		this.openAccountType = openAccountType;
	}

	public int getIsActivited() {
		return isActivited;
	}

	public void setIsActivited(int isActivited) {
		this.isActivited = isActivited;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLmodifyUser() {
		return lmodifyUser;
	}

	public void setLmodifyUser(String lmodifyUser) {
		this.lmodifyUser = lmodifyUser;
	}

	public String getLmodifyDate() {
		return lmodifyDate;
	}

	public void setLmodifyDate(String lmodifyDate) {
		this.lmodifyDate = lmodifyDate;
	}

}
