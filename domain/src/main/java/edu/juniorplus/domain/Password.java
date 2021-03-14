package edu.juniorplus.domain;

import java.util.regex.Pattern;


public final class Password {

	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Password should contain minimum eight characters, at least one uppercase letter, " +
		"one lowercase letter, one number and one special character";

	// TODO: 3/14/2021 Change name
	private String password;

	public Password() {
	}

	public Password(String password) {
		if (checkFormat(password)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static boolean checkFormat(String password) {
		return PASSWORD_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		return password;
	}
}
