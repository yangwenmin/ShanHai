package com.shanhai.func_video;

import android.app.Activity;
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
import com.shanhai.func_video.domain.CategoryStc;

import java.util.List;
import java.util.Random;

/**
 *
 */
public class VideoCategoryAdapter extends BaseAdapter {

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .centerCrop() // 裁剪图片将imageView填满
            .bitmapTransform(new RoundedCorners(14));

    private Activity context;
    private List<CategoryStc> dataLst;

    private Random rand;


    public VideoCategoryAdapter(Activity context, List<CategoryStc> valueLst) {
        this.context = context;
        this.dataLst = valueLst;

        rand = new Random();

    }

    // item的个数
    @Override
    public int getCount() {
        return dataLst.size();
    }

    // 根据位置获取对象
    @Override
    public CategoryStc getItem(int position) {
        return dataLst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 初始化每一个item的布局(待优化)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_layout, null);
            holder.video_img = (ImageView) convertView.findViewById(R.id.item_video_img);
            holder.operation_text = (TextView) convertView.findViewById(R.id.item_video_tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CategoryStc item = (CategoryStc) dataLst.get(position);

        Glide.with(context)
                /*.setDefaultRequestOptions(new RequestOptions()
                        .centerCrop()// 裁剪图片将imageView填满
                        .bitmapTransform(new RoundedCorners(18))// 圆角
                )*/
                .setDefaultRequestOptions(BANNER_OPTIONS)
                .load(item.getImageurl())
                .into(holder.video_img);

        holder.operation_text.setText(item.getCategoryname());


        return convertView;
    }

    private class ViewHolder {
        private ImageView video_img;
        private TextView operation_text;
    }

}
