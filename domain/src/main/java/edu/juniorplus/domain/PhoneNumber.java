package edu.juniorplus.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;

public final class PhoneNumber {

	private static final String PHONE_NUMBER_REGEX = "^[+][0-9]{3}[(][0-9]{2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}$";
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Phone number should be represented in the following format " +
		"+[3 digit code]([2 digit operator code])[3 digits]-[2 digits]-[2 digits]";

	private final Integer countryCode;
	private final Integer operatorCode;
	private final Integer number;

	@JsonCreator
	public PhoneNumber(@JsonProperty("phoneNumber") String phoneNumber) {
		if (!checkFormat(phoneNumber)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		operatorCode = Integer.valueOf(phoneNumber.substring(1, 3));
		countryCode = Integer.valueOf(phoneNumber.substring(5, 7));
		number = Integer.valueOf(phoneNumber.substring(8,17).replace("-", ""));
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public Integer getNumber() {
		return number;
	}

	public static boolean checkFormat(String password) {
		return PHONE_NUMBER_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		String phone = number.toString().replaceAll("(\\d{3})(\\d{2})(\\d{2})", "$1-$2-$3");
		return String.format("+%d(%d)%s",operatorCode, countryCode, phone);
	}
}
