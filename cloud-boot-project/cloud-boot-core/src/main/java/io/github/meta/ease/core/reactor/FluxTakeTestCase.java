package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxTakeTestCase extends BaseTestCase {

    public static void main(String[] args) {
        //根据数量获取
        Flux.range(1, 10).take(0).log().subscribe(System.out::println);
        //根据实际获取
        Flux.range(1, 10000).take(Duration.ofMillis(2)).log().subscribe(System.out::println);
        //根据条件获取
        Flux.range(1, 10).takeUntil(item -> item == 5).log().subscribe(System.out::println);
    }
}
