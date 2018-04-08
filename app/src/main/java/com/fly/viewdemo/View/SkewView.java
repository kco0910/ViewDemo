package com.fly.viewdemo.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/8.
 */

public class SkewView extends BaseView {
    private float sy = 0.0f;
    private float sx = 0.0f;
    private Bitmap mGoogleBitmap;

    public SkewView(Context context) {
        super(context);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        mGoogleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google_map);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimator();
            }
        },2*1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = mGoogleBitmap.getWidth();
        int height = mGoogleBitmap.getHeight();

        canvas.save();
        canvas.skew(sx,sy);
        canvas.drawBitmap(mGoogleBitmap,-width/2,-height/2,mPaint);
        canvas.restore();
    }


    private void startAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sx =  (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setDuration(10*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }
}
