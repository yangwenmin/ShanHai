package com.core.audioplayer.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.R;
import com.core.audioplayer.info.RecordVoiceInfo;
import com.core.audioplayer.utils.DensityUtil;

import java.util.List;

/**
 * Created by zlc on 2017/9/19.
 */
public class RecordAdapter extends CommonAdapter<RecordVoiceInfo>{


    public RecordAdapter(Activity context, List<RecordVoiceInfo> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.getHolder(mContext, convertView, parent, R.layout.list_voice_item, position);
        RecordVoiceInfo info = mDatas.get(position);
        LinearLayout id_ll_voice_listen = holder.getView(R.id.id_ll_voice_listen);
        voiceExtend(info.getTime(),id_ll_voice_listen);

        return holder.getConvertView();
    }

    private void voiceExtend(float seconds,LinearLayout ll) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ll.getLayoutParams();
        lp.gravity = Gravity.CENTER_VERTICAL;
        if(seconds <= 60){
            lp.width = DensityUtil.dp2px(mContext, 142) + DensityUtil.dp2px(mContext, seconds * (100f / 120f));
        }else{
            lp.width = DensityUtil.dp2px(mContext,192);
        }
        ll.setLayoutParams(lp);
    }

}
