package com.shanhai.func_video.haoping;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.core.utils.flyn.Eyes;
import com.shanhai.R;
import com.shanhai.application.ConstValues;
import com.shanhai.base.BaseActivity;
import com.shanhai.func_video.domain.CategoryStc;
import com.shanhai.utils.ShanHaiUtil;
import com.shanhai.utils.ViewUtil;
import com.shanhai.func_video.domain.VideoStc;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.jzvd.Jzvd;

/**
 * Created by y on 16/7/31.
 */
public class VideoRandomActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    ListView listView;

    private int count = 10;

    SensorManager sensorManager;
    Jzvd.JZAutoFullscreenListener sensorEventListener;

    private Random rand;

    // 上一页传递过来的名称
    private String name;


    private MyHandler handler;


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<VideoRandomActivity> fragmentRef;

        public MyHandler(VideoRandomActivity fragment) {
            fragmentRef = new SoftReference<VideoRandomActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoRandomActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            Bundle bundle = msg.getData();

            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0: // 下载文件的回调
                    // fragment.downloadFileSuc(bundle);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_normal);

        // 标题栏白底黑字
        Eyes.setStatusBarLightMode(this, Color.WHITE);

        // 初始化视图
        initView();
        // 初始化数据
        initData();

    }

    // 初始化视图
    private void initView() {

        backBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) findViewById(R.id.top_navigation_tv_title);
        backBtn.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

        listView = findViewById(R.id.listview);
    }

    // 初始化数据
    private void initData() {

        rand = new Random();

        // 获取上一页传递过来的数据
        Intent i = getIntent();
        name = i.getStringExtra("name");

        titleTv.setText(name);
        confirmTv.setText("刷新");
        confirmTv.setTextSize(14);

        handler = new MyHandler(VideoRandomActivity.this);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new Jzvd.JZAutoFullscreenListener();

        // 读取本地txt
        readTxtFromPhone();
    }


    // 读取本地txt
    private void readTxtFromPhone() {
        // 拼接文件路径
        String pdfpath = ViewUtil.parseFilePath(ConstValues.TXTNAME);

        File docFile = new File(pdfpath);

        if (docFile.exists()) {//存在本地;
            try {
                // 读取文件内容
                String result = ShanHaiUtil.parseFileToString(docFile);
                // 将字符串分割成数组
                String[] read = ShanHaiUtil.splitStringToArray(result);

                // 将分类数组转成分类集合
                final ArrayList<CategoryStc> categoryStcs = ShanHaiUtil.parseStringArrayToList(read);


                final ArrayList<VideoStc> videoStcs = new ArrayList<>();

                // 第一步: 从多个分类中,先随机获取一个分类
                if (categoryStcs != null && categoryStcs.size() > 0) {

                    VideoStc stc;
                    for (int i = 0; i < count; i++) {

                        stc = new VideoStc();
                        String categoryname = categoryStcs.get(rand.nextInt(categoryStcs.size())).getCategoryname();
                        stc.setCategory(categoryname);

                        String fileName = categoryname + ConstValues.TXTNAME;
                        // 拼接文件路径
                        String filepath = ViewUtil.parseFilePath(fileName);

                        File listFile = new File(filepath);

                        if (listFile.exists()) {//存在本地;
                            try {

                                // 读取文件内容
                                String listresult = ShanHaiUtil.parseFileToString(listFile);
                                // 将字符串分割成数组
                                String[] listread = ShanHaiUtil.splitStringToArray(listresult);

                                // 第二步: 获取该分类下的所有视频 将字符串数组转成视频对象集合
                                final ArrayList<VideoStc> allVideo = ShanHaiUtil.getVideoListByVideoArray(ShanHaiUtil.getHttpid(this), categoryname, listread);

                                // 第三步: 从该分类的所有视频随机获取一个
                                if (allVideo != null && allVideo.size() > 0) {

                                    int select = rand.nextInt(allVideo.size());
                                    VideoStc video = allVideo.get(select);
                                    stc.setVideoname(video.getVideoname());
                                    stc.setVideourl(video.getVideourl());
                                    stc.setImageurl(video.getImageurl());

                                    videoStcs.add(stc);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(VideoRandomActivity.this, "没有找到指定文件", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
                if (videoStcs != null && videoStcs.size() > 0) {

                    listView.setAdapter(new VideoRandomAdapter(this, videoStcs));


                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {

                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            Jzvd.onScrollReleaseAllVideos(view, firstVisibleItem, visibleItemCount, totalItemCount);
                        }
                    });

                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(VideoRandomActivity.this, "没有找到指定文件", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back://
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;
                this.finish();

                break;
            case R.id.top_navigation_rl_confirm://
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                // 读取本地txt
                readTxtFromPhone();

                break;
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        Jzvd.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
