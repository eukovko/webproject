package edu.javajunior.entity;

public final class UserEntity extends Entity {

	private final String login;
	private final String email;
	private final String password;

	public UserEntity(String login, String email, String password) {
		this(null, login, email, password);
	}
	public UserEntity(Long id, String login, String email, String password) {
		super(id);
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public UserEntity withId(Long id) {
		return new UserEntity(id, login, email, password);
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
