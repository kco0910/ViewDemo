package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class BreakTextFragment extends BaseFragment {
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
            //breakText 个方法也是用来测量文字宽度的。但和 measureText() 的区别是，
            // breakText() 是在给出宽度上限的前提下测量文字的宽度。
            // 如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
            //这个方法可以用于多行文字的折行计算。

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(30.0f);
            paint.setColor(Color.BLACK);
            String str = "I may be crazy,dont mind me ,say";
            float[] measuredWidth = {0};
            int len = paint.breakText(str, 0, str.length(), true, 100, measuredWidth);
            canvas.drawText(str,0,len,100,100,paint);

            len = paint.breakText(str, 0, str.length(), true, 200, measuredWidth);
            canvas.drawText(str,0,len,100,150,paint);

            len = paint.breakText(str, 0, str.length(), true, 300, measuredWidth);
            canvas.drawText(str,0,len,100,200,paint);

            len = paint.breakText(str, 0, str.length(), true, 400, measuredWidth);
            canvas.drawText(str,0,len,100,250,paint);

            len = paint.breakText(str, 0, str.length(), true, 500, measuredWidth);
            canvas.drawText(str,0,len,100,300,paint);
        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
