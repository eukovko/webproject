package edu.juniorplus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.juniorplus.controller.request.Operation;
import edu.juniorplus.controller.request.Request;
import edu.juniorplus.controller.request.RequestUserController;
import edu.juniorplus.controller.request.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServerHandler implements HttpHandler {

	private final RequestUserController userController;

	public ServerHandler() {
		userController = Context.userController();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("Handling a request...");
		String method = exchange.getRequestMethod();
		Operation operation = Operation.valueOf(method);
		String requestPayload = readBody(exchange);
		Request request = new Request(operation, requestPayload);

		Response response = userController.handleRequest(request);
		String responsePayload = response.getPayload() != null ? response.getPayload() : response.getError();

		System.out.printf("%s %s%n", operation.toString(), responsePayload);

		exchange.sendResponseHeaders(200, responsePayload.length());
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(responsePayload.getBytes());
			outputStream.flush();
		}

	}

	protected String readBody(HttpExchange exchange) {
		String body;
		try (InputStream inputStream = exchange.getRequestBody();
			 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			int b;
			while ((b = inputStream.read()) != -1) {
				outputStream.write(b);
			}
			body = outputStream.toString();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return body;
	}
}
