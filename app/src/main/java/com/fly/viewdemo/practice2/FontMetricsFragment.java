package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class FontMetricsFragment extends BaseFragment {

    @Override
    public View createChildView() {
        return new FontMetricsView(getActivity());
    }

    private class FontMetricsView extends BaseView{

        public FontMetricsView(Context context) {
            super(context);
        }

        public FontMetricsView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public FontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setTextSize(30.0f);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            String str = "ABCDEFGHIJKLNM夜神";
            float length = paint.measureText(str);
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float ascent = fontMetrics.ascent;
            float descent = fontMetrics.descent;
            float top = fontMetrics.top; //值为负
            float bottom = fontMetrics.bottom;
            canvas.drawText(str,0,0,paint);

            paint.setColor(Color.RED);
            canvas.drawLine(0,ascent,length,ascent,paint);

            RectF rectF = new RectF(0,60,20,80);
            canvas.drawRect(rectF,paint);
            canvas.drawText("ascent",rectF.right,rectF.bottom,paint);

            paint.setColor(Color.GREEN);
            canvas.drawLine(0,descent,length,descent,paint);

            rectF = new RectF(40,100,60,120);
            canvas.drawRect(rectF,paint);
            canvas.drawText("descent",rectF.right,rectF.bottom,paint);


            paint.setColor(Color.BLUE);
            canvas.drawLine(0,top,length,top,paint);
            rectF = new RectF(80,140,100,160);
            canvas.drawRect(rectF,paint);
            canvas.drawText("top",rectF.right,rectF.bottom,paint);

            paint.setColor(0xFFA607F8);
            canvas.drawLine(0,bottom,length,bottom,paint);

            rectF = new RectF(120,180,140,200);
            canvas.drawRect(rectF,paint);
            canvas.drawText("bottom",rectF.right,rectF.bottom,paint);


        }
    }
}
