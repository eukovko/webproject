package edu.juniorplus;

import edu.javajunior.dao.H2UserDao;
import edu.javajunior.dao.UserDao;
import edu.juniorplus.controller.request.BaseRequestUserController;
import edu.juniorplus.controller.request.RequestUserController;
import edu.juniorplus.service.BasicUserService;
import edu.juniorplus.service.UserService;

public class Context {

	public static RequestUserController userController(){
		return new BaseRequestUserController(userService());
	}

	public static UserService userService(){
		return new BasicUserService(userDao());
	}

	public static UserDao userDao(){
		return new H2UserDao();
	}


}
