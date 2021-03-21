package edu.juniorplus.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;

public final class Login {

	private static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
	private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Login should contain only certain characters and be not longer than 19 characters";

	private final String value;

	@JsonCreator
	public Login(@JsonProperty("value") String value) {
		if (!checkFormat(value)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static boolean checkFormat(String password) {
		return LOGIN_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		return value;
	}
}
