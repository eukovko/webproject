package edu.juniorplus.controller.request;

public abstract class AbstractRequestUserController implements RequestUserController {

	public abstract Response createUser(Request request);
	public abstract Response getUser(Request request);
	public abstract Response removeUser(Request request);
	public abstract Response updateUser(Request request);

	@Override
	public Response handleRequest(Request request) {
		Operation operation = request.getOperation();
		switch (operation) {
			case GET:
				return getUser(request);
			case POST:
				return createUser(request);
			case PUT:
				return updateUser(request);
			case DELETE:
				return removeUser(request);
			default:
				throw new IllegalStateException("Unexpected value: " + operation);
		}
	}
}
