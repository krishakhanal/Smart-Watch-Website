
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Product;
import service.OrderDAO;
import service.ProductDAO;

/**
 * Servlet implementation class ConfirmOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/confirmOrder" })
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("loggedInUserId");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		int orderedQuantity = Integer.parseInt(request.getParameter("quantity"));

		// Remove cart items associated with the order
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateCartItems(orderedQuantity , cart_id);
		System.out.println("Updated Card");

		List<Order> cartItems = orderDAO.getCartItems(userId);

		request.setAttribute("cartItems", cartItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
		dispatcher.forward(request, response);
	}

}
