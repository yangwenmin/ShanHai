package com.shanhai.func_other;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.core.utils.flyn.Eyes;
import com.shanhai.R;
import com.shanhai.application.ConstValues;
import com.shanhai.base.BaseActivity;

import java.lang.ref.SoftReference;


public class ShowActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_top;

    private String username;
    private String password;

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public class MyHandler extends Handler {

        // 软引用
        SoftReference<ShowActivity> fragmentRef;

        public MyHandler(ShowActivity fragment) {
            fragmentRef = new SoftReference<ShowActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ShowActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            Bundle bundle = msg.getData();
            super.handleMessage(msg);
            // 处理UI 变化
            switch (msg.what) {
                // 提示信息
                case ConstValues.WAIT0:
                    //
                    // fragment.getPicSuc(bundle);
                    break;
                case ConstValues.WAIT1:
                    // 关闭缓冲界面
                    // LatteLoader.stopLoading();
                    break;

                default:
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_layout);

        // 标题栏白底黑字
        // Eyes.setStatusBarLightMode(this, Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // 接收传递过来的参数
        initCreate();
        // 初始化视图
        initView();
        // 初始化数据
        initData();
    }

    private void initCreate() {

        /*// 上一页面设置数据
        Intent intent = null;
        intent = new Intent(this, RePwdActivity.class);
        Bundle bundl = new Bundle();
        intent.putExtra("username", "");
        intent.putExtra("password", "");
        startActivity(intent);*/

        // 本页面获取数据
        Intent i = getIntent();
        username = (String) i.getSerializableExtra("username");
        password = (String) i.getSerializableExtra("password");
    }

    private void initView() {

        img_top = (ImageView) findViewById(R.id.mine_img_top);


    }

    private void initData() {
        handler = new MyHandler(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                this.finish();
                break;
            case R.id.top_navigation_rl_confirm://
                break;
        }
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
        this.finish();
    }
}