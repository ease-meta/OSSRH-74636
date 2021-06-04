package com.open.cloud.maven;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.io.File;
import java.util.Collections;

/**
 * Maven本地仓库私包上传
 *
 * @author leijian
 * @version 1.0
 * @date 2021/6/1 19:47
 */
public class Depoly2 {

    public static void main(String[] args) {

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("pom.xml"));
        request.setGoals(Collections.singletonList("compile"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("D:/work/environment/Maven/apache-maven-3.3.9"));

        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();

        }
    }
}
