package com.shanhai.http;

/**
 * 需sim卡 的链接
 * Created by yangwenmin on 2017/10/28.
 */

public class HttpDBT {


    /*// 正式sim 接口环境
    public static final String HEAD_URL = "http://10.220.6.225";
    // 正式sim Web环境
    public static final String HEAD_URL_WEB = "http://10.220.6.225";












    public static final String BASE_URL = HEAD_URL + "/app-intf";

    // 虽然废弃了 但不能删除  2018年11月30日15:29:48
    public static final String API_HOST = BASE_URL + "/DdController/";
    public static final String IP_END = "method";

    // 接口: 全局
    public static final String LOGIN_API = BASE_URL + "/ComController/Api";
    // 接口: 业代数据
    public static final String DBTPLUS_DATA_API = BASE_URL + "/YdController/Api";
    // 接口: 督导数据
    public static final String DD_PUSH_DATA = BASE_URL + "/DdController/Api";
    // 接口: 主管数据
    public static final String ZG_PUSH_DATA = BASE_URL + "/ZgController/Api";


    // web: 终端明细,产品展示,考勤,周计划,
    public static final String DBTPLUS_DATA_WEB = HEAD_URL_WEB + "/pad/";

    // web: 堆头店申请列表
    public static final String DBTPLUS_DATA_NEWAPP = HEAD_URL_WEB + "/newapp/";

    // 语音:
    public static final String DBTPLUS_DATA_YUYIN = HEAD_URL + "/static/";

    // banner:
    public static final String DBTPLUS_DATA_BANNER = HEAD_URL + "/static/banner/";

    // pdf:
    public static final String DBTPLUS_DATA_PDF = HEAD_URL + "/static/pdf/";

    // 头像:
    public static final String DBTPLUS_DATA_HEADPHOTO = HEAD_URL + "/static/headphoto/";

    // 下载apk
    public static final String DBTPLUS_DATA_APPAPK = HEAD_URL + "/static/app/";

    // 发送消息url  图片
    public static final String DBTPLUS_DATA_MESSAGE = HEAD_URL + "/static/pic/";

    // 主管权限  图片
    public static final String DBTPLUS_DATA_ZGFUNROLE = HEAD_URL + "/static/zgfunrole/";

    // 业代 运营管理 图片
    public static final String DBTPLUS_DATA_YDFUNROLE = HEAD_URL + "/static";


    // 正式sim 日工作推进环境
    public static final String DAYWORK_URL_WEB = "http://172.16.1.95:8001/FSA_WEB_2/";*/






    // ----下面是复制HttpBaiDu的-----------------------------------------------------------------------------------------------------

    // wifi 测试接口环境
    // public static final String HEAD_URL = "http://192.168.0.33";
    // public static final String HEAD_URL = "http://192.168.0.89:8001";
    //  public static final String HEAD_URL = "http://192.168.1.208:8001";
    // public static final String HEAD_URL = "http://192.168.1.147:8089";
    //public static final String HEAD_URL = "http://192.168.1.198:8001";

    // wifi 正式接口环境
    // public static final String HEAD_URL = "http://smp.dbtplus.com";
    public static final String HEAD_URL = "https://smp.tsingtao.com.cn";

    // wifi 测试Web环境
    // public static final String HEAD_URL_WEB = "http://192.168.0.33";
    // wifi 正式Web环境
    // public static final String HEAD_URL_WEB = "http://smp.dbtplus.com";
    // public static final String HEAD_URL_WEB = "http://192.168.1.208:8001";
    public static final String HEAD_URL_WEB = "http://smp.tsingtao.com.cn";
























    // WiFi环境
    public static final String BASE_URL = HEAD_URL + "/app-intf";
    // public static final String BASE_URL = HEAD_URL + "/app-intfcs";

    // 虽然废弃了 但不能删除  2018年11月30日15:29:48
    public static final String API_HOST = BASE_URL + "/DdController/";
    public static final String IP_END = "method";

    // 接口: 全局
    public static final String LOGIN_API = BASE_URL + "/ComController/Api";
    // 接口: 业代数据
    public static final String DBTPLUS_DATA_API = BASE_URL + "/YdController/Api";
    // 接口: 督导数据
    public static final String DD_PUSH_DATA = BASE_URL + "/DdController/Api";
    // 接口: 主管数据
    public static final String ZG_PUSH_DATA = BASE_URL + "/ZgController/Api";
    // 接口: 考勤
    public static final String KQ_PUSH_DATA = BASE_URL + "/KqController/Api";

    // ---------------------------------------------------------------------------------------------------
    // web: 终端明细,产品展示,考勤,周计划,
    public static final String DBTPLUS_DATA_WEB = HEAD_URL_WEB + "/app/";

    // web: 堆头店申请列表
    public static final String DBTPLUS_DATA_NEWAPP = HEAD_URL_WEB + "/newapp/";

    // 语音:
    public static final String DBTPLUS_DATA_YUYIN = HEAD_URL + "/static/";

    // banner:
    public static final String DBTPLUS_DATA_BANNER = HEAD_URL + "/static/banner/";

    // pdf:
    public static final String DBTPLUS_DATA_PDF = HEAD_URL + "/static/pdf/";

    // 头像:
    public static final String DBTPLUS_DATA_HEADPHOTO = HEAD_URL + "/static/headphoto/";
    // public static final String DBTPLUS_DATA_HEADPHOTO = "http://192.168.0.33" + "/static/headphoto/";

    // 下载apk
    public static final String DBTPLUS_DATA_APPAPK = HEAD_URL + "/static/app/";

    // 发送消息url  图片
    public static final String DBTPLUS_DATA_MESSAGE = HEAD_URL + "/static/pic/";

    // 督导  图片
    public static final String DD_PUSH_DATA_PIC = HEAD_URL + "/static/ddrectifypic/";
    // 督导 整改计划 图片
    public static final String DD_DDRECTIFYPIC_PIC = HEAD_URL + "/static/ddrectifypic/";

    // 协同拜访  图片
    public static final String DD_DDSYNPIC_PIC = HEAD_URL + "/static/ddsynpic/";

    // 主管权限  图片
    public static final String DBTPLUS_DATA_ZGFUNROLE = HEAD_URL + "/static/zgfunrole/";
    // 业代 运营管理 图片
    public static final String DBTPLUS_DATA_YDFUNROLE = HEAD_URL + "/static";

    // ---------------------------------------------------------------------------------------------------

    // wifi 日工作推进环境  http://smp.tsingtao.com.cn
    public static final String DAYWORK_URL_WEB = "http://cms.tsingtao.com.cn:8001/FSA_WEB_2/";

}
