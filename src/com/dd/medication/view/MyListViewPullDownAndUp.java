package com.dd.medication.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dd.medication.R;

public class MyListViewPullDownAndUp extends ListView{

		int firstVisibleItemIndex;//��Ļ��ʾ�ĵ�һ��item������ֵ
		int lastVisibleItemIndex;//��Ļ�ܼ������һ��item������ֵ
		private View header;
		private ImageView headerArrow;
		private ProgressBar headerProgressBar;
		private TextView headerTitle;
		private TextView headerLastUpdated;
		 private View footer;
		 private ImageView footerArrow;
	private ProgressBar footerProgressBar;
		private TextView footerTitle;
		private TextView footerLastUpdated;
		
		private int headerWidth;
		private int headerHeight;
		
		
		private Animation animation;
		private Animation reverseAnimation;
		  
		private static final int PULL_TO_REFRESH=0;
		private static final int RELEASE_TO_REFERESH=1;
		private static final int REFERESHING=2;
		private static final int DONE=3;
		  private static final float RATIO = 3;
		  private static boolean isBack = false;
		private boolean refereshEnable;//�Ƿ���Խ���ˢ��
		private int state;//��ǰˢ��״̬
		
		boolean isRecorded;
		float startY;
		float firstTempY = 0;
		float secondTempY = 0; 
		RefreshListener rListener;
		
		
		int pulltype;
		
