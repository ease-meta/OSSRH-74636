package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * https://www.cnblogs.com/JavaWeiBianCheng/p/12021925.html
 *
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 22:22
 */
public class FluxBufferTestCase {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> stringFlux = Flux.just("a", "b", "c", "d", "e", "f", "g");
        stringFlux.subscribe(x -> System.out.print("->" + x));
        System.out.println();
        Flux<List<String>> listFlux = stringFlux.buffer(2);
        listFlux.subscribe(x -> System.out.print("->" + x));
    }
}
