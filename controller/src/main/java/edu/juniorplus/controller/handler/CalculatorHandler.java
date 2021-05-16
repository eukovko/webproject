package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CalculatorHandler implements HttpHandler {

	// TODO: 4/10/2021 2. Create a calculator which will return the sum of two numbers which will be set from headers
	// TODO: 5/16/2021 Create a request with curl

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers requestHeaders = exchange.getRequestHeaders();
		String a = requestHeaders.getFirst("a");
		String b = requestHeaders.getFirst("b");
		String operation = requestHeaders.getFirst("operation");

		int result = 0;
		int firstOperand = 0;
		int secondOperand = 0;
		if ((a != null) && (b != null) && (operation != null)) {
			firstOperand = Integer.parseInt(a);
			secondOperand = Integer.parseInt(b);
			switch (operation) {
				case "sum": result = firstOperand + secondOperand; break;
				default: result = 0;
			}
		}
		String html = "<h1>Hello to our calculator</h1>\n" +
			"<h2>First operand: %d</h2>\n" +
			"<h2>Second operand: %d</h2>\n" +
			"<h2>Operation: %s</h2>\n" +
			"<h2>The result is: %d</h2>";

		String message = String.format(html, firstOperand, secondOperand, operation, result);
		exchange.sendResponseHeaders(200, message.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
	}
}
