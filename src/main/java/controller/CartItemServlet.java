package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Order;
import model.Product;
import service.OrderDAO;
import service.ProductDAO;

/**
 * Servlet implementation class CartItemServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addToCart" })
public class CartItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		int userId = (int) request.getSession().getAttribute("loggedInUserId");
		int productId = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insertCartItem(userId, productId, quantity);

		// Retrieve cart items for the user
		List<Order> cartItems = orderDAO.getCartItems(userId);

		// Set the cart items as an attribute in the request
		request.setAttribute("cartItems", cartItems);

		// Forward the request to the cart.jsp for display
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUserId") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginErrorPage.jsp");
			dispatcher.forward(request, response);
		}
		else {
			int userId = (int) request.getSession().getAttribute("loggedInUserId");

			OrderDAO orderDAO = new OrderDAO();
			List<Order> cartItems = orderDAO.getCartItems(userId);

			request.setAttribute("cartItems", cartItems);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
			dispatcher.forward(request, response);
		}

	}
}