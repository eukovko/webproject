package edu.juniorplus.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;


public final class Password {

	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Password should contain minimum eight characters, at least one uppercase letter, " +
		"one lowercase letter, one number and one special character";

	private final String value;

	@JsonCreator
	public Password(@JsonProperty("value") String value) {
		if (checkFormat(value)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static boolean checkFormat(String password) {
		return PASSWORD_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		return value;
	}
}
