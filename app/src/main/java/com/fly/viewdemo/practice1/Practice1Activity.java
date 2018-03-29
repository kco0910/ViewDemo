package com.fly.viewdemo.practice1;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;

/**
 * Created by Fj on 2018/3/29.
 */

public class Practice1Activity extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        ShapeFragment shapeFragment = new ShapeFragment();
        ColorFilterFragment colorFilterFragment = new ColorFilterFragment();
        XfermodeFragment xfermodeFragment  = new XfermodeFragment();
        return new Fragment[]{shapeFragment,colorFilterFragment,xfermodeFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"Shape","ColorFilter","Xfermode"};
    }


}
