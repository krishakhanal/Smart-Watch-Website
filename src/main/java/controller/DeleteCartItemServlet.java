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
import service.OrderDAO;

/**
 * Servlet implementation class DeleteCartItemServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/deleteCartItem" })
public class DeleteCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCartItemServlet() {
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
		// TODO Auto-generated method stub
		int userId = (int) request.getSession().getAttribute("loggedInUserId");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		int orderedQuantity = Integer.parseInt(request.getParameter("quantity"));

		// Remove cart items associated with the order
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.removeCartItems(orderedQuantity , cart_id);

		List<Order> cartItems = orderDAO.getCartItems(userId);

		request.setAttribute("cartItems", cartItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
		dispatcher.forward(request, response);
	}

}
