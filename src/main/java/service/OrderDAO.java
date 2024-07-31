package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;
import utils.DatabaseConnectivity;

public class OrderDAO {
	// 	private String jdbcURL = "jdbc:mysql://localhost:3306/time_treasures2";
	//    private String jdbcUsername = "root";
	//    private String jdbcPassword = "";

	private static final String INSERT_CART_ITEM_SQL = "INSERT INTO cart_items" +
			" (user_id, product_id)" +
			" VALUES (?, ?);";

	public OrderDAO() {}

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




	public void insertCartItem(int userId, int productId, int quantity) {
		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM cart_items WHERE user_id = ? AND product_id = ?");
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?, ?, ?)");
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE cart_items SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?");
				PreparedStatement updateStockStatement = connection.prepareStatement("UPDATE products SET stock = stock - ? WHERE product_id = ?");		 ) {

			checkStatement.setInt(1, userId);
			checkStatement.setInt(2, productId);
			ResultSet resultSet = checkStatement.executeQuery();

			if (resultSet.next()) {
				updateStockStatement.setInt(1, quantity);
				updateStockStatement.setInt(2, productId);
				updateStockStatement.executeUpdate();
				// Product already exists in cart, update quantity
				updateStatement.setInt(1, quantity);
				updateStatement.setInt(2, userId);
				updateStatement.setInt(3, productId);
				updateStatement.executeUpdate();
			} else {
				updateStockStatement.setInt(1, quantity);
				updateStockStatement.setInt(2, productId);
				updateStockStatement.executeUpdate();
				// Product doesn't exist in cart, insert new row
				insertStatement.setInt(1, userId);
				insertStatement.setInt(2, productId);
				insertStatement.setInt(3, quantity);
				insertStatement.executeUpdate();
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void insertOrders(int cartId,int userId, int productId, int quantity) {
		try (Connection connection = DatabaseConnectivity.getConnection();

				PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM cart_items WHERE user_id = ? AND product_id = ?");
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO orders (cart_id,user_id, product_id, quantity) VALUES (?, ?, ?,?)");
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE orders SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?")) {

			checkStatement.setInt(1, userId);
			checkStatement.setInt(2, productId);
			ResultSet resultSet = checkStatement.executeQuery();

			if (resultSet.next()) {
				// Product already exists in cart, update quantity
				updateStatement.setInt(1, quantity);
				updateStatement.setInt(2, userId);
				updateStatement.setInt(3, productId);
				updateStatement.executeUpdate();
			} else {
				// Product doesn't exist in cart, insert new row
				checkStatement.setInt(1, cartId);
				insertStatement.setInt(2, userId);
				insertStatement.setInt(3, productId);
				insertStatement.setInt(4, quantity);
				insertStatement.executeUpdate();
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}



	public List<Order> getCartItems(int userId) {
		List<Order> cartItems = new ArrayList<>();
		System.out.println("Inside get cart items");
		String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.stock, p.image,ci.quantity, ci.cart_id " +
				"FROM products p " +
				"INNER JOIN cart_items ci ON p.product_id = ci.product_id " +
				"WHERE ci.user_id = ?";

		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");
				int quantity = rs.getInt("quantity");
				int cart_id = rs.getInt("cart_id");

				Order order = new Order(productId, productName, description, price, stock, image, quantity,cart_id);
				cartItems.add(order);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cartItems;
	}


	public void updateCartItems(int orderedQuantity,int cart_id) {
		try (Connection connection = DatabaseConnectivity.getConnection()) {
			// Update products table
			/*
			 * try (PreparedStatement preparedStatement2 = connection.prepareStatement(
			 * "UPDATE products SET stock = stock - ? WHERE product_id IN (SELECT product_id FROM cart_items WHERE cart_id = ?)"
			 * )) { preparedStatement2.setInt(1, orderedQuantity);
			 * preparedStatement2.setInt(2, cart_id);
			 * System.out.println("preparedStatement2: " + preparedStatement2);
			 * preparedStatement2.executeUpdate(); }
			 */

			// Insert into orders table
			try (PreparedStatement preparedStatement3 = connection.prepareStatement(
					"INSERT INTO orders (product_id, user_id, cart_id, quantity) SELECT product_id, user_id, cart_id, ? FROM cart_items WHERE cart_id = ?")) {
				preparedStatement3.setInt(1, orderedQuantity);
				preparedStatement3.setInt(2, cart_id);
				System.out.println("preparedStatement3: " + preparedStatement3);
				preparedStatement3.executeUpdate();
			}

			// Update cart_items table
			try (PreparedStatement preparedStatement1 = connection.prepareStatement(
					"DELETE from cart_items  WHERE cart_id = ?")) {
				preparedStatement1.setInt(1, cart_id);
				System.out.println("preparedStatement1: " + preparedStatement1);
				preparedStatement1.executeUpdate();
			}


		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void removeCartItems(int orderedQuantity,int cart_id) {
		try (Connection connection = DatabaseConnectivity.getConnection()) {

			// Insert into orders table
			try (PreparedStatement preparedStatement3 = connection.prepareStatement(
					"Update products set stock = stock + ? where product_id in( SELECT product_id FROM cart_items WHERE cart_id = ?)")) {
				preparedStatement3.setInt(1, orderedQuantity);
				preparedStatement3.setInt(2, cart_id);
				System.out.println("preparedStatement3: " + preparedStatement3);
				preparedStatement3.executeUpdate();
			}

			// Update cart_items table
			try (PreparedStatement preparedStatement1 = connection.prepareStatement(
					"DELETE from cart_items  WHERE cart_id = ?")) {
				preparedStatement1.setInt(1, cart_id);
				System.out.println("preparedStatement1: " + preparedStatement1);
				preparedStatement1.executeUpdate();
			}


		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public List<Order> getUserConfirmedOrders(int userId) {
		List<Order> cartItems = new ArrayList<>();
		System.out.println("Inside get user specific confirmed order items");
		String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.stock, p.image,ci.quantity, ci.cart_id, ci.orderStatus " +
				"FROM products p " +
				"INNER JOIN orders ci ON p.product_id = ci.product_id " +
				"WHERE ci.user_id = ? ";

		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");
				int quantity = rs.getInt("quantity");
				int cart_id = rs.getInt("cart_id");
				int orderStatus = rs.getInt("orderStatus");

				Order order = new Order(productId, productName, description, price, stock, image, quantity,cart_id, orderStatus);
				cartItems.add(order);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cartItems;
	}

	public List<Order> getAllConfirmedOrders() {
		List<Order> cartItems = new ArrayList<>();
		System.out.println("Inside get all confirmed items");
		String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.stock, p.image, ci.quantity, ci.cart_id, u.username, u.email , ci.orderStatus " +
				"FROM products p " +
				"INNER JOIN orders ci ON p.product_id = ci.product_id " +
				"INNER JOIN users u ON ci.user_id = u.id ";

		try (Connection connection = DatabaseConnectivity.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				byte[] image = rs.getBytes("image");
				int quantity = rs.getInt("quantity");
				int cart_id = rs.getInt("cart_id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				int orderStatus = rs.getInt("orderStatus");

				Order order = new Order(productId, productName, description, price, stock, image, quantity, cart_id, username, email, orderStatus );
				cartItems.add(order);
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

	public void updateOrderStatus(int cart_id, int orderStatus) {
		try (Connection connection = DatabaseConnectivity.getConnection()) {
			// Update cart_items table
			try (PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE orders SET orderStatus = ? WHERE cart_id = ?")) {
				preparedStatement.setInt(1, orderStatus);
				preparedStatement.setInt(2, cart_id);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {


		}
	}}