package com.shanhai.func_video;

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

import com.core.utils.dbtutil.FileUtil;
import com.core.utils.dbtutil.PrefUtils;
import com.core.utils.flyn.Eyes;
import com.shanhai.DownloadService;
import com.shanhai.R;
import com.shanhai.application.ConstValues;
import com.shanhai.base.BaseActivity;
import com.shanhai.func_video.domain.VideoStc;
import com.shanhai.utils.ShanHaiUtil;
import com.shanhai.utils.ViewUtil;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;

/**
 * Created by y on 16/7/31.
 */
public class VideoListActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    ListView listView;

    SensorManager sensorManager;
    Jzvd.JZAutoFullscreenListener sensorEventListener;


    private DownloadService service;
    private MyHandler handler;

    // 是否当前页面 第一次发起请求
    private boolean firstSend = true;
    private String fileName;

    // 上一页传递过来的名称
    private String name;



    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<VideoListActivity> fragmentRef;

        public MyHandler(VideoListActivity fragment) {
            fragmentRef = new SoftReference<VideoListActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoListActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            Bundle bundle = msg.getData();

            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0: // 下载文件的回调
                    fragment.downloadFileSuc(bundle);
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
        confirmBtn.setVisibility(View.INVISIBLE);
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

        listView = findViewById(R.id.listview);
    }

    // 初始化数据
    private void initData() {

        // 获取上一页传递过来的数据
        Intent i = getIntent();
        name = i.getStringExtra("name");

        titleTv.setText(name);

        handler = new MyHandler(VideoListActivity.this);
        service = new DownloadService(VideoListActivity.this, handler);




        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new Jzvd.JZAutoFullscreenListener();

        // 本地文件名称
        fileName = name + ConstValues.TXTNAME;

        // 读取本地txt
        readTxtFromPhone();
    }


    // 读取本地txt
    private void readTxtFromPhone() {
        // 拼接文件路径
        String pdfpath = ViewUtil.parseFilePath(fileName);

        File docFile = new File(pdfpath);

        if (docFile.exists()) {//存在本地;
            try {
                // 读取文件内容
                String result = ShanHaiUtil.parseFileToString(docFile);
                // 将字符串分割成数组
                String[] read = ShanHaiUtil.splitStringToArray(result);
                // 将字符串数组转成视频对象集合
                final ArrayList<VideoStc> videoStcs = ShanHaiUtil.getVideoListByVideoArray(ShanHaiUtil.getHttpid(this) ,name, read);

                if (videoStcs != null && videoStcs.size() > 0) {

                    listView.setAdapter(new VideoListAdapter(this, videoStcs));

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
                Toast.makeText(VideoListActivity.this, "没有找到指定文件", Toast.LENGTH_SHORT).show();
            }
        } else {// 本地不存在
            // 下载文件
            sendDownloadFile();
        }
    }


    // ----↓ 下载文件 ↓——————————————————————————————————————————------------------------------------------

    // 若是当前页第一次请求 则开始下载文件
    private void sendDownloadFile() {

        // 是否当前页面 第一次发起请求
        if (!firstSend) {
            return;
        }

        if (hasPermission(ConstValues.WRITE_READ_EXTERNAL_PERMISSION)) {
            // 拥有了此权限,那么直接执行业务逻辑
            sendDownRequest();//发起下载文件的请求

        } else {
            // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
            requestPermission(ConstValues.WRITE_READ_EXTERNAL_CODE, ConstValues.WRITE_READ_EXTERNAL_PERMISSION);
        }
    }

    // 手动拥有读写权限后执行
    @Override
    public void doWriteSDCard() {
        try {
            // 拥有了此权限,那么直接执行业务逻辑
            sendDownRequest();//发起下载文件的请求

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发起下载文件的请求
    private void sendDownRequest() {
        // 请求
        service.downloadText(ShanHaiUtil.getHttpid(this) + ConstValues.VIDEOPATH + name + "/" + ConstValues.TXTNAME,// 请求url
                fileName,// 本地存储名称
                ConstValues.WAIT0);// handle回调接收

    }


    // 下载文件的回调
    private void downloadFileSuc(Bundle bundle) {
        // 是否当前页面 第一次发起请求
        firstSend = false;

        String formjson = bundle.getString("formjson");
        String status = bundle.getString("status");

        if (ConstValues.SUCCESS.equals(status)) {
            // 读取本地txt
            readTxtFromPhone();
        } else {
            Toast.makeText(VideoListActivity.this, formjson, Toast.LENGTH_SHORT).show();
        }
    }

    // ----↑ 下载文件 ↑------------------------------------------------------------------------------------


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back://
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;
                this.finish();

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
