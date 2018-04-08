package com.fly.viewdemo.practice3;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;
import com.fly.viewdemo.practice2.BreakTextFragment;
import com.fly.viewdemo.practice2.FontMetricsFragment;
import com.fly.viewdemo.practice2.OtherFragment;
import com.fly.viewdemo.practice2.Text2Fragment;
import com.fly.viewdemo.practice2.TextBoundsFragment;
import com.fly.viewdemo.practice2.TextFragment;

/**
 * Created by Fj on 2018/3/29.
 */

public class PracticeActivity3 extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        ClipFragment clipFragment  = new ClipFragment();
        MatrixFragment matrixFragment = new MatrixFragment();
        return new Fragment[]{clipFragment,matrixFragment};
    }

    @Override

    protected String[] getTitles() {
        return new String[]{"clipRect","matrix"};
    }



}
