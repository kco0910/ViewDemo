package com.fly.viewdemo.View;

import android.animation.AnimatorSet;
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
 * 1、先让图片的左半部分翘起来，然后保持翘起来得角度不变，进行270度旋转，
 *      在旋转时右半边保持不变，当270角度完成时，将另一半没翘的边翘起来。然后再回原样
 * 2、以上的动画顺序通过AnimatorSet.playSequentially来控制
 * Created by Fj on 2018/4/8.
 */
public class SkewView extends BaseView {
    private Bitmap mGoogleBitmap;
    private float degrees = 0.0f;
    private float degreesY = 0.0f;
    private float endDegrees = 0.0f;
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
        int bitmapWidth = mGoogleBitmap.getWidth();
        int bitmapHeight = mGoogleBitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;

        //图片右边的对半矩形进行折叠
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degrees);
        mCamera.rotateY(degreesY);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.rotate(degrees);
        mCamera.restore();
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(mGoogleBitmap,x, y,mPaint);
        canvas.restore();


        //从图片左边的对半矩形开始裁减
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);
        //先旋转角度得到裁减区域
        canvas.rotate(-degrees);
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        mCamera.rotateY(endDegrees);
        mCamera.applyToCanvas(canvas);
        //恢复旋转角度，绘制图片
        canvas.rotate(degrees);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();
        canvas.drawBitmap(mGoogleBitmap,x,y,mPaint);
        canvas.restore();
    }

    @Override
    protected boolean toCenter() {
        return false;
    }

    private void startAnimator(){
        ValueAnimator valueY = ValueAnimator.ofFloat(0, -45);
        valueY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degreesY = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueY.setDuration(800);

        ValueAnimator endAnimator = ValueAnimator.ofFloat(0,30);
        endAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                endDegrees = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        endAnimator.setDuration(800);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 270);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees =  (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(valueY,valueAnimator,endAnimator);
        animatorSet.start();
    }

}
