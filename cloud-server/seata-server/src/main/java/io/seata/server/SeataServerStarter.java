package io.seata.server;

import java.io.IOException;

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
		server = new Server();
		server.main(args);
	}

}