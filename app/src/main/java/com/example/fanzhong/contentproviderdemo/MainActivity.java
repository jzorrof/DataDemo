package com.example.fanzhong.contentproviderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.widget.ListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private DemoAdapter da;
    private LinkedList<DemoData> listDemoData;
    private ListView listView;
    private DemoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        da = new DemoAdapter(this);
        listDemoData = new LinkedList<DemoData>();
        listDemoData.add(new DemoData(1, "test"));
        listDemoData.add(new DemoData(2, "test2"));

        listDemoData = da.getAllArticles();
        Log.e("Fanzhong", "title: " + listDemoData.size());
        for(DemoData da: listDemoData){
            Log.e("Fanzhong", "title: " + da.getTitle());
        }

        listView = (ListView) findViewById(R.id.lv);
        adapter = new DemoListAdapter(this);
        listView.setAdapter(adapter);
    }
}
