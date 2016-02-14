package com.mouse.cookie.elasticlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/2/14.
 */
public class TestAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private List<View> mLists;

    public TestAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mLists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mLists.add(mLayoutInflater.inflate(R.layout.layout_item, null));
        }
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public View getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position);
    }

}
