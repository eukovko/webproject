package edu.juniorplus.controller.string;

import edu.juniorplus.service.UserService;

public abstract class AbstractUserController implements UserController{

	private static final String CREATE = "create";
	private static final String GET = "get";
	private static final String REMOVE = "remove";
	private static final String UPDATE = "update";
	private final UserService userService;

	public AbstractUserController(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public abstract String createUser(String data);
	public abstract String getUser(String data);
	public abstract String removeUser(String data);
	public abstract String updateUser(String data);

	@Override
	public String handleRequest(String string) {
		string = string.trim();
		if (string.startsWith(CREATE)) {
			return createUser(removeOperation(string, CREATE));
		}
		if (string.startsWith(GET)) {
			return getUser(removeOperation(string, GET));
		}
		if (string.startsWith(REMOVE)) {
			return removeUser(removeOperation(string, REMOVE));
		}
		if (string.startsWith(UPDATE)) {
			return updateUser(removeOperation(string, UPDATE));
		}
		throw new IllegalArgumentException("Unsupported operation");
	}
	private String removeOperation(String string, String operation){
		return string.substring(operation.length()).trim();
	}
}
