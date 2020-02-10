package com.open.cloud.seata.server;

import java.io.IOException;

import io.seata.core.store.StoreMode;
import io.seata.server.Server;

public class SeataServerStarter {

    /**
     * The Server.
     */
    static Server server = null;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Server.main(args);
    }

}
