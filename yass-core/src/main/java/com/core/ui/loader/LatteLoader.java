package com.core.ui.loader;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.core.R;
import com.core.utils.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


/**
 * Created by yangwenmin on 2017/10/16.
 */

public class LatteLoader {

    // 设置dialog占全屏幕的宽高比
    private static final int LOADER_SIZE_SCALE = 8;
    //private static final int LOADER_SIZE_SCALE = 4;
    // 偏移量
    private static final int LOADER_OFFSET_SCALE = 10;

    // 创建一个集合存放Loader,便于管理
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    // 设置一个默认缓存界面,
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    private static AnimationDrawable animationDrawable;

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();


    /*// 根据传入不同的type,展示不同缓存页面
    public static void showLoading(Context context, String type, boolean protype) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loading_dialog2);

        // final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        // dialog.setContentView(avLoadingIndicatorView);
        // 修改过渡动画 2018年12月3日14:28:51
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_detail_navigation, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_ll_loading_iv);
        imageView.setBackgroundResource(R.drawable.animation_pijiu);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }

        dialog.setContentView(view);

        if (protype) {
            dialog.setCancelable(protype);// 点击不可消失
        } else {
            dialog.setCancelable(false);// 点击不可消失
        }


        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        // 设置dialog窗口大小
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            //lp.width = deviceWidth / LOADER_SIZE_SCALE;
            //lp.height = deviceHeight / LOADER_SIZE_SCALE;
            //lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            //lp.gravity = Gravity.CENTER;


            //lp.width = deviceWidth / LOADER_SIZE_SCALE;
            //lp.height = deviceWidth / LOADER_SIZE_SCALE;

            lp.width = deviceWidth;
            lp.height = deviceHeight;



            lp.gravity = Gravity.CENTER;
        }
        dialogWindow.setWindowAnimations(R.style.dialogAnimation); //设置窗口弹出动画
        LOADERS.add(dialog);
        dialog.show();
    }*/

    // 根据传入不同的type,展示不同缓存页面
    public static void showLoading(Context context, String type,boolean protype) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loading_dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        if(protype){
            dialog.setCancelable(protype);// 点击不可消失
        }else{
            dialog.setCancelable(false);// 点击不可消失
        }


        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        // 设置dialog窗口大小
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            /*lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;*/

            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceWidth / LOADER_SIZE_SCALE;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    // 默认展示的 Loading页面 // 点击不可取消
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER, false);
    }

    // 默认展示的 Loading页面 // false: 点击不可隐藏滚动条  true:点击可隐藏滚动
    public static void showLoading(Context context, boolean protype) {
        showLoading(context, DEFAULT_LOADER, protype);
    }

    // 根据传入的不同type展示 Loading页面
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name(), false);
    }

    // 关闭Loading页面
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    try {
                        dialog.cancel();
                        // dialog.dismiss();
                        stopAnimation();
                    }catch (Exception e){

                    }
                }
            }
        }
    }

    private static void stopAnimation() {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
}
