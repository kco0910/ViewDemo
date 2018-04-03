package com.fly.viewdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fly.viewdemo.R;
import com.orhanobut.logger.Logger;

/**
 * Created by Fj on 2018/4/3.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView");
        View containView = inflater.inflate(R.layout.fragment_base_layout,null);
        return containView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.i("onViewCreated");
        FrameLayout frameLayout = view.findViewById(R.id.fragment_contain);
        View childView = createChildView();
        if (childView != null){
            frameLayout.addView(childView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    /**创建子view*/
    public abstract View createChildView();

}
