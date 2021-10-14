package com.core.audioplayer.adapter;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.core.R;
import com.core.newvoice.MediaBusiness;
import com.core.audioplayer.info.FendaListInfo;
import com.core.audioplayer.utils.DensityUtil;
import com.core.audioplayer.utils.LogUtil;
import com.core.audioplayer.utils.StringUtil;

import java.util.List;

/**
 * Created by zlc on 2017/9/16.
 */

public class VoiceListAdapter extends CommonAdapter<FendaListInfo.ObjsEntity> {

    public VoiceListAdapter(Activity context, List<FendaListInfo.ObjsEntity> datas) {
        super(context, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.getHolder(mContext, convertView, parent, R.layout.fenda_list_item, position);
        FendaListInfo.ObjsEntity obj = mDatas.get(position);
        holder.setText(R.id.id_tv_question,obj.question);
        String userName = obj.aUser != null ? obj.aUser.userName : "";
        String title = obj.aUser != null ? obj.aUser.title : "";
        holder.setText(R.id.id_tv_expert_name,userName);
        holder.setText(R.id.id_tv_expert_dec,title);
        holder.setText(R.id.id_tv_listen_num,obj.listen+"人听过");
        holder.setText(R.id.id_tv_agree_num,obj.zan+"人赞过");
        RelativeLayout id_rl_voice_kuang = holder.getView(R.id.id_rl_voice_kuang);
        final ImageView iv_voice = holder.getView(R.id.iv_voice);

        setVoiceAnimation(iv_voice,obj);

        Float videoTime = TextUtils.isEmpty(obj.videoTime) || !StringUtil.isNumber(obj.videoTime) ? 0 : Float.valueOf(obj.videoTime);
        voiceExtend(videoTime, id_rl_voice_kuang);

        if(onClickListener!=null){
            id_rl_voice_kuang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(view,iv_voice,position);
                }
            });
        }

        return holder.getConvertView();
    }

    private void setVoiceAnimation(ImageView iv_voice, FendaListInfo.ObjsEntity obj) {

        //处理动画复用问题
        AnimationDrawable animationDrawable;
        if(obj.isSelect){
            iv_voice.setBackgroundResource(R.drawable.animation_voice);
            animationDrawable = (AnimationDrawable) iv_voice.getBackground();
            if(MediaBusiness.isPlaying() && animationDrawable!=null){
                animationDrawable.start();
            }else{
                iv_voice.setBackgroundResource(R.drawable.voice_listen);
                animationDrawable.stop();
            }
        }else{
            iv_voice.setBackgroundResource(R.drawable.voice_listen);
        }
    }

    private void voiceExtend(float seconds,RelativeLayout rl) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rl.getLayoutParams();
        lp.gravity = Gravity.CENTER_VERTICAL;
        if(seconds <= 60){
            lp.width = DensityUtil.dp2px(mContext, 142) + DensityUtil.dp2px(mContext, seconds * (100f / 120f));
        }else{
            lp.width = DensityUtil.dp2px(mContext,192);
        }
        rl.setLayoutParams(lp);
    }

    //点击了某一个条目 这个条目isSelect=true 上一个条目isSelect需要改为false 防止滑动过程中 帧动画复用问题
    public void selectItem(int position, int lastPosition) {

        LogUtil.e("selectItem"," ;lastPosition="+lastPosition+" ;position="+position);
        if(lastPosition >= 0 && lastPosition < mDatas.size() && lastPosition != position){
            FendaListInfo.ObjsEntity bean = mDatas.get(lastPosition);
            bean.isSelect = false;
            mDatas.set(lastPosition, bean);
            notifyDataSetChanged();
        }

        if(position < mDatas.size() && position != lastPosition){
            FendaListInfo.ObjsEntity bean = mDatas.get(position);
            bean.isSelect = true;
            mDatas.set(position,bean);
        }
    }

    public interface OnClickListener{
        void onClick(View v, ImageView iv_voice, int position);
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
