/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            Template template = Velocity.getTemplate("template/provider.xml.vm", "UTF-8");
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
            Writer mywriter = new PrintWriter(new FileOutputStream(new File(outpath)));
            template.merge(root, mywriter);
            mywriter.flush();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}