package com.open.cloud.workflow;


import com.alibaba.fastjson.JSON;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/7/8 23:40
 **/
public class DemoMain {

    public static void main(String[] args) {
        String jsonMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";

        Object jsonObject = JSON.parse(jsonMessage);

        System.out.println(jsonObject.toString());

        jsonMessage = "[{'num':'成绩', '外语':88, '历史':65, '地理':99, 'object':{'aaa':'1111','bbb':'2222','cccc':'3333'}}," +
                "{'num':'兴趣', '外语':28, '历史':45, '地理':19, 'object':{'aaa':'11a11','bbb':'2222','cccc':'3333'}}," +
                "{'num':'爱好', '外语':48, '历史':62, '地理':39, 'object':{'aaa':'11c11','bbb':'2222','cccc':'3333'}}]";
        jsonObject = JSON.parse(jsonMessage);

        System.out.println(jsonObject.toString());
    }
}