		public MyListViewPullDownAndUp(Context context, AttributeSet attrs) {
		      super(context, attrs); 
		      init(context); 
		  } 
		  public MyListViewPullDownAndUp(Context context) {
		      super(context);
		      init(context);
		  } 
		  /**
		 * ��ʼ��listview
		 * @param context
		 */
		  private void init(Context context) {
		      
		      animation=new RotateAnimation(-180,0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		      animation.setDuration(150);
		      animation.setFillAfter(true);
		      animation.setInterpolator(new LinearInterpolator());
		      
		      reverseAnimation=new RotateAnimation(0,-180, RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		      reverseAnimation.setDuration(150);
		      reverseAnimation.setFillAfter(true);
		      reverseAnimation.setInterpolator(new LinearInterpolator());
		      
		      LayoutInflater inflater=LayoutInflater.from(context);
		      header=inflater.inflate(R.layout.header, null); 
		      headerArrow=(ImageView) header.findViewById(R.id.arrow);
		      headerProgressBar=(ProgressBar) header.findViewById(R.id.progerssbar);
		      headerTitle=(TextView) header.findViewById(R.id.title);
		      headerLastUpdated=(TextView) header.findViewById(R.id.updated);
		      headerArrow.setMinimumWidth(70);
		      headerArrow.setMaxHeight(50);
		      
		      footer=inflater.inflate(R.layout.header, null);
		      footerArrow=(ImageView) footer.findViewById(R.id.arrow);
		      footerArrow.startAnimation(reverseAnimation);//�Ѽ�ͷ����ת����
		      footerProgressBar=(ProgressBar) footer.findViewById(R.id.progerssbar);
		      footerTitle=(TextView) footer.findViewById(R.id.title);
		      footerLastUpdated=(TextView) footer.findViewById(R.id.updated);
		      footerTitle.setText("����ˢ��");
		      footerLastUpdated.setText("����ˢ��");
		      footerArrow.setMinimumWidth(70);
		      footerArrow.setMaxHeight(50);
		      
		      measureView(header);
		      
		      headerWidth=header.getMeasuredWidth();
		      headerHeight=header.getMeasuredHeight();
		      
		      header.setPadding(0,-1*headerHeight,0,0);//���� ������ϱ߾�ľ���
		      header.invalidate();//�ؼ��ػ�
		      
		      footer.setPadding(0,-1*headerHeight,0,0);//����������ϱ߾�ľ���
		      footer.invalidate();//�ؼ��ػ�
		      
		      addHeaderView(header);
		      addFooterView(footer);
		            
		      
		      
		      
		      state=DONE;
		      refereshEnable=false;
		  }
		  private void measureView(View v) {
		      ViewGroup.LayoutParams lp=v.getLayoutParams();
		      if(lp==null){
		          lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		      }
		      int measureWidth=ViewGroup.getChildMeasureSpec(0,0,lp.width);
		      int measureHeight;
		      if(lp.height>0){
		          measureHeight=MeasureSpec.makeMeasureSpec(lp.height,MeasureSpec.EXACTLY);
		      }else {
		          measureHeight=MeasureSpec.makeMeasureSpec(lp.height,MeasureSpec.UNSPECIFIED);
		      }
		      v.measure(measureWidth, measureHeight);
		  } 
		
		  public interface RefreshListener{
		      public void pullDownRefresh();
		      public void pullUpRefresh();
		  }
		  public void setRefreshListener(RefreshListener l){
		      rListener=l;
		      refereshEnable=true;
		  }
		  /**
		 * ��������ˢ����ɺ�����
		 */
		  public void onPulldownRefreshComplete(){
		      state=DONE;
		      onHeaderStateChange();
		      SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		      headerLastUpdated.setText("���ˢ��ʱ�䣺"+sdf.format(new Date()));
		  }
		  /**
		 * ��������ˢ����ɺ�����
		 */
		  public void onPullupRefreshComplete(){
		      state=DONE;
		      onFooterStateChange();
		      SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		      footerLastUpdated.setText("���ˢ��ʱ�䣺"+sdf.format(new Date()));
		  }
		  
		  /**
		 * �������̨
		 * �������е������¼����ɴ�����
		 */
		  @Override
		  public boolean onTouchEvent(MotionEvent ev) { 
		      lastVisibleItemIndex=getLastVisiblePosition()-1;//��Ϊ����һβ��ͼ����������Ҫ��һ
		      int totalCounts=getCount()-1;//��Ϊ��listview����һͷһβ����ͼ��������Ҫ���� 
		      if(refereshEnable){
		          
		          switch (ev.getAction()) {
		          case MotionEvent.ACTION_DOWN: 
		              
		              firstTempY=ev.getY(); 
		              isRecorded=false; 
		              if(getFirstVisiblePosition()==0){
		                  if(!isRecorded){
		                      startY=ev.getY();
		                      Log.i("info", "touch_dwstartY=ev.getY();");
		                      isRecorded=true;
		                  }
		                  Log.i("info", "touch_downgetFirstVisiblePosition()==0"); 
		              }
		              break; 
		          case MotionEvent.ACTION_MOVE:
		              Log.i("info", "touch_mv************************************************");
		              Log.i("info", "firstTempY="+firstTempY+",,,secondTempY="+secondTempY+",,,startY="+startY);
		              if (getFirstVisiblePosition() == 0) { 
		                  Log.i("info", "touch_mv-------------����ˢ�¿�ʼִ��------------");
		                  Log.i("info", "touch_mv ����ˢ�� tempY=ev.getY()=" + secondTempY);
		                  firstTempY=secondTempY;
		              secondTempY=ev.getY();
		                  if (!isRecorded) {
		                      startY = secondTempY;
		                      Log.i("info", "touch_mvstartY=tempY;");
		                      isRecorded = true;
		                  }
		                  if (state != REFERESHING) {
		                      Log.i("info", "touch_mv ����ˢ�� state != REFERESHING");
		                      if (state == DONE) {
		                          Log.i("info", "touch_mvstate == DONE");
		                          if (secondTempY - startY > 0) {
		                              // ˢ����� /��ʼ״̬--�� ���� ����ˢ��
		                              state = PULL_TO_REFRESH; 
		                              onHeaderStateChange();
		                          }
		                      }
		                      if (state == PULL_TO_REFRESH) {
		                          Log.i("info", "touch_mvstate == PULL_TO_REFRESH");
		                          if ((secondTempY - startY) / RATIO > headerHeight&& secondTempY-firstTempY > 3) {
		                              // ����ˢ�� --�� �ɿ�ˢ��
		                              state = RELEASE_TO_REFERESH;
		                              onHeaderStateChange();
		                          } else if (secondTempY - startY <= -5) {
		                              // ����ˢ�� --�� �ص� ˢ�����
		                              state = DONE;
		                              onHeaderStateChange();
		                          }
		                      }
		                      if (state == RELEASE_TO_REFERESH) {
		                          Log.i("info","touch_mvstate == RELEASE_TO_REFERESHheaderHeight="+ headerHeight);
		                          Log.i("info","touch_mvstate == RELEASE_TO_REFERESHtempY="+ secondTempY + ",,,firstTempY="+ firstTempY);
		                          if (firstTempY-secondTempY>5) {
		                              Log.i("info","*touch_mv(tempY - startY) / RATIO < headerHeight && tempY - startY > 0");
		                              // �ɿ�ˢ�� --���ص�����ˢ��
		                              state = PULL_TO_REFRESH;
		                              isBack = true;// ���ɿ�ˢ�� �ص�������ˢ��
		                              onHeaderStateChange();
		                          } else if (secondTempY - startY <= -5) {
		                              // �ɿ�ˢ�� --�� �ص� ˢ�����
		                              state = DONE; 
		                              onHeaderStateChange();
		                          }
		                      }
		                      
		                      Log.i("info", "touch_mvtempY =" + secondTempY    + ",,,startY=" + startY);
		                      if (state == PULL_TO_REFRESH|| state == RELEASE_TO_REFERESH) {
		                          header.setPadding(0, (int) ((secondTempY - startY)/ RATIO - headerHeight), 0, 0);
		                      }
		                  } else {
		                      Log.i("info", "touch_mv ����ˢ�� state == REFERESHING");
		                  }
		                  Log.i("info", "touch_mv-------------����ˢ��ִ�����------------");
		              }
		              if(getLastVisiblePosition()==getCount()-2||getLastVisiblePosition()==getCount()-1){
		                  Log.i("info", "touch_mv-------------����ˢ�¿�ʼִ��------------"); 
		                  firstTempY=secondTempY;
		                  secondTempY=ev.getY();
		                  Log.i("info", "touch_mv ����ˢ�� tempY=ev.getY()=" + secondTempY);
		                  if (!isRecorded) {
		                      startY = secondTempY;
		                      Log.i("info", "touch_mvstartY=tempY;");
		                      isRecorded = true;
		                  }
		                  
		                  if(state!=REFERESHING){//��������ˢ��״̬
		                      Log.i("info", "touch_mv ����ˢ�� state != REFERESHING");
		                      if (state == DONE) {
		                          Log.i("info", "touch_mvstate == DONE");
		                          if (startY - secondTempY> 0) {
		                              // ˢ�����/��ʼ״̬ --�� ���� ����ˢ��
		                              state = PULL_TO_REFRESH; 
		                              onFooterStateChange();
		                          }
		                      }
		                      if (state == PULL_TO_REFRESH) {
		                          Log.i("info", "touch_mvstate == PULL_TO_REFRESH");
		                          if ((startY-secondTempY) / RATIO > headerHeight    && firstTempY-secondTempY>=9) {
		                              // ����ˢ�� --�� �ɿ�ˢ��
		                              state = RELEASE_TO_REFERESH;
		                              onFooterStateChange();
		                          } else if (startY-secondTempY <= 0) {
		                              // ����ˢ�� --�� �ص� ˢ�����
		                              state = DONE;
		                              onFooterStateChange();
		                          }
		                      }
		                      if (state == RELEASE_TO_REFERESH) {
		                          Log.i("info","touch_mvstate == RELEASE_TO_REFERESHheaderHeight="+ headerHeight);
		                          Log.i("info","touch_mvstate == RELEASE_TO_REFERESHtempY="+ secondTempY + ",,,firstTempY="+ firstTempY);
		                          if(firstTempY-secondTempY<-5){
		                              Log.i("info","*touch_mv footer.getPaddingBottom()="+footer.getPaddingBottom()+",,,headerHeight="+headerHeight);
		                              state = PULL_TO_REFRESH;
		                              isBack = true;// ���ɿ�ˢ�� �ص�������ˢ��
		                              onFooterStateChange();
		                          } else if (secondTempY-startY >= 0) {
		                              // �ɿ�ˢ�� --�� �ص� ˢ�����
		                              state = DONE; 
		                              onFooterStateChange();
		                          }
		                      }
		                      if((state==PULL_TO_REFRESH||state==RELEASE_TO_REFERESH)&&secondTempY<startY){
		                          Log.i("info","����β��ͼ�ڱ߾�");
		                          footer.setPadding(0, 0, 0, (int) ((startY-secondTempY)/RATIO-headerHeight));
		                      }
		                  }else {
		                      Log.i("info", "touch_mv ����ˢ�� state == REFERESHING");
		                  }
		                  Log.i("info", "touch_mv-------------����ˢ��ִ�����------------");
		              }
		              Log.i("info", "touch_mv************************************************");
		              break;
		              
		          case MotionEvent.ACTION_UP: 
		              System.out.println("state="+state);
		              if(state != REFERESHING){ 
		                  
		                  if(state == PULL_TO_REFRESH){
		                      Log.i("info","up --------state == PULL_TO_REFRESH");
		                      state = DONE;
		                      if(getFirstVisiblePosition()==0)//����
		                       onHeaderStateChange();
		                      if(getLastVisiblePosition()==getCount()-1||getLastVisiblePosition()==getCount()-2)//����
		                          onFooterStateChange();
		                  }
		                  
		                  if(state == RELEASE_TO_REFERESH){
		                      Log.i("info","up --------state == RELEASE_TO_REFERESH");
		                      state = REFERESHING; 
		                      if(getFirstVisiblePosition()==0){
		                          //����
		                          onHeaderStateChange(); 
		                          onPullDownRefresh();//ˢ�µõ�����������
		                      }
		                      if(getLastVisiblePosition()==getCount()-1||getLastVisiblePosition()==getCount()-2){
		                          //����
		                          onFooterStateChange();
		                          onPullUpRefresh();//ˢ�µõ�����������
		                      }
		                  }
		              }
		              break;    
		          }
		      }
		      return super.onTouchEvent(ev);
		  }
		  /**
		 * ����β��ͼ��ʾ״̬
		 */
		  private void onHeaderStateChange() {
		      switch (state) {
		      case PULL_TO_REFRESH: 
		          headerProgressBar.setVisibility(View.GONE);
		          headerArrow.setVisibility(View.VISIBLE);
		          headerTitle.setVisibility(View.VISIBLE);
		          headerLastUpdated.setVisibility(View.VISIBLE);
		          
		          headerTitle.setText("����ˢ��");
		          headerArrow.clearAnimation();
		          if(isBack){
		              headerArrow.startAnimation(animation);
		              isBack=false;
		          }
		          break;
		
		      case RELEASE_TO_REFERESH:
		          headerProgressBar.setVisibility(View.GONE);
		          headerArrow.setVisibility(View.VISIBLE);
		          headerTitle.setVisibility(View.VISIBLE);
		          headerLastUpdated.setVisibility(View.VISIBLE);
		          
		          headerTitle.setText(" �ɿ�ˢ��");
		          headerArrow.clearAnimation();
		          headerArrow.startAnimation(reverseAnimation);
		          break; 
		      
		      case REFERESHING:
		          headerProgressBar.setVisibility(View.VISIBLE);
		          headerArrow.setVisibility(View.GONE); 
		          headerTitle.setVisibility(View.VISIBLE);
		          headerLastUpdated.setVisibility(View.VISIBLE);
		          
		          headerTitle.setText("����ˢ��");
		          headerArrow.clearAnimation();
		          
		          header.setPadding(0, 0, 0,0);
		      break;
		      case DONE:
		          headerProgressBar.setVisibility(View.GONE);
		          headerArrow.setVisibility(View.VISIBLE);
		          headerTitle.setVisibility(View.VISIBLE);
		          headerLastUpdated.setVisibility(View.VISIBLE); 
		          headerTitle.setText("����ˢ��");
		          headerArrow.clearAnimation(); 
		          header.setPadding(0, -1*headerHeight, 0,0);
		      break;
		      }
		  }
		  /**
		 * ����β��ͼ��ʾ״̬
		 */
		  private void onFooterStateChange() {
		      switch (state) {
		      case PULL_TO_REFRESH: 
		          footerProgressBar.setVisibility(View.GONE);
		          footerArrow.setVisibility(View.VISIBLE);
		          footerTitle.setVisibility(View.VISIBLE);
		          footerLastUpdated.setVisibility(View.VISIBLE);
		          
		          footerTitle.setText("����ˢ��");
		          footerArrow.clearAnimation();
		          if(isBack){
		              footerArrow.startAnimation(reverseAnimation);
		              isBack=false;
		          }
		          break;
		
		      case RELEASE_TO_REFERESH:
		          footerProgressBar.setVisibility(View.GONE);
		          footerArrow.setVisibility(View.VISIBLE);
		          footerTitle.setVisibility(View.VISIBLE);
		          footerLastUpdated.setVisibility(View.VISIBLE);
		          
		          footerTitle.setText(" �ɿ�ˢ��");
		          footerArrow.clearAnimation();
		          footerArrow.startAnimation(animation);
		          break; 
		      
		      case REFERESHING:
		          footerProgressBar.setVisibility(View.VISIBLE);
		          footerArrow.setVisibility(View.GONE); 
		          footerTitle.setVisibility(View.VISIBLE);
		          footerLastUpdated.setVisibility(View.VISIBLE);
		          
		          footerTitle.setText("����ˢ��");
		          footerArrow.clearAnimation();
		          
		          footer.setPadding(0, 0, 0,0);
		      break;
		      case DONE:
		          footerProgressBar.setVisibility(View.GONE);
		          footerArrow.setVisibility(View.VISIBLE);
		          footerTitle.setVisibility(View.VISIBLE);
		          footerLastUpdated.setVisibility(View.VISIBLE);
		          
		          footerTitle.setText("����ˢ��");
		          footerArrow.clearAnimation();
		          
		          footer.setPadding(0, -1*headerHeight, 0,0);
		      break;
		      }    
		  }
		  /**
		 * ����ˢ�µ�ʵ�ַ���
		 */
		  private void onPullDownRefresh() {
		      if(rListener!=null){
		          rListener.pullDownRefresh();
		      }
		  }
		  /**
		 * ����ˢ�µ�ʵ�ַ���
		 */
		  private void onPullUpRefresh() {
		      if(rListener!=null)
		          rListener.pullUpRefresh();
		  }
			
	
}
