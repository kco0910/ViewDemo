package com.fly.viewdemo.practice5;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fly.viewdemo.R;
import com.fly.viewdemo.View.CircleView;
import com.fly.viewdemo.base.BaseFragment;

/**
 * 1、系统带的Evaluator
 *
 * Created by Fj on 2018/4/8.
 */

public class EvaluatorFragment extends BaseFragment {

    private CircleView mCircleView;


    @Override
    public View createChildView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_evaluator_layout, null);
        FrameLayout flContain = view.findViewById(R.id.fl_contain);
        mCircleView = new CircleView(getActivity());

        flContain.addView(mCircleView, new FrameLayout.LayoutParams(400, 400));
        initView(view);
        return view;
    }

    private void initView(View view){


        view.findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator color = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    color = ObjectAnimator.ofArgb(mCircleView,"color",0xFFFD0404, 0xFF0A04FD);
                }else{
                    color = ObjectAnimator.ofInt(mCircleView, "color", 0xFFFD0404, 0xFF0A04FD);
                    color.setEvaluator(new ArgbEvaluator());
                }
                color.setDuration(5*1000);
                color.start();
            }
        });
        view.findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator color = ObjectAnimator.ofInt(mCircleView, "color", 0xFFFD0404, 0xFF0A04FD);
                color.setEvaluator(new HsvEvaluator());
                color.setDuration(5*1000);
                color.start();
            }
        });
    }

}
