package edu.juniorplus.controller.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.juniorplus.domain.User;
import edu.juniorplus.service.UserService;

public class JsonUserController extends AbstractUserController {

	private ObjectMapper objectMapper = new ObjectMapper();

	public JsonUserController(UserService userService) {
		super(userService);
	}

	@Override
	public String createUser(String data) {
		try {
			User user = objectMapper.readValue(data, User.class);
			User newUser = getUserService().createUser(user);
			return objectMapper.writeValueAsString(newUser);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Invalid data", e);
		}
	}

	@Override
	public String getUser(String data) {
		long id = Long.parseLong(data);
		User user = getUserService().getUser(id);
		try {
			return objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Invalid data", e);
		}
	}

	@Override
	public String removeUser(String data) {
		long id = Long.parseLong(data);
		getUserService().removeUser(id);
		return String.format("User %d has been removed", id);
	}

	@Override
	public String updateUser(String data) {
		User user = null;
		try {
			user = objectMapper.readValue(data, User.class);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Invalid data", e);
		}
		getUserService().updateUser(user);
		return String.format("User %d has been updated", user.getId());
	}
}
