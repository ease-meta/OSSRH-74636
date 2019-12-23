package com.open.cloud.server;

import org.apache.curator.test.TestingServer;

public class ZKTestingServer {

    private static TestingServer server;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        //mock zk server
        mockZKServer();
    }

    private static void mockZKServer() throws Exception {
        //Mock zk server，作为  配置中心
        server = new TestingServer(2181, true);
    }

}