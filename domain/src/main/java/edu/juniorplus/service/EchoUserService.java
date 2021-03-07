package edu.juniorplus.service;

import edu.juniorplus.domain.User;

public class EchoUserService implements UserService{

	@Override
	public User createUser(User user) {
		System.out.println("Create user in EchoUserService");
		return user;
	}

	@Override
	public User getUser(Long id) {
		System.out.println("Get user in EchoUserService");
		User user = new User();
		user.setId(id);
		return user;
	}

	@Override
	public void removeUser(Long id) {
		System.out.println("Remove user in EchoUserService");
	}

	@Override
	public void updateUser(User user) {
		System.out.println("Update user in EchoUserService");
	}
}
