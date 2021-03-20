package edu.juniorplus.domain;

import java.util.regex.Pattern;

// TODO: 3/14/2021 Make immutable
// TODO: 3/14/2021 Use Jackson annotation like JsonCreator and JsonProperty
public final class Email {

	private static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.[^@]+$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	private static final String EMAIL_DELIMITER = "@";
	private  String address;
	private String domain;

	public Email() {
	}

	public Email(String email) {
		if (!checkFormat(email)) {
			throw new IllegalArgumentException("Invalid email format: " + email);
		}
		String[] mailElements = email.split(EMAIL_DELIMITER);
		this.address = mailElements[0];
		this.domain = mailElements[1];
	}

	public Email(String address, String domain) {
		this.address = address;
		this.domain = domain;
	}

	public String getAddress() {
		return address;
	}

	public String getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		return String.format("%s@%s", address, domain);
	}

	public static boolean checkFormat(String email){
		return PATTERN.matcher(email).matches();
	}
}
