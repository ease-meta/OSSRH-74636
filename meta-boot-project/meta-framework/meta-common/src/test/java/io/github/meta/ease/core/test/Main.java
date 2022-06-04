package io.github.meta.ease.core.test;

import org.openjdk.jol.info.ClassLayout;

public class Main {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new String("123")).toPrintable());
    }

}