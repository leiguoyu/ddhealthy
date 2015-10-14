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
	 * ��ȡ����ӿ����ĸ����ӿڷ��������ص�
	 * */
	public static String getAction(String jsonString) {
		String str = "";
		try {
			JSONTokener jsonParser = new JSONTokener(jsonString);
			// ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����
			// �����ʱ�Ķ�ȡλ����"name" : �ˣ���ônextValue����"yuanzhifei89"��String��
			JSONObject person = (JSONObject) jsonParser.nextValue();
			// �������ľ���JSON����Ĳ�����
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
			// �쳣�������
			ex.printStackTrace();
			return str;
		}
		return str;
	}

	/**
	 * ��ȡ�˽ӿڵķ���״̬
	 * */
	public static boolean getResult(String jsonString) {
		boolean str = false;
		try {
			JSONTokener jsonParser = new JSONTokener(jsonString);
			// ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����
			// �����ʱ�Ķ�ȡλ����"name" : �ˣ���ônextValue����"yuanzhifei89"��String��
			JSONObject person = (JSONObject) jsonParser.nextValue();
			// �������ľ���JSON����Ĳ�����
			if (person != null) {
				str = person.getBoolean("result");
			}
			return str;
		} catch (JSONException ex) {
			// �쳣�������
			ex.printStackTrace();
		}
		return str;
	}

	/**
	 * ��������Json���ݵ�msg
	 * */
	public static String registerJsonFalse(String strResult) {
		try {
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"���ֻ�����ע��"},"action":"register"}
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
	 * �����������ݵ�Json
	 * */
	public static String[] registerData(String strResult) {
		String[] result = new String[2];
		try {
			// {"result":"true","data":{"sessionId":"559ba98cb28e534ad735c10ca6ce8263","memberId":"1"},"action":"register"}
			// {"result":"false","data":{"id":"20001","msg":"���ֻ�����ע��"},"action":"register"}
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

	// ����������ݵ�Json
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
				s += "ID��" + id + ", ������" + name + ",�Ա�" + gender + "\n";
			}
			// tvJson.setText(s);
		} catch (JSONException e) {
			System.out.println("Jsons parse error !");
			e.printStackTrace();
		}
	}

	/**
	 * ��������ҩƷ��ҩƷ����
	 * */
	public static ArrayList<AllProductModel> SerachProductData(String result) {
		// ===result====={"result":"true","data":{"pageTotal":"48","pageNo":"1","pageSize":"10","resultList":[{"allProductId":"36","productname":"������ð�����","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"42","productname":"������ð��Ƭ","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"},{"allProductId":"47","productname":"��ð���ȿ���","company":"����ͬ���ÿƼ���չ�ɷ����޹�˾��ҩ��","productType":"CP_TYPE_01"},{"allProductId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"142","productname":"��ð��������","company":"ʯҩ����ŷ��ҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"156","productname":"��ð��Ƭ","company":"��������(����)��ҩ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"203","productname":"��ð���Ƭ","company":"������ҩ�����޹�˾","productType":"CP_TYPE_01"},{"allProductId":"206","productname":"��ð�齺��","company":"��������ҽҩ�ɷ����޹�˾","productType":"CP_TYPE_01"},{"allProductId":"207","productname":"С����ð����","company":"��������(��ׯ)ҩҵ���޹�˾","productType":"CP_TYPE_01"},{"allProductId":"212","productname":"�ļ���ð����","company":"���϶���������ҩ���޹�˾","productType":"CP_TYPE_01"}]},"action":"serachProduct"}
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
				// "allProductId":"36","productname":"������ð�����","company":"����˫��ҩҵ��˾","productType":"CP_TYPE_01"
				int allProductId = jsonStr.getInt("allProductId");
				String productname = jsonStr.getString("productname");
				System.out.println("productname===" + productname);
				String company = jsonStr.getString("company");
				String productType = jsonStr.getString("productType"); // CP_TYPE_01  ҩƷ CP_TYPE_02����Ʒ

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
			System.out.println("����ʧ�ܣ�");
		}

		return null;
	}

	/**
	 * ��������ҩƷ��ҳ�� ÿҳ10�� ��ǰ��һҳ
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
			System.out.println("����ʧ�ܣ�");
		}
		return pageTotal;
	}

	/**����ҩƷ �����ķ���   ��ҩƷ  ���Ǳ���Ʒ*/
	public static String getProductType(String result) {
//		===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","specification":"12g/����6g/��","dosage":"������","ingredient":"��޽�㡢�ջ������̡�����Ҷ�����������ػơ��ع�Ƥ����ޱ�����ɡ�ʯ�ࣻ����Ϊ���ǡ�������","functions":"��������Ƚⶾ������С�����ȸ�ð��֢�����ȡ�ͷ��ʹ������̵𤡢�ʺ���ʹ�����м�����֤���ߡ�","theusage":"��ˮ�����һ������һ��6�ˣ�һ��������һ��612�ˣ�����������һ��12-18�ˣ�������ʮ����һ��24�ˣ�һ��2�Ρ�","reactions":"�в���ȷ��","taboo":"�в���ȷ��","note":"1.�����������䡢����ʳ� 2.�����ڷ�ҩ�ڼ�ͬʱ�����̲�����ҩ�� 3.Ӥ��Ӧ��ҽʦָ���·��á� 4.�纮��ð�߲����á� 5.���򲡻�����Ƣ���׸�к��Ӧ��ҽʦָ���·��á� 6.�������³���38��5��Ļ��ߣ�ӦȥҽԺ��� 7.��ҩ3��֢״�޻��⣬ӦȥҽԺ��� 8.�Ա�Ʒ�����߽��ã��������������á� 9.��Ʒ��״�����ı�ʱ��ֹʹ�á� 10.��ͯ�����ڳ��˼໤��ʹ�á� 11.�뽫��Ʒ���ڶ�ͯ���ܽӴ��ĵط��� 12.������ʹ������ҩƷ��ʹ�ñ�Ʒǰ����ѯҽʦ��ҩʦ�� 13.��ˮ���ݣ�����ʻ���״���á�","store":"[02]ҩƷ����������(20������)","validity":"36����","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
		String productType="";
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			productType = jsonObj.getString("productType");//CP_TYPE_01  ҩƷ  //����ƷCP_TYPE_02
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		return productType;
	}
	
	/**����ҩƷ����*/
	public static MedicineDetailModel getMedicineDetail(String result) {
//		===result====={"result":"true","data":{"healthProductDetail":"","medicineDetail":{"medicineDetailId":"103","productname":"С����ð����","company":"���Ķ�άҩҵ���޹�˾","specification":"12g/����6g/��","dosage":"������","ingredient":"��޽�㡢�ջ������̡�����Ҷ�����������ػơ��ع�Ƥ����ޱ�����ɡ�ʯ�ࣻ����Ϊ���ǡ�������","functions":"��������Ƚⶾ������С�����ȸ�ð��֢�����ȡ�ͷ��ʹ������̵𤡢�ʺ���ʹ�����м�����֤���ߡ�",
//		"theusage":"��ˮ�����һ������һ��6�ˣ�һ��������һ��612�ˣ�����������һ��12-18�ˣ�������ʮ����һ��24�ˣ�һ��2�Ρ�","reactions":"�в���ȷ��","taboo":"�в���ȷ��","note":"1.�����������䡢����ʳ� 2.�����ڷ�ҩ�ڼ�ͬʱ�����̲�����ҩ�� 3.Ӥ��Ӧ��ҽʦָ���·��á� 4.�纮��ð�߲����á� 5.���򲡻�����Ƣ���׸�к��Ӧ��ҽʦָ���·��á� 6.�������³���38��5��Ļ��ߣ�ӦȥҽԺ��� 7.��ҩ3��֢״�޻��⣬ӦȥҽԺ��� 8.�Ա�Ʒ�����߽��ã��������������á� 9.��Ʒ��״�����ı�ʱ��ֹʹ�á� 10.��ͯ�����ڳ��˼໤��ʹ�á� 
//		11.�뽫��Ʒ���ڶ�ͯ���ܽӴ��ĵط��� 12.������ʹ������ҩƷ��ʹ�ñ�Ʒǰ����ѯҽʦ��ҩʦ�� 13.��ˮ���ݣ�����ʻ���״���á�","store":"[02]ҩƷ����������(20������)","validity":"36����","indication":"","dosageCategory":"DOSAGE_04","usingTime":""},"productType":"CP_TYPE_01"},"action":"getProductDetail"}
		MedicineDetailModel medicineDetailInfo = new MedicineDetailModel();
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			String healthProductDetail = jsonObj.getString("healthProductDetail");
			String medicineDetail = jsonObj.getString("medicineDetail");
			String productType = jsonObj.getString("productType");//CP_TYPE_01  ҩƷ  //����ƷCP_TYPE_02
			
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
	 * ��������Ʒ����
	 * **/
	public static HealthProductModel getHealthProduct(String result) {
		HealthProductModel healthProductInfo = new HealthProductModel();
		try {
			JSONObject jsonObj = new JSONObject(result).getJSONObject("data");
			String healthProductDetail = jsonObj.getString("healthProductDetail");
			String medicineDetail = jsonObj.getString("medicineDetail");
			String productType = jsonObj.getString("productType");//CP_TYPE_01  ҩƷ  //����ƷCP_TYPE_02
			
			JSONObject jsonObjs = new JSONObject(medicineDetail);
			
			healthProductInfo.setHealthProductDetailId(jsonObjs.getString("healthProductDetailId"));
			healthProductInfo.setProductname(jsonObjs.getString("productname"));//��Ʒ����
			healthProductInfo.setCompany(jsonObjs.getString("company"));//��ҵ����
			healthProductInfo.setDomesticOrImport(jsonObjs.getString("domesticOrImport"));//��������ڣ�ֵ�μ�ddinfo��
			healthProductInfo.setScgdq(jsonObjs.getString("scgdq"));//��������������
			healthProductInfo.setBjgn(jsonObjs.getString("bjgn"));//��������
			healthProductInfo.setGxcfbzxcfhl(jsonObjs.getString("gxcfbzxcfhl"));//��Ч�ɷ�/��־�Գɷݺ���
			healthProductInfo.setZyyl(jsonObjs.getString("zyyl"));//��Ҫԭ��
			healthProductInfo.setSyrq(jsonObjs.getString("syrq"));//������Ⱥ
			healthProductInfo.setBsyrq(jsonObjs.getString("bsyrq"));//��������Ⱥ
			healthProductInfo.setSyffjsyl(jsonObjs.getString("syffjsyl"));//ʳ�÷�����ʳ����
			healthProductInfo.setCpgg(jsonObjs.getString("cpgg"));//��Ʒ���
			healthProductInfo.setBzq(jsonObjs.getString("bzq"));//������
			healthProductInfo.setZcff(jsonObjs.getString("zcff"));//���ط���
			healthProductInfo.setZysx(jsonObjs.getString("zysx"));//ע������
			healthProductInfo.setUsingTime(jsonObjs.getString("usingTime"));//����ʱ��
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		return healthProductInfo;
	
	}



}
