package com.open.cloud;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alipay.common.tracer.core.context.trace.SofaTraceContext;
import com.alipay.common.tracer.core.holder.SofaTraceContextHolder;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.alipay.sofa.rpc.common.TracerCompatibleConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/29 1:06
 **/
@Slf4j
public class TraceIdPatternConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {

        String traceId = "";
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        log.info("{}",sofaTraceContext);
        SofaTracerSpan sofaTracerSpan = sofaTraceContext.getCurrentSpan();
        System.out.println(sofaTraceContext.isEmpty());
        System.out.println(sofaTraceContext.getThreadLocalSpanSize());
        if (sofaTracerSpan != null) {
            String TRACE_ID_KEY = sofaTracerSpan.getBaggageItem(TracerCompatibleConstants.TRACE_ID_KEY);
            String RPC_ID_KEY = sofaTracerSpan.getBaggageItem(TracerCompatibleConstants.RPC_ID_KEY);
            String CALLER_IP_KEY = sofaTracerSpan.getBaggageItem(TracerCompatibleConstants.CALLER_IP_KEY);
            StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
            return stringJoiner.add(TRACE_ID_KEY).add(RPC_ID_KEY).add(CALLER_IP_KEY).toString();
        }


        return StringUtils.isEmpty(traceId) ? "," : traceId;
    }
}
