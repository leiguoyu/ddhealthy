package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class HealthProductModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 * */
	private String healthProductDetailId;
	/**
	 * 获取途径。  0:扫码获取的药品详情            1：手动搜索获取到的药品详情
	 * */
	private int type;
	/**
	 * :商品名称
	 * */
	private String productname;
	/**
	 * 企业名称
	 * */
	private String company;
	/**
	 * :国产或进口
	 * */
	private String domesticOrImport;
	/**
	 * :生产国（地区）
	 * */
	private String scgdq;
	/**
	 * :保健功能
	 * */
	private String bjgn;
	/**
	 * :功效成分/标志性成份含量
	 * */
	private String gxcfbzxcfhl;
	/**
	 * :主要原料
	 * */
	private String zyyl;
	/**
	 * :适宜人群
	 * */
	private String syrq;
	/**
	 * :不适宜人群
	 * */
	private String bsyrq;
	/**
	 * :食用方法及食用量
	 * */
	private String syffjsyl;
	/**
	 * :产品规格
	 * */
	private String cpgg;
	/**
	 * :保质期
	 * */
	private String bzq;
	/**
	 * :贮藏方法
	 * */
	private String zcff;
	/**
	 * :注意事项
	 * */
	private String zysx;
	/**
	 * :服用时机
	 * */
	private String usingTime;
	/**
	 * 条形码
	 * */
	private String barCode;
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
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public String getHealthProductDetailId() {
		return healthProductDetailId;
	}

	public void setHealthProductDetailId(String healthProductDetailId) {
		this.healthProductDetailId = healthProductDetailId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDomesticOrImport() {
		return domesticOrImport;
	}

	public void setDomesticOrImport(String domesticOrImport) {
		this.domesticOrImport = domesticOrImport;
	}

	public String getScgdq() {
		return scgdq;
	}

	public void setScgdq(String scgdq) {
		this.scgdq = scgdq;
	}

	public String getBjgn() {
		return bjgn;
	}

	public void setBjgn(String bjgn) {
		this.bjgn = bjgn;
	}

	public String getGxcfbzxcfhl() {
		return gxcfbzxcfhl;
	}

	public void setGxcfbzxcfhl(String gxcfbzxcfhl) {
		this.gxcfbzxcfhl = gxcfbzxcfhl;
	}

	public String getZyyl() {
		return zyyl;
	}

	public void setZyyl(String zyyl) {
		this.zyyl = zyyl;
	}

	public String getSyrq() {
		return syrq;
	}

	public void setSyrq(String syrq) {
		this.syrq = syrq;
	}

	public String getBsyrq() {
		return bsyrq;
	}

	public void setBsyrq(String bsyrq) {
		this.bsyrq = bsyrq;
	}

	public String getSyffjsyl() {
		return syffjsyl;
	}

	public void setSyffjsyl(String syffjsyl) {
		this.syffjsyl = syffjsyl;
	}

	public String getCpgg() {
		return cpgg;
	}

	public void setCpgg(String cpgg) {
		this.cpgg = cpgg;
	}

	public String getBzq() {
		return bzq;
	}

	public void setBzq(String bzq) {
		this.bzq = bzq;
	}

	public String getZcff() {
		return zcff;
	}

	public void setZcff(String zcff) {
		this.zcff = zcff;
	}

	public String getZysx() {
		return zysx;
	}

	public void setZysx(String zysx) {
		this.zysx = zysx;
	}

	public String getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(String usingTime) {
		this.usingTime = usingTime;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
