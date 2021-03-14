package edu.juniorplus.service;

import edu.javajunior.dao.UserDao;
import edu.javajunior.entity.UserEntity;
import edu.juniorplus.domain.User;
import edu.juniorplus.exception.ServiceLayerException;

import java.util.Optional;

public class BasicUserService extends AbstractUserService {

	private UserConverter userConverter = new UserConverter();

	public BasicUserService(UserDao userDao) {
		super(userDao);
	}

	@Override
	public User createUser(User user) {
		if (user.getId() != null) {
			throw new ServiceLayerException("New user should not contain id");
		}
		UserEntity entity = userConverter.convert(user);
		UserEntity newUser = getUserDao().createUserEntity(entity);
		return userConverter.convert(newUser);
	}

	@Override
	public User getUser(Long id) {
		UserEntity userEntity = getUserDao().getUserEntity(id);

		if (userEntity == null) {
			throw new ServiceLayerException("User doesn't exists");
		}
		return userConverter.convert(userEntity);
	}

	@Override
	public void removeUser(Long id) {
		if (getUser(id) == null) {
			throw new ServiceLayerException("Cannot remove nonexistent user");
		}
		getUserDao().removeUserEntity(id);
	}

	@Override
	public void updateUser(User user) {
		if (user.getId()!=null) {
			throw new ServiceLayerException("User should contain id in order to be updated");
		}
		UserEntity userEntity = getUserDao().getUserEntity(user.getId());
		if (userEntity==null) {
			throw new ServiceLayerException("Cannot update nonexistent user");
		}
		UserEntity entity = userConverter.convert(user);
		getUserDao().updateUserEntity(entity);
	}


}
