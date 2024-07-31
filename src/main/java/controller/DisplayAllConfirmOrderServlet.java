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
 * Servlet implementation class DisplayAllConfirmOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/allConfirmOrder" })
public class DisplayAllConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayAllConfirmOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = (int) request.getSession().getAttribute("loggedInUserId");

		OrderDAO orderDAO = new OrderDAO();
		List<Order> cartItems = orderDAO.getAllConfirmedOrders();

		request.setAttribute("cartItems", cartItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/allConfirmedOrders.jsp");
		dispatcher.forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
