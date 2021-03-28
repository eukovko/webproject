package edu.juniorplus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHandler implements HttpHandler {


	@Override
	// TODO: 3/28/2021  Return an HTML page based on HTTP request localhost:8080/hello?user=userName
	public void handle(HttpExchange exchange) throws IOException {
		String hello = "<h1>This is response for %s method</h1><h2>This is the page from the server we have set up</h2>";
		String message = String.format(hello, exchange.getRequestMethod());
		exchange.sendResponseHeaders(200, message.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
	}
}
