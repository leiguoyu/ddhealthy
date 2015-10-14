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
	 * �ֻ��ţ�Ψһ����¼ʹ�ã�
	 * */
	private String mobile;
	/**
	 * �û����루MD5���ܣ�
	 * */
	private String password;
	/**
	 * �ỰID session ID
	 * */
	private String sessionId;
	/**
	 * �ǳ�
	 * */
	private String nickName;
	/**
	 * ����
	 * */
	private String memberName;

	/**
	 * �Ա� GGMK_XB_01 �� GGMK_XB_02 Ů GGMK_XB_03 ����
	 * */
	private String sex;
	/**
	 * ����
	 * */
	private int age;
	/**
	 * ������ƽ̨ID
	 * */
	private String openId;
	/**
	 * ������ƽ̨���ͣ���Դddinfo��
	 * */
	private String openAccountType;
	/**
	 * �Ƿ���Ч
	 * */
	private int isActivited;
	/**
	 * �Ƿ�ɾ��
	 * */
	private int status;
	/**
	 * ������
	 * */
	private String createUser;
	/**
	 * ����ʱ��
	 * */
	private String createDate;
	/**
	 * ���һ���޸���
	 * */
	private String lmodifyUser;
	/**
	 * ���һ���޸�ʱ��
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
