package edu.javajunior.dao;

import edu.javajunior.entity.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class H2Dao<E extends Entity> implements Dao<E> {

	private static final String DB_URL = "jdbc:h2:./app";
	private static final String DRIVER_NAME = "org.h2.Driver";

	public H2Dao() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static protected Long checkId(ResultSet generatedKeys) throws SQLException {
		Long id = null;
		if (generatedKeys.next()) {
			id = generatedKeys.getLong(1);
		}
		if (id == null) {
			throw new RuntimeException("Id is not set");
		}
		return id;
	}

	protected Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
