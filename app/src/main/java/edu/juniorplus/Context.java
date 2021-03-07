package edu.juniorplus;

import edu.javajunior.dao.MapUserDao;
import edu.javajunior.dao.UserDao;
import edu.juniorplus.controller.request.BaseRequestUserController;
import edu.juniorplus.controller.request.RequestUserController;
import edu.juniorplus.service.EchoUserService;
import edu.juniorplus.service.UserService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Context {

	public static RequestUserController userController(){
		return new BaseRequestUserController(userService());
	}

	public static UserService userService(){
		return new EchoUserService();
	}

	public static UserDao userDao(){
		return new MapUserDao();
	}

	public static InputStream inputStream(){
		String request = "create {\"id\":42,\"login\":\"John Doe\",\"email\":\"jdoe@gmail.com\",\"password\":\"213412\",\"phoneNumber\":[\"21335242\",\"1234242\"]}\n" +
			"get 42\n" +
			"update {\"id\":42,\"login\":\"John Doe\",\"email\":\"jdoe123@gmail.com\",\"password\":\"$tr0nG_pa$$W0Rd\",\"phoneNumber\":[\"21335242\",\"1234242\"]}\n" +
			"get 42\n" +
			"delete 42\n" +
			"get 42\n" +
			"exit app";
		return new ByteArrayInputStream(request.getBytes());
	}

}
