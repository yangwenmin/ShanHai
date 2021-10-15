package com.shanhai;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.core.utils.dbtutil.PrefUtils;
import com.core.utils.flyn.Eyes;
import com.shanhai.application.ConstValues;
import com.shanhai.base.BaseActivity;
import com.shanhai.func_game.MsgWebActivity;
import com.shanhai.utils.ViewUtil;
import com.shanhai.func_video.VideoCategoryActivity;

import java.lang.ref.SoftReference;
import java.util.Random;

import cn.jzvd.Jzvd;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .centerCrop() // 裁剪图片将imageView填满
            .bitmapTransform(new RoundedCorners(18));

    private static final RequestOptions BANNER_OPTIONS2 = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop().placeholder(R.drawable.ic_launcher);

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private ImageView headphotoImg;
    private AppCompatTextView username;
    private AppCompatTextView titleTv;

    private ImageView img_video;
    private TextView tv_video;
    private ImageView img_pic;
    private TextView tv_pic;
    private ImageView img_book;
    private TextView tv_book;
    private ImageView img_game;
    private TextView tv_game;


    private DownloadService service;
    private MyHandler handler;
    private Random rand;
    private String headPhoto;


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<MainActivity> fragmentRef;

        public MyHandler(MainActivity fragment) {
            fragmentRef = new SoftReference<MainActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity fragment = fragmentRef.get();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        // 标题栏白底黑字
        Eyes.setStatusBarLightMode(this, Color.WHITE);

        // 初始化视图
        initView();
        // 初始化数据
        initData();
    }

    // 初始化视图
    private void initView() {

        backBtn = (RelativeLayout) findViewById(R.id.main_rl_back);
        headphotoImg = (ImageView) findViewById(R.id.main_img_headphoto);
        username = (AppCompatTextView) findViewById(R.id.main_tv_username);
        confirmBtn = (RelativeLayout) findViewById(R.id.main_rl_confirm);
        confirmTv = (AppCompatTextView) findViewById(R.id.main_bt_confirm);
        titleTv = (AppCompatTextView) findViewById(R.id.main_tv_title);

        backBtn.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.INVISIBLE);
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);


        img_video = (ImageView) findViewById(R.id.main_img_video);
        tv_video = (TextView) findViewById(R.id.main_tv_video);
        img_pic = (ImageView) findViewById(R.id.main_img_pic);
        tv_pic = (TextView) findViewById(R.id.main_tv_pic);
        img_book = (ImageView) findViewById(R.id.main_img_book);
        tv_book = (TextView) findViewById(R.id.main_tv_book);
        img_game = (ImageView) findViewById(R.id.main_img_game);
        tv_game = (TextView) findViewById(R.id.main_tv_game);

        img_video.setOnClickListener(this);
        img_pic.setOnClickListener(this);
        img_book.setOnClickListener(this);
        img_game.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        tv_pic.setOnClickListener(this);
        tv_book.setOnClickListener(this);
        tv_game.setOnClickListener(this);

    }

    // 初始化数据
    private void initData() {

        handler = new MyHandler(MainActivity.this);
        service = new DownloadService(MainActivity.this, handler);
        rand = new Random();

        titleTv.setText("王者小知识");

        // 显示用户名
        String name = PrefUtils.getString(MainActivity.this,ConstValues.HTTPHEAD, "http://192.168.31.128:8080/");
        if(ConstValues.HTTPHEAD_HOME.equals(name)){
            username.setText("Home");
        }else{
            username.setText("Mac");
        }


        // 头像
        int ab = rand.nextInt(ConstValues.videoUrlList.length);
        // headPhoto = ConstValues.videoUrlList[ab];
        headPhoto = ConstValues.HEADPHOTO;

        Glide.with(MainActivity.this)
                .load(headPhoto)
                .apply(BANNER_OPTIONS2)
                .into(headphotoImg);

        Glide.with(MainActivity.this)
                .setDefaultRequestOptions(BANNER_OPTIONS)
                // .load(ConstValues.homeImageList[rand.nextInt(ConstValues.homeImageList.length)])
                .load(ConstValues.homeImageList[0])
                .into(img_video);

        Glide.with(MainActivity.this)
                .setDefaultRequestOptions(BANNER_OPTIONS)
                // .load(ConstValues.homeImageList[rand.nextInt(ConstValues.homeImageList.length)])
                .load(ConstValues.homeImageList[1])
                .into(img_book);

        Glide.with(MainActivity.this)
                .setDefaultRequestOptions(BANNER_OPTIONS)
                // .load(ConstValues.homeImageList[rand.nextInt(ConstValues.homeImageList.length)])
                .load(ConstValues.homeImageList[2])
                .into(img_pic);

        Glide.with(MainActivity.this)
                .setDefaultRequestOptions(BANNER_OPTIONS)
                // .load(ConstValues.homeImageList[rand.nextInt(ConstValues.homeImageList.length)])
                .load(ConstValues.homeImageList[3])
                .into(img_game);

    }


    private Dialog dialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_rl_back:// 头像
                if (ViewUtil.isDoubleClick(v.getId(), 800))
                    return;

                // 设置http
                String name = username.getText().toString();
                if("Mac".equals(name)){
                    username.setText("Home");
                    PrefUtils.putString(MainActivity.this,ConstValues.HTTPHEAD, ConstValues.HTTPHEAD_HOME);
                }else{
                    username.setText("Mac");
                    PrefUtils.putString(MainActivity.this,ConstValues.HTTPHEAD, ConstValues.HTTPHEAD_MAC);
                }

                dialog = new Dialog(MainActivity.this, R.style.edit_AlertDialog_style);
                dialog.setContentView(R.layout.dialog_bigimage);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.my_image);

                //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
                dialog.setCanceledOnTouchOutside(true);

                Window w = dialog.getWindow();
                WindowManager.LayoutParams lp = w.getAttributes();
                lp.x = 0;
                lp.y = 40;
                dialog.onWindowAttributesChanged(lp);

                Glide.with(MainActivity.this)
                        .load(headPhoto)
                        .apply(BANNER_OPTIONS2)
                        .into(imageView);

                //大图的点击事件（点击让他消失）
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;

            case R.id.main_tv_video:// 视频
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                Intent intent = new Intent(MainActivity.this, VideoCategoryActivity.class);
                intent.putExtra("title", tv_video.getText().toString());      // 保存本地文件名称 _LIST.TXT
                startActivity(intent);

                break;



            // -------------------------------------------------------------------------------------
            case R.id.main_img_video:// 视频教学
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                Intent videoI = new Intent(MainActivity.this, MsgWebActivity.class);
                videoI.putExtra("weburl", "https://pvp.qq.com/m/");
                startActivity(videoI);

                break;

            case R.id.main_img_book:// 文章攻略
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                Intent bookI = new Intent(MainActivity.this, MsgWebActivity.class);
                bookI.putExtra("weburl", "https://pvp.qq.com/m/");
                startActivity(bookI);

                break;

            case R.id.main_img_pic:// 原画壁纸
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                Intent picI = new Intent(MainActivity.this, MsgWebActivity.class);
                picI.putExtra("weburl", "https://pvp.qq.com/m/");
                startActivity(picI);

                break;

            case R.id.main_img_game://
                if (ViewUtil.isDoubleClick(v.getId(), 2500))
                    return;

                Intent bibizhiintent = new Intent(MainActivity.this, MsgWebActivity.class);
                bibizhiintent.putExtra("weburl", "https://game.gtimg.cn/images/yxzj/web201706/images/comm/code.png");
                startActivity(bibizhiintent);

                break;
        }
    }


    @Override
    public void onBackPressedSupport() {

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            if (Jzvd.backPress()) {
                return;
            }
            // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
            super.onBackPressedSupport();
            // finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(MainActivity.this, "再按一次返回键退出应用", Toast.LENGTH_SHORT).show();
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
