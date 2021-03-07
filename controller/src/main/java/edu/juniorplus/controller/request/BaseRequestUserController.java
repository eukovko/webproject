package edu.juniorplus.controller.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.juniorplus.domain.User;
import edu.juniorplus.service.UserService;

public class BaseRequestUserController extends AbstractRequestUserController {

	private UserService userService;
	private ObjectMapper objectMapper = new ObjectMapper();

	public BaseRequestUserController(UserService userService) {
		this.userService = userService;
	}

	public BaseRequestUserController() {

	}

	@Override
	public Response createUser(Request request) {
		Response response = new Response();
		try {
			User user = objectMapper.readValue(request.getPayload(), User.class);
			User newUser = userService.createUser(user);
			response.setPayload(objectMapper.writeValueAsString(newUser));
		} catch (JsonProcessingException e) {
			response.setError(e.getMessage());
		}
		return response;
	}

	@Override
	public Response getUser(Request request) {
		Response response = new Response();
		try {
			Long id = Long.valueOf(request.getPayload());
			User newUser = userService.getUser(id);
			response.setPayload(objectMapper.writeValueAsString(newUser));
		} catch (JsonProcessingException e) {
			response.setError(e.getMessage());
		}
		return response;
	}

	@Override
	public Response removeUser(Request request) {
		Response response = new Response();
		Long id = Long.valueOf(request.getPayload());
		userService.removeUser(id);
		response.setPayload(String.format("User %d has been removed", id));
		return response;
	}

	@Override
	public Response updateUser(Request request) {
		Response response = new Response();
		try {
			User user = objectMapper.readValue(request.getPayload(), User.class);
			userService.updateUser(user);
			response.setPayload(String.format("User %d has been updated", user.getId()));
		} catch (JsonProcessingException e) {
			response.setError(e.getMessage());
		}
		return response;
	}
}
