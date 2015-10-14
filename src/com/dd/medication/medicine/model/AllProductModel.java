package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * ȫ����Ʒ
 * */
public class AllProductModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 * */
	private int allProductId;
	/**
	 * ��Ʒ����
	 * */
	private String productName;
	/**
	 * ��ҵ����
	 * */
	private String company;
	/**
	 * ������
	 * */
	private String barCode;
	/**
	 * ��Ʒ���ͣ���Դddinfo��
	 * */
	private String productType;
	/**
	 * ����ID
	 * */
	private int reId;
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

	public int getAllProductId() {
		return allProductId;
	}

	public void setAllProductId(int allProductId) {
		this.allProductId = allProductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getReId() {
		return reId;
	}

	public void setReId(int reId) {
		this.reId = reId;
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
