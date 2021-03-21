package edu.juniorplus;

import edu.javajunior.dao.H2PhoneNumberDao;
import edu.javajunior.dao.H2UserDao;
import edu.juniorplus.controller.request.BaseRequestUserController;
import edu.juniorplus.controller.request.RequestUserController;
import edu.juniorplus.service.BasicUserService;
import edu.juniorplus.service.UserService;

public class Context {

	public static RequestUserController userController() {
		return new BaseRequestUserController(userService());
	}

	public static UserService userService() {
		return new BasicUserService(userDao(), phoneNumberDao());
	}

	public static H2UserDao userDao() {
		return new H2UserDao();
	}

	public static H2PhoneNumberDao phoneNumberDao() {
		return new H2PhoneNumberDao();
	}

}
