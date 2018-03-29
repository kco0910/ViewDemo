package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
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
public class ShapeFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new ColorView(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private class  ColorView extends BaseView{
        private int[] colors = new int[]{0xff00EB9B,0xff0A51F2,0xff820AF2};
        private Bitmap mBitmap;

        public ColorView(Context context) {
            super(context);
        }

        public ColorView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
            float radius = 50;
            float cx,cy=0.0f;
            //shader 使用，一般使用子类  LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader
            //LinearGradient 从点A到点B之间进行渐变

            cx = radius;
            LinearGradient linearGradient = new LinearGradient(radius,-radius,radius,radius,colors,null, Shader.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
            canvas.drawCircle(cx,cy,radius,mPaint);

            cx = 3*radius;
            linearGradient = new LinearGradient(2*radius,0,3*radius,0,colors,null, Shader.TileMode.MIRROR);
            mPaint.setShader(linearGradient);
            canvas.drawCircle(cx,cy,radius,mPaint);

            cx = 5*radius;
            linearGradient = new LinearGradient(4*radius,0,6*radius,0,colors,null, Shader.TileMode.REPEAT);
            mPaint.setShader(linearGradient);
            canvas.drawCircle(cx,cy,radius,mPaint);

            cx  = -radius;
            RadialGradient radialGradient = new RadialGradient(cx,0,radius,colors,null, Shader.TileMode.CLAMP);
            mPaint.setShader(radialGradient);
            canvas.drawCircle(cx,0,radius,mPaint);

            cx = 0;
            cy = -2*radius;
            SweepGradient sweepGradient = new SweepGradient(cx,cy,colors,null);
            mPaint.setShader(sweepGradient);
            canvas.drawCircle(cx,cy,radius,mPaint);


            canvas.translate(radius,radius);

            cx =  mBitmap.getWidth()/2;
            cy = cx;
            //图片默认是从0,0添加到画布的，如果绘制的形状的坐标不在图片的坐标区域内，则不会绘制出来。
            BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(bitmapShader);
            canvas.drawCircle(cx,cy,cx,mPaint);


            canvas.translate(-radius,-radius);
            //ComposeShader  将多个shader合并,它的Mode 有17中,具体效果参照assets/PorterDuff_Mode_1,2,3
            ComposeShader composeShader = new ComposeShader(sweepGradient,bitmapShader, PorterDuff.Mode.DST_OUT);
        }
    }

}
