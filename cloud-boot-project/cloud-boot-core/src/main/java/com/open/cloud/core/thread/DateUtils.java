package com.open.cloud.core.thread;

public class DateUtils {

    /**
     * 每秒毫秒数
     */
    public static final int MILLISECONDS_PER_SECONDE = 1000;
    /**
     * 每分毫秒数 60*1000
     */
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    /**
     * 每小时毫秒数 36*60*1000
     */
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    /**
     * 每天毫秒数 24*60*60*1000;
     */
    public static final long MILLISECONDS_PER_DAY = 86400000;

    /**
     * 普通时间的格式
     */
    public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 毫秒级时间的格式
     */
    public static final String DATE_FORMAT_MILLS_TIME = "yyyy-MM-dd HH:mm:ss.SSS";

}
