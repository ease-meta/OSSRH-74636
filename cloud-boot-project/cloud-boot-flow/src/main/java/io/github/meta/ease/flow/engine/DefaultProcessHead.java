package io.github.meta.ease.flow.engine;

import io.github.meta.ease.domain.dto.BaseRequest;
import io.github.meta.ease.domain.dto.BaseResponse;
import io.github.meta.ease.domain.dto.HeadOut;
import io.github.meta.ease.flow.engine.process.IProcessHead;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 12:03
 */
public class DefaultProcessHead implements IProcessHead {
    @Override
    public <T extends BaseRequest, R extends BaseResponse> R success(T resquest, R response) {
        HeadOut headOut = new HeadOut();
        getHeadOut(resquest, headOut);
        response.setHead(headOut);
        return response;
    }

    @Override
    public <T extends BaseRequest, R extends BaseResponse> R exception(T resquest, R response, Throwable throwable) {
        HeadOut headOut = new HeadOut();
        getHeadOut(resquest, headOut);
        response.setHead(headOut);
        return response;
    }

    @Override
    public <T extends BaseRequest, R extends BaseResponse> R conver2ChannleCode(R response, String destChannle) {
        return null;
    }

    @Override
    public <T extends BaseRequest, R extends BaseResponse> R conver2RespCode(R response, String sourceChannle) {
        return null;
    }

    private <T extends BaseRequest, R extends BaseResponse> void getHeadOut(T request, HeadOut headOut) {
        Optional.ofNullable(request.getHead()).ifPresent(head1 -> BeanUtils.copyProperties(head1, headOut));
    }
}
