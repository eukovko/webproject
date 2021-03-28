package edu.juniorplus.controller.request;

// TODO: 3/28/2021 Remove controller and set up HttpHandler
public abstract class AbstractRequestUserController implements RequestUserController {

	public abstract Response createUser(Request request);
	public abstract Response getUser(Request request);
	public abstract Response removeUser(Request request);
	public abstract Response updateUser(Request request);

	@Override
	public Response handleRequest(Request request) {
		System.out.println("Controller dispatcher");
		Operation operation = request.getOperation();
		try {
			switch (operation) {
				case GET:
					System.out.println("Handling GET method");
					return getUser(request);
				case POST:
					System.out.println("Handling POST method");
					return createUser(request);
				case PUT:
					System.out.println("Handling PUT method");
					return updateUser(request);
				case DELETE:
					System.out.println("Handling DELETE method");
					return removeUser(request);
				default:
					throw new IllegalStateException("Unexpected value: " + operation);
			}
		} catch (Exception e) {
			Response response = new Response();
			response.setError(e.getMessage());
			return response;
		}
	}
}
