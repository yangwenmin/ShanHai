package com.core.audioplayer.info;

import java.util.List;

/**
 * Created by zlc on 2016/6/17.
 */
public class QuestInfo {
   

    public String status;
    public List<BodyEntity> body;

    public static class BodyEntity {

        public AUserEntity a_user;
        public QUserEntity q_user;
        public int price;
        public int fdId;
        public String question;
        public List<PicDescEntity> picDesc;
        public int status;
        public String time;
        public int listen;
        public int zan;
        public String path;
        public int zanStatus;
        public String videoTime;
        public String shareUrl;

        public static class PicDescEntity{
            public String accessory;
        }

        public static class AUserEntity{
            public String remark;
            public String title;
            public String trueName;
            public long uid;
            public String user_img;
        }

        public static class QUserEntity {
            public long uid;
            public String trueName;
            public String user_img;
        }
    }
}
