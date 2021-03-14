package edu.juniorplus.service;

import edu.javajunior.entity.UserEntity;
import edu.juniorplus.domain.Email;
import edu.juniorplus.domain.Login;
import edu.juniorplus.domain.Password;
import edu.juniorplus.domain.PhoneNumber;
import edu.juniorplus.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserConverter {

	public UserEntity convert(User user){
		List<String> phoneNumbers = user.getPhoneNumbers().stream().map(Objects::toString).collect(Collectors.toList());
		return new UserEntity(user.getId(), user.getLogin().toString(), user.getEmail().toString(), user.getPassword().toString(), phoneNumbers);
	}

	public User convert(UserEntity userEntity){
		List<PhoneNumber> phoneNumbers = userEntity.getPhoneNumber().stream().map(PhoneNumber::new).collect(Collectors.toList());
		return new User(userEntity.getId(), new Login(userEntity.getLogin()), new Email(userEntity.getEmail()), new Password(userEntity.getPassword()), phoneNumbers);
	}
}
