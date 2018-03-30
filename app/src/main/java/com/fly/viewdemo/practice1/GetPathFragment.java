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

public class GetPathFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new GetPathView(getActivity());
    }

    private static  class GetPathView extends BaseView{

        public GetPathView(Context context) {
            super(context);
        }

        public GetPathView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public GetPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //所谓实际 Path ，指的就是 drawPath() 的绘制内容的轮廓，要算上线条宽度和设置的 PathEffect。
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(20.0f);
            mPaint.setStyle(Paint.Style.STROKE);
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(20,20);
            path.lineTo(40,80);
            path.lineTo(60,120);
            path.lineTo(80,60);
            canvas.drawPath(path,mPaint);

            //默认情况下（线条宽度为 0、没有 PathEffect），
            // 原 Path 和实际 Path 是一样的；而在线条宽度不为 0 （并且模式为 STROKE 模式或 FLL_AND_STROKE ），
            // 或者设置了 PathEffect 的时候，实际 Path 就和原 Path 不一样了
            canvas.translate(0,200);
            Path dst = new Path();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            mPaint.getFillPath(path,dst);
            canvas.drawPath(dst,paint);

            canvas.translate(0,-200);
            Paint textPaint = new Paint();
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setStrokeWidth(20.0f);
            textPaint.setColor(Color.GREEN);
            String text = "测试下文字的Path";
            canvas.drawText(text,100,20,textPaint);

            paint.getTextPath(text,0,text.length(),100,60,dst);
            canvas.drawPath(dst,paint);
        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
