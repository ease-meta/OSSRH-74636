package com.open.cloud.sofa.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/6/23 20:01
 */
public class Student {

    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    private String name = "Student1";

    private Body body;

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Body {

        private Integer age;

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }
    }
}
