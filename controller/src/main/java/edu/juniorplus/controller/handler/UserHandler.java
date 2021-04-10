package edu.juniorplus.controller.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import edu.juniorplus.domain.User;
import edu.juniorplus.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO: 4/10/2021 Create a main handler which will process all the requests and will handle it down based on the path
//  e.g exchange.getRequestURI().getPath().startsWith("/phones");
public class UserHandler extends AbstractHandler {

	private static final String INCORRECT_PATH_MESSAGE = "Incorrect path";
	private final UserService userService;
	private final ObjectMapper objectMapper;
	private final Pattern GET_USER_BY_ID = Pattern.compile("/users/(\\d+)");
	private final Pattern GET_ALL_USERS = Pattern.compile("/users");
	private final String EMPTY_RESPONSE = "";

	public UserHandler(UserService userService) {
		this.userService = userService;
		this.objectMapper = new ObjectMapper();
	}

	// TODO: 4/10/2021 Set appropriate response messages for each operation
	@Override
	public String post(HttpExchange exchange) throws IOException {
		// Read user from http request body
		String userData = readRequest(exchange);
		// Pass it to the service layer
		User user = objectMapper.readValue(userData, User.class);
		User createdUser = userService.createUser(user);
		return objectMapper.writeValueAsString(createdUser);
	}

	@Override
	public String get(HttpExchange exchange) throws JsonProcessingException {
		// Check the path
		String path = exchange.getRequestURI().getPath();
		// Fetch one user
		if (GET_USER_BY_ID.matcher(path).matches()) {
			return getUserById(exchange);
		// Fetch all users
		} else if (GET_ALL_USERS.matcher(path).matches()){
			return getAllUsers(exchange);
		}
		throw new IllegalArgumentException(INCORRECT_PATH_MESSAGE);
	}

	// These methods are private as they doesn't used outside the method
	// Private methods encapsulates logic and makes the class more readable
	// Usually private methods should be defined right after the place where they were used
	private String getUserById(HttpExchange exchange) throws JsonProcessingException {
		long userId = getUserId(exchange);
		User user = userService.getUser(userId);
		return objectMapper.writeValueAsString(user);
	}

	private String getAllUsers(HttpExchange exchange) throws JsonProcessingException {
		List<User> users = userService.getAllUsers();
		return objectMapper.writeValueAsString(users);
	}

	@Override
	public String put(HttpExchange exchange) throws IOException {
		// Read user from http request body
		String userData = readRequest(exchange);
		// Pass it to the service layer
		User user = objectMapper.readValue(userData, User.class);
		userService.updateUser(user);
		return EMPTY_RESPONSE;
	}

	@Override
	public String delete(HttpExchange exchange) {
		long userId = getUserId(exchange);
		userService.removeUser(userId);
		return EMPTY_RESPONSE;
	}

	// TODO: 4/10/2021 Move this method to either MainHandler or Abstract handler and use it for PhoneNumber as well
	private long getUserId(HttpExchange exchange) {
		String path = exchange.getRequestURI().getPath();
		Matcher matcher = GET_USER_BY_ID.matcher(path);
		if (matcher.matches()) {
			return Long.parseLong(matcher.group(1));
		}
		throw new IllegalArgumentException("Incorrect path");
	}
}
