package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxMonoCollectMapTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        //转换HashMap
        Mono<Map<String, Employee>> mono = employeeFlux
                .collectMap(key -> key.getName(), val -> val);
        mono.subscribe(System.out::println);
    }
}
