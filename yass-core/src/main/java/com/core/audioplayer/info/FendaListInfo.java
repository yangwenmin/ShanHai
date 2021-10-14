package com.core.audioplayer.info;

import java.util.List;

/**
 * Created by zlc on 2016/6/18.
 */
public class FendaListInfo {

    public String status;
    public String msg;
    public List<BodyEntity> body;
    
    public static class BodyEntity {
        public int fid;
        public String title;
        public int zan;
        public AUserEntity aUser;
        public QUserEntity qUser;
        public List<ObjsEntity> objs;
    }

    public static class ObjsEntity{

        public AUserEntity aUser;
        public int fid;
        public int listen;
        public QUserEntity qUser;
        public String question;
        public int zan;
        public String status;
        public String addTime;
        public long price;
        public String videoTime;
        public String path;
        public boolean isSelect = false;
    }

    public static class AUserEntity {
        public int uid;
        public String userName;
        public String userImg;
        public String title;
    }

    public static class QUserEntity {
        public int uid;
        public String userName;
        public String userImg;
    }
}
