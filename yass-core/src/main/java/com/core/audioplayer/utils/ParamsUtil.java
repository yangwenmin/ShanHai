package com.core.audioplayer.utils;

/**
 * Created by zlc on 2017/9/15.
 */

public class ParamsUtil {

    //获取语音列表json
    public static String getVoiceList(){

        return "{\n" +
                "  \"body\": [\n" +
                "    {\n" +
                "      \"objs\": [\n" +
                "        {\n" +
                "          \"fid\": 43,\n" +
                "          \"videoTime\": \"40\",\n" +
                "          \"price\": 1,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 9,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/42035438346.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33086,\n" +
                "            \"title\": \"养护\",\n" +
                "            \"userName\": \"丹丹\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33284,\n" +
                "            \"userName\": \"桑尔柔\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"经常住海边，老人风湿易发，请问有什么好的方法来改善这种状况吗？\",\n" +
                "          \"listen\": 12,\n" +
                "          \"addTime\": \"2016-08-10\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 19,\n" +
                "          \"videoTime\": \"56\",\n" +
                "          \"price\": 3,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 8,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/43131946474.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33086,\n" +
                "            \"title\": \"养护\",\n" +
                "            \"userName\": \"丹丹\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 32778,\n" +
                "            \"userName\": \"猜猜我是谁\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"天天对着电脑屏幕工作，眼睛干涩，眼球发现好多血丝，请问有什么办法缓解一下吗？\",\n" +
                "          \"listen\": 10,\n" +
                "          \"addTime\": \"2016-08-10\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 114,\n" +
                "          \"videoTime\": \"60\",\n" +
                "          \"price\": 5,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 8,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/26/101234753914.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33375,\n" +
                "            \"title\": \"均衡营养\",\n" +
                "            \"userName\": \"贾斌\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33087,\n" +
                "            \"userName\": \"笑笑\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"最近一段时间一直血压低，输液血压升到正常，可是又会反反复复低下来，请问如何通过饮食调节血压？\",\n" +
                "          \"listen\": 10,\n" +
                "          \"addTime\": \"2016-08-26\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 110,\n" +
                "          \"videoTime\": \"60\",\n" +
                "          \"price\": 15,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 6,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/26/1093275129.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33375,\n" +
                "            \"title\": \"均衡营养\",\n" +
                "            \"userName\": \"贾斌\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33523,\n" +
                "            \"userName\": \"Didi\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"我给我们宝宝炒菜都用橄榄油，橄榄油能高温炒吗？好像听说不能。另外听说椰子油很好，那个能高温炒不？\",\n" +
                "          \"listen\": 9,\n" +
                "          \"addTime\": \"2016-08-25\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 124,\n" +
                "          \"videoTime\": \"60\",\n" +
                "          \"price\": 5,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 7,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/26/101441210442.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33375,\n" +
                "            \"title\": \"均衡营养\",\n" +
                "            \"userName\": \"贾斌\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33087,\n" +
                "            \"userName\": \"笑笑\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"在购买大米和食用油时该如何挑选？重点看哪些标签或指标？\",\n" +
                "          \"listen\": 7,\n" +
                "          \"addTime\": \"2016-08-26\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 44,\n" +
                "          \"videoTime\": \"53\",\n" +
                "          \"price\": 1,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 6,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/115951450296.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33087,\n" +
                "            \"title\": \"营养\",\n" +
                "            \"userName\": \"笑笑\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33219,\n" +
                "            \"userName\": \"如冰☺\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"您好，我最近在节食减肥，但是效果一直不好，请问在下有什么既营养又健康的减肥方法吗？\",\n" +
                "          \"listen\": 6,\n" +
                "          \"addTime\": \"2016-08-10\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 28,\n" +
                "          \"videoTime\": \"50\",\n" +
                "          \"price\": 1,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 3,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/4486676938.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33086,\n" +
                "            \"title\": \"养护\",\n" +
                "            \"userName\": \"丹丹\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 33433,\n" +
                "            \"userName\": \"一颗菠菜\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"家里老人有冠心病，应该使用什么营养品做日常调节？\",\n" +
                "          \"listen\": 6,\n" +
                "          \"addTime\": \"2016-08-10\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"fid\": 12,\n" +
                "          \"videoTime\": \"58\",\n" +
                "          \"price\": 2,\n" +
                "          \"status\": \"2\",\n" +
                "          \"zan\": 4,\n" +
                "          \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/53232728065.mp3\",\n" +
                "          \"aUser\": {\n" +
                "            \"uid\": 33087,\n" +
                "            \"title\": \"营养\",\n" +
                "            \"userName\": \"笑笑\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"qUser\": {\n" +
                "            \"uid\": 32778,\n" +
                "            \"userName\": \"猜猜我是谁\",\n" +
                "            \"userImg\": \"\"\n" +
                "          },\n" +
                "          \"question\": \"跑步对膝盖是不是有损伤，怎么跑才不会有损伤?\",\n" +
                "          \"listen\": 5,\n" +
                "          \"addTime\": \"2016-08-10\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"1\",\n" +
                "  \"msg\": \"\"\n" +
                "}";
    }

    //获取单个语音json
    public static String getSingleVoice(){
        return "{\n" +
                "  \"body\": [\n" +
                "    {\n" +
                "      \"videoTime\": 40,\n" +
                "      \"status\": 2,\n" +
                "      \"zanStatus\": 1,\n" +
                "      \"time\": \"1年前\",\n" +
                "      \"q_user\": {\n" +
                "        \"uid\": 33284,\n" +
                "        \"trueName\": \"桑尔柔\"\n" +
                "      },\n" +
                "      \"price\": 1,\n" +
                "      \"a_user\": {\n" +
                "        \"uid\": 33086,\n" +
                "        \"title\": \"养护\",\n" +
                "        \"trueName\": \"丹丹\",\n" +
                "        \"remark\": \"关注慢病护理相关营养素的功效及搭配。\"\n" +
                "      },\n" +
                "      \"zan\": 9,\n" +
                "      \"picDesc\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"fdId\": 43,\n" +
                "      \"path\": \"http://7xoe4e.com1.z0.glb.clouddn.com/mp3/2016/8/10/42035438346.mp3\",\n" +
                "      \"question\": \"经常住海边，老人风湿易发，请问有什么好的方法来改善这种状况吗？\",\n" +
                "      \"listen\": 12\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"1\",\n" +
                "  \"msg\": \"\"\n" +
                "}";
    }

}
