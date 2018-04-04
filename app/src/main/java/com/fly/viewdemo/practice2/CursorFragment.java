package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class CursorFragment extends BaseFragment {
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
        protected boolean toCenter() {
            return false;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(30.0f);
            paint.setColor(Color.BLACK);

            //对于一段文字，计算出某个字符处光标的 x 坐标。
            // start end 是文字的起始和结束坐标；
            // contextStart contextEnd 是上下文的起始和结束坐标；
            // isRtl 是文字的方向；offset 是字数的偏移，即计算第几个字符处的光标

            String str = "getRunAdvance,getOffsetForAdvance只能在API23以后使用";
            canvas.drawText(str,0,60,paint);
            int length = (int) paint.measureText(str);
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                float runAdvance = paint.getRunAdvance(str, 0, length,
                        0, length, false, 3);

            }
            //getOffsetForAdvance  给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）
        }
    }

}
