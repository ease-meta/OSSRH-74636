package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxWindowTestCase extends BaseTestCase {

    public static void main(String[] args) {
        //一维Flux
        Flux<String> stringFlux1 = Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i");
        //二维Flux
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2.count().subscribe(System.out::println);
        //三维Flux
        Flux<Flux<Flux<String>>> stringFlux3 = stringFlux2.window(3);
        stringFlux3.count().subscribe(System.out::println);
    }
}
