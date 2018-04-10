package com.fly.viewdemo.practice3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class MatrixFragment extends BaseFragment {
    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }

    private class MyView extends BaseView{

        private Bitmap mBitmap;
        private Matrix mMatrix;

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
        protected void init() {
            super.init();
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google_map);
            mMatrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mMatrix.reset();
            //平移
            mMatrix.postTranslate(20,20);
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);
            //缩放
            canvas.translate(0,+mBitmap.getHeight()+20*2);
            canvas.drawLine(0,0,getWidth(),0,mPaint);
            mMatrix.reset();
            mMatrix.postScale(0.8f,0.8f);//默认基于左定点进行缩放
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);

            mMatrix.reset();
            mMatrix.postTranslate(mBitmap.getWidth()*0.8f,0.0f);
            mMatrix.postScale(1.2f,1.2f,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);
            //旋转
            canvas.translate(0,+mBitmap.getHeight());
            canvas.drawLine(0,0,getWidth(),0,mPaint);
            mMatrix.reset();
            mMatrix.setRotate(45.0f,0,0);
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);

        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
