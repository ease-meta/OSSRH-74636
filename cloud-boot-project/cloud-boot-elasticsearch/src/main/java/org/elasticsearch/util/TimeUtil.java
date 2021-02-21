package org.elasticsearch.util;

/**
 * @ClassName: TimeUtil
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/5 22:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TimeUtil {
    public static void timeSleep(long sleep){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
