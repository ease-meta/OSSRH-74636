package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.cnblogs.com/JavaWeiBianCheng/p/12021925.html
 *
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 22:22
 */
public class FluxTestCase {

    public static void main(String[] args) throws InterruptedException {
        //整型
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
        //字符串
        Flux<String> stringFlux = Flux.just("hello", "world");
        List<String> list = Arrays.asList("hello", "world");
        //列表
        Flux<String> stringFlux1 = Flux.fromIterable(list);
        //范围
        Flux<Integer> integerFlux1 = Flux.range(1, 5);
        //时间间隔
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(1000));
        longFlux.subscribe(System.out::println);

        //Flux 创建
        Flux<String> stringFlux2 = Flux.from(stringFlux1);
        stringFlux2.subscribe(System.out::println);
        Thread.sleep(Long.MAX_VALUE);
    }
}
