package io.github.meta.ease.flow.engine.base;

import io.github.meta.ease.domain.dto.BaseRequest;
import io.github.meta.ease.domain.dto.BaseResponse;
import io.github.meta.ease.flow.engine.process.IProcess;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 11:18
 */
public interface IBusinessHandler<T extends BaseRequest, R extends BaseResponse> {

    void businessHanler(IProcess process, T request, R response) throws Throwable;

    R cacheHandler(IProcess process, Throwable throwable, T request, R response);

    void clear();
}
