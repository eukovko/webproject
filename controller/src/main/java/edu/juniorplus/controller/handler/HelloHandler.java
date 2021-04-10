package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHandler implements HttpHandler {

	@Override
	// TODO: 3/28/2021 Return an HTML page based on HTTP request localhost:8080/hello?user=userName
	// TODO: 4/10/2021 Create a simple HTML rendered, which will produce some output based on the info
	// TODO: 4/10/2021 1. Return hello page with the name of the user which is passed in query string ?userName=TheBestUser
	// TODO: 4/10/2021 2. Create a calculator which will return the sum of two numbers which will be set from headers
	// TODO: 4/10/2021 3. Create an HTML page, put it into resources folder and return it, basically create one file server
	// TODO: 4/10/2021 4. Do 1 and 2 task with the form: create a page which will accept data, e.g. text fields
	//  and will have a submit button, which will pass the data to the server
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
