package utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DatabaseConnectivity {
	private static BasicDataSource dataSource;
	private static Connection conn;
	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/time_treasures2");
		dataSource.setUsername("root");
		dataSource.setPassword("");

	}

	public static Connection getConnection() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
