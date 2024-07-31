package controller;

import java.io.IOException;
import java.util.List;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductDAO;

/**
 * Servlet implementation class Product
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/product" })
public class ProductUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductUser() {
		//        super();
		this.productDAO = new ProductDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//		request.getRequestDispatcher("/WEB-INF/view/productuser.jsp").forward(request, response);
		List <Product> listProduct = productDAO.selectAllProducts();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productuser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
