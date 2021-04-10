package edu.javajunior.dao;

import edu.javajunior.entity.UserEntity;
import edu.javajunior.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2UserDao extends H2Dao<UserEntity> {

	private static final String CREATE_USER = "INSERT INTO user (login, email, password) VALUES (?,?,?)";
	private static final String SELECT_USER = "SELECT id, login, email, password FROM user WHERE id = ?";
	private static final String SELECT_ALL_USERS = "SELECT id, login, email, password FROM user";
	private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
	private static final String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ? WHERE id = ?";

	public H2UserDao() {
		init();
	}

	@Override
	public UserEntity createEntity(UserEntity user) {
		Long id = null;
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)){
			connection.setAutoCommit(false);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
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
			throw new DaoException("Database user creation error", e);
		}
		return user.withId(id);
	}

	@Override
	public UserEntity getEntity(Long id) {
		UserEntity entity = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Long userId = resultSet.getLong(1);
				String login = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				entity = new UserEntity(userId, login, email, password);
			}
		} catch (SQLException e) {
			throw new DaoException("Database user fetching error", e);
		}
		return entity;
	}

	@Override
	public List<UserEntity> getAll() {
		List<UserEntity> users = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Long userId = resultSet.getLong(1);
				String login = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				users.add(new UserEntity(userId, login, email, password));
			}
		} catch (SQLException e) {
			throw new DaoException("Database user fetching error", e);
		}
		return users;
	}

	@Override
	public void removeEntity(Long id) {
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
			connection.setAutoCommit(false);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			throw new DaoException("Database user deletion error", e);
		}
	}

	@Override
	public void updateEntity(UserEntity user) {
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setLong(4, user.getId());
			connection.commit();
		} catch (SQLException e) {
			throw new DaoException("Database user update error", e);
		}
	}

	private void init() {
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {
			String sql =
				"CREATE TABLE IF NOT EXISTS user\n" +
					"(\n" +
					"    id       BIGINT AUTO_INCREMENT,\n" +
					"    login    VARCHAR(20),\n" +
					"    email    VARCHAR(50),\n" +
					"    password VARCHAR(20),\n" +
					"    PRIMARY KEY (id)\n" +
					");";
			statement.executeUpdate(sql);
			String users = new String(Files.readAllBytes(Paths.get("dao/src/main/resources/user.sql")));
			statement.executeUpdate(users);
		} catch (SQLException | IOException e) {
			throw new DaoException("Error during user dao initialization", e);
		}
	}
}
