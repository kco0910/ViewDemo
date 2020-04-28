package com.fly.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;
import android.widget.Scroller;

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
    private VelocityTracker mVelocityTracker;
    private int mScaledTouchSlop;
    private int mScaledMaximumFlingVelocity;
    private Scroller mScroller;

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
        mScroller = new Scroller(getContext());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        mScaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
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
        canvas.save();
        canvas.clipRect(-halfW,0,halfW,height);
        mPaint.setColor(lineColor);
        int count = max -min;
        float x;
        dx = dx>0?0:dx;
        dx = Math.abs(dx)>count*gap?-count*gap:dx;
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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),100);
    }

    private float preX = 0.0f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                preX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x-preX) >=mScaledTouchSlop){
                    dx +=(x-preX);
                    preX = x;
                    postInvalidate();
                }
                return  true;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mScaledMaximumFlingVelocity);
                fling();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void fling(){
        mScroller.fling(getScrollX(),0,(int)mVelocityTracker.getXVelocity(),0,0,0,0,0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            int currX = mScroller.getCurrX();
            Logger.i("currX :"+currX);
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mVelocityTracker != null){
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
