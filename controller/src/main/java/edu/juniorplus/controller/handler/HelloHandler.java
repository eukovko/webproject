package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class HelloHandler implements HttpHandler {

	@Override
	// TODO: 3/28/2021 Return an HTML page based on HTTP request localhost:8080/hello?user=userName
	// TODO: 4/10/2021 Create a simple HTML rendered, which will produce some output based on the info
	// TODO: 4/10/2021 1. Return hello page with the name of the user which is passed in query string ?userName=TheBestUser

	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		switch (requestMethod) {
			case "GET":
				doGet(exchange);
				break;
			case "POST":
				doPost(exchange);
				break;
			default:
				throw new IllegalArgumentException();
		}

	}

	private void doPost(HttpExchange exchange) throws IOException {
		String html = "<h1>Hello there, %s!</h1>\n" +
			"<h2>This is response for %s method</h2>\n" +
			"<h2>This is the page from the server we have set up</h2>";
		String user = getUserNameFromPostRequest(exchange);
		String message = String.format(html, user, exchange.getRequestMethod());
		exchange.sendResponseHeaders(200, message.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
	}

	private void doGet(HttpExchange exchange) throws IOException {
		String html = "<h1>Hello there, %s!</h1>\n" +
			"<h2>This is response for %s method</h2>\n" +
			"<h2>This is the page from the server we have set up</h2>";
		String user = getUserNameFromGetRequest(exchange);
		String message = String.format(html, user, exchange.getRequestMethod());
		exchange.sendResponseHeaders(200, message.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
	}

	private String getUserNameFromGetRequest(HttpExchange exchange) {
		String fallbackUser = "null";
		String query = exchange.getRequestURI().getQuery();
		if (query != null && !query.isEmpty()) {
			return Arrays.stream(query.split("&")) // Split user=Bob, a=2, pass=1231
				.filter(s -> s.startsWith("user")) // Choose only ones that start with user
				.map(s -> s.substring(s.indexOf("=") + 1)) // user=Bob
				.findFirst().orElse(fallbackUser);
		}
		return fallbackUser;
	}

	private String getUserNameFromPostRequest(HttpExchange exchange) throws IOException {
		StringBuilder body = new StringBuilder();
		int c;
		while ((c = exchange.getRequestBody().read()) != -1) {
			body.append(((char) c));
		}
		String data = body.toString(); // name=UserName
		String fallbackUser = "null";
		if (!data.isEmpty()) {
			return data.substring(data.indexOf("=") + 1);
		}
		return fallbackUser;
	}
}
