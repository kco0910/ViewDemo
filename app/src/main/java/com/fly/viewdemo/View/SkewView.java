package com.fly.viewdemo.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.LinearInterpolator;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/8.
 */

public class SkewView extends BaseView {
    private Bitmap mGoogleBitmap;
    private float degrees = 0.0f;
    private float degreesY = 0.0f;
    private RectF mRectF = new RectF();
    private Camera mCamera;

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
        //给camera的Z轴做距离适配，避免绘制糊脸?
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        mCamera = new Camera();
        mCamera.setLocation(0, 0, newZ);

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
        int width = getWidth();
        int height = getHeight();
        int bitmapWidth = mGoogleBitmap.getWidth();
        int bitmapHeight = mGoogleBitmap.getHeight();
        float halfBitmapW = bitmapWidth/2;
        float halfBitmapH = bitmapHeight/2;

        canvas.translate(width/2,height/2);
        //从图片左边的对半矩形开始裁减
        canvas.save();
        //先旋转角度得到裁减区域
        canvas.rotate(-degrees);
        mRectF.set(-halfBitmapW,-halfBitmapH,0,halfBitmapH);
        canvas.clipRect(mRectF);
        //恢复旋转角度，绘制图片
        canvas.rotate(degrees);
        canvas.drawBitmap(mGoogleBitmap,-halfBitmapW,-halfBitmapH,mPaint);
        canvas.restore();

        //图片右边的对半矩形进行折叠
        canvas.save();
        mCamera.save();
        canvas.rotate(-degrees);
        mRectF.set(0,-halfBitmapH,halfBitmapW,halfBitmapH);
        canvas.clipRect(mRectF);
        mCamera.rotateY(degreesY);
        mCamera.applyToCanvas(canvas);
        canvas.rotate(degrees);
        canvas.drawBitmap(mGoogleBitmap,-halfBitmapW,-halfBitmapH,mPaint);
        mCamera.restore();
        canvas.restore();

    }

    @Override
    protected boolean toCenter() {
        return false;
    }

    private void startAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees =  (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();

        ValueAnimator valueY = ValueAnimator.ofFloat(0, -45);
        valueY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degreesY = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueY.setInterpolator(new LinearInterpolator());
        valueY.setDuration(800);
        valueY.start();
    }
}
