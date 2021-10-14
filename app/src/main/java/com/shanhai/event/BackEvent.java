package com.shanhai.event;

import com.core.web.event.Event;


/**
 * 点击H5页面上的返回按钮  直接返回
 * Created by yangwenmin
 */

public class BackEvent extends Event {
    @Override
    public String execute(String params) {
        // Toast.makeText(getContext(), "返回", Toast.LENGTH_LONG).show();
        /*if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }*/
        getDelegate().pop();
        //((BaseFragment)getDelegate().getParentFragment()).pop();
        return null;
    }
}
