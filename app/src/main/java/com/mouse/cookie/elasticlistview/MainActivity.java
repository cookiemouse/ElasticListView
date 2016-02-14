package com.mouse.cookie.elasticlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mouse.cookie.lib.ElasticListView;

public class MainActivity extends AppCompatActivity {

    private ElasticListView mElasticListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用初始化方法
        intialize();
    }

    //初始化，在布局文件中找到相应控件
    private void intialize() {
        mElasticListView = (ElasticListView) findViewById(R.id.elv_mainactivity);

        TestAdapter adapter = new TestAdapter(this);

        mElasticListView.setAdapter(adapter);
    }
}
