package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * https://www.cnblogs.com/JavaWeiBianCheng/p/12037519.html
 *
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 22:32
 */
@Slf4j
public class FluxSubscribeTestCase {

    public static void main(String[] args) {
        Flux<String> stringFlux = Flux.just("Hello", "World");
        //stringFlux.subscribe(System.out::println);
        //订阅方式一
        stringFlux.subscribe(val -> {
            log.info("val:{}", val);
        }, error -> {
            log.error("error:{}", error);
        }, () -> {
            log.info("Finished");
        }, subscription -> {
            subscription.request(1);
        });
        //订阅方式二
        stringFlux.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                log.info("onNext:{}", s);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
    }
}
