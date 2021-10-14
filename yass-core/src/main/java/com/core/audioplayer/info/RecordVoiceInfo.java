package com.core.audioplayer.info;

/**
 * Created by Administrator on 2017/5/25.
 */

public class RecordVoiceInfo {

    private int time;
    private String path;
    private String name;// 语音名称("1-23WD67.mp3")

    public RecordVoiceInfo(int time, String path) {
        this.time = time;
        this.path = path;
    }

    public RecordVoiceInfo(){}

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
