package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxExceptionTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux.range(-2, 5)
                .map(val -> {
                    int i = val / val;
                    return val;
                })
                .onErrorContinue((ex, val) -> {  //遇到错误继续订阅
                    if (ex instanceof IOException) {
                        log.error("ex:{},val:{}", ex, val);
                    } else {
                    }

                })
                .onErrorResume((ex) -> {   //遇到错误，返回新的Flux。继续订阅
                    return Flux.range(-2, 5);
                })
                .subscribe(System.out::println);
    }
}
