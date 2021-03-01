package edu.juniorplus;

import edu.javajunior.dao.MapUserDao;
import edu.javajunior.dao.UserDao;
import edu.juniorplus.controller.BasicUserController;
import edu.juniorplus.controller.EchoController;
import edu.juniorplus.controller.UserController;
import edu.juniorplus.service.BasicUserService;
import edu.juniorplus.service.UserService;

public class Context {

	public static UserController userController(){
		return new EchoController(userService());
	}

	public static UserService userService(){
		return new BasicUserService(userDao());
	}

	public static UserDao userDao(){
		return new MapUserDao();
	}
}
