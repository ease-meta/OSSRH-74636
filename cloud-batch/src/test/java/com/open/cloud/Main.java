package com.open.cloud;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @ClassName Main
 * @Description TODO
 * @Author leijian
 * @Date 2019/8/18 20:30
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("1Qaz0oKm");
        System.out.println(textEncryptor.encrypt("root"));
    }
}
