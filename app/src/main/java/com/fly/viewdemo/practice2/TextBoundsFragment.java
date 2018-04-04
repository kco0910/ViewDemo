package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class TextBoundsFragment extends BaseFragment {
    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }

    private class MyView extends BaseView{

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
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(30.0f);
            paint.setStyle(Paint.Style.FILL);

            float x = 60.0f;
            float y = 60.0f;
            Rect rect = new Rect();
            String str = "给你看下文字的显示范围";
            canvas.drawText(str,x,y,paint);
            paint.getTextBounds(str,0,str.length(),rect);

            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            //不执行下面的代码，矩形框不能绘制出来，getTextBounds得到的矩形框是负数
            rect.left+=x;
            rect.top +=y;
            rect.right+=x;
            rect.bottom+=y;
            canvas.drawRect(rect,paint);

            mPaint.setColor(Color.BLACK);
            StringBuilder builder = new StringBuilder();
            builder.append("getTextBounds: 它测量的是文字的显示范围").append("\n");
            builder.append("measureText(): 它测量的是文字绘制时所占用的宽度").append("\n");
            builder.append("measureText的宽度比getTextBounds大").append("\n");
            builder.append("getTextWidths : 获取字符串中每个字符的宽度，并把结果填入参数 widths");
            TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(25.0f);
            StaticLayout staticLayout = new StaticLayout(builder.toString(),textPaint,600,
                    Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
            canvas.save();
            canvas.translate(x,80);
            staticLayout.draw(canvas);
            canvas.restore();

        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
