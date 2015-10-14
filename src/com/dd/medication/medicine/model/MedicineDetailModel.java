package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class MedicineDetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ����ID
	 * */
	private String medicineDetailId;
	/**
	 * ��ȡ;����  0:ɨ���ȡ��ҩƷ����            1���ֶ�������ȡ����ҩƷ����
	 * */
	private int type; 
	/**
	 * ��Ʒ����
	 * **/
	private String productname;
	/**
	 * ��ҵ����
	 * **/
	private String company;
	/**
	 * ���
	 * */
	private String specification;
	/**
	 * ������
	 * */
	private String barCode;
	/**
	 * ����
	 * */
	private String dosage;
	/**
	 * �ɷ�
	 * */
	private String ingredient;
	/**
	 * ��������
	 * */
	private String functions;
	/**
	 * �÷�����
	 * */
	private String theusage;
	/**
	 * ������Ӧ
	 * */
	private String reactions;
	/**
	 * ����
	 * */
	private String taboo;
	/**
	 * ע������
	 * */
	private String note;
	/**
	 * ����
	 * */
	private String store;
	/**
	 * ��Ч��
	 * */
	private String validity;
	/**
	 * ��Ӧ֢
	 * */
	private String indication;
	/**
	 * ������𣨹����ļ��ͣ�
	 * */
	private String dosageCategory;
	/**
	 * ����ʱ��
	 * */
	private String usingTime;
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

	public String getMedicineDetailId() {
		return medicineDetailId;
	}

	public void setMedicineDetailId(String medicineDetailId) {
		this.medicineDetailId = medicineDetailId;
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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getFunctions() {
		return functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public String getTheusage() {
		return theusage;
	}

	public void setTheusage(String theusage) {
		this.theusage = theusage;
	}

	public String getReactions() {
		return reactions;
	}

	public void setReactions(String reactions) {
		this.reactions = reactions;
	}

	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getDosageCategory() {
		return dosageCategory;
	}

	public void setDosageCategory(String dosageCategory) {
		this.dosageCategory = dosageCategory;
	}

	public String getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(String usingTime) {
		this.usingTime = usingTime;
	}

}
