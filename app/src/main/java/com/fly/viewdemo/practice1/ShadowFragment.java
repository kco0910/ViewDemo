package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/3/30.
 */
public class ShadowFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new ShadowMaskView(getActivity());

    }

    private static class ShadowMaskView extends BaseView{

        public ShadowMaskView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //在硬件加速开启的情况下， setShadowLayer() 只支持文字的绘制，文字之外的绘制必须关闭硬件加速才能正常绘制阴影。
            //如果 shadowColor 是半透明的，阴影的透明度就使用 shadowColor 自己的透明度；而如果 shadowColor 是不透明的，阴影的透明度就使用 paint 的透明度
            mPaint.setShadowLayer(10,0,0, Color.RED);
            mPaint.setTextSize(32.0f);
            mPaint.setMaskFilter(null);
            canvas.drawText("我是测试文字，我有阴影",200,40,mPaint);
        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
