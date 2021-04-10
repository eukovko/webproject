package edu.juniorplus;

import com.sun.net.httpserver.HttpHandler;
import edu.javajunior.dao.H2PhoneNumberDao;
import edu.javajunior.dao.H2UserDao;
import edu.juniorplus.controller.handler.UserHandler;
import edu.juniorplus.service.BasicUserService;
import edu.juniorplus.service.UserService;

public class Context {

	public static UserService userService() {
		return new BasicUserService(userDao(), phoneNumberDao());
	}

	public static H2UserDao userDao() {
		return new H2UserDao();
	}

	public static H2PhoneNumberDao phoneNumberDao() {
		return new H2PhoneNumberDao();
	}

	public static HttpHandler handler() {
		return new UserHandler(userService());
	}
}
