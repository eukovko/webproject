package edu.juniorplus.service;

import edu.javajunior.dao.H2PhoneNumberDao;
import edu.javajunior.dao.H2UserDao;
import edu.javajunior.entity.PhoneNumberEntity;
import edu.javajunior.entity.UserEntity;
import edu.juniorplus.domain.Email;
import edu.juniorplus.domain.Login;
import edu.juniorplus.domain.Password;
import edu.juniorplus.domain.PhoneNumber;
import edu.juniorplus.domain.User;
import edu.juniorplus.exception.ServiceLayerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasicUserService extends AbstractUserService {

	public BasicUserService(H2UserDao userDao, H2PhoneNumberDao phoneNumberDao) {
		super(userDao, phoneNumberDao);
	}

	@Override
	public User createUser(User user) {
		if (user.getId() != null) {
			throw new ServiceLayerException("New user should not contain id");
		}
		UserEntity entity = new UserEntity(user.getLogin().toString(), user.getEmail().toString(), user.getPassword().toString());
		List<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
		List<PhoneNumberEntity> phoneNumberEntities = phoneNumbers.stream()
			.map(PhoneNumber::toString)
			.map(PhoneNumberEntity::new)
			.collect(Collectors.toList());

		// TODO: 3/21/2021 ACID (Atomicity)
		// getConnection
		UserEntity userEntity = getUserDao().createEntity(entity); // pass connection
		phoneNumberEntities.stream()
			.map(s-> s.withUserId(userEntity.getId()))
			.forEach(getPhoneNumberDao()::createEntity); // pass connection
		// connection commit
		return user.withId(userEntity.getId());
	}

	@Override
	public User getUser(long id) {
		UserEntity user = getUserDao().getEntity(id);
		List<PhoneNumberEntity> phones = getPhoneNumberDao().getUserEntities(id);
		if (user == null) {
			throw new ServiceLayerException("User doesn't exists");
		}

		Login login = new Login(user.getLogin());
		Email email = new Email(user.getEmail());
		Password password = new Password(user.getPassword());
		List<PhoneNumber> numbers = phones.stream().map(PhoneNumberEntity::getPhoneNumber).map(PhoneNumber::new).collect(Collectors.toList());

		return new User(user.getId(), login, email, password, numbers);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<>();
		List<UserEntity> users = getUserDao().getAll();
		for (UserEntity user : users) {
			List<PhoneNumberEntity> phones = getPhoneNumberDao().getUserEntities(user.getId());
			Login login = new Login(user.getLogin());
			Email email = new Email(user.getEmail());
			Password password = new Password(user.getPassword());
			List<PhoneNumber> numbers = phones.stream().map(PhoneNumberEntity::getPhoneNumber).map(PhoneNumber::new).collect(Collectors.toList());

			result.add(new User(user.getId(), login, email, password, numbers));
		}
		return result;
	}

	@Override
	public void removeUser(long id) {
		if (getUser(id) == null) {
			throw new ServiceLayerException("Cannot remove nonexistent user");
		}
		getPhoneNumberDao().removeUserEntities(id);
		getUserDao().removeEntity(id);
	}

	@Override
	public void updateUser(User user) {
		if (user.getId()!=null) {
			throw new ServiceLayerException("User should contain id in order to be updated");
		}
		UserEntity userEntity = getUserDao().getEntity(user.getId());
		if (userEntity==null) {
			throw new ServiceLayerException("Cannot update nonexistent user");
		}
		UserEntity entity = new UserEntity(user.getLogin().toString(), user.getEmail().toString(), user.getPassword().toString());
		getUserDao().updateEntity(entity);
		getPhoneNumberDao().removeUserEntities(user.getId());
		user.getPhoneNumbers().stream()
			.map(PhoneNumber::toString)
			.map(PhoneNumberEntity::new)
			.map(s -> s.withUserId(user.getId()))
			.forEach(getPhoneNumberDao()::createEntity);
	}
}
