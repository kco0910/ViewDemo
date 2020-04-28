package com.fly.viewdemo.layoutManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.fly.viewdemo.R;

/**
 * https://blog.csdn.net/u011387817/article/details/81875021
 * Created by Fj on 2018/11/1.
 */
public class LayoutManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);

        RecyclerView recyclerView =  findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager();
    }
}
