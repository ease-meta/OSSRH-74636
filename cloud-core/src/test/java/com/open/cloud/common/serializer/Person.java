package com.open.cloud.common.serializer;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 123456L;
    private String name;
    private /*transient*/int age;
    private boolean sex;

    public Person(String name,int age,boolean sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}