package io.github.meta.ease.core.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 12:07
 */
public class FluxBackpressureTestCase extends BaseTestCase {

    public static void main(String[] args) throws InterruptedException {
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(1));
        longFlux.subscribe(new Subscriber<Long>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Long aLong) {
                //perform(3L);
                subscription.request(3);
                System.out.println("val:" + aLong);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        Thread.sleep(1000);
    }
}
