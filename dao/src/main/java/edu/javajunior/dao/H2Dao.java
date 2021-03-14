package edu.javajunior.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class H2Dao {

	private static final String DB_URL = "jdbc:h2:./app";
	private static final String DRIVER_NAME = "org.h2.Driver";

	public H2Dao() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
