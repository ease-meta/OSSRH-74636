package com.open.cloud.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leijian
 */
public class ToolsStrBusi {

    /**
     * 取字符串，如果是空则赋值为""
     *
     * @param in
     */
    public static String getString(String in) {
        return in == null ? "" : new String(in.trim());
    }

    /**
     * 获取系统时间
     *
     * @param format 想要的格式yyyyMMddHHmmss
     *               <p>
     *               yyyy表示年，如2013；
     *               MM表示月，如12；
     *               dd表示天，如31；
     *               hh表示用12小时制，如7；
     *               HH表示用24小时制，如18；
     *               mm表示分，如59；
     *               ss表示秒，如59；
     *               SSS表示毫米，如333
     * @return 系统时间
     */
    public static String getSysDate( String format) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }


}
