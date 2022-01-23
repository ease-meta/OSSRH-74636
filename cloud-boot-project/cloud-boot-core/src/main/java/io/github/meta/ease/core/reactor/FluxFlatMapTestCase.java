package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxFlatMapTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<String> stringFlux1 = Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i");
        //嵌套Flux
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2.flatMap(flux1 -> flux1.map(word -> word.toUpperCase()))
                .subscribe(System.out::println);
        //从嵌套Flux还原字符串Flux
        Flux<String> stringFlux3 = stringFlux2.flatMap(flux1 -> flux1);
        // stringFlux1 等于 stringFlux3
        stringFlux3.subscribe(System.out::println);
    }
}
