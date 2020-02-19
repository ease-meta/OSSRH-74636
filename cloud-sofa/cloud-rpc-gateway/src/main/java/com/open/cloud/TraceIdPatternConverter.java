/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        log.info("{}", sofaTraceContext);
        SofaTracerSpan sofaTracerSpan = sofaTraceContext.getCurrentSpan();
        System.out.println(sofaTraceContext.isEmpty());
        System.out.println(sofaTraceContext.getThreadLocalSpanSize());
        if (sofaTracerSpan != null) {
            String TRACE_ID_KEY = sofaTracerSpan
                .getBaggageItem(TracerCompatibleConstants.TRACE_ID_KEY);
            String RPC_ID_KEY = sofaTracerSpan.getBaggageItem(TracerCompatibleConstants.RPC_ID_KEY);
            String CALLER_IP_KEY = sofaTracerSpan
                .getBaggageItem(TracerCompatibleConstants.CALLER_IP_KEY);
            StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
            return stringJoiner.add(TRACE_ID_KEY).add(RPC_ID_KEY).add(CALLER_IP_KEY).toString();
        }

        return StringUtils.isEmpty(traceId) ? "," : traceId;
    }
}
