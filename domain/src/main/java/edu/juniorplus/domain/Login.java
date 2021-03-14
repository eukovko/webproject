package edu.juniorplus.domain;

import java.util.regex.Pattern;

public class Login {

	private static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
	private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Login should contain only certain characters and be not longer than 19 characters";

	// TODO: 3/14/2021 Change name (value)
	private String login;

	public Login() {
	}

	public Login(String login) {
		if (!checkFormat(login)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public static boolean checkFormat(String password) {
		return LOGIN_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		return login;
	}
}
