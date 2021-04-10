package edu.juniorplus.service;

import edu.juniorplus.domain.User;

import java.util.List;

public interface UserService {
	User createUser(User user);
	User getUser(long id);
	List<User> getAllUsers();
	void removeUser(long id);
	void updateUser(User user);
}
