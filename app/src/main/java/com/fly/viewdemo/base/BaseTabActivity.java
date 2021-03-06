package com.fly.viewdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fly.viewdemo.R;

/**
 * Created by Fj on 2018/3/29.
 */

public abstract class BaseTabActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_base);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager  = findViewById(R.id.viewpager);
        init();
    }

    private void init(){
        final Fragment[] fragments = getFragments();
        final String[] titles = getTitles();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(10);

    }

    protected abstract Fragment[] getFragments();

    protected abstract String[] getTitles();


}
