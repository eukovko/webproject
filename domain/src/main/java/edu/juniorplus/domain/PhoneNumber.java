package edu.juniorplus.domain;

import java.util.regex.Pattern;

// TODO: 3/14/2021 Validate with setters (mutable)
public class PhoneNumber {

	private static final String PHONE_NUMBER_REGEX = "^[+][0-9]{3}[(][0-9]{1,2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}$";
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
	private static final String FORMAT_ERROR_MESSAGE = "Phone number should be represented in the following format " +
		"+[3 digit code]([2 digit operator code])[3 digits]-[2 digits]-[2 digits]";

	// TODO: 3/14/2021 Split into country code, operator code and number
	private String phoneNumber;

	public PhoneNumber() {
	}

	public PhoneNumber(String phoneNumber) {
		if (!checkFormat(phoneNumber)) {
			throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
		}
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static boolean checkFormat(String password) {
		return PHONE_NUMBER_PATTERN.matcher(password).matches();
	}

	@Override
	public String toString() {
		return phoneNumber;
	}
}
