package com.fly.viewdemo.practice5;

import android.view.View;

import com.fly.viewdemo.View.SkewView;
import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/8.
 */

public class SkewFragment extends BaseFragment {

    @Override
    public View createChildView() {
        return new SkewView(getActivity());
//        return new SkewView2(getActivity());
    }
}
