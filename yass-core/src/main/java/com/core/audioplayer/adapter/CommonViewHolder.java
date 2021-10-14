package com.core.audioplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zlc on 2016/6/22.
 * 通用的ViewHolder
 */
public class CommonViewHolder {

    private final SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    private Context mContext;
    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position){

        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    //获取ViewHolder对象
    public static  CommonViewHolder getHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position){
        if(convertView==null){
            return new CommonViewHolder(context,parent,layoutId,position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的Id获取对应的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){

        View view = mViews.get(viewId,null);
        if(view==null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }


    public View getConvertView(){
        return mConvertView;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        if(view!=null)
            view.setText(text);
        return this;
    }


    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setTextColor(int viewId, String color)
    {
        TextView view = getView(viewId);
        if(view!=null)
            view.setTextColor(Color.parseColor(color));
        return this;
    }


    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageResource(int viewId,int resId){

        ImageView imageView = getView(viewId);
        if(imageView!=null)
            imageView.setImageResource(resId);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageBitmap(int viewId,Bitmap bitmap){

        ImageView imageView = getView(viewId);
        if(imageView!=null)
            imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 返回位置信息
     */
    public int getPosition() {

        return mPosition;
    }
}
