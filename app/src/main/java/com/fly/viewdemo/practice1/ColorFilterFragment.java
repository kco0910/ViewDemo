package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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
 * Created by Fj on 2018/3/29.
 */

public class ColorFilterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new ColorFilterView(getActivity());

    }

    private class ColorFilterView extends BaseView{


        private Bitmap mBitmap;

        public ColorFilterView(Context context) {
            super(context);
        }

        public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.woniu);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //ColorFilter 的子类 LightingColorFilter ,PorterDuffColorFilter,ColorMatrixColorFilter
            //R' = R * mul.R / 0xff + add.R
            //G' = G * mul.G / 0xff + add.G
            //B' = B * mul.B / 0xff + add.B

            //去掉红色
            LightingColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0xA40000);
            mPaint.setColorFilter(lightingColorFilter);
            canvas.drawBitmap(mBitmap,0,0,mPaint);
            // TODO: 2018/3/29  颜色的替换公式不懂(相当于调色)

            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(0xff15EA38, PorterDuff.Mode.LIGHTEN);
            mPaint.setColorFilter(porterDuffColorFilter);
            canvas.drawBitmap(mBitmap,0,-mBitmap.getHeight(),mPaint);

            //ColorMatrixColorFilter的使用   https://github.com/chengdazhi/StyleImageView






        }
    }
}
