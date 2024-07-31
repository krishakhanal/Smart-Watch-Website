package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.DatabaseConnectivity;

public class ProductDAO {
	//	 	private String jdbcURL = "jdbc:mysql://localhost:3306/time_treasures2";
	//	    private String jdbcUsername = "root";
	//	    private String jdbcPassword = "";

	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (product_name, description, price, stock, image) VALUES " +
			" (?, ?, ?, ?, ?);";

	private static final String SELECT_PRODUCT_BY_ID = "select product_id,product_name, description, price, stock, image from products where product_id =?";
	private static final String SELECT_ALL_PRODUCTS = "select * from products";
	private static final String DELETE_PRODUCTS_SQL = "delete from products where product_id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set product_name = ?,description= ?, price =?, stock =?, image =? where product_id = ?;";
	private static final String UPDATE_PRODUCTS_SQL_NOIMAGE = "update products set product_name = ?,description= ?, price =?, stock =? where product_id = ?;";

	public ProductDAO() {}

	//	    protected Connection getConnection() {
	//	        Connection connection = null;
	//	        try {
	//	            Class.forName("com.mysql.jdbc.Driver");
	//	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	//	        } catch (SQLException e) {
	//	            // TODO Auto-generated catch block
	//	            e.printStackTrace();
	//	        } catch (ClassNotFoundException e) {
	//	            // TODO Auto-generated catch block
	//	            e.printStackTrace();
	//	        }
	//	        return connection;
	//	    }

	public void insertProduct(Product product) throws SQLException {
		System.out.println(INSERT_PRODUCTS_SQL);
		try (Connection connection = DatabaseConnectivity.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			preparedStatement.setString(1, product.getProduct_name());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getStock());
			preparedStatement.setBytes(5, product.getImage()); 
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectProduct(int id) {
		Product product = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DatabaseConnectivity.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String product_name = rs.getString("product_name");
				String description = rs.getString("description");
				Double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");
				product = new Product(id, product_name, description, price, stock,image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public List <Product> selectAllProducts() {
		List <Product> products = new ArrayList < > ();
		// Step 1: Establishing a Connection
		try (Connection connection = DatabaseConnectivity.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				String description = rs.getString("description");
				Double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");
				products.add(new Product(product_id, product_name, description, price, stock, image));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return products;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DatabaseConnectivity.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
		if (product.getImage() == null || product.getImage().length == 0) {
			try (Connection connection = DatabaseConnectivity.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL_NOIMAGE);) {
				statement.setString(1, product.getProduct_name());
				statement.setString(2, product.getDescription());
				statement.setDouble(3, product.getPrice());
				statement.setInt(4, product.getStock());
				statement.setInt(5, product.getProduct_id());

				rowUpdated = statement.executeUpdate() > 0;
			}
		} else {
			try (Connection connection = DatabaseConnectivity.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
				statement.setString(1, product.getProduct_name());
				statement.setString(2, product.getDescription());
				statement.setDouble(3, product.getPrice());
				statement.setInt(4, product.getStock());
				statement.setBytes(5, product.getImage()); 
				statement.setInt(6, product.getProduct_id());

				rowUpdated = statement.executeUpdate() > 0;
			}
		}

		return rowUpdated;
	}



	public List<Product> searchAvailableItems(String searchQuery) {
		List<Product> cartItems = new ArrayList<>();
		String sql = "select product_id,product_name, description, price, stock, image from products where product_name like ? or price =  ?";

		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, "%" + searchQuery + "%");
			preparedStatement.setString(2, searchQuery );
			System.out.println("prepare: "+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");

				Product product = new Product(productId, productName, description, price, stock, image);
				cartItems.add(product);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cartItems;
	}



	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}