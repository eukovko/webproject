package edu.juniorplus;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

// TODO: 3/21/2021 Check this out
public class Server {

	private static final String LOCALHOST = "localhost";
	private static final int PORT = 8080;
	private static final String PATH = "/";

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(LOCALHOST, PORT), 0);
		httpServer.createContext(PATH, new HelloHandler());
		httpServer.start();
	}
}
