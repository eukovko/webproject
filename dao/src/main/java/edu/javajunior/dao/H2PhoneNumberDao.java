package edu.javajunior.dao;

import edu.javajunior.entity.PhoneNumberEntity;
import edu.javajunior.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2PhoneNumberDao extends H2Dao<PhoneNumberEntity> {

	private static final String CREATE_PHONE_NUMBER = "INSERT INTO phone_number (user_id, number) VALUES (?, ?)";
	private static final String SELECT_PHONE_NUMBER = "SELECT * FROM phone_number WHERE id = ?";
	private static final String SELECT_USER_PHONE_NUMBERS = "SELECT * FROM phone_number WHERE user_id = ?";
	private static final String UPDATE_PHONE_NUMBER = "UPDATE phone_number SET user_id = ?, number = ? WHERE id = ?";
	private static final String DELETE_PHONE_NUMBER = "DELETE FROM phone_number WHERE id = ?";
	private static final String DELETE_USER_PHONE_NUMBERS = "DELETE FROM phone_number WHERE user_id = ?";

	public H2PhoneNumberDao() {
		init();
	}

	@Override
	public PhoneNumberEntity createEntity(PhoneNumberEntity entity) {
		Long id = null;
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(CREATE_PHONE_NUMBER, Statement.RETURN_GENERATED_KEYS)) {
			connection.setAutoCommit(false);
			statement.setLong(1, entity.getUserId());
			statement.setString(2, entity.getPhoneNumber());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			id = checkId(generatedKeys);
			connection.commit();
		} catch (SQLException e) {
			throw new DaoException("Database create number error", e);
		}
		return entity.withId(id);
	}

	@Override
	public PhoneNumberEntity getEntity(Long id) {
		PhoneNumberEntity phoneNumber = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_NUMBER)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				long phoneNumberId = resultSet.getLong(1);
				long userId = resultSet.getLong(2);
				String number = resultSet.getString(3);
				phoneNumber = new PhoneNumberEntity(phoneNumberId, userId, number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phoneNumber;
	}

	public List<PhoneNumberEntity> getUserEntities(Long userId) {
		ArrayList<PhoneNumberEntity> numbers = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_PHONE_NUMBERS)) {
			preparedStatement.setLong(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				long phoneNumberId = resultSet.getLong(1);
				long phoneUserId = resultSet.getLong(2);
				String number = resultSet.getString(3);
				numbers.add(new PhoneNumberEntity(phoneNumberId, phoneUserId, number));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numbers;
	}

	@Override
	public void removeEntity(Long id) {
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PHONE_NUMBER)) {
			connection.setAutoCommit(false);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeUserEntities(Long userId) {
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_PHONE_NUMBERS)) {
			connection.setAutoCommit(false);
			preparedStatement.setLong(1, userId);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEntity(PhoneNumberEntity entity) {
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHONE_NUMBER)) {
			connection.setAutoCommit(false);
			preparedStatement.setLong(1, entity.getUserId());
			preparedStatement.setString(2, entity.getPhoneNumber());
			preparedStatement.setLong(3, entity.getId());
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {
			String sql =
				"CREATE TABLE IF NOT EXISTS phone_number\n" +
					"(\n" +
					"    id           BIGINT AUTO_INCREMENT,\n" +
					"    user_id      BIGINT,\n" +
					"    number VARCHAR(20),\n" +
					"    PRIMARY KEY (id),\n" +
					"    FOREIGN KEY (user_id) REFERENCES user (id)\n" +
					");\n";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException("Error during phone number dao initialization", e);
		}
	}
}
