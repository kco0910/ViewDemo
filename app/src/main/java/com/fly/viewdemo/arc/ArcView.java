package com.fly.viewdemo.arc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/3/29.
 */

public class ArcView extends BaseView {
    private int mAngle;
    private int mSweepAngle;
    public ArcView(Context context) {
        super(context);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mRectF.left =  -100;
        mRectF.top = -100;
        mRectF.right = 100;
        mRectF.bottom = 100;

        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mRectF,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        //弧线开始绘制的中心点，是矩形的中心点
        canvas.drawArc(mRectF,mAngle,mSweepAngle,false,mPaint);
    }

    public void setStartAngle(int angle){
        mAngle  = angle;
        postInvalidate();
    }

    public void setSweepAngle(int sweepAngle){
        mSweepAngle = sweepAngle;
        postInvalidate();
    }


}
