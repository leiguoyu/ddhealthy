package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * 全部产品
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
	 * 产品名称
	 * */
	private String productName;
	/**
	 * 企业名称
	 * */
	private String company;
	/**
	 * 条形码
	 * */
	private String barCode;
	/**
	 * 产品类型（来源ddinfo）
	 * */
	private String productType;
	/**
	 * 关联ID
	 * */
	private int reId;
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
