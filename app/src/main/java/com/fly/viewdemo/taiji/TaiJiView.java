package com.fly.viewdemo.taiji;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.orhanobut.logger.Logger;

/**
 * Created by Fj on 2018/3/20.
 */

public class TaiJiView extends View {
    private Paint mPaint;
    private int degrees = 0;
    public TaiJiView(Context context) {
        super(context);
        init();
    }

    public TaiJiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaiJiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int dx = getWidth() / 2;
        int dy = getHeight() /2;
        canvas.translate(dx,dy);
        canvas.rotate(degrees);//先旋转角度，再绘制图形

        mPaint.setColor(Color.WHITE);
        RectF rectF = new RectF(-200,-200,200,200);
        canvas.drawArc(rectF,90.0f,180.0f,true,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF,270.0f,180.0f,true,mPaint);

        float height = rectF.height()/4;
        float width = rectF.width()/4;
        canvas.drawCircle(0,-height,width,mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0,height,width,mPaint);

        canvas.drawCircle(0,-height,width/4,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0,height,width/4,mPaint);

        Logger.i("角度:"+degrees);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * 开始动画
     */
    private void startAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(10*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

}
