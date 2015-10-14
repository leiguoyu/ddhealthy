package com.dd.medication.base.model;

/**
 * 扫码记录
 * **/
public class ScanRec extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 * */
	private int scanRecId;
	/**
	 * 会员ID
	 * */
	private int memberId;
	/**
	 * 设备唯一标识
	 * */
	private String deviceUid;
	/**
	 * 对应产品ID
	 * */
	private int allProductId;
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

	public int getScanRecId() {
		return scanRecId;
	}

	public void setScanRecId(int scanRecId) {
		this.scanRecId = scanRecId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public int getAllProductId() {
		return allProductId;
	}

	public void setAllProductId(int allProductId) {
		this.allProductId = allProductId;
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
