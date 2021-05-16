package edu.juniorplus.controller.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.juniorplus.service.UserService;

import java.io.IOException;

public class UserFormHandler implements HttpHandler {

	private final UserService userService;

	public UserFormHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
	}
}
