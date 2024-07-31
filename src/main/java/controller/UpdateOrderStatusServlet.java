package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderDAO;
import service.ProductDAO;

/**
 * Servlet implementation class UpdateOrderStatusServlet
 */
@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateOrderStatus(cart_id, orderStatus);

		// Redirect back to the page displaying the confirmed orders
		response.sendRedirect(request.getContextPath() + "/allConfirmOrder");
	}
}

