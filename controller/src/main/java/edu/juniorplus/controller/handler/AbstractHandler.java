package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.juniorplus.exception.ServiceLayerException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractHandler implements HttpHandler {

	private static final String ERROR_MESSAGE = "Sorry, something went wrong. Try again.";

	@Override
	// This is a dispatcher method which route the execution to a specific method
	// Method can be chosen based on the method name or other attributes
	public void handle(HttpExchange exchange) {
		String method = exchange.getRequestMethod();
		String response = "";
		try {
			switch (method) {
				case "POST":
					response = post(exchange);
					break;
				case "GET":
					response = get(exchange);
					break;
				case "PUT":
					response = put(exchange);
					break;
				case "DELETE":
					response = delete(exchange);
					break;
				default:
					throw new RuntimeException("Operation is not supported");
			}
			// TODO: 4/10/2021 Add handlers for each exception, the best way to do it is to create own exception
		} catch (ServiceLayerException e) {
			writeResponse(exchange, e.getMessage());
		} catch (Exception e) {
			writeResponse(exchange, ERROR_MESSAGE);
		}
		writeResponse(exchange, response);
	}

	protected String readRequest(HttpExchange exchange) {
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

	protected void writeResponse(HttpExchange exchange, String response) {
		byte[] payload = response.getBytes();
		try (OutputStream outputStream = exchange.getResponseBody()) {
			// TODO: 4/10/2021 Put appropriate response status for each method
			exchange.sendResponseHeaders(200, payload.length);
			outputStream.write(payload);
			outputStream.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public abstract String post(HttpExchange exchange) throws Exception;

	public abstract String get(HttpExchange exchange) throws Exception;

	public abstract String put(HttpExchange exchange) throws Exception;

	public abstract String delete(HttpExchange exchange) throws Exception;
}
