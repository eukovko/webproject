package edu.juniorplus.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;

public final class Email {

	private static final String ADDRESS_REGEX = "^[^@]$";
	private static final String DOMAIN_REGEX = "^[^@]+\\.[^@]+$";
	private static final Pattern ADDRESS_PATTERN = Pattern.compile(ADDRESS_REGEX);
	private static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX);
	private static final String EMAIL_DELIMITER = "@";
	private final String address;
	private final String domain;

	public Email(String email) {
		String[] emailElements = email.split(EMAIL_DELIMITER);
		if (emailElements.length != 2) {
			throw new IllegalArgumentException("Email format is invalid");
		}
		checkEmailFormat(emailElements[0], emailElements[1]);
		this.address = emailElements[0];
		this.domain = emailElements[1];
	}
	@JsonCreator
	public Email(@JsonProperty("address") String address,@JsonProperty("domain") String domain) {
		checkEmailFormat(address, domain);
		this.address = address;
		this.domain = domain;
	}

	private void checkEmailFormat(String address, String domain) {
		boolean validAddress = checkAddressFormat(address);
		boolean validDomain = checkDomainFormat(domain);
		if (!validAddress && !validDomain) {
			throw new IllegalArgumentException("Email format is invalid");
		}
	}

	public String getAddress() {
		return address;
	}

	public String getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		return String.format("%s%s%s", address, EMAIL_DELIMITER, domain);
	}

	public static boolean checkAddressFormat(String address){
		return ADDRESS_PATTERN.matcher(address).matches();
	}

	public static boolean checkDomainFormat(String domain) {
		return DOMAIN_PATTERN.matcher(domain).matches();
	}
}
