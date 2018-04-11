package com.fly.viewdemo.practice5;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.animator.FloatAnimatorFactor;
import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;
import com.fly.viewdemo.model.C;

/**
 *
 * Created by Fj on 2018/4/11.
 */

public class CoordinateConvertFragment extends BaseFragment {
    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }

    private class MyView extends BaseView{
        private float degrees = 0.0f;
        public MyView(Context context) {
            super(context);
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    FloatAnimatorFactor.floatAnimator(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            degrees = (float)animation.getAnimatedValue();
                            postInvalidate();
                        }
                    }, C.DURATION_10,0.0f,360.0f).start();
                }
            },2*1000);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.save();
            canvas.rotate(degrees);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(20,20,10,mPaint);
            canvas.restore();
        }
    }
}
