package com.fly.viewdemo.arc;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fly.viewdemo.R;

/**
 * Created by Fj on 2018/3/29.
 */

public class ArcActivity extends AppCompatActivity {
    private ArcView arc_view;
    private SeekBar angleSeekbar,sweepAngleSeekar;
    private ValueAnimator mValueAnimator;
    private TextView tvAngle,tvSweepAngle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc);

        arc_view = findViewById(R.id.arc_view);
        tvAngle = findViewById(R.id.tv_angle);
        tvSweepAngle = findViewById(R.id.tv_sweep_angle);

        angleSeekbar = findViewById(R.id.angle_seekBar);
        angleSeekbar.setMax(360);
        angleSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAngle.setText("值;"+progress);
                arc_view.setStartAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sweepAngleSeekar = findViewById(R.id.sweep_angle_seekBar);
        sweepAngleSeekar.setMax(360);
        sweepAngleSeekar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSweepAngle.setText("值;"+progress);
                arc_view.setSweepAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void  startAnim(View view){
        if (mValueAnimator == null){
            mValueAnimator = ValueAnimator.ofInt(0,360);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.setDuration(10*1000);
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animValue = (int)animation.getAnimatedValue();
                    arc_view.setStartAngle(animValue);
                }
            });
        }
        if (mValueAnimator.isStarted()){
            mValueAnimator.cancel();
        }
        mValueAnimator.start();
    }





}
