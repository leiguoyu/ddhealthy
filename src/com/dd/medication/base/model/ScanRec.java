package com.dd.medication.base.model;

/**
 * ɨ���¼
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
	 * ��ԱID
	 * */
	private int memberId;
	/**
	 * �豸Ψһ��ʶ
	 * */
	private String deviceUid;
	/**
	 * ��Ӧ��ƷID
	 * */
	private int allProductId;
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
