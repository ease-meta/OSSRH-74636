package com.open.cloud.common.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang.StringUtils;

/**
 * @author Leijian
 * @date   2020/2/8
 */
public class P6spyLogFormat implements MessageFormattingStrategy {

    /**
    * @Description //生成美观SQL
    * @author leijian
    * @date 2019/9/12 10:21
    * @param connectionId, now, elapsed, category, prepared, sql, url
    * @return java.lang.String
    **/
    @Override
    public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {

        return StringUtils.isNotEmpty(sql) ? new StringBuilder().append(" Execute SQL：").append(sql.replaceAll("[\\s]+", " ")).toString() : null;
    }
}
