package com.shanhai.func_video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shanhai.R;
import com.shanhai.func_video.domain.VideoStc;

import java.util.List;
import java.util.Random;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by y
 * On 2016/02/07 01:20
 */
public class VideoListAdapter extends BaseAdapter {

    public static final String TAG = "JZVD";

    private Context context;

    private List<VideoStc> videoStcs;



    public VideoListAdapter(Context context, List<VideoStc> videoStcs) {
        this.context = context;
        this.videoStcs = videoStcs;

    }

    @Override
    public int getCount() {
        return videoStcs.size();
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
            convertView = mInflater.inflate(R.layout.item_videoview, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VideoStc item = (VideoStc)videoStcs.get(position);

        viewHolder.jzvdStd = convertView.findViewById(R.id.videoplayer);
        viewHolder.jzvdStd.setUp(
                item.getVideourl(),
                item.getVideoname(), Jzvd.SCREEN_WINDOW_LIST);
        viewHolder.jzvdStd.titleTextView.setTextSize(12);

        Glide.with(convertView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .centerCrop()
                        // .placeholder(R.drawable.home_list_img_default_02)
                        // .fitCenter()
                )
                // .load(videoThumbs[position])
                .load(item.getImageurl())
                /// .centerCrop()
                .into(viewHolder.jzvdStd.thumbImageView);

        viewHolder.jzvdStd.positionInList = position;
        return convertView;
    }

    class ViewHolder {
        JzvdStd jzvdStd;
    }
}
