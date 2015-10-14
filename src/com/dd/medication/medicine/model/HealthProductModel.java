package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class HealthProductModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ����ID
	 * */
	private String healthProductDetailId;
	/**
	 * ��ȡ;����  0:ɨ���ȡ��ҩƷ����            1���ֶ�������ȡ����ҩƷ����
	 * */
	private int type;
	/**
	 * :��Ʒ����
	 * */
	private String productname;
	/**
	 * ��ҵ����
	 * */
	private String company;
	/**
	 * :���������
	 * */
	private String domesticOrImport;
	/**
	 * :��������������
	 * */
	private String scgdq;
	/**
	 * :��������
	 * */
	private String bjgn;
	/**
	 * :��Ч�ɷ�/��־�Գɷݺ���
	 * */
	private String gxcfbzxcfhl;
	/**
	 * :��Ҫԭ��
	 * */
	private String zyyl;
	/**
	 * :������Ⱥ
	 * */
	private String syrq;
	/**
	 * :��������Ⱥ
	 * */
	private String bsyrq;
	/**
	 * :ʳ�÷�����ʳ����
	 * */
	private String syffjsyl;
	/**
	 * :��Ʒ���
	 * */
	private String cpgg;
	/**
	 * :������
	 * */
	private String bzq;
	/**
	 * :���ط���
	 * */
	private String zcff;
	/**
	 * :ע������
	 * */
	private String zysx;
	/**
	 * :����ʱ��
	 * */
	private String usingTime;
	/**
	 * ������
	 * */
	private String barCode;
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
