package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/3/30.
 */

public class TextFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getActivity());
    }

    private static class TextView extends BaseView{

        public TextView(Context context) {
            super(context);
        }

        public TextView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //setStrokeWidth  会影响文字的绘制
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(40.0f);
            //拐弯处要用圆角，别用尖角
            mPaint.setPathEffect(new CornerPathEffect(10));
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(100,100);
            path.lineTo(200,50);
            path.lineTo(280,100);
            path.lineTo(400,0);
            canvas.drawPath(path,mPaint);
            String text = "沿着path的路径绘制文字";
            canvas.drawTextOnPath(text,path,0,0,mPaint);

            //drawText只能绘制单行的文字，如果需要换行需要使用StaticLayout

            text = "我就想看看你能不能换行，如果不能，我再看看。看你真的换行了呀!";
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(50.0f);
            textPaint.setColor(Color.GREEN);
            StaticLayout staticLayout = new StaticLayout(text,textPaint,600, Layout.Alignment.ALIGN_NORMAL,
                    0,1,true);
            canvas.save();
            canvas.translate(0,120);
            staticLayout.draw(canvas);

        }


        @Override
        protected boolean toCenter() {
            return false;
        }
    }


}
