package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class MedicineDetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 * */
	private String medicineDetailId;
	/**
	 * 获取途径。  0:扫码获取的药品详情            1：手动搜索获取到的药品详情
	 * */
	private int type; 
	/**
	 * 商品名称
	 * **/
	private String productname;
	/**
	 * 企业名称
	 * **/
	private String company;
	/**
	 * 规格
	 * */
	private String specification;
	/**
	 * 条形码
	 * */
	private String barCode;
	/**
	 * 剂型
	 * */
	private String dosage;
	/**
	 * 成份
	 * */
	private String ingredient;
	/**
	 * 功能主治
	 * */
	private String functions;
	/**
	 * 用法用量
	 * */
	private String theusage;
	/**
	 * 不良反应
	 * */
	private String reactions;
	/**
	 * 禁忌
	 * */
	private String taboo;
	/**
	 * 注意事项
	 * */
	private String note;
	/**
	 * 贮藏
	 * */
	private String store;
	/**
	 * 有效期
	 * */
	private String validity;
	/**
	 * 适应症
	 * */
	private String indication;
	/**
	 * 剂型类别（归类后的剂型）
	 * */
	private String dosageCategory;
	/**
	 * 服用时机
	 * */
	private String usingTime;
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
