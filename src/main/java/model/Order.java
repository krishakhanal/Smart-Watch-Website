package model;

import java.util.Base64;

public class Order {
	private int product_id;
	private String product_name;
	private String description;
	private double price;
	private int stock;
	private byte[] image;
	private int quantity;
	private int cart_id;
	private String username;
	private String email;
	private int orderStatus;

	public Order() {

	}

	public Order(int product_id,String product_name, String description, double price, int stock, byte[] image, int quantity, int cart_id, String username, String email, int orderStatus) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.quantity = quantity;
		this.cart_id = cart_id;
		this.username = username;
		this.email = email;
		this.orderStatus = orderStatus;
	}

	public Order(int product_id,String product_name, String description, double price, int stock, byte[] image, int quantity, int cart_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.quantity = quantity;
		this.cart_id = cart_id;
	}

	public Order(int product_id,String product_name, String description, double price, int stock, byte[] image, int quantity, int cart_id, int orderStatus) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.quantity = quantity;
		this.cart_id = cart_id;
		this.orderStatus = orderStatus;
	}
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageBase64() {
		if (image != null) {
			return Base64.getEncoder().encodeToString(image);
		}
		return "";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//for orders and carts
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

}