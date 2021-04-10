package edu.juniorplus;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
	private static final String USERS_PATH = "/users";
	private static final String LOCALHOST = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		System.out.println("Server has started");
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(LOCALHOST, PORT), 0);
		// TODO: 4/10/2021 Add an appropriate handler for phoneNumbers
		// TODO: 4/10/2021 Create main handler and dispatch requests based on the path
		httpServer.createContext(USERS_PATH, Context.handler());
		httpServer.start();
	}
}
