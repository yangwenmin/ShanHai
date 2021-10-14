package com.shanhai.func_video.player;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shanhai.R;
import com.shanhai.base.BaseActivity;
import com.shanhai.websetting.JsBridge;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


/**
 * web Activity
 */
public class PlayerActivity extends BaseActivity implements View.OnClickListener, JsBridge {


    private JzvdStd jzvdStd;

    Jzvd.JZAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;

    private String videoname;
    private String videourl;
    private String imageurl;

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<PlayerActivity> fragmentRef;

        public MyHandler(PlayerActivity fragment) {
            fragmentRef = new SoftReference<PlayerActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            PlayerActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            Bundle bundle = msg.getData();

            // 处理UI 变化
            switch (msg.what) {
                case 0:
                    fragment.finishSuc();
                    break;
                case 1:
                    fragment.showShareWx(bundle);
                    break;
                case 2:
                    // fragment.showTzAdapter();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_player);

        jzvdStd = (JzvdStd) findViewById(R.id.main_videoplayer);


        // 获取上一页传递过来的数据
        Intent i = getIntent();
        videoname = i.getStringExtra("videoname");
        videourl = i.getStringExtra("videourl");
        imageurl = i.getStringExtra("imageurl");


        LinkedHashMap map = new LinkedHashMap();

        /*map.put("高清", proxyUrl);
        map.put("标清", VideoConstant.videoUrls[0][6]);
        map.put("普清", VideoConstant.videoUrlList[0]);*/
        map.put("高清", videourl);
        map.put("标清", videourl);
        map.put("普清", videourl);
        JZDataSource jzDataSource = new JZDataSource(map, videoname);
        jzDataSource.looping = true;
        jzDataSource.currentUrlIndex = 2;
        jzDataSource.headerMap.put("key", "value");//header
        jzvdStd.setUp(jzDataSource
                , JzvdStd.SCREEN_WINDOW_NORMAL);
        // Glide.with(this).load(imageurl).into(jzvdStd.thumbImageView);

        Glide.with(this)
                .setDefaultRequestOptions(new RequestOptions()
                                .centerCrop()
                        // .placeholder(R.drawable.home_list_img_default_02)
                        // .fitCenter()
                )
                // .load(videoThumbs[position])
                .load(imageurl)
                /// .centerCrop()
                .into(jzvdStd.thumbImageView);


        jzvdStd.seekToInAdvance = 0;



        jzvdStd.startVideo();


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new Jzvd.JZAutoFullscreenListener();



    }


    @Override
    public void setTextValue(String value) {

        /*handler.post(new Runnable() {
            @Override
            public void run() {
                YdWebActivity.this.finish();
            }
        });*/

        // handler.sendEmptyMessage(ConstValues.WAIT0);

    }

    @Override
    public void setCallShare(String value) {

        /*handler.post(new Runnable() {
            @Override
            public void run() {
                YdWebActivity.this.finish();
            }
        });*/

    }

    private void showShareWx(Bundle bundle) {

    }


    private void finishSuc() {
        PlayerActivity.this.finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //home back
        Jzvd.goOnPlayOnResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        Jzvd.clearSavedProgress(this, null);
        //home back
        Jzvd.goOnPlayOnPause();
    }

    @Override
    public void onBackPressedSupport() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
