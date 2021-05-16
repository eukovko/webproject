package edu.juniorplus;

import com.sun.net.httpserver.HttpHandler;
import edu.javajunior.dao.H2PhoneNumberDao;
import edu.javajunior.dao.H2UserDao;
import edu.juniorplus.controller.handler.CalculatorHandler;
import edu.juniorplus.controller.handler.HelloHandler;
import edu.juniorplus.controller.handler.IndexPageHandler;
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

	public static HttpHandler helloHandler() {
		return new HelloHandler();
	}

	public static HttpHandler userHandler() {
		return new UserHandler(userService());
	}

	public static HttpHandler calculatorHandler() {
		return new CalculatorHandler();
	}

	public static HttpHandler indexPageHandler() {
		return new IndexPageHandler();
	}
}
