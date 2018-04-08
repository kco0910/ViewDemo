package com.fly.viewdemo.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * 1、Paint 的 style 默认是 FILL
 * 2、坐标点移到中间
 * 3、画右坐标轴
 * Created by Fj on 2018/3/29.
 */

public class BaseView extends View {
    protected Paint mPaint;
    protected RectF mRectF;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init(){
        mPaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (toCenter()){
            //坐标点移动屏幕中心
            float dx = getWidth()/2;
            float dy = getHeight()/2;
            canvas.translate(dx,dy);

            //画坐标轴
            mPaint.setColor(Color.GRAY);
            canvas.drawLine(-dx,0,dx,0,mPaint);
            canvas.drawLine(0,-dx,0,dx,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }


    /**
     * 将坐标点移到中心点
     * @return
     */
    protected boolean toCenter(){
        return  true;
    }
}
