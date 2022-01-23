package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxZipWithTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<String> stringFlux1 = Flux.just("a", "b", "c", "d", "e");
        Flux<String> stringFlux2 = Flux.just("f", "g", "h", "i");
        Flux<String> stringFlux3 = Flux.just("1", "2", "3", "4");
        //方法一zipWith
        stringFlux1.zipWith(stringFlux2).subscribe(x -> log.info("->{}", x));
        System.out.println();
        //方法二zip
        Flux<Tuple3<String, String, String>> tuple2Flux = Flux.zip(stringFlux1, stringFlux2, stringFlux3);
        tuple2Flux.subscribe(x -> log.info("->{}", x));
    }
}
