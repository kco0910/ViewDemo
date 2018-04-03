package com.fly.viewdemo.practice2;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/2.
 */

public class LightPointFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new LightPointView(getActivity());

    }

    private static class LightPointView extends BaseView{

        private PathMeasure mPathMeasure;
        private Path mPath;

        public LightPointView(Context context) {
            super(context);
        }

        public LightPointView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public LightPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            mPathMeasure = new PathMeasure();
            mPath = new Path();


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setStrokeWidth(5.0f);
            mPaint.setColor(Color.RED);

            mPath.reset();
            mPath.addCircle(0,0,200,Path.Direction.CW);
            canvas.drawPath(mPath,mPaint);

            BlurMaskFilter blurMaskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL);
            mPaint.setMaskFilter(blurMaskFilter);
            mPathMeasure.setPath(mPath,false);
            float length = mPathMeasure.getLength();

        }

        private void startAnima(){
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1.0f);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setDuration(10*1000);

        }
    }
}
