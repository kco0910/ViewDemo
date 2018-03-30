package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
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

public class MaskFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new MaskView(getActivity());
    }


    private static class  MaskView extends BaseView{
        private Bitmap mBitmap;

        public MaskView(Context context) {
            super(context);
        }

        public MaskView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yezi_2);
        }

        public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yezi_2);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //NORMAL: 内外都模糊绘制
            BlurMaskFilter blurMaskFilter = new BlurMaskFilter(40, BlurMaskFilter.Blur.NORMAL);
            mPaint.setMaskFilter(blurMaskFilter);
            canvas.drawBitmap(mBitmap,0,0,mPaint);
            //内部模糊，外部不绘制
            blurMaskFilter = new BlurMaskFilter(40, BlurMaskFilter.Blur.INNER);
            mPaint.setMaskFilter(blurMaskFilter);
            canvas.drawBitmap(mBitmap,0,mBitmap.getHeight(),mPaint);
            //内部不绘制，外部模糊
            blurMaskFilter = new BlurMaskFilter(40, BlurMaskFilter.Blur.OUTER);
            mPaint.setMaskFilter(blurMaskFilter);
            canvas.drawBitmap(mBitmap,0,2*mBitmap.getHeight(),mPaint);
            //内部正常绘制，外部模糊
            blurMaskFilter = new BlurMaskFilter(40, BlurMaskFilter.Blur.SOLID);
            mPaint.setMaskFilter(blurMaskFilter);
            canvas.drawBitmap(mBitmap,0,3*mBitmap.getHeight(),mPaint);

            //浮雕效果
            mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
            canvas.drawBitmap(mBitmap,0,4*mBitmap.getHeight(),mPaint);
        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
