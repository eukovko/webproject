package edu.juniorplus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHandler implements HttpHandler {


	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String hello = "<h1>Hello there</h1><h2>This is the page from the server we have set up</h2>";

		exchange.sendResponseHeaders(200, hello.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(hello.getBytes());
			outputStream.flush();
		}
	}
}
