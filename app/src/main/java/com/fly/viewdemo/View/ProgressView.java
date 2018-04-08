package com.fly.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.fly.viewdemo.base.BaseView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Fj on 2018/4/8.
 */

public class ProgressView extends BaseView {
    private float progress = 0;
    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        mRectF.set(-200,-200,200,200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        //画圆弧
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mRectF,0,360*progress,false,mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(32.0f);
        mPaint.setStrokeWidth(1.0f);
        String value = parseValue(progress*100)+"%";
        float length = mPaint.measureText(value);
        canvas.drawText(value,-length/2,10.0f,mPaint);

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        postInvalidate();
    }


    private String parseValue(float value){
        DecimalFormat decimalFormat  = new DecimalFormat("##0.00");
        String format = decimalFormat.format(value);
        return format;
    }

}
