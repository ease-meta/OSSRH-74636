package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 12:10
 */
@Slf4j
public class FluxParalletTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(x -> log.info("->{}", x));
    }
}
