package com.core.view.refresh;

/**
 * 数据接口的回调
 * Created by ywm.
 */
public interface UltraRefreshListener {

    //下拉刷新
    void onRefresh();

    //上拉加载
    void addMore();
}
