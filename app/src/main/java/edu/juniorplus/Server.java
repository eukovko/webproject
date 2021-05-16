package edu.juniorplus;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
	private static final String HELLO_PATH = "/hello";
	private static final String USERS_PATH = "/users";
	private static final String CALCULATOR_PATH = "/calculator";
	private static final String INDEX_PATH = "/index";
	private static final String LOCALHOST = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		System.out.println("Server has started");
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(LOCALHOST, PORT), 0);
		// TODO: 4/10/2021 Add an appropriate handler for phoneNumbers
		// TODO: 4/10/2021 Create main handler and dispatch requests based on the path
		httpServer.createContext(HELLO_PATH, Context.helloHandler());
		httpServer.createContext(USERS_PATH, Context.userHandler());
		httpServer.createContext(CALCULATOR_PATH, Context.calculatorHandler());
		httpServer.createContext(INDEX_PATH, Context.indexPageHandler());
		httpServer.start();
	}
}
