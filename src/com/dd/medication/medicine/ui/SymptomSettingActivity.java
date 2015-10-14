package com.dd.medication.medicine.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.ChaildPartsAdapter;
import com.dd.medication.medicine.adapter.SpinnerAdapter;
import com.dd.medication.medicine.dao.MyHealthyDao;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.timeline.ui.TimeLineActivity;
import com.dd.medication.util.DateUtil;
import com.dd.medication.util.time.TimePopupWindow;
import com.dd.medication.util.time.TimePopupWindow.OnTimeSelectListener;
import com.dd.medication.util.time.TimePopupWindow.Type;

public class SymptomSettingActivity extends BaseActivity implements
		OnClickListener {
	private MyHealthyModel myHealthyModel;
	private String body_part, symptom;
	private TextView dateTime;//显示日期时间，默认当前的日期时间
	private TextView bodyPartText,symptomText;
	private EditText symptomExplain;
	private TextView symptom_yyzt,symptom_kyrs,symptom_nyrs,symptom_cxjd,symptom_cxjc,symptom_cxbd,symptom_dyccj,symptom_jjtfx,symptom_jjzfx,symptom_oefz,symptom_jcfz;
    private GridLayout symptomLayout;
    private RadioButton historySymptom,selectedTimeBut;
    private ArrayList<String> symptomSeverity=new ArrayList<String>();//用来保存症状程度
    private TimePopupWindow pwTime;
    private int indexCount=0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom_setting);

		intView();
		
		// 获取从症状记录中获取到的数据
		Intent intent = getIntent();
		myHealthyModel = (MyHealthyModel) intent
				.getSerializableExtra("myHealthyModel");
		body_part = intent.getStringExtra("body_part");
		symptom = intent.getStringExtra("symptom");
        
		intData();

		
        //时间选择器
        pwTime = new TimePopupWindow(this, Type.ALL);
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
            	dateTime.setText(getTime(date));
            }
        });
        
	}

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
        return format.format(date);
    }
    
	private void intData() {
		if(!"".equals(body_part)){
			bodyPartText.setText(body_part);
			bodyPartText.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
			bodyPartText.getPaint().setAntiAlias(true);//抗锯齿
			symptomText.setText(symptom);
			symptomText.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
			symptomText.getPaint().setAntiAlias(true);//抗锯齿
			String dateTiem=DateUtil.getDateTime();
			dateTime.setText(dateTiem);//显示当前的日期时间
		}
		if(null !=myHealthyModel){
			bodyPartText.setText(myHealthyModel.getParts());
			bodyPartText.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
			bodyPartText.getPaint().setAntiAlias(true);//抗锯齿
			symptomText.setText(myHealthyModel.getSymptoms());
			symptomText.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
			symptomText.getPaint().setAntiAlias(true);//抗锯齿
			String dateTiem=myHealthyModel.getLocalCreateDay()+" "+myHealthyModel.getLocalCreateTime();
			dateTime.setText(dateTiem);
			symptomExplain.setText(myHealthyModel.getSymptomsDetail());
            RadioButton newButton = new RadioButton(context);
            newButton.setPadding(15, 0, 15, 0);
            newButton.setTextSize(18);
			 newButton.setButtonDrawable(null);
			 Drawable drawable=getResources().getDrawable(R.drawable.delete_icon);
			 newButton.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            newButton.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
            newButton.getPaint().setAntiAlias(true);//抗锯齿
            newButton.setTextColor(getResources().getColor(R.color.color_41BC6C));
            String symptomsSeverity=myHealthyModel.getSymptomsSeverity();
            newButton.setText(symptomsSeverity);
            indexCount=indexCount+1;
            newButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
	            	if(indexCount>0){
	            		symptomLayout.removeView(v);
	            		indexCount=indexCount-1;
	            	}
                	//这里移除后可以要恢复一下按钮可点击
                	
                }
            });
            symptomLayout.addView(newButton, Math.min(0, symptomLayout.getChildCount()));
		}
		
	}

	private void intView() {
		selectedTimeBut = (RadioButton) this
				.findViewById(R.id.symptom_setting_time);
		historySymptom = (RadioButton) this
				.findViewById(R.id.symptom_setting_history_symptom);
		selectedTimeBut.setOnClickListener(this);
		historySymptom.setOnClickListener(this);
		dateTime=(TextView) this.findViewById(R.id.symptom_setting_date_time_view);
		bodyPartText=(TextView) this.findViewById(R.id.symptom_setting_body_part);
		symptomText=(TextView) this.findViewById(R.id.symptom_setting_symptom);
		symptomExplain=(EditText) this.findViewById(R.id.symptom_setting_explain);
		
		symptom_yyzt=(TextView) this.findViewById(R.id.symptom_setting_yyzt);
		symptom_yyzt.setOnClickListener(this);
		symptom_kyrs=(TextView) this.findViewById(R.id.symptom_setting_kyrs);
		symptom_kyrs.setOnClickListener(this);
		symptom_nyrs=(TextView) this.findViewById(R.id.symptom_setting_nyrs);
		symptom_nyrs.setOnClickListener(this);
		symptom_cxjc=(TextView) this.findViewById(R.id.symptom_setting_cxjc);
		symptom_cxjc.setOnClickListener(this);
		symptom_cxjd=(TextView) this.findViewById(R.id.symptom_setting_cxjd);
		symptom_cxjd.setOnClickListener(this);
		symptom_cxbd=(TextView) this.findViewById(R.id.symptom_setting_cxbd);
		symptom_cxbd.setOnClickListener(this);
		symptom_dyccj=(TextView) this.findViewById(R.id.symptom_setting_dyccj);
		symptom_dyccj.setOnClickListener(this);
		symptom_jjtfx=(TextView) this.findViewById(R.id.symptom_setting_jjtfx);
		symptom_jjtfx.setOnClickListener(this);
		symptom_jjzfx=(TextView) this.findViewById(R.id.symptom_setting_jjzfx);
		symptom_jjzfx.setOnClickListener(this);
		symptom_oefz=(TextView) this.findViewById(R.id.symptom_setting_oefz);
		symptom_oefz.setOnClickListener(this);
		symptom_jcfz=(TextView) this.findViewById(R.id.symptom_setting_jcfz);
		symptom_jcfz.setOnClickListener(this);
		symptomLayout=(GridLayout) this.findViewById(R.id.symptom_setting_add_cd_layout);
		
		RadioButton save = (RadioButton) this
				.findViewById(R.id.symptom_setting_more);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 保存所选择的症状记录
				int count=symptomLayout.getChildCount();
				if(count<=0){
					Toast.makeText(context, "症状严重程度不能为空！", Toast.LENGTH_SHORT).show();
					return;
				}
				String symptomsSeverity="";
				for (int i = 0; i <count-2; i++) {
					symptomsSeverity=((TextView)symptomLayout.getChildAt(2+i)).getText().toString();
				System.out.println("symptomsSeverity===="+symptomsSeverity);
				}
				MyHealthyDao myHealthyDao=new MyHealthyDao();
				MyHealthyModel info=new MyHealthyModel();
				info.setParts(bodyPartText.getText().toString());
				info.setSymptoms(symptomText.getText().toString());
				info.setSymptomsSeverity(symptomsSeverity);
				info.setSymptomsDetail(symptomExplain.getText().toString());
				String dateTiemStr=dateTime.getText().toString();
				String str[]=dateTiemStr.split(" ");
				info.setLocalCreateDay(str[0]);
				info.setLocalCreateTime(str[1]);
//				info.setOperateType(operateType);
				myHealthyDao.addMyHealthy(info);
				
			    //暂时跳转到 tiemLime
				Intent  intent =new  Intent(context, TimeLineActivity.class);
				startActivity(intent);
			}
		});

		TextView back = (TextView) this.findViewById(R.id.symptom_setting_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.symptom_setting_time:
			// 点击选择日期时间
			pwTime.showAtLocation(selectedTimeBut, Gravity.BOTTOM, 0, 0,new Date());
			
			break;
		case R.id.symptom_setting_history_symptom:
			// 点击选择历史病症
			selectSymptomDialog();
			break;
		case R.id.symptom_setting_yyzt:
			//选择隐隐作痛
			addchaildViewInGridLayout("隐隐作痛");
			symptom_yyzt.setClickable(false);
			break;
		case R.id.symptom_setting_kyrs:
			// 选择可以忍受
			addchaildViewInGridLayout("可以忍受");
			symptom_kyrs.setClickable(false);
			break;
		case R.id.symptom_setting_nyrs:
			// 选择难以忍受
			addchaildViewInGridLayout("难以忍受");
			symptom_nyrs.setClickable(false);
			break;
		case R.id.symptom_setting_cxjd:
			// 选择持续较短
			addchaildViewInGridLayout("持续较短");
			symptom_cxjd.setClickable(false);
			break;
		case R.id.symptom_setting_cxjc:
			// 选择持续较长
			addchaildViewInGridLayout("持续较长");
			symptom_cxjc.setClickable(false);
			break;
		case R.id.symptom_setting_cxbd:
			// 选择持续不断
			addchaildViewInGridLayout("持续不断");
			symptom_cxbd.setClickable(false);
			break;
		case R.id.symptom_setting_dyccj:
			//第一次觉察
			addchaildViewInGridLayout("第一次觉察");
			symptom_dyccj.setClickable(false);
			break;
		case R.id.symptom_setting_jjtfx:
			// 近几天发现
			addchaildViewInGridLayout("近几天发现");
			symptom_jjtfx.setClickable(false);
			break;
		case R.id.symptom_setting_jjzfx:
			// 近几周发现
			addchaildViewInGridLayout("近几周发现");
			symptom_jjzfx.setClickable(false);
			break;
		case R.id.symptom_setting_oefz:
			//偶尔发作
			addchaildViewInGridLayout("偶尔发作");
			symptom_oefz.setClickable(false);
			break;
		case R.id.symptom_setting_jcfz:
			// 经常发作
			addchaildViewInGridLayout("经常发作");
			symptom_jcfz.setClickable(false);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		//按钮可以点击
		symptom_yyzt.setClickable(true);
		symptom_kyrs.setClickable(true);
		symptom_nyrs.setClickable(true);
		symptom_cxjd.setClickable(true);
		symptom_cxjc.setClickable(true);
		symptom_cxbd.setClickable(true);
		symptom_dyccj.setClickable(true);
		symptom_jjtfx.setClickable(true);
		symptom_jjzfx.setClickable(true);
		symptom_oefz.setClickable(true);
		symptom_jcfz.setClickable(true);
		super.onResume();
	}
	
	private void addchaildViewInGridLayout(final String string) {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				indexCount=indexCount+1;
				if(indexCount>2){
					Toast.makeText(context, "限定最多两条严重程度", Toast.LENGTH_SHORT).show();
					return;
				}
				RadioButton newButton = new RadioButton(context);
				newButton.setPadding(15, 0, 15, 0);
				 newButton.setTextSize(18);
				 newButton.setButtonDrawable(null);
				 Drawable drawable=getResources().getDrawable(R.drawable.delete_icon);
				 newButton.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
		            newButton.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
		            newButton.getPaint().setAntiAlias(true);//抗锯齿
		        newButton.setTextColor(getResources().getColor(R.color.color_41BC6C));
		        newButton.setText(string);
		        newButton.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	if(indexCount>0){
		            		symptomLayout.removeView(v);
		            		indexCount=indexCount-1;
		            	}
		            	//恢复所有可点击
		        		symptom_yyzt.setClickable(true);
		        		symptom_kyrs.setClickable(true);
		        		symptom_nyrs.setClickable(true);
		        		symptom_cxjd.setClickable(true);
		        		symptom_cxjc.setClickable(true);
		        		symptom_cxbd.setClickable(true);
		        		symptom_dyccj.setClickable(true);
		        		symptom_jjtfx.setClickable(true);
		        		symptom_jjzfx.setClickable(true);
		        		symptom_oefz.setClickable(true);
		        		symptom_jcfz.setClickable(true);
		            }
		        });
		        symptomLayout.addView(newButton, Math.min(0, symptomLayout.getChildCount()));
			}
		}, 10);

	}

	
	/**
	 * 用药提醒系统弹出框
	 * */
	private void selectSymptomDialog() {
		// 获取LayoutInflater实例
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
		// 该方法返回的是一个View的对象，是布局中的根
		View layout = inflater.inflate(R.layout.select_history_symptom, null);
		// 下面我们要考虑了，我怎样将我的layout加入到PopupWindow中呢？？？很简单
		final PopupWindow menuWindow = new PopupWindow(layout,
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); // 后两个参数是width和height
		// 设置如下四条信息，当点击其他区域使其隐藏，要在show之前配置
		menuWindow.setFocusable(true);
		menuWindow.setOutsideTouchable(true);
		menuWindow.update();
		menuWindow.setBackgroundDrawable(new BitmapDrawable());
//		menuWindow.showAsDropDown(layout); //设置弹出效果
//		menuWindow.showAsDropDown(layout, 0, layout.getHeight());
		menuWindow.showAtLocation(layout,Gravity.BOTTOM,0,0);
		// 如何获取我们main中的控件呢？也很简单
		ListView historySymptomList = (ListView) layout.findViewById(R.id.select_history_symptom_list);
		ArrayList<String> historySymptoms =new MyHealthyDao().getHistorySymptom();
		// 给List装载数据
		ChaildPartsAdapter adapter = new ChaildPartsAdapter(context, historySymptoms);
		historySymptomList.setAdapter(adapter);
		historySymptomList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//将获取到的数据添加到相应位置
				
				menuWindow.dismiss();
			}
		} );

	}
	
}
