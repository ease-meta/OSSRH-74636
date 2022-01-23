package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 12:12
 */
public class FluxStepVerifierTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(1, 5);
        integerFlux.subscribe(x -> System.out.print("->" + x));
       /* StepVerifier.create(integerFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3,4,5)
                .expectComplete()
                .verify();*/
    }
}
