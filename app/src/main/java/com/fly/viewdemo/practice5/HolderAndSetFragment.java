package com.fly.viewdemo.practice5;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.fly.viewdemo.R;
import com.fly.viewdemo.View.ProgressView;
import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/8.
 */

public class HolderAndSetFragment extends BaseFragment {
    private ImageView mImgView;
    private ImageView mImageView2;
    private ProgressView mProgressView;
    @Override
    public View createChildView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_holder_set, null);
        initView(view);
        return view;
    }

    private void initView(View view){
        mImgView = view.findViewById(R.id.img_view);
        mImageView2 = view.findViewById(R.id.img_view_2);
        mProgressView = view.findViewById(R.id.progress_view);

        view.findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("scaleX",0,1);
                PropertyValuesHolder holder1  = PropertyValuesHolder.ofFloat("scaleY",0,1);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("alpha",0,1);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mImgView, holder, holder1, holder2);
                objectAnimator.setDuration(5*1000);
                objectAnimator.start();
                //同以下动画
                //mImgView.animate().scaleX(1).scaleY(1).alpha(1).setDuration(5*1000).start();
            }
        });
        view.findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView2, "scaleY", 0, 1);
                objectAnimator.setInterpolator(new LinearInterpolator());
                objectAnimator.setDuration(2*1000);
                ObjectAnimator translateX = ObjectAnimator.ofFloat(mImageView2, "translationX", 400);
                translateX.setInterpolator(new AccelerateInterpolator());
                translateX.setDuration(3*1000);
                AnimatorSet animatorSet  = new AnimatorSet();
                animatorSet.playSequentially(objectAnimator,translateX);
//                animatorSet.playTogether(objectAnimator,translateX);同时执行
//                animatorSet.play(objectAnimator).with(translateX);
//                animatorSet.play(objectAnimator).before(translateX);
//                animatorSet.play(objectAnimator).after(translateX);
                animatorSet.start();
            }
        });
        view.findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fraction :动画进度 (0,1)
                //value : 位置
                Keyframe keyframe0 = Keyframe.ofFloat(0f, 0f);
                Keyframe keyframe1 = Keyframe.ofFloat(0.1f, 0.2f);
                Keyframe keyframe3 = Keyframe.ofFloat(0.2f, 0.4f);
                Keyframe keyframe4 = Keyframe.ofFloat(0.3f, 0.6f);
                Keyframe keyframe5 = Keyframe.ofFloat(0.4f, 0.8f);
                Keyframe keyframe6 = Keyframe.ofFloat(0.5f, 0.85f);
                Keyframe keyframe7 = Keyframe.ofFloat(0.6f, 0.88f);
                Keyframe keyframe8 = Keyframe.ofFloat(0.7f, 0.90f);
                Keyframe keyframe9 = Keyframe.ofFloat(0.8f, 1.0f);
                Keyframe keyframe10 = Keyframe.ofFloat(0.85f, 0.9f);
                Keyframe keyframe11 = Keyframe.ofFloat(0.9f, 0.88f);
                Keyframe keyframe12 = Keyframe.ofFloat(0.95f, 0.85f);
                Keyframe keyframe14 = Keyframe.ofFloat(1f, 0.80f);
                PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe0,keyframe1,keyframe3,
                        keyframe4,keyframe5,keyframe6,keyframe7,keyframe8,keyframe9,keyframe10,keyframe11,keyframe12,keyframe14);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mProgressView, holder);
                objectAnimator.setDuration(5*1000);
                objectAnimator.setInterpolator(new DecelerateInterpolator());
                objectAnimator.start();
            }
        });

    }



}
