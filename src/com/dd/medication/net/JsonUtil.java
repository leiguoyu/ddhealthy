package com.dd.medication.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.medicine.model.HealthProductModel;
import com.dd.medication.medicine.model.MedicineDetailModel;

public class JsonUtil {

	/**
	 * 获取这个接口是哪个（接口方法）返回的
	 * */
	public static String getAction(String jsonString) {
		String str = "";
		try {
			JSONTokener jsonParser = new JSONTokener(jsonString);
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			JSONObject person = (JSONObject) jsonParser.nextValue();
			// 接下来的就是JSON对象的操作了
			// person.getJSONArray("phone");
			// person.getString("name");
			// person.getInt("age");
			// person.getJSONObject("address");
			// person.getBoolean("married");
			str = person.getString("action");
			if (!"".equals(str)) {
				return str;
			}
		} catch (JSONException ex) {
			// 异常处理代码
			ex.printStackTrace();
			return str;
		}
		return str;
	}

	/**
	 * 获取此接口的返回状态
	 * */
	public static boolean getResult(String jsonString) {
		boolean str = false;
		try {
			JSONTokener jsonParser = new JSONTokener(jsonString);
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			JSONObject person = (JSONObject) jsonParser.nextValue();
			// 接下来的就是JSON对象的操作了
			if (person != null) {
				str = person.getBoolean("result");
			}
			return str;
		} catch (JSONException ex) {
			// 异常处理代码
			ex.printStackTrace();
		}
		return str;
	}

