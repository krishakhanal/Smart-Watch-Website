package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductDAO;

@WebServlet("/")
@MultipartConfig  //For image
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	public ProductServlet() {
		this.productDAO = new ProductDAO();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			case "/list":
				listProduct(request, response);
				break;
			default:
				homeProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Product> listProduct = productDAO.selectAllProducts();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/product-list.jsp");
		dispatcher.forward(request, response);
	}

	private void homeProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Product> listProduct = productDAO.selectAllProducts();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/home.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		Product existingProduct = productDAO.selectProduct(product_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/product-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}



	/*
	 * private void insertProduct(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException { String product_name =
	 * request.getParameter("product_name"); String description =
	 * request.getParameter("description"); String priceStr =
	 * request.getParameter("price"); double price = 0; if (priceStr != null &&
	 * priceStr.length() > 0) { price = Double.parseDouble(priceStr); } String
	 * stockStr = request.getParameter("stock"); int stock = 0; if (stockStr != null
	 * && stockStr.length() > 0) { stock = Integer.parseInt(stockStr); } Product
	 * newProduct = new Product(product_name, description, price, stock );
	 * productDAO.insertProduct(newProduct); response.sendRedirect("list"); }
	 */

	private void insertProduct(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException, ServletException {
		String product_name = request.getParameter("product_name"); 
		String description = request.getParameter("description"); 
		String priceStr = request.getParameter("price"); 
		Part filePart = request.getPart("image"); 
		double price = 0; 
		if (priceStr != null && priceStr.length() > 0) {
			price = Double.parseDouble(priceStr);
		} 
		String stockStr = request.getParameter("stock"); 
		int stock = 0; if (stockStr != null  && stockStr.length() > 0) {
			stock = Integer.parseInt(stockStr); 
		} 
		InputStream inputStream = filePart.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
		Product newProduct = new Product(product_name, description, price, stock, imageBytes );
		productDAO.insertProduct(newProduct); 
		response.sendRedirect("list"); 
	}



	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String product_name = request.getParameter("product_name");
		String description = request.getParameter("description");
		String priceStr = request.getParameter("price");
		Part filePart = request.getPart("image"); 
		double price = 0;  
		if (priceStr != null && priceStr.length() > 0) {
			price = Double.parseDouble(priceStr);
		}
		String stockStr = request.getParameter("stock");
		int stock = 0;  
		if (stockStr != null && stockStr.length() > 0) {
			stock = Integer.parseInt(stockStr);
		}
		InputStream inputStream = filePart.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
		Product product = new Product(product_id,product_name, description, price, stock, imageBytes );
		productDAO.updateProduct(product);
		response.sendRedirect("list");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		productDAO.deleteProduct(product_id);
		response.sendRedirect("list");

	}



}