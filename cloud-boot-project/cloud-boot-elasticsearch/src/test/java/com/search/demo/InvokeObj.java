package com.search.demo;
 
public class InvokeObj {
 
    public void show() {
        System.out.println("无参show()方法。");
    }
    public void show (String name) {
        System.out.println("show方法：" + name);
    }
    public String[] arrayShow (String[] arr) {
        return arr;
    }
    public String StringShow (String str) {
        return str;
    }
    public int intShow (int num) {
        return num;
    }
}