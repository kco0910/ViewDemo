package com.fly.viewdemo.practice5;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;
import com.fly.viewdemo.practice4.DispatchDrawFragment;
import com.fly.viewdemo.practice4.GistFragment;
import com.fly.viewdemo.practice4.OnDrawForegroundFragment;
import com.fly.viewdemo.practice4.OnDrawFragment;

/**
 * Created by Fj on 2018/3/29.
 */

public class PracticeActivity5 extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        PropertyAnimationFragment propertyAnimationFragment = new PropertyAnimationFragment();
        EvaluatorFragment evaluatorFragment  = new EvaluatorFragment();
        HolderAndSetFragment holderAndSetFragment = new HolderAndSetFragment();
        SkewFragment skewFragment = new SkewFragment();
        CameraFragment cameraFragment = new CameraFragment();
        return new Fragment[]{propertyAnimationFragment,evaluatorFragment,
                holderAndSetFragment,skewFragment,cameraFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"propertyAnimation","evaluator",
                "单View多动画","斜交动画","camera"};
    }
}
