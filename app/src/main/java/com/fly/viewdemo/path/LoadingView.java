package com.fly.viewdemo.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.R;

/**
 * Created by Fj on 2018/3/26.
 */
public class LoadingView extends View {
    private static final int OUT_COLOR = 0xFFFCE197;
    private static final int INNER_COLOR = 0xFFFEA902;
    private static final int TOTAL_PROGRESS = 100;
    private static final int RADIUS = 60;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private RectF tempRect = new RectF();
    private RectF outRectF, innerRectF;
    private int progress;
    private float mProgressLength;
    /**外圈和进度条之间的间距*/
    private float gap = 10.0f;
    /**总长度(外圆+进度+风扇)*/
    private int totalLength = 500;
    /**风扇长度*/
    private int fanLength =  80;
    private Bitmap mFanBitmap;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mFanBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fengshan);
        outRectF = new RectF();
        innerRectF = new RectF();
        mProgressLength = totalLength - fanLength/2 - gap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int dx = getWidth()/2;
        int dy = getHeight()/2;
        //将坐标点移到进度条的左顶点
        canvas.translate(dx-totalLength/2,dy-fanLength /2);

        //画外圈
        float fanHalf = fanLength/2;
        mPaint.setColor(OUT_COLOR);
        mPaint.setStyle(Paint.Style.FILL);

        outRectF.top = 0;
        outRectF.left = 0;
        outRectF.right = totalLength;
        outRectF.bottom = fanLength;

        canvas.drawRoundRect(outRectF,fanHalf,fanHalf,mPaint);//rx,ry大于等于高度时会是圆角

        //画进度
        float progressWidth = progress*mProgressLength/100.0f;
        mPaint.setColor(INNER_COLOR);
        float arcLength;
        if (progressWidth >fanHalf){
            arcLength = fanHalf-gap;
            innerRectF.left = fanHalf-gap;
            innerRectF.top = gap;
            innerRectF.right = gap+progressWidth;
            innerRectF.bottom = fanLength - gap;
            canvas.drawRect(innerRectF,mPaint);
        }else{
            arcLength = progressWidth;
        }
        innerRectF.left = gap;
        innerRectF.top = gap;
        innerRectF.right = gap+arcLength;
        innerRectF.bottom = fanLength -gap;
        canvas.drawArc(innerRectF,90,180,false,mPaint);
        // TODO: 2018/3/29 弧度转换公式不清楚


        //飞树叶 正弦函数 y = A(wx+Q)+h


        //画风扇相关
        float fanX = totalLength - fanHalf;
        float fanY = fanHalf;
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(fanX,fanY,fanHalf,mPaint);
        mPaint.setColor(0xFFFDCC4A);
        canvas.drawCircle(fanX,fanY,fanHalf-5,mPaint);
        canvas.drawBitmap(mFanBitmap,fanX- mFanBitmap.getWidth()/2,fanHalf-mFanBitmap.getHeight()/2,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }
}
