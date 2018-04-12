package com.fly.viewdemo.animator;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Fj on 2018/4/11.
 */

public class AnimatorFactor {

    public static ValueAnimator floatAnimator(ValueAnimator.AnimatorUpdateListener listener,long duration,float... values){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(listener);
        return valueAnimator;
    }

    public static ValueAnimator intAnimator(ValueAnimator.AnimatorUpdateListener listener,long duration,int... values){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(listener);
        return valueAnimator;
    }

}
