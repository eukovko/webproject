package edu.juniorplus;

import edu.juniorplus.controller.request.Operation;
import edu.juniorplus.controller.request.Request;
import edu.juniorplus.controller.request.RequestUserController;
import edu.juniorplus.controller.request.Response;

public class App
{
    public static void main( String[] args )
    {
        Request request = new Request();
        request.setOperation(Operation.POST);
		// TODO: 3/14/2021 Remove id
        request.setPayload("{\n" +
			"  \"id\": null,\n" +
			"  \"login\": {\n" +
			"    \"login\": \"JohnDoe\"\n" +
			"  },\n" +
			"  \"email\": {\n" +
			"    \"address\": \"jdoe@@\",\n" +
			"    \"domain\": \"google.com\"\n" +
			"  },\n" +
			"  \"password\": {\n" +
			"    \"password\": \"132Uds342@#!\"\n" +
			"  },\n" +
			"  \"phoneNumbers\": [\n" +
			"    {\n" +
			"      \"phoneNumber\": \"+435(34)324-32-43\"\n" +
			"    }\n" +
			"  ]\n" +
			"}");

        RequestUserController controller = Context.userController();

        Response response = controller.handleRequest(request);

        if (response.getError() == null) {
            System.out.println(response.getPayload());
        } else {
            System.out.println(response.getError());
        }

    }
}
