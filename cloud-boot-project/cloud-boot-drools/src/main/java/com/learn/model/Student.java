package com.learn.model;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
public class Student {

    private int age;

    private String name;

    private String message;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
