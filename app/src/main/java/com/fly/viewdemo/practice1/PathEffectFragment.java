package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/3/30.
 */

public class PathEffectFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new PathEffectView(getActivity());

    }

    private static class PathEffectView extends BaseView{

        public PathEffectView(Context context) {
            super(context);
        }

        public PathEffectView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setStyle(Paint.Style.STROKE);

            //PathEffect 的子类有 CornerPathEffect,DashPathEffect,PathDashPathEffect,DiscretePathEffect
            // 组合效果:SumPathEffect,ComposePathEffect
            //从默认的直角变成圆角
            PathEffect pathEffect = new CornerPathEffect(4.0f);
            mPaint.setPathEffect(pathEffect);
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(40,40);
            path.lineTo(60,20);
            path.lineTo(80,80);
            path.lineTo(100,60);
            path.lineTo(120,30);
            mPaint.setColor(Color.BLUE);
            canvas.drawPath(path,mPaint);
            mPaint.setPathEffect(null);
            canvas.drawText("转角变圆角",0,140,mPaint);

            canvas.translate(0,200);
            //随机弯曲线条
            pathEffect = new DiscretePathEffect(20,5);
            mPaint.setPathEffect(pathEffect);
            canvas.drawPath(path,mPaint);
            mPaint.setPathEffect(null);
            canvas.drawText("随机弯曲线条",0,140,mPaint);


            canvas.translate(0,200);
            pathEffect = new DashPathEffect(new float[]{20,10,5,6},0);
            mPaint.setPathEffect(pathEffect);
            canvas.drawPath(path,mPaint);
            mPaint.setPathEffect(null);
            canvas.drawText("虚线",0,140,mPaint);

            canvas.translate(0,200);
            //TRANSLATE：位移,ROTATE：旋转,MORPH：变体
            Path dash = new Path();
            dash.addCircle(5,5,5, Path.Direction.CW);
            pathEffect = new PathDashPathEffect(dash,40,0,PathDashPathEffect.Style.MORPH);
            mPaint.setPathEffect(pathEffect);
            canvas.drawPath(path,mPaint);

        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
