package edu.javajunior.dao;

import java.util.List;

// TODO: 3/21/2021 Remove this interface
//  and create two separate for UserDao and PhoneNumberDao with appropriately named methods
public interface Dao<E> {

	E createEntity(E entity);

	E getEntity(Long id);

	List<E> getAll();

	void removeEntity(Long id);

	void updateEntity(E entity);
}
