package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:31
 */
public class FluxMapTestCase extends BaseTestCase {

    public static void main(String[] args) {
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        employeeFlux.filter(employee -> employee.getSalary() == 2000)
                .map(employee -> {
                    Leader leader = new Leader();
                    leader.setName(employee.getName());
                    leader.setSalary(employee.getSalary());
                    return leader;
                }).log().subscribe();
        employeeFlux.map((in) -> {
            Leader leader = new Leader();
            leader.setName(in.getName());
            leader.setSalary(in.getSalary());
            return leader;
        });
    }
}
