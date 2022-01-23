package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 22:24
 */
public class MonoTestCase {

    public static void main(String[] args) {
        //字符串
        Mono<String> stringMono = Mono.just("Hello World");
        //Callable创建
        Mono<String> stringMono1 = Mono.fromCallable(() ->
        {
            return "Hello World";
        });
        //Future创建
        Mono<String> stringMono2 = Mono.fromFuture(CompletableFuture.completedFuture("Hello World"));
        Random random = new Random();
        //Suppier创建
        Mono<Double> doubleMono = Mono.fromSupplier(random::nextDouble);
        //Mono创建
        Mono<Double> doubleMono1 = Mono.from(doubleMono);
        //Flux创建
        Mono<Integer> integerMono = Mono.from(Flux.range(1, 5));
        integerMono.subscribe(System.out::println);
        stringMono2.subscribe(System.out::println);
    }
}
