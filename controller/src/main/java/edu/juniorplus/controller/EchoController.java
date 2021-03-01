package edu.juniorplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.juniorplus.domain.User;
import edu.juniorplus.service.UserService;

import java.io.IOException;

public class EchoController extends AbstractUserController {

    private static final String CREATE = "create";
    private static final String GET = "get";
    private static final String REMOVE = "remove";
    private static final String UPDATE = "update";
    private ObjectMapper objectMapper = new ObjectMapper();

    public EchoController(UserService userService) {
        super(userService);
    }

    @Override
    public String createUser(String data) throws IOException {
        System.out.println("Create user");
        User user = objectMapper.readValue(data, User.class);
        User newUser = getUserService().createUser(user);
        System.out.println(objectMapper.writeValueAsString(newUser));
        return objectMapper.writeValueAsString(newUser);
    }

    @Override
    public String getUser(String data) throws JsonProcessingException {
        System.out.println("Get user");
        Long id = Long.valueOf(data);
        User user = getUserService().getUser(id);
        return objectMapper.writeValueAsString(user);
    }

    @Override
    public String removeUser(String data) throws JsonProcessingException {
        System.out.println("Remove user");
        Long id = Long.valueOf(data);
        User user = getUserService().getUser(id);
        getUserService().removeUser(id);
        return objectMapper.writeValueAsString(user);
    }

    @Override
    public String updateUser(String data) throws IOException {
        System.out.println("Update user");
        User user = objectMapper.readValue(data, User.class);
        getUserService().updateUser(user);
        return objectMapper.writeValueAsString(user);

    }

    @Override
    public String handleRequest(String string) throws IOException {
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

    private String removeOperation(String string, String operation) {
        return string.substring(operation.length()).trim();
    }
}