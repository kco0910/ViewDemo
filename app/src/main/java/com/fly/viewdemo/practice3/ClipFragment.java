package com.fly.viewdemo.practice3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class ClipFragment extends BaseFragment {

    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }

    private class MyView extends BaseView{

        private Bitmap mBitmap;
        private Bitmap mYezi;

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
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.woniu_2);
            mYezi = BitmapFactory.decodeResource(getResources(), R.drawable.yezi_2);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();

            canvas.save();
            Rect rect = new Rect(0,0,100,100);
            canvas.clipRect(rect);
            canvas.drawBitmap(mBitmap,0,0,paint);
            canvas.restore();


            width  = mYezi.getWidth();
            height = mYezi.getHeight();
            canvas.save();
            canvas.translate(width + 20,0);
            Path path = new Path();
            path.addCircle(width/2,height/2,100, Path.Direction.CW);
            canvas.clipPath(path);
            canvas.drawBitmap(mYezi, 0, 0, paint);
            canvas.restore();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                canvas.save();
                canvas.translate(0,height+10);
                canvas.clipOutPath(path);
                canvas.drawBitmap(mYezi, 0, 0, paint);
                canvas.restore();
            }
            //缩放
            canvas.translate(0,height+10);
            canvas.save();
            canvas.scale(0.8f,0.8f,width/2,height/2);
            canvas.drawBitmap(mYezi,0,0,paint);
            canvas.restore();
            //错切
            canvas.translate(0,height+10);
            canvas.save();
            canvas.skew(0,0.5f);
            canvas.drawBitmap(mYezi,0,0,paint);
            canvas.restore();
            //旋转
            canvas.translate(width,0);
            canvas.save();
            canvas.rotate(45.0f,width/2,height/2);
            canvas.drawBitmap(mYezi,0,0,paint);
            canvas.restore();
        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }


}
