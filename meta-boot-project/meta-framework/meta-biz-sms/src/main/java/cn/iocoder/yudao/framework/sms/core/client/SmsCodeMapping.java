package cn.iocoder.yudao.framework.sms.core.client;


import io.github.meta.ease.common.exception.ErrorCode;

import java.util.function.Function;

/**
 * 将 API 的错误码，转换为通用的错误码
 *
 * @author 芋道源码
 * @see cn.iocoder.yudao.framework.sms.core.client.SmsCommonResult
 * @see cn.iocoder.yudao.framework.sms.core.enums.SmsFrameworkErrorCodeConstants
 */
public interface SmsCodeMapping extends Function<String, ErrorCode> {
}
