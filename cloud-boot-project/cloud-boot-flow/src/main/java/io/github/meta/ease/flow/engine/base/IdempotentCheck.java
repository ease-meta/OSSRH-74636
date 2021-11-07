package io.github.meta.ease.flow.engine.base;

import io.github.meta.ease.domain.api.BaseRequest;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 11:34
 */
public interface IdempotentCheck<T extends BaseRequest> {

    String traceKey(T request);
}
