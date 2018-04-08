package com.fly.viewdemo.practice4;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;
import com.fly.viewdemo.practice3.ClipFragment;
import com.fly.viewdemo.practice3.MatrixFragment;

/**
 * Created by Fj on 2018/3/29.
 */

public class PracticeActivity4 extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        OnDrawFragment onDrawFragment = new OnDrawFragment();
        DispatchDrawFragment dispatchDrawFragment  = new DispatchDrawFragment();
        OnDrawForegroundFragment onDrawForegroundFragment = new OnDrawForegroundFragment();
        GistFragment gistFragment = new GistFragment();
        return new Fragment[]{onDrawFragment,dispatchDrawFragment,
                onDrawForegroundFragment,gistFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"super.onDraw","DispatchDraw","onDrawForeground","要点"};
    }



}
