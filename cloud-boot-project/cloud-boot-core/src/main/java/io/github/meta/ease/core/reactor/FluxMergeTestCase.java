package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 12:11
 */
@Slf4j
public class FluxMergeTestCase extends BaseTestCase {

    public static void main(String[] args) throws InterruptedException {
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(100)).take(10);
        Flux<Long> longFlux2 = Flux.interval(Duration.ofMillis(100)).take(10);
        Flux<Long> longFlux3 = Flux.merge(longFlux, longFlux2);
        longFlux3.subscribe(val -> log.info("->{}", val));
        Thread.sleep(2000);
    }
}
