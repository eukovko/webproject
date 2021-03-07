package edu.juniorplus;

import edu.juniorplus.controller.request.Operation;
import edu.juniorplus.controller.request.Request;
import edu.juniorplus.controller.request.RequestUserController;

public class App
{
    public static void main( String[] args )
    {
        Request request = new Request();
        request.setOperation(Operation.POST);
        request.setPayload("{'id': 42, 'login': 'jdoe'}");

        RequestUserController baseRequestUserController = Context.userController();
        baseRequestUserController.handleRequest(request);

    }
}
