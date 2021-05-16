package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexPageHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO: 4/10/2021 3. Create an HTML page, put it into resources folder and return it, basically create one file server
		// TODO: 4/10/2021 4. Do 1 and 2 task with the form: create a page which will accept data, e.g. text fields
		// TODO: 5/16/2021 Check all the file in resources and create handlers for static files
		byte[] html = Files.readAllBytes(Paths.get("controller/src/main/resources/index.html").toAbsolutePath());
		exchange.sendResponseHeaders(200, html.length);
		try (OutputStream outputStream = exchange.getResponseBody()) {
			outputStream.write(html);
			outputStream.flush();
		}
	}
}
