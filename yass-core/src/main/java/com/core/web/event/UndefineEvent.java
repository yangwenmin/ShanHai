package com.core.web.event;


import com.core.utils.log.LatteLogger;

/**
 * Created by ywm
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
