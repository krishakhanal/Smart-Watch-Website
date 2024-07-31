package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.DatabaseConnectivity;
import utils.PasswordHash;

public class UserDAO {
	//	private String jdbcURL = "jdbc:mysql://localhost:3306/time_treasures2";
	//    private String jdbcUsername = "root";
	//    private String jdbcPassword = "";

	private static final String INSERT_USER_SQL = "INSERT INTO users" +
			"  (username, password, first_name, last_name, email, is_admin) VALUES " +
			" (?, ?, ?, ?, ?, ?);";

	private static final String UPDATE_USER_SQL = "UPDATE users " +
			"SET username = ?, first_name = ?, last_name = ?, email = ? " +
			"WHERE id = ?;";



	public UserDAO() {}

	//    protected Connection getConnection() {
	//        Connection connection = null;
	//        try {
	//            Class.forName("com.mysql.jdbc.Driver");
	//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	//        } catch (SQLException e) {
	//            // TODO Auto-generated catch block
	//            e.printStackTrace();
	//        } catch (ClassNotFoundException e) {
	//            // TODO Auto-generated catch block
	//            e.printStackTrace();
	//        }
	//        return connection;
	//    }

	public User getUserByUsername(String username) throws SQLException {
		String query = "SELECT * FROM users WHERE username = ?";
		try (	Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setFirstName(resultSet.getString("first_name"));
					user.setLastName(resultSet.getString("last_name"));
					user.setEmail(resultSet.getString("email"));
					user.setAdmin(resultSet.getBoolean("is_admin"));
					return user;
				}
			}
		}
		return null;
	}

	public User getUserByEmail(String email) throws SQLException {
		String query = "SELECT * FROM users WHERE email = ?";
		try (	Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setFirstName(resultSet.getString("first_name"));
					user.setLastName(resultSet.getString("last_name"));
					user.setEmail(resultSet.getString("email"));
					user.setAdmin(resultSet.getBoolean("is_admin"));
					return user;
				}
			}
		}
		return null;
	}


	public void insertUser(User user) throws SQLException {
		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, PasswordHash.getPasswordHash(user.getPassword()));
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setBoolean(6, user.isAdmin());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}
	} 

	public void editUser(User user) throws SQLException {
		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5,  user.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}
	} 
}