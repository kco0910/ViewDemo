package com.fly.viewdemo.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseTabActivity;

/**
 * Created by Fj on 2018/4/12.
 */

public class ViewActivity extends BaseTabActivity {

    @Override
    protected Fragment[] getFragments() {
        PraiseFragment praiseFragment = new PraiseFragment();
        RuleProgressViewFragment ruleProgressViewFragment = new RuleProgressViewFragment();
        return new Fragment[]{praiseFragment,ruleProgressViewFragment};
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"点赞","尺子"};
    }


    public static class PraiseFragment extends BaseFragment{

        @Override
        public View createChildView() {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_praise_layout, null);
            FrameLayout frameLayout = inflate.findViewById(R.id.fl_contain);
            final PraiseView praiseView = new PraiseView(getActivity());
            frameLayout.addView(praiseView,new FrameLayout.LayoutParams(200,200));
            inflate.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    praiseView.praise();
                }
            });
            inflate.findViewById(R.id.btn_reduce).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    praiseView.cancelPraise();
                }
            });
            return inflate;
        }
    }

    public static class RuleProgressViewFragment extends BaseFragment{

        @Override
        public View createChildView() {
            return new RuleProgressView(getActivity());
        }
    }
}
