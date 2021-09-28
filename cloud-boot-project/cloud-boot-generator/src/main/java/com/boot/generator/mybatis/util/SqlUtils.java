package com.boot.generator.mybatis.util;

import com.boot.generator.mybatis.annotation.SqlLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.boot.generator.mybatis.util.StringPool.PERCENT;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/28 21:18
 */
public class SqlUtils {

    private static final Logger logger = LoggerFactory.getLogger(SqlUtils.class);

    /**
     * 用%连接like
     *
     * @param str 原字符串
     * @return like 的值
     */
    public static String concatLike(Object str, SqlLike type) {
        switch (type) {
            case LEFT:
                return PERCENT + str;
            case RIGHT:
                return str + PERCENT;
            default:
                return PERCENT + str + PERCENT;
        }
    }
}
