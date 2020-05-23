package com.open.cloud.sofa.dynamic.text;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Velocity转换
 * @author

 */

public class VelocityTest {
    /**
     * 主函数
     * @param args

     */
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        try {
            //初始化模板
            Velocity.init(prop);
            //获取模板
            Template template =  Velocity.getTemplate("template/provider.xml.vm", "UTF-8");
            //获取上下文
            VelocityContext root = new VelocityContext();
            //把数据填入上下文
            LinkedList linkedList = new LinkedList();
            Provider provider = new Provider();
            provider.setInterfaceName("1");
            provider.setRef("1");
            provider.setUniqueId("1");
            linkedList.add(provider);
            Provider provider2 = new Provider();
            provider2.setInterfaceName("2");
            provider2.setRef("2");
            linkedList.add(provider2);
            root.put("lists", linkedList);
            //输出路径
            String outpath = "D:/helloworld.html";
            //输出
            Writer mywriter = new PrintWriter(new FileOutputStream(
            new File(outpath)));
            template.merge(root, mywriter);
            mywriter.flush();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}