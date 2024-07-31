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

import model.Order;
import model.Product;
import service.OrderDAO;
import service.ProductDAO;

/**
 * Servlet implementation class DisplayConfirmOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userConfirmOrder" })
public class DisplayUserConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayUserConfirmOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUserId") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginErrorPage.jsp");
			dispatcher.forward(request, response);
		}
		else {
			int userId = (int) request.getSession().getAttribute("loggedInUserId");

			OrderDAO orderDAO = new OrderDAO();
			List<Order> cartItems = orderDAO.getUserConfirmedOrders(userId);

			request.setAttribute("cartItems", cartItems);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userConfirmedOrders.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}