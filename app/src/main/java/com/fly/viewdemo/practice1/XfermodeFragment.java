package com.fly.viewdemo.practice1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

public class XfermodeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new XfermodeView(getActivity());

    }

    private class XfermodeView extends BaseView{

        private Bitmap mBlue;
        private Bitmap mWoniu2;

        public XfermodeView(Context context) {
            super(context);
        }

        public XfermodeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void init() {
            super.init();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            mBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue,options);
            mWoniu2 = BitmapFactory.decodeResource(getResources(), R.drawable.woniu_2);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //要想使用 setXfermode() 正常绘制，必须使用离屏缓存 (Off-screen Buffer) 把内容绘制在额外的层上，再把绘制好的内容贴回 View 中

            //setup1
            int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(mBlue,0,0,mPaint);

            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            mPaint.setXfermode(porterDuffXfermode);

            canvas.drawBitmap(mWoniu2,100,100,mPaint);
            mPaint.setXfermode(null);

            //setup2
            canvas.restoreToCount(saved);
        }
    }
}
