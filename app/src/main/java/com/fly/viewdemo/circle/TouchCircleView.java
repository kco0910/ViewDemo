package com.fly.viewdemo.circle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fly.viewdemo.R;

public class TouchCircleView  extends FrameLayout {
    private static final String TAG = "TouchCircleView";
    private ImageView iv_arrow;

    public TouchCircleView(Context context) {
        this(context,null);
    }

    public TouchCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.touch_circle_view,this,true);
        iv_arrow = findViewById(R.id.iv_arrow);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        FrameLayout.LayoutParams layoutParams = (LayoutParams) iv_arrow.getLayoutParams();
        int width = layoutParams.width;
        int height = layoutParams.height;
        Log.i(TAG, "onSizeChanged: width:"+width+",height:"+height);
        iv_arrow.setPivotX(width*0.5f);
        iv_arrow.setPivotY(height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                handTouch(event);
                break;
        }
        return true;
    }

    private void handTouch(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        int width = getWidth();
        int height = getHeight();
        float rotationBetweenLines = getRotationBetweenLines(width * 0.5f, height * 0.5f, x, y);
        Log.i(TAG, "handTouch: 角度:"+rotationBetweenLines);

        iv_arrow.setRotation(rotationBetweenLines);

    }


    /**
     *获取两条线的夹角   本方法为往上拷贝的方法，验证可以使用
     * @param centerX
     * @param centerY
     * @param xInView
     * @param yInView
     * @return
     */
    public static float getRotationBetweenLines(float centerX, float centerY, float xInView, float yInView) {
        double rotation = 0;

//        double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
        double k1 = 0;
        double k2 = (double) (yInView - centerY) / (xInView - centerX);
        double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;

        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 90 - tmpDegree;
        } else if (xInView > centerX && yInView > centerY) //第二象限
        {
            rotation = 90 + tmpDegree;
        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 270 - tmpDegree;
        } else if (xInView < centerX && yInView < centerY) { //第四象限
            rotation = 270 + tmpDegree;
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 0;
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 180;
        }

        return (float) rotation;
    }
}
