package com.moc.cloud.workflow.framework.idgenerator;

import com.moc.cloud.workflow.framework.config.EnvironmentConfiguration;
import com.moc.cloud.workflow.framework.config.SpringApplicationConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname: TimeSequenceGenerator
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/21
 * @Version: 1.0
 */
public class TimeSequenceGenerator {

    /**
     * 工作机器ID(0~9999)
     */
    private long workerId;
    /**
     * 1秒内序列(1~999999)
     */
    private long sequence = 0L;

    /**
     * 时间戳格式
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 上次生成ID的时间截(yyyyMMddHHmmss)
     */
    private String lastTimestamp = sdf.format(new Date());
    /**
     * 1秒内最大序列号
     */
    private long sequenceMask = 9999;

    public TimeSequenceGenerator(final String name) {
        EnvironmentConfiguration environmentConfiguration = SpringApplicationConfiguration.getBean(EnvironmentConfiguration.class);
        this.workerId = Long.parseLong(environmentConfiguration.getWordId());
    }


    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return
     */
    public synchronized String nextId() {
        String timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp.compareTo(lastTimestamp) < 0) {
            throw new RuntimeException("时钟回拨，不能生成序列号，时间过了上次时间之后，恢复正常。");
        }
        // 如果是同一时间生成的，则进行秒内序列
        if (lastTimestamp.equals(timestamp)) {
            sequence = (sequence + 1) % sequenceMask;
            // 秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个秒,获得新的时间戳
                timestamp = tilNextSecond(lastTimestamp);
                sequence = 1;
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 1L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        String ssequence = String.valueOf(sequence);
        if (sequence < 1000) {
            while (ssequence.length() < 4) {
                ssequence = '0' + ssequence;
            }
        }
        String sworkerId = String.valueOf(workerId);
        return timestamp + sworkerId + ssequence;
    }

    /**
     * 阻塞到下一个秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected String tilNextSecond(String lastTimestamp) {
        String timestamp = timeGen();
        while (timestamp.compareTo(lastTimestamp) <= 0) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected String timeGen() {
        return sdf.format(new Date());
    }
}
