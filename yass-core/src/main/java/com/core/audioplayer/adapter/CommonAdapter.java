package com.core.audioplayer.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zlc on 2016/6/22.
 * listview和gridview的万能适配器
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;
    protected Activity mContext;
    public CommonAdapter(Activity context, List<T> datas){

        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas!=null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
