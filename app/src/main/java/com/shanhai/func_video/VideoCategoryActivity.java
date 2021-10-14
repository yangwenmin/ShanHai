package com.shanhai.func_video;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.core.utils.dbtutil.FileUtil;
import com.core.utils.flyn.Eyes;
import com.shanhai.DownloadService;
import com.shanhai.R;
import com.shanhai.application.ConstValues;
import com.shanhai.base.BaseActivity;
import com.shanhai.func_video.domain.CategoryStc;
import com.shanhai.func_video.haoping.VideoHaopingActivity;
import com.shanhai.func_video.haoping.VideoRandomActivity;
import com.shanhai.utils.ShanHaiUtil;
import com.shanhai.utils.ViewUtil;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Random;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 视频分类
 */
public class VideoCategoryActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    private JzvdStd jzvdStd;
    private GridView platform_gv;

    private DownloadService service;
    private MyHandler handler;

    // 是否当前页面 第一次发起请求
    private boolean downCateFile = false;


    private String title;


    /*private String downloadhead;// 请求头 http://192.168.31.128:8080/
    private String downloadcate;// 请求路劲1类别 landking/video/
    private String downloadpath;// 请求路劲2  name1/name2/
    private String downloadname;// 请求名称 _LIST.TXT
    private String localname;   // 保存本地文件名称 name_LIST.TXT*/


    private Random rand;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<VideoCategoryActivity> fragmentRef;

        public MyHandler(VideoCategoryActivity fragment) {
            fragmentRef = new SoftReference<VideoCategoryActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoCategoryActivity fragment = fragmentRef.get();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category_layout);

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
        backBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);


        jzvdStd = (JzvdStd) findViewById(R.id.category_videoplayer);
        platform_gv = (GridView) findViewById(R.id.category_personmanage_gv);
    }

    // 初始化数据
    private void initData() {

        handler = new MyHandler(VideoCategoryActivity.this);
        service = new DownloadService(VideoCategoryActivity.this, handler);

        // 获取上一页传递过来的数据
        Intent i = getIntent();
        title = i.getStringExtra("title");

        titleTv.setText(title);
        confirmTv.setText("刷新");
        confirmTv.setTextSize(14);

        rand = new Random();

        // 获取上一页传递过来的数据
        /*Intent i = getIntent();
        downloadhead = i.getStringExtra("downloadhead");
        downloadcate = i.getStringExtra("downloadcate");
        downloadpath = i.getStringExtra("downloadpath");
        downloadname = i.getStringExtra("downloadname");
        localname = i.getStringExtra("localname");*/

        // 获取屏幕长宽
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = (int) (screenWidth / 1.626);
        // 设置控件高度
        jzvdStd.setMinimumHeight(screenHeight);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) jzvdStd.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = screenHeight;// 控件的高强制设成20
        jzvdStd.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

        // 播放默认视频 已隐藏gone
        jzvdStd.setUp(ConstValues.mp4url, "眼睛", Jzvd.SCREEN_WINDOW_NORMAL);
        jzvdStd.titleTextView.setTextSize(12);
        Glide.with(VideoCategoryActivity.this)
                .load(ConstValues.picurl)
                .into(jzvdStd.thumbImageView);

        if (hasPermission(ConstValues.WRITE_READ_EXTERNAL_PERMISSION)) {
            // 拥有了此权限,那么直接执行业务逻辑

            // 读取本地txt
            readTxtFromPhone();

        } else {
            // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
            requestPermission(ConstValues.WRITE_READ_EXTERNAL_CODE, ConstValues.WRITE_READ_EXTERNAL_PERMISSION);
        }
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

                if (categoryStcs != null && categoryStcs.size() > 0) {

                    // 是否下载所有分类目录
                    if (downCateFile) {
                        for (CategoryStc categoryStc : categoryStcs) {
                            // 存储本地文件名称
                            String fileName = categoryStc.getCategoryname() + ConstValues.TXTNAME;//

                            // 请求
                            service.downloadText(ConstValues.HTTPID + ConstValues.VIDEOPATH + categoryStc.getCategoryname() + "/" + ConstValues.TXTNAME,// 请求url
                                    fileName, // 本地存储名称
                                    false, // false不显示进度框,true显示进度框
                                    ConstValues.WAIT1);// 当前页面handle回调接收
                        }
                    }

                    categoryStcs.add(0, new CategoryStc("大众好评", ConstValues.videoUrlList[rand.nextInt(ConstValues.videoUrlList.length)]));
                    categoryStcs.add(0, new CategoryStc("为您推荐", ConstValues.videoUrlList[rand.nextInt(ConstValues.videoUrlList.length)]));


                    VideoCategoryAdapter jieMianAdapter = new VideoCategoryAdapter(VideoCategoryActivity.this, categoryStcs);
                    platform_gv.setAdapter(jieMianAdapter);
                    // 设置item的点击监听
                    platform_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            CategoryStc stc = categoryStcs.get(position);
                            String name = stc.getCategoryname();

                            if ("为您推荐".equals(name)) {
                                Intent intent = new Intent(VideoCategoryActivity.this, VideoRandomActivity.class);
                                intent.putExtra("name", name);
                                startActivity(intent);
                            } else if ("大众好评".equals(name)) {
                                Intent intent = new Intent(VideoCategoryActivity.this, VideoHaopingActivity.class);
                                intent.putExtra("name", name);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(VideoCategoryActivity.this, VideoListActivity.class);
                                intent.putExtra("name", name);
                                startActivity(intent);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(VideoCategoryActivity.this, "没有找到指定文件", Toast.LENGTH_SHORT).show();
            }
        } else {// 本地不存在
            // 下载文件
            downloadFile();
            // getJsonFile();
        }
    }

    private void getJsonFile() {
        service.getJsonWorks(ConstValues.HTTPID + ConstValues.VIDEOPATH + ConstValues.TXTNAME);// 本页面handle需要用0接收
    }

    // ----↓ 下载文件 ↓——————————————————————————————————————————------------------------------------------

    // 若是当前页第一次请求 则开始下载文件
    private void downloadFile() {
        //发起下载文件的请求
        service.downloadText(ConstValues.HTTPID + ConstValues.VIDEOPATH + ConstValues.TXTNAME, // 请求url
                ConstValues.TXTNAME,// 保存本地文件名称 name_LIST.TXT
                ConstValues.WAIT0);// 本页面handle需要用0接收

    }

    // 手动拥有读写权限后 执行下载文件
    @Override
    public void doWriteSDCard() {
        try {
            // 拥有了此权限,那么直接执行业务逻辑
            // 读取本地txt
            readTxtFromPhone();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 下载文件的回调
    private void downloadFileSuc(Bundle bundle) {

        String formjson = bundle.getString("formjson");
        String status = bundle.getString("status");

        if (ConstValues.SUCCESS.equals(status)) {
            // 读取本地txt
            readTxtFromPhone();
        } else {
            Toast.makeText(VideoCategoryActivity.this, formjson, Toast.LENGTH_SHORT).show();
        }
    }

    // ----↑ 下载文件 ↑------------------------------------------------------------------------------------


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_confirm:// 刷新
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;
                // delete文件
                File docFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + ConstValues.LOCALPATH);
                FileUtil.deleteFile(docFile);

                downCateFile = true;

                //发起下载文件的请求
                downloadFile();
                break;
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
        // return new DefaultNoAnimator();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
