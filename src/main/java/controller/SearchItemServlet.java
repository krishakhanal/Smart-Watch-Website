package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductDAO;

/**
 * Servlet implementation class SearchItemServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/searchItems" })
public class SearchItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("searchQuery");

		ProductDAO productDAO = new ProductDAO();
		List<Product> listProduct = productDAO.searchAvailableItems(searchQuery);

		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/home.jsp");
		dispatcher.forward(request, response);
	}
}

