package edu.juniorplus.service;

import edu.javajunior.dao.UserDao;
import edu.javajunior.entity.UserEntity;
import edu.juniorplus.domain.User;

public class BasicUserService extends AbstractUserService {

	private UserConverter userConverter = new UserConverter();

	public BasicUserService(UserDao userDao) {
		super(userDao);
	}

	@Override
	public User createUser(User user) {
		UserEntity entity = userConverter.convert(user);
		UserEntity newUser = getUserDao().createUserEntity(entity);
		return userConverter.convert(newUser);
	}

	@Override
	public User getUser(Long id) {
		UserEntity userEntity = getUserDao().getUserEntity(id);
		return userConverter.convert(userEntity);
	}

	@Override
	public void removeUser(Long id) {
		getUserDao().removeUserEntity(id);
	}

	@Override
	public void updateUser(User user) {

		UserEntity userEntity = getUserDao().getUserEntity(user.getId());
		if (userEntity==null) {
			throw new IllegalArgumentException();
		}

		UserEntity entity = userConverter.convert(user);
		getUserDao().updateUserEntity(entity);
	}

}
