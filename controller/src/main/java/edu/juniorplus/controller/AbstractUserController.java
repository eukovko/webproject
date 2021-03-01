package edu.juniorplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.juniorplus.service.UserService;

import java.io.IOException;

public abstract class AbstractUserController implements UserController{

	private final UserService userService;

	public AbstractUserController(UserService userService) {
		this.userService = userService;
	}

	public abstract String createUser(String data) throws IOException;
	public abstract String getUser(String data) throws JsonProcessingException;
	public abstract String removeUser(String data) throws JsonProcessingException;
	public abstract String updateUser(String data) throws IOException;

	public UserService getUserService() {
		return userService;
	}
}
