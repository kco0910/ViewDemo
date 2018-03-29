package com.fly.viewdemo.path;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;

import com.fly.viewdemo.R;

/**
 * Created by Fj on 2018/3/16.
 */

public class PathActivity extends AppCompatActivity {
    private LoadingView mLoadingView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        mLoadingView = findViewById(R.id.leaf_loading);

        mLoadingView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        },2*1000);
    }


    private void startAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
        valueAnimator.setDuration(10*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();
                mLoadingView.setProgress(value);
            }
        });
        valueAnimator.start();
    }
}
