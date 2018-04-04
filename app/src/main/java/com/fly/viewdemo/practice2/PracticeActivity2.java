package com.fly.viewdemo.practice2;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;

/**
 * Created by Fj on 2018/3/29.
 */

public class PracticeActivity2 extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        TextFragment textFragment = new TextFragment();
        Text2Fragment text2Fragment = new Text2Fragment();
        FontMetricsFragment fontMetricsFragment = new FontMetricsFragment();
        TextBoundsFragment textBoundsFragment = new TextBoundsFragment();
        BreakTextFragment breakTextFragment = new BreakTextFragment();
        OtherFragment otherFragment = new OtherFragment();
        return new Fragment[]{textFragment,text2Fragment, fontMetricsFragment,
                textBoundsFragment,breakTextFragment,
                otherFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"Text","Text2","FontMetrics","TextBounds","BreakText","光标","其他的方法"};
    }



}
