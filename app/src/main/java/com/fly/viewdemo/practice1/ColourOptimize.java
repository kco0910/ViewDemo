package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/3/30.
 */

public class ColourOptimize extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new ColourOptimizeView(getActivity());
    }

    private static class ColourOptimizeView extends BaseView{

        private Bitmap mBitmap;

        public ColourOptimizeView(Context context) {
            super(context);
        }

        public ColourOptimizeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public ColourOptimizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yezi_2, options);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setColor(Color.MAGENTA);

            canvas.drawBitmap(mBitmap,0,0,mPaint);
            canvas.drawText("降低色彩深度后绘制(无抖动)",0,0,mPaint);

            mPaint.setDither(true);//图像抖动，只有在16位通道的情况下才有明显效果，在32位通道下是看不出效果的。
            float left = mBitmap.getWidth()+10;

            canvas.drawBitmap(mBitmap,left,0,mPaint);
            canvas.drawText("降低色彩深度后绘制(抖动)",left,0,mPaint);

//            setFilterBitmap();  //开启双线过滤 图像在放大绘制的时候，默认使用的是最近邻插值过滤，这种算法简单，但会出现马赛克现象；而如果开启了双线性过滤，就可以让结果图像显得更加平滑


        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
