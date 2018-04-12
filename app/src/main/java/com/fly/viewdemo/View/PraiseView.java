package com.fly.viewdemo.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.animator.AnimatorFactor;

/**
 * Created by Fj on 2018/4/11.
 */

public class PraiseView extends View{

    private Paint mPaint;
    private int preValue = 9;
    private int newValue = 0;
    private float animatorY = 0;
    private int animatorAlpha = 255;


    public PraiseView(Context context) {
        this(context,null);
    }

    public PraiseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PraiseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(30.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dx = getWidth()/2;
        float dy = getHeight() /2;
        float x = 0,y=0;
        canvas.translate(dx,dy);
        int index = diffCharIndex(preValue, newValue);
        String preValueStr = String.valueOf(preValue);
        String newValueStr = String.valueOf(newValue);
        if (index != -1){
            float len = mPaint.measureText(preValueStr, 0, index);
            //先绘制出不变的文字
            mPaint.setAlpha(255);
            canvas.drawText(preValueStr,0,index,x,y,mPaint);
            canvas.save();
            mPaint.setAlpha(animatorAlpha);
            canvas.drawText(preValueStr,index,preValueStr.length(),x+len,y-animatorY,mPaint);
            mPaint.setAlpha(255-animatorAlpha);
            canvas.drawText(newValueStr,index,newValueStr.length(),x+len,y+22-animatorY,mPaint);
            canvas.restore();
        }else{
            canvas.drawText(preValueStr,x,y,mPaint);
        }
    }

    public void praise(){
        newValue = preValue+1;
        startAnimator();
    }



    public void cancelPraise(){
        newValue = preValue-1;
        startAnimator();
    }

    private void startAnimator(){
        ValueAnimator valueAnimator = AnimatorFactor.floatAnimator(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorY = (float) animation.getAnimatedValue();
            }
        }, 300, 0, 20);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                preValue = newValue;
            }
        });
        ValueAnimator alphaAnimator = AnimatorFactor.intAnimator(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorAlpha = (int) animation.getAnimatedValue();
                postInvalidate();

            }
        }, 300, 255, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator,alphaAnimator);
        animatorSet.start();
    }

    /**
     * 两个数字之间第一个不同的下标索引 <br>
     * eg: 99 -- 100 ,index = 0 <br>
     * @param preNum
     * @param newNum
     * @return -1: 未找到此下标索引
     */
    private int diffCharIndex(int preNum,int newNum){
        int index = -1;
        if (preNum <0 || newNum <0){
            return index;
        }
        String newValueStr = String.valueOf(newNum);
        String preValue = String.valueOf(preNum);
        int count = Math.min(newValueStr.length(),preValue.length());
        for (int i = 0; i < count; i++) {
            if (preValue.charAt(i)!=newValueStr.charAt(i)){
                index = i;
                break;
            }
        }
        return index;
    }

}
