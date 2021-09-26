package com.fly.viewdemo.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView  extends View {
    private Paint circlePaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context) {
        this(context,null);

    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width,height);
        float radius  = min * 0.5f;
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.BLACK);
        //画圆
        canvas.drawCircle(width*0.5f,height*0.5f,radius,circlePaint);
        //画坐标系
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);
        canvas.drawLine(0,height*0.5f,width,height*0.5f,circlePaint);
        canvas.drawLine(width*0.5f,0,width*0.5f,height,circlePaint);
    }
}
