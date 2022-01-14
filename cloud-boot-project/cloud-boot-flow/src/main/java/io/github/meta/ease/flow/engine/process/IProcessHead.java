package io.github.meta.ease.flow.engine.process;

import io.github.meta.ease.domain.dto.BaseRequest;
import io.github.meta.ease.domain.dto.BaseResponse;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 11:54
 */
public interface IProcessHead {
    <T extends BaseRequest, R extends BaseResponse> R success(T resquest, R response);

    <T extends BaseRequest, R extends BaseResponse> R exception(T resquest, R response, Throwable throwable);

    <T extends BaseRequest, R extends BaseResponse> R conver2ChannleCode(R response, String destChannle);

    <T extends BaseRequest, R extends BaseResponse> R conver2RespCode(R response, String sourceChannle);


}