	/**
	 * 解析单个Json数据的msg
	 * */
	public static String registerJsonFalse(String strResult) {
		try {
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"该手机号已注册"},"action":"register"}
			JSONObject jsonObj = new JSONObject(strResult)
					.getJSONObject("data");
			strResult = jsonObj.getString("msg");
		} catch (JSONException e) {
			System.out.println("Json parse error");
			e.printStackTrace();
			return "";
		}
		return strResult;
	}

	/**
	 * 解析单个数据的Json
	 * */
	public static String[] registerData(String strResult) {
		String[] result = new String[2];
		try {
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"该手机号已注册"},"action":"register"}
			JSONObject jsonObj = new JSONObject(strResult)
					.getJSONObject("data");
			String sessionId = jsonObj.getString("sessionId");
			String memberId = jsonObj.getString("memberId");
			result[0] = sessionId;
			result[1] = memberId;

		} catch (JSONException e) {
			System.out.println("Json parse error");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	// 解析多个数据的Json
	private void parseJsonMulti(String strResult) {
		try {
			JSONArray jsonObjs = new JSONObject(strResult)
					.getJSONArray("singers");
			String s = "";
			for (int i = 0; i < jsonObjs.length(); i++) {
				JSONObject jsonObj = ((JSONObject) jsonObjs.opt(i))
						.getJSONObject("singer");
				int id = jsonObj.getInt("id");
				String name = jsonObj.getString("name");
				String gender = jsonObj.getString("gender");
				s += "ID号" + id + ", 姓名：" + name + ",性别：" + gender + "\n";
			}
			// tvJson.setText(s);
		} catch (JSONException e) {
			System.out.println("Jsons parse error !");
			e.printStackTrace();
		}
	}

	/**
	 * 解析搜索药品的药品数据
	 * */
	public static ArrayList<AllProductModel> SerachProductData(String result) {
		// ===result====={"result":"true","data":{"pageTotal":"48","pageNo":"1","pageSize":"10","resultList":[{"allProductId":"36","productname":"复方感冒灵颗粒","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"复方感冒灵片","company":"广西双蚁药业公司","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"感冒清热颗粒","company":"北京同仁堂科技发展股份有限公司制药厂","productType":"CP_TYPE_01"},{"allProductId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"142","productname":"感冒清热软胶囊","company":"石药集团欧意药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"156","productname":"感冒清片","company":"华润三九(郴州)制药有限公司","productType":"CP_TYPE_01"},{"allProductId":"203","productname":"感冒疏风片","company":"昆明中药厂有限公司","productType":"CP_TYPE_01"},{"allProductId":"206","productname":"感冒灵胶囊","company":"华润三九医药股份有限公司","productType":"CP_TYPE_01"},{"allProductId":"207","productname":"小儿感冒颗粒","company":"华润三九(枣庄)药业有限公司","productType":"CP_TYPE_01"},{"allProductId":"212","productname":"四季感冒胶囊","company":"湖南东润联合制药有限公司","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			String pageTotal = jsonObj.getString("pageTotal");
			String pageNo = jsonObj.getString("pageNo");
			String pageSize = jsonObj.getString("pageSize");
			String resultList = jsonObj.getString("resultList");
			System.out.println("resultList====" + resultList);
			ArrayList<AllProductModel> lists = new ArrayList<AllProductModel>();
			JSONArray jsonObjs = new JSONObject("{'resultList':" + resultList
					+ "}").getJSONArray("resultList");
			System.out.println("jsonObjs====" + jsonObjs);
			for (int i = 0; i < jsonObjs.length(); i++) {
				JSONObject jsonStr = ((JSONObject) jsonObjs.opt(i));
				// "allProductId":"36","productname":"复方感冒灵颗粒","company":"广西双蚁药业公司","productType":"CP_TYPE_01"
				int allProductId = jsonStr.getInt("allProductId");
				String productname = jsonStr.getString("productname");
				System.out.println("productname===" + productname);
				String company = jsonStr.getString("company");
				String productType = jsonStr.getString("productType"); // CP_TYPE_01  药品 CP_TYPE_02保健品

				AllProductModel allProductModel = new AllProductModel();
				allProductModel.setAllProductId(allProductId);
				allProductModel.setProductName(productname);
				allProductModel.setCompany(company);
				allProductModel.setProductType(productType);

				lists.add(allProductModel);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("解析失败！");
		}

		return null;
	}

	/**
	 * 解析搜索药品的页数 每页10条 当前第一页
	 * */
	public static String SerachProductPageTotal(String result) {
		String pageTotal = "";
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			pageTotal = jsonObj.getString("pageTotal");
			System.out.println("pageTotal====" + pageTotal);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("解析失败！");
		}
		return pageTotal;
	}

	/**解析药品 所属的分类   是药品  还是保健品*/
	public static String getProductType(String result) {
//		===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","specification":"12g/袋；6g/袋","dosage":"颗粒剂","ingredient":"广藿香、菊花、连翘、大青叶、板蓝根、地黄、地骨皮、白薇、薄荷、石膏；辅料为蔗糖、糊精。","functions":"疏风解表，清热解毒。用于小儿风热感冒，症见发热、头胀痛、咳嗽痰黏、咽喉肿痛；流感见上述证候者。","theusage":"开水冲服。一岁以内一次6克，一岁至三岁一次612克，四岁至七岁一次12-18克，八岁至十二岁一次24克，一日2次。","reactions":"尚不明确。","taboo":"尚不明确。","note":"1.忌辛辣、生冷、油腻食物。 2.不宜在服药期间同时服用滋补性中药。 3.婴儿应在医师指导下服用。 4.风寒感冒者不适用。 5.糖尿病患儿、脾虚易腹泻者应在医师指导下服用。 6.发热体温超过38．5℃的患者，应去医院就诊。 7.服药3天症状无缓解，应去医院就诊。 8.对本品过敏者禁用，过敏体质者慎用。 9.本品性状发生改变时禁止使用。 10.儿童必须在成人监护下使用。 11.请将本品放在儿童不能接触的地方。 12.如正在使用其他药品，使用本品前请咨询医师或药师。 13.开水冲泡，搅拌呈混悬状服用。","store":"[02]药品阴凉贮存区(20℃以下)","validity":"36个月","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
		String productType="";
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			productType = jsonObj.getString("productType");//CP_TYPE_01  药品  //保健品CP_TYPE_02
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		return productType;
	}
	
	/**解析药品详请*/
	public static MedicineDetailModel getMedicineDetail(String result) {
//		===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"小儿感冒颗粒","company":"宁夏多维药业有限公司","specification":"12g/袋；6g/袋","dosage":"颗粒剂","ingredient":"广藿香、菊花、连翘、大青叶、板蓝根、地黄、地骨皮、白薇、薄荷、石膏；辅料为蔗糖、糊精。","functions":"疏风解表，清热解毒。用于小儿风热感冒，症见发热、头胀痛、咳嗽痰黏、咽喉肿痛；流感见上述证候者。",
//		"theusage":"开水冲服。一岁以内一次6克，一岁至三岁一次612克，四岁至七岁一次12-18克，八岁至十二岁一次24克，一日2次。","reactions":"尚不明确。","taboo":"尚不明确。","note":"1.忌辛辣、生冷、油腻食物。 2.不宜在服药期间同时服用滋补性中药。 3.婴儿应在医师指导下服用。 4.风寒感冒者不适用。 5.糖尿病患儿、脾虚易腹泻者应在医师指导下服用。 6.发热体温超过38．5℃的患者，应去医院就诊。 7.服药3天症状无缓解，应去医院就诊。 8.对本品过敏者禁用，过敏体质者慎用。 9.本品性状发生改变时禁止使用。 10.儿童必须在成人监护下使用。 
//		11.请将本品放在儿童不能接触的地方。 12.如正在使用其他药品，使用本品前请咨询医师或药师。 13.开水冲泡，搅拌呈混悬状服用。","store":"[02]药品阴凉贮存区(20℃以下)","validity":"36个月","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
		MedicineDetailModel medicineDetailInfo = new MedicineDetailModel();
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			String healthProductDetail = jsonObj.getString("healthProductDetail");
			String medicineDetail = jsonObj.getString("medicineDetail");
			String productType = jsonObj.getString("productType");//CP_TYPE_01  药品  //保健品CP_TYPE_02
			
			JSONObject jsonObjs = new JSONObject(medicineDetail);
			
			medicineDetailInfo.setMedicineDetailId(jsonObjs.getString("medicineDetailId"));
			medicineDetailInfo.setProductname(jsonObjs.getString("productname"));
			medicineDetailInfo.setCompany(jsonObjs.getString("company"));
			medicineDetailInfo.setSpecification(jsonObjs.getString("specification"));//specification
			medicineDetailInfo.setDosage(jsonObjs.getString("dosage"));//dosage
			medicineDetailInfo.setIngredient(jsonObjs.getString("ingredient"));//ingredient
			medicineDetailInfo.setFunctions(jsonObjs.getString("functions"));//functions
			medicineDetailInfo.setTheusage(jsonObjs.getString("theusage"));//theusage
			medicineDetailInfo.setReactions(jsonObjs.getString("reactions"));//reactions
			medicineDetailInfo.setTaboo(jsonObjs.getString("taboo"));//taboo
			medicineDetailInfo.setNote(jsonObjs.getString("note"));//note
			medicineDetailInfo.setStore(jsonObjs.getString("store"));//store
			medicineDetailInfo.setValidity(jsonObjs.getString("validity"));//validity
			medicineDetailInfo.setIndication(jsonObjs.getString("indication"));//indication
			medicineDetailInfo.setDosageCategory(jsonObjs.getString("dosageCategory"));//dosageCategory
			medicineDetailInfo.setUsingTime(jsonObjs.getString("usingTime"));//usingTime
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		return medicineDetailInfo;
	}

	/***
	 * 解析保健品详情
	 * **/
	public static HealthProductModel getHealthProduct(String result) {
		HealthProductModel healthProductInfo = new HealthProductModel();
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			String healthProductDetail = jsonObj.getString("healthProductDetail");
			String medicineDetail = jsonObj.getString("medicineDetail");
			String productType = jsonObj.getString("productType");//CP_TYPE_01  药品  //保健品CP_TYPE_02
			
			JSONObject jsonObjs = new JSONObject(medicineDetail);
			
			healthProductInfo.setHealthProductDetailId(jsonObjs.getString("healthProductDetailId"));
			healthProductInfo.setProductname(jsonObjs.getString("productname"));//商品名称
			healthProductInfo.setCompany(jsonObjs.getString("company"));//企业名称
			healthProductInfo.setDomesticOrImport(jsonObjs.getString("domesticOrImport"));//国产或进口（值参见ddinfo）
			healthProductInfo.setScgdq(jsonObjs.getString("scgdq"));//生产国（地区）
			healthProductInfo.setBjgn(jsonObjs.getString("bjgn"));//保健功能
			healthProductInfo.setGxcfbzxcfhl(jsonObjs.getString("gxcfbzxcfhl"));//功效成分/标志性成份含量
			healthProductInfo.setZyyl(jsonObjs.getString("zyyl"));//主要原料
			healthProductInfo.setSyrq(jsonObjs.getString("syrq"));//适宜人群
			healthProductInfo.setBsyrq(jsonObjs.getString("bsyrq"));//不适宜人群
			healthProductInfo.setSyffjsyl(jsonObjs.getString("syffjsyl"));//食用方法及食用量
			healthProductInfo.setCpgg(jsonObjs.getString("cpgg"));//产品规格
			healthProductInfo.setBzq(jsonObjs.getString("bzq"));//保质期
			healthProductInfo.setZcff(jsonObjs.getString("zcff"));//贮藏方法
			healthProductInfo.setZysx(jsonObjs.getString("zysx"));//注意事项
			healthProductInfo.setUsingTime(jsonObjs.getString("usingTime"));//服用时机
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		return healthProductInfo;
	
	}



}
