package com.dd.medication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** 
 * ˫����
 * */  
public class RingView extends RelativeLayout{  
  
    public RingView(Context context) {  
        super(context);  
    }  
  
    public RingView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public RingView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
      
    /** 
     * ������ɫ
     * */  
    private final static int RingColor = Color.parseColor("#8dd4a3");  
      
    /** 
     * ���ȵ���ɫ 
     * */  
    private final static int PecentColor = Color.parseColor("#41BC6C"); 
      
    /** 
     * ���� 
     */  
    private Paint paint;  
    /** 
     * �Ƿ��һ�� 
     */  
    private boolean init = false;  
    /** 
     * ������νǶ� 
     */  
    private static final float startAngle = 120;  
    /** 
     * �������ĵ�X�� 
     */  
    private float content_X;  
    /** 
     * �������ĵ�Y�� 
     */  
    private float content_Y;  
    /** 
     * ������뾶 
     */  
    private float bigRadius;  
    /** 
     * �����ڰ뾶 
     */  
    private float smallRadius;  
    /** 
     * Ĭ���յ�Ƕ� 
     */  
    private float SweepAngle = 270;  
    /** 
     * �ؼ��� 
     */  
    private int width;  
    /** 
     * �ؼ��� 
     */  
    private int height;  
    /** 
     * �ļ���ʾ���ı� 
     */  
    private String text;  
    private static final int TEXTSIZE = 25;  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
        if (!init) {  
            initPaint();  
        }  
    }  
  
    private void initPaint() {  
        setPadding(0, 0, 0, 0);  
        paint = new Paint();  
        paint.setStyle(Style.FILL);  
        paint.setAntiAlias(true);  
        paint.setColor(RingColor);//ring����ɫ  
        width = getMeasuredWidth();  
        height = getMeasuredHeight();  
        bigRadius = ((float) width / 2);  
        smallRadius = (float) width / 4;  
        content_X = (float) width / 2;  
        content_Y = (float) height / 2;  
        init = true;  
    }  
      
    @Override  
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        paint.setColor(RingColor);//ring����ɫ  
        Path path = new Path();  
        path.reset();  
        /*��Բ*/  
        path.addCircle(content_X, content_Y, bigRadius -3 , Path.Direction.CCW);  
        path.close();  
        canvas.drawPath(path, paint);  
        path.reset();  
        paint.setColor(Color.WHITE);  
        path.addCircle(content_X, content_Y, smallRadius, Path.Direction.CCW);  
        path.close();  
        canvas.drawPath(path, paint);  
        getSectorClip(canvas,startAngle);  
        path.reset();  
        paint.setColor(Color.WHITE);  
        path.addCircle(content_X, content_Y, smallRadius-3, Path.Direction.CCW);  
        path.close();  
        canvas.drawPath(path, paint);  
        if (text!=null) {  
            paint.setColor(Color.GREEN);  
            paint.setFakeBoldText(true);  
            paint.setTextSize(TEXTSIZE);  
            canvas.drawText(text,width/4,height/2, paint);  
        }  
    }  
    /** 
     * ����һ�����εļ����� 
     *  
     * @param canvas 
     *            //���� 
     * @param startAngle 
     *            //��ʼ�Ƕ� 
     */  
    private void getSectorClip(Canvas canvas,float startAngle) {  
        paint.setColor(PecentColor);//���ȵ���ɫ  
        Path path = new Path();  
        // �����ǻ��һ�������εļ�����  
        path.moveTo(content_X, content_Y); // Բ��  
        path.lineTo(  
                (float) (content_X + bigRadius * Math.cos(startAngle * Math.PI / 180)), // ��ʼ��Ƕ���Բ�϶�Ӧ�ĺ�����  
  
                (float) (content_Y + bigRadius * Math.sin(startAngle * Math.PI / 180))); // ��ʼ��Ƕ���Բ�϶�Ӧ��������  
        path.lineTo(  
                (float) (content_X + bigRadius * Math.cos(SweepAngle * Math.PI / 180)), // �յ�Ƕ���Բ�϶�Ӧ�ĺ�����  
  
                (float) (content_Y + bigRadius * Math.sin(SweepAngle * Math.PI / 180))); // �յ��Ƕ���Բ�϶�Ӧ��������  
        path.close();  
        // //����һ�������Σ�����Բ  
        RectF rectF = new RectF(content_X - bigRadius, content_Y - bigRadius, content_X + bigRadius,  
                content_Y + bigRadius);  
        // �����ǻ�û��μ������ķ���  
        path.addArc(rectF, startAngle, SweepAngle - startAngle);  
        canvas.drawPath(path,paint);  
          
          
    }  
      
    /** 
     * ����һ�����εļ����� 
     *  
     * @param canvas 
     *            //���� 
     * @param startAngle 
     *            //��ʼ�Ƕ� 
     */  
    private void getSmallSectorClip(Canvas canvas,float startAngle) {  
        paint.setColor(Color.WHITE);  
        Path path = new Path();  
        // �����ǻ��һ�������εļ�����  
        path.moveTo(content_X, content_Y); // Բ��  
        path.lineTo(  
                (float) (content_X + smallRadius * Math.cos(startAngle * Math.PI / 180)), // ��ʼ��Ƕ���Բ�϶�Ӧ�ĺ�����  
  
                (float) (content_Y + smallRadius * Math.sin(startAngle * Math.PI / 180))); // ��ʼ��Ƕ���Բ�϶�Ӧ��������  
        path.lineTo(  
                (float) (content_X + smallRadius * Math.cos(SweepAngle * Math.PI / 180)), // �յ�Ƕ���Բ�϶�Ӧ�ĺ�����  
  
                (float) (content_Y + smallRadius * Math.sin(SweepAngle * Math.PI / 180))); // �յ��Ƕ���Բ�϶�Ӧ��������  
        path.close();  
        // //����һ�������Σ�����Բ  
        RectF rectF = new RectF(content_X - smallRadius, content_Y - smallRadius, content_X + smallRadius,  
                content_Y + smallRadius);  
        // �����ǻ�û��μ������ķ���  
        path.addArc(rectF, startAngle, SweepAngle - startAngle);  
        canvas.drawPath(path,paint);  
          
          
    }  
      
    /** 
     * @param startAngle�ٷֱ� 
     */  
    public void setAngle(float startAngle){  
        SweepAngle = (360*startAngle/100 + 270);   
    }  
      
    public void setText(String text){  
        this.text = text;  
    }  
}  