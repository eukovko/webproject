package edu.juniorplus.service;

import edu.javajunior.dao.H2PhoneNumberDao;
import edu.javajunior.dao.H2UserDao;

public abstract class AbstractUserService implements UserService{

	private final H2UserDao userDao;
	private final H2PhoneNumberDao phoneNumberDao;

	public AbstractUserService(H2UserDao userDao, H2PhoneNumberDao phoneNumberDao) {
		this.userDao = userDao;
		this.phoneNumberDao = phoneNumberDao;
	}

	public H2UserDao  getUserDao() {
		return userDao;
	}

	public H2PhoneNumberDao getPhoneNumberDao() {
		return phoneNumberDao;
	}
}
