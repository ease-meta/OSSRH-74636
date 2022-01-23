package io.github.meta.ease.core.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
@Slf4j
public class FluxMonoCollectTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(1, 5);
        //转换成以List<Integer>为对象的Mono
        Mono<List<Integer>> mono = integerFlux.collectList();
        mono.subscribe(System.out::println);
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        //转换成以List<Employee>为对象的Mono
        Mono<List<Employee>> mono1 = employeeFlux.collectSortedList(Comparator.comparing(Employee::getSalary));
        mono1.subscribe(System.out::println);
        //转换成以Map<String,Employee>为对象的Mono
        Mono<Map<String, Employee>> mono2 = employeeFlux.collectMap(item -> item.getName(), item -> item);
        mono2.subscribe(System.out::println);
    }
}
