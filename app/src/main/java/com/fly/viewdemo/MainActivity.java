package com.fly.viewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fly.viewdemo.arc.ArcActivity;
import com.fly.viewdemo.model.IntentData;
import com.fly.viewdemo.path.PathActivity;
import com.fly.viewdemo.picture.PictureActivity;
import com.fly.viewdemo.practice1.Practice1Activity;
import com.fly.viewdemo.practice2.PracticeActivity2;
import com.fly.viewdemo.practice3.PracticeActivity3;
import com.fly.viewdemo.practice4.PracticeActivity4;
import com.fly.viewdemo.practice5.PracticeActivity5;
import com.fly.viewdemo.taiji.TaiJiActivity;
import com.fly.viewdemo.test.TestActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv);
        ArrayList<IntentData> datas = new ArrayList<>();
        datas.add(new IntentData("练习1",Practice1Activity.class));
        datas.add(new IntentData("文本相关练习",PracticeActivity2.class));
        datas.add(new IntentData("练习3",PracticeActivity3.class));
        datas.add(new IntentData("onDraw相关练习",PracticeActivity4.class));
        datas.add(new IntentData("动画", PracticeActivity5.class));
        datas.add(new IntentData("测试",TestActivity.class));
        datas.add(new IntentData("Path",PathActivity.class));
        datas.add(new IntentData("太极",TaiJiActivity.class));
        datas.add(new IntentData("Picture",PictureActivity.class));
        datas.add(new IntentData("弧度相关", ArcActivity.class));
        ArrayAdapter<IntentData> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentData intentData = (IntentData) parent.getItemAtPosition(position);
                if (intentData != null && intentData.cls!= null){
                    startActivity(new Intent(MainActivity.this,intentData.cls));
                }
            }
        });
    }

}
