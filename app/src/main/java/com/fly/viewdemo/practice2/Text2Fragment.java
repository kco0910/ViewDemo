package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/3.
 */

public class Text2Fragment extends BaseFragment {


    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }


    private static class MyView extends BaseView{

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
            Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setTextSize(25.0f);

            mPaint.setTypeface(Typeface.DEFAULT);
            String str = "shi yong ying wen cai you ming xian xiao guo:default";
            canvas.drawText(str,0,30.0f,mPaint);

            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.MONOSPACE);
            str = "shi yong ying wen cai you ming xian xiao guo:monospace";
            canvas.drawText(str,0,30.0f,mPaint);

            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.SANS_SERIF);
            str = "shi yong ying wen cai you ming xian xiao guo:SANS_SERIF";
            canvas.drawText(str,0,30.0f,mPaint);

            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.SERIF);
            str = "shi yong ying wen cai you ming xian xiao guo:SERIF";
            canvas.drawText(str,0,30.0f,mPaint);

            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            str = "shi yong ying wen cai you ming xian xiao guo:DEFAULT_BOLD";
            canvas.drawText(str,0,30.0f,mPaint);

            //伪粗体
            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.DEFAULT);
            str = "伪粗体:false";
            mPaint.setFakeBoldText(false);
            canvas.drawText(str,0,30.0f,mPaint);

            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.DEFAULT);
            str = "伪粗体:true";
            mPaint.setFakeBoldText(true);
            canvas.drawText(str,0,30.0f,mPaint);

            //删除线
            canvas.translate(0,30.0f);
            mPaint.setTypeface(Typeface.DEFAULT);
            str = "删除线";
            mPaint.setStrikeThruText(true);
            mPaint.setFakeBoldText(false);
            canvas.drawText(str,0,30.0f,mPaint);
            //下划线
            canvas.translate(0,30.0f);
            str = "下划线";
            mPaint.setStrikeThruText(false);
            mPaint.setUnderlineText(true);
            canvas.drawText(str,0,30.0f,mPaint);
            //文字倾斜度
            canvas.translate(0,30.0f);
            str = "文字倾斜度";
            mPaint.setUnderlineText(false);
            mPaint.setTextSkewX(0.5f);//负数向右倾，正数向左倾
            canvas.drawText(str,0,30.0f,mPaint);
            //文字横向缩放
            canvas.translate(0,30.0f);
            str = "文字横向缩放";
            mPaint.setTextSkewX(0);
            mPaint.setTextScaleX(3.0f);
            canvas.drawText(str,0,30.0f,mPaint);
            mPaint.setTextScaleX(0.0f);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //字符间距
                canvas.translate(0,30.0f);
                str = "字符间距";
                mPaint.setTextScaleX(0.0f);
                mPaint.setLetterSpacing(5.0f);//默认值是 0,API 21
                canvas.drawText(str,0,30.0f,mPaint);
                mPaint.setLetterSpacing(0f);

                //css font-feature-settings 方式
                canvas.translate(0,30.0f);
                str = "font-feature-settings";
                mPaint.setFontFeatureSettings("smcp");
                canvas.drawText(str,0,30.0f,mPaint);
                mPaint.setFontFeatureSettings("");
            }
            //文本绘制方式



        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }


}
