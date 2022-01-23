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
public class FluxConcatMapTestCase extends BaseTestCase {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> stringFlux1 = Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i");
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2.concatMap(flux1 -> flux1.map(word -> word.toUpperCase())
                        .delayElements(Duration.ofMillis(200)))
                .subscribe(x -> System.out.print("->" + x));
        Thread.sleep(2000);
    }
}
