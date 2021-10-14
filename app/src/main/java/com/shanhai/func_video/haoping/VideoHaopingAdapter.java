package com.shanhai.func_video.haoping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shanhai.R;
import com.shanhai.func_video.domain.VideoStc;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by y
 * On 2016/02/07 01:20
 */
public class VideoHaopingAdapter extends BaseAdapter {

    public static final String TAG = "JZVD";

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .centerCrop()// 裁剪图片将imageView填满
            .bitmapTransform(new RoundedCorners(18));

    private Context context;

    private List<VideoStc> lst;


    public VideoHaopingAdapter(Context context, List<VideoStc> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);

            convertView = mInflater.inflate(R.layout.item_videodetail, null);
            viewHolder.titleTv = (TextView)convertView.findViewById(R.id.item_videodetail_tv_title);
            viewHolder.fengmianImg = (ImageView)convertView.findViewById(R.id.item_videodetail_img_fengmian);





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VideoStc stc = lst.get(position);

        viewHolder.titleTv.setText(stc.getVideoname());
        Glide.with(convertView.getContext())
                .setDefaultRequestOptions(BANNER_OPTIONS)
                .load(stc.getImageurl())
                .into(viewHolder.fengmianImg);

        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        ImageView fengmianImg;
    }
}
