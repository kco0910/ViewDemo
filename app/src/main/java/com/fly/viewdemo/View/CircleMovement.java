package com.fly.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * https://insight.io/github.com/SickWorm/MISportsConnectWidget/tree/master
 * Created by Fj on 2018/4/16.
 */
public class CircleMovement extends View{

    public CircleMovement(Context context) {
        this(context,null);
    }

    public CircleMovement(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleMovement(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}
