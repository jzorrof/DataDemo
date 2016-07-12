package com.example.fanzhong.contentproviderdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanzhong on 16-7-12.
 */
public class DemoListAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private DemoAdapter da;
    private List<Map<String, Object>> data;

    public DemoListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        da = new DemoAdapter(context);
    }

    @Override
    public int getCount() {
        int count = 0;
        for (DemoData demoData : da.getAllArticles()){
            count = count + 1;

            Log.e("Fanzhong" , "count: " + demoData.getTitle());
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return da.getArticleByPos(position);
    }

    @Override
    public long getItemId(int position) {
        return da.getArticleByPos(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= null;
        DemoData dd = (DemoData) getItem(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.title = (TextView) convertView.findViewById(R.id.info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText("ID: " + dd.getId());
        viewHolder.title.setText("Title: " + dd.getTitle());


        return convertView;
    }

    static class ViewHolder
    {
        public TextView id;
        public TextView title;
    }

}
