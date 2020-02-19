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
package com.open.cloud;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/16 21:23
 **/
@Component
public class UndertowServer {

    public static final String MYAPP = "/";

    public void start() throws ServletException {

        DeploymentInfo servletBuilder = deployment()
            .setClassLoader(UndertowServer.class.getClassLoader()).setContextPath(MYAPP)
            .setDeploymentName("test.war")
            .addServlets(servlet("MessageServlet", MessageServlet.class).addMapping("/*"));

        DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        HttpHandler servletHandler = manager.start();

        PathHandler path = Handlers.path(Handlers.redirect(MYAPP)).addPrefixPath(MYAPP,
            servletHandler);

        Undertow server = Undertow.builder().addHttpListener(8081, "localhost").setHandler(path)
            .build();
        server.start();
    }

    public static void main(String[] args) throws ServletException {
        UndertowServer undertowServer = new UndertowServer();
        undertowServer.start();
    }
}
