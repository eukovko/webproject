package edu.juniorplus.controller.string;

public class EchoController implements UserController{

	@Override
	public String handleRequest(String string) {
		return string;
	}
}
