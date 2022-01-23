package io.github.meta.ease.core.reactor;

import java.util.Arrays;
import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/23 11:30
 */
public class BaseTestCase {
    protected static final List<Employee> list = Arrays.asList(
            new Employee(1, "Alex", 1000),
            new Employee(2, "Michael", 2000),
            new Employee(3, "Jack", 1500),
            new Employee(4, "Owen", 1500),
            new Employee(5, "Denny", 2000));
}
