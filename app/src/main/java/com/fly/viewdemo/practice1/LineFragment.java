package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

public class LineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new LineView(getActivity());
    }

    private static class LineView extends BaseView{

        public LineView(Context context) {
            super(context);
        }

        public LineView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setStrokeWidth(20);
            mPaint.setStrokeCap(Paint.Cap.BUTT); //默认
            mPaint.setColor(Color.CYAN);
            canvas.drawLine(20,10,200,10,mPaint);
            //线头的形状
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setColor(Color.GREEN);
            canvas.drawLine(20,40,200,40,mPaint);

            mPaint.setStrokeCap(Paint.Cap.SQUARE);
            canvas.drawLine(20,80,200,80,mPaint);


            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeCap(Paint.Cap.BUTT);

            //拐角的形状 MITER+StrokeMiter 尖头，ROUND:圆头，BEVEL :平角
            mPaint.setStrokeWidth(10);
            mPaint.setColor(Color.MAGENTA);
            Path path = new Path();
            path.moveTo(20,100);
            path.lineTo(200,100);
            path.lineTo(100,150);
            //strokeJoin 为MITER时，需要使用StrokeMiter来进行补偿，才会出现尖角，否则会和BEVEL的展示形式一样
            mPaint.setStrokeMiter(10);
            mPaint.setStrokeJoin(Paint.Join.MITER);
            canvas.drawPath(path,mPaint);

            path.moveTo(300,100);
            path.lineTo(600,100);
            path.lineTo(400,150);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            canvas.drawPath(path,mPaint);

        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
