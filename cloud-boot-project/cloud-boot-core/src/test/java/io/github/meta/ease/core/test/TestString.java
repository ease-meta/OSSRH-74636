package io.github.meta.ease.core.test;

import org.openjdk.jol.info.ClassLayout;

import java.io.UnsupportedEncodingException;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试String占用的内存空间
 */
public class TestString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /* String[] strContainer = new String[4000000];
        for(int i = 0; i < 4000000; i++){
            strContainer[i] = UUIDUtils.getUUID();
            System.out.println(i);
        }*/
        String str = "";
        System.out.println("\"搞java\".length():" + (str.length()));
        System.out.println("\"搞java\".getBytes().length:" + (str.getBytes().length));
        System.out.println("\"搞java\".getBytes(\"GBK\").length:" + (str.getBytes("GBK").length));
        System.out.println("\"搞java\".getBytes(\"UTF-8\").length:" + (str.getBytes("UTF-8").length));
/**
 "搞java".length():5
 "搞java".getBytes().length:6
 "搞java".getBytes("GBK").length:6
 "搞java".getBytes("UTF-8").length:7
 */
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
        //防止程序退出
        while (true) {

        }
    }
}
