package com.fly.viewdemo.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.R;

/**
 * 1、需要关闭硬件加速才能关闭
 * 2、一张图上有不同的图片，通过显示图片上不同的位置来进行播放，类似于做游戏时取不同的图片
 * Created by Fj on 2018/3/23.
 */
public class PictureView extends View {
    private Picture mPicture = new Picture();
    private Bitmap mBitmap;
    private Paint mPaint = new Paint();
    private int i;

    public PictureView(Context context) {
        super(context);
        init();
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        Canvas canvas = mPicture.beginRecording(500, 500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(250,250);
        canvas.drawCircle(0,0,100,paint);
        mPicture.endRecording();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mark);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                anim();
            }
        },1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mPicture.draw(canvas);//有性能影响
        canvas.drawPicture(mPicture);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();


        Rect src = new Rect(i*height,0,height+i*height,height);//原图显示的区域
        Rect rect = new Rect(40,40,40+height,40+height);//图片显示的位置


        mPaint.setColor(Color.RED);
        canvas.drawRect(rect,mPaint);
        canvas.drawBitmap(mBitmap,src,rect,mPaint);
        i++;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    private void anim(){
        HandlerThread handlerThread = new HandlerThread("refresh");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<13){
                    postInvalidate();
                    try {
                        Thread.sleep(1000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
