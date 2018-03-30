package com.fly.viewdemo.practice2;

import android.support.v4.app.Fragment;

import com.fly.viewdemo.base.BaseTabActivity;
import com.fly.viewdemo.practice1.ColorFilterFragment;
import com.fly.viewdemo.practice1.ColourOptimize;
import com.fly.viewdemo.practice1.GetPathFragment;
import com.fly.viewdemo.practice1.LineFragment;
import com.fly.viewdemo.practice1.MaskFragment;
import com.fly.viewdemo.practice1.PathEffectFragment;
import com.fly.viewdemo.practice1.ShadowFragment;
import com.fly.viewdemo.practice1.ShapeFragment;
import com.fly.viewdemo.practice1.XfermodeFragment;

/**
 * Created by Fj on 2018/3/29.
 */

public class PracticeActivity2 extends BaseTabActivity {


    @Override
    protected Fragment[] getFragments() {
        TextFragment textFragment = new TextFragment();
        return new Fragment[]{textFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"Text"};
    }


}
