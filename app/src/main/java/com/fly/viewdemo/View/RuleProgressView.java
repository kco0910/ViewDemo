package com.fly.viewdemo.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.fly.viewdemo.animator.AnimatorFactor;
import com.orhanobut.logger.Logger;

/**
 * Created by Fj on 2018/4/12.
 */

public class RuleProgressView extends View {
    private float gap = 20.0f;
    private int min = 100;
    private int max = 230;
    private float dx = 0.0f;
    private int backgroundColor = 0xFFC1FE03;
    private int lineColor = Color.WHITE;
    private Paint mPaint;
    private Path mPath;

    public RuleProgressView(Context context) {
        this(context,null);
    }

    public RuleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RuleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                //animator();
            }
        },2*1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float height5 = height * 0.5f;
        float height25 = height * 0.25f;
        float height80 = height *0.8f;

        float halfW = width/2;
        canvas.drawColor(backgroundColor);

        canvas.translate(halfW,0);

        //画固定的箭头
        mPath.reset();
        mPath.moveTo(-10,0);
        mPath.lineTo(10,0);
        mPath.lineTo(0,20);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPath(mPath,mPaint);
        Logger.i("dx值:"+dx);
        canvas.save();
        canvas.clipRect(-halfW,0,halfW,height);
        mPaint.setColor(lineColor);
        int count = max -min;
        float x;
        for (int i = 0; i <=count ; i++) {
            x = dx+i*gap;
            if (i%10 == 0){
                mPaint.setStrokeWidth(5);
                canvas.drawLine(x,0,x,height5,mPaint);
                canvas.drawText(String.valueOf(min+i),x,height80,mPaint);
            }else{
                mPaint.setStrokeWidth(3);
                mPaint.setTextSize(30.0f);
                canvas.drawLine(x,0,x,height25,mPaint);
            }
            mPaint.setTextAlign(Paint.Align.CENTER);
        }
        canvas.restore();
    }





    private void animator(){
        AnimatorFactor.floatAnimator(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        },10*1000,0,130).start();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),100);
    }

    private float preX = 0.0f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
//        Logger.i("x:"+x+",preX:"+preX);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                preX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (x >= preX){ //向右
                    dx -= (preX -x);
                }else if (x < preX ){ //向左
                    dx += (x-preX);
                }
                preX = x;
                postInvalidate();
                return  true;
        }
        return super.onTouchEvent(event);
    }

}
