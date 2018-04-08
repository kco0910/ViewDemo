package com.fly.viewdemo.practice5;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;

/**
 * 1、动画可以分为两类：Animation 和 Transition
 * 2、Animation 又可以再分为 View Animation 和 Property Animation (主要的)
 * 3、属性动画后带有by方法的，表示在原来动画的数值上再增加一次数值再执行.
 * 4.View.animate,ObjectAnimation
 * 5、Interpolator
 *    AccelerateDecelerateInterpolator :  加速再减速  FastOutSlowInInterpolator
 *    LinearInterpolator : 匀速
 *    AccelerateInterpolator : 加速      FastOutLinearInInterpolator :5.0和v4包中加入  相比 一个是指数曲线，一个是贝塞尔曲线，视觉效果是初始速度快一点
 *    DecelerateInterpolator : 减速     LinearOutSlowInInterpolator
 *    AnticipateInterpolator :
 *    OvershootInterpolator :
 *    AnticipateOvershootInterpolator :
 *    BounceInterpolator :
 *    CycleInterpolator :
 *    PathInterpolator : 自定义速度模型
 * Created by Fj on 2018/4/8.
 */
public class PropertyAnimationFragment extends BaseFragment {
    private ImageView img;
    private ImageView img_view_2;
    private ImageView mImg_view_3;
    private ImageView mImg_view_4;
    private ImageView mImg_view_5;
    private ImageView mImg_view_6;
    private ImageView mImg_view_7;
    private ImageView mImg_view_8;

    @Override
    public View createChildView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_animator_layout, null, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        img = view.findViewById(R.id.img_view);
        img_view_2 = view.findViewById(R.id.img_view_2);
        mImg_view_3 = view.findViewById(R.id.img_view_3);
        mImg_view_4 = view.findViewById(R.id.img_view_4);
        mImg_view_5 = view.findViewById(R.id.img_view_5);
        mImg_view_6 = view.findViewById(R.id.img_view_6);
        mImg_view_7 = view.findViewById(R.id.img_view_7);
        mImg_view_8 = view.findViewById(R.id.img_view_8);

        view.findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translation();
            }
        });

        view.findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translationBy();
            }
        });
        view.findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anticipateInterpolator();
            }
        });
        view.findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overshootInterpolator();
            }
        });
        view.findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anticipateOvershootInterpolator();
            }
        });
        view.findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bounceInterpolator();
            }
        });
        view.findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleInterpolator();
            }
        });
    }


    private void translation(){
        img.animate().translationX(100);
        //ObjectAnimator.ofFloat(img,"translationX",100);
    }

    private void translationBy(){
        img_view_2.animate().translationXBy(100);
    }

    private void anticipateInterpolator(){
        mImg_view_3.animate()
                .setInterpolator(new AnticipateInterpolator())
                .setDuration(5*1000)
                .translationX(400);
    }

    private void overshootInterpolator(){
        mImg_view_4.animate()
                .setInterpolator(new OvershootInterpolator())
                .setDuration(5*1000)
                .translationX(400);
    }

    private void anticipateOvershootInterpolator(){
        mImg_view_5.animate()
                .setInterpolator(new AnticipateOvershootInterpolator())
                .setDuration(5*1000)
                .translationX(400);
    }

    private void bounceInterpolator(){
        mImg_view_6.animate()
                .setInterpolator(new BounceInterpolator())
                .setDuration(5*1000)
                .translationX(400);
    }

    private void cycleInterpolator(){
        mImg_view_7.animate()
                .setInterpolator(new CycleInterpolator(2.0f))
                .setDuration(5*1000)
                .translationX(400);
    }


}
