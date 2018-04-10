package com.fly.viewdemo.practice5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/10.
 */

public class CameraFragment extends BaseFragment {

    @Override
    public View createChildView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_camera_layout, null);
        FrameLayout frameLayout = view.findViewById(R.id.fl_contain);
        final MyView myView = new MyView(getActivity());
        frameLayout.addView(myView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,600));
        SeekBar seekBarX  = view.findViewById(R.id.seek_bar_x);
        seekBarX.setMax(360);
        SeekBar seekBarY = view.findViewById(R.id.seek_bar_y);
        seekBarY.setMax(360);
        SeekBar seekBarZ = view.findViewById(R.id.seek_bar_z);
        seekBarZ.setMax(360);
        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    myView.setDegreesX(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        seekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.setDegreesY(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.setDegreesZ(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    private class MyView extends BaseView{
        private float degreesX = 0.0f;
        private float degreesY = 0.0f;
        private float degreesZ = 0.0f;
        private Camera mCamera;
        private Bitmap mGoogleBitmap;
        public MyView(Context context) {
            super(context);
            init();
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        @Override
        protected void init() {
            super.init();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float newZ = -displayMetrics.density * 6;
            mCamera = new Camera();
            mCamera.setLocation(0, 0, newZ);

            mGoogleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google_map);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            canvas.save();
            mCamera.save();
            mCamera.rotateY(degreesY); //沿着Y轴做空间旋转  视觉效果为 :0-360 由后往前
            mCamera.rotateX(degreesX); //沿着X轴做空间旋转  视觉效果为 :0-360 由前往后
            mCamera.rotateZ(degreesZ); //沿着Z轴做空间旋转  视觉效果为 :0- 360 顺时针绕着中心点做旋转

            mCamera.applyToCanvas(canvas);
            canvas.drawBitmap(mGoogleBitmap,0,0,paint);
            mCamera.restore();
            canvas.restore();
        }

        public float getDegreesX() {
            return degreesX;
        }

        public void setDegreesX(float degreesX) {
            this.degreesX = degreesX;
            postInvalidate();
        }

        public float getDegreesY() {
            return degreesY;
        }

        public void setDegreesY(float degreesY) {
            this.degreesY = degreesY;
            postInvalidate();
        }

        public float getDegreesZ() {
            return degreesZ;
        }

        public void setDegreesZ(float degreesZ) {
            this.degreesZ = degreesZ;
            postInvalidate();
        }
    }



}
