package edu.juniorplus.domain;

import java.util.List;

public class User {

	private Long id;
	private Login login;
	private Email email;
	private Password password;
	private List<PhoneNumber> phoneNumbers;

	public User() {
	}

	public User(Long id, Login login, Email email, Password password, List<PhoneNumber> phoneNumbers) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.phoneNumbers = phoneNumbers;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
