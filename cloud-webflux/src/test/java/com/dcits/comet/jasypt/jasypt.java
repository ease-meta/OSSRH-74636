package com.dcits.comet.jasypt;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author wbyf021
 * @version 1.0
 * @Description TODO
 * @date 2019/9/12 16:50
 **/
public class jasypt {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("G0CvDz7oJn6");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("1qaz2wsX");
        String password = textEncryptor.encrypt("1qaz2wsX");
        System.out.println(textEncryptor.decrypt("1JBgo0/a878qEDYR3RdUvYtITF1xGFA6"));
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
