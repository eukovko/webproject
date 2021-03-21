package edu.juniorplus.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class User {

	private final Long id;
	private final Login login;
	private final Email email;
	private final Password password;
	private final List<PhoneNumber> phoneNumbers;

	@JsonCreator
	public User(@JsonProperty("id") Long id, @JsonProperty("login") Login login, @JsonProperty("email") Email email,
				@JsonProperty("password") Password password, @JsonProperty("phoneNumbers") List<PhoneNumber> phoneNumbers) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.phoneNumbers = phoneNumbers;
	}

	public User withId(Long id) {
		return new User(id, login, email, password, phoneNumbers);
	}

	public Long getId() {
		return id;
	}

	public Login getLogin() {
		return login;
	}

	public Email getEmail() {
		return email;
	}

	public Password getPassword() {
		return password;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

}
