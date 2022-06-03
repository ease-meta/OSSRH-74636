package cn.iocoder.yudao.framework.sms.core.client.impl.yunpian;

import cn.iocoder.yudao.framework.sms.core.client.SmsCodeMapping;
import cn.iocoder.yudao.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;
import io.github.meta.ease.common.exception.ErrorCode;

import static com.yunpian.sdk.constant.Code.API_NOT_ALLOWED;
import static com.yunpian.sdk.constant.Code.ARGUMENT_MISSING;
import static com.yunpian.sdk.constant.Code.BAD_API_KEY;
import static com.yunpian.sdk.constant.Code.BAD_ARGUMENT_FORMAT;
import static com.yunpian.sdk.constant.Code.BAD_SIGN_FORMAT;
import static com.yunpian.sdk.constant.Code.BLACK_PHONE_FILTER;
import static com.yunpian.sdk.constant.Code.BLACK_WORD;
import static com.yunpian.sdk.constant.Code.DAY_LIMIT_PER_MOBILE;
import static com.yunpian.sdk.constant.Code.DUP_IN_SHORT_TIME;
import static com.yunpian.sdk.constant.Code.HOUR_LIMIT_PER_MOBILE;
import static com.yunpian.sdk.constant.Code.IP_NOT_ALLOWED;
import static com.yunpian.sdk.constant.Code.MONEY_NOT_ENOUGH;
import static com.yunpian.sdk.constant.Code.OK;
import static com.yunpian.sdk.constant.Code.SIGN_NOT_MATCH;
import static com.yunpian.sdk.constant.Code.SIGN_NOT_VALID;
import static com.yunpian.sdk.constant.Code.TOO_MANY_TIME_IN_5;
import static com.yunpian.sdk.constant.Code.TPL_NOT_FOUND;
import static com.yunpian.sdk.constant.Code.TPL_NOT_VALID;
import static io.github.meta.ease.common.exception.enums.GlobalErrorCodeConstants.SUCCESS;

/**
 * 云片的 SmsCodeMapping 实现类
 * <p>
 * 参见 https://www.yunpian.com/official/document/sms/zh_CN/returnvalue_common 文档
 *
 * @author 芋道源码
 */
public class YunpianSmsCodeMapping implements SmsCodeMapping {

    @Override
    public ErrorCode apply(String apiCode) {
        int code = Integer.parseInt(apiCode);
        switch (code) {
            case OK:
                return SUCCESS;
            case ARGUMENT_MISSING:
                return SmsFrameworkErrorCodeConstants.SMS_API_PARAM_ERROR;
            case BAD_ARGUMENT_FORMAT:
                return SmsFrameworkErrorCodeConstants.SMS_TEMPLATE_PARAM_ERROR;
            case TPL_NOT_FOUND:
            case TPL_NOT_VALID:
                return SmsFrameworkErrorCodeConstants.SMS_TEMPLATE_INVALID;
            case MONEY_NOT_ENOUGH:
                return SmsFrameworkErrorCodeConstants.SMS_ACCOUNT_MONEY_NOT_ENOUGH;
            case BLACK_WORD:
                return SmsFrameworkErrorCodeConstants.SMS_SEND_CONTENT_INVALID;
            case DUP_IN_SHORT_TIME:
            case TOO_MANY_TIME_IN_5:
            case DAY_LIMIT_PER_MOBILE:
            case HOUR_LIMIT_PER_MOBILE:
                return SmsFrameworkErrorCodeConstants.SMS_SEND_BUSINESS_LIMIT_CONTROL;
            case BLACK_PHONE_FILTER:
                return SmsFrameworkErrorCodeConstants.SMS_MOBILE_BLACK;
            case SIGN_NOT_MATCH:
            case BAD_SIGN_FORMAT:
            case SIGN_NOT_VALID:
                return SmsFrameworkErrorCodeConstants.SMS_SIGN_INVALID;
            case BAD_API_KEY:
                return SmsFrameworkErrorCodeConstants.SMS_ACCOUNT_INVALID;
            case API_NOT_ALLOWED:
                return SmsFrameworkErrorCodeConstants.SMS_PERMISSION_DENY;
            case IP_NOT_ALLOWED:
                return SmsFrameworkErrorCodeConstants.SMS_IP_DENY;
            default:
                break;
        }
        return SmsFrameworkErrorCodeConstants.SMS_UNKNOWN;
    }

}
