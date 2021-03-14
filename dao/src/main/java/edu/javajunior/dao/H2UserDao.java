package edu.javajunior.dao;

import edu.javajunior.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2UserDao extends H2Dao implements UserDao {

	// TODO: 3/14/2021  
	private static final String CREATE_USER = "INSERT INTO user (login, email, password, phone_number) VALUES (?,?,?,?)";
	private static final String SELECT_USER = "SELECT * FROM user WHERE id = ?";
	private static final String DELETE_USER = "DELETE user WHERE id = ?";
	private static final String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ?, phone_number = ? WHERE id = ?";
	private static final String PHONE_NUMBER_DELIMITER = ":";

	public H2UserDao() {
		init();
	}

	@Override
	public UserEntity createUserEntity(UserEntity user) {
		Long id = null;
		try (Connection connection = getConnection();
			 // TODO: 3/14/2021 Read about SQL injections
			 PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)){

			connection.setAutoCommit(false);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, String.join(PHONE_NUMBER_DELIMITER, user.getPhoneNumber()));
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			}
			if (id == null) {
				throw new RuntimeException("User id is null");
			}
			connection.commit();
		} catch (SQLException e) {
			// TODO: 3/14/2021 Create new dao exception
			throw new RuntimeException(e);
		}
		return user.withId(id);
	}

	@Override
	public UserEntity getUserEntity(Long id) {
		return null;
	}

	@Override
	public void removeUserEntity(Long id) {

	}

	@Override
	public void updateUserEntity(UserEntity user) {

	}

	private void init() {
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {
			String sql = "CREATE TABLE IF NOT EXISTS user (" +
				"id BIGINT AUTO_INCREMENT, " +
				"login VARCHAR(10), " +
				"email VARCHAR(20), " +
				"password VARCHAR(20), " +
				"phone_number VARCHAR(50), " +
				"PRIMARY KEY (id)" +
				")";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: 3/14/2021 Create new dao exception
			throw new RuntimeException(e);
		}
	}
}
