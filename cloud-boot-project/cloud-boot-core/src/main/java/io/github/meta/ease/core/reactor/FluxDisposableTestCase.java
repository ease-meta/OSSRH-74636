package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 12:09
 */
@Slf4j
public class FluxDisposableTestCase extends BaseTestCase {

    public static void main(String[] args) throws InterruptedException {
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(1));
        //take方法准确获取订阅数据量
        Disposable disposable = longFlux.take(50).subscribe(x -> log.info("->{}", x));
        //不能停止正在推送数据中的Flux或Mono流
        Thread.sleep(100);
        //彻底停止正在推送数据中的Flux或Mono流
        disposable.dispose();
        log.info("->Stop");
    }
}
