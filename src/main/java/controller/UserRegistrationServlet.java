package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.ProductDAO;
import service.UserDAO;

/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/userRegistration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		userDAO = new UserDAO(); // Initialize UserDAO
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		User existingUser;
		User existingEmail;
		try {
			existingUser = userDAO.getUserByUsername(username) ;
			existingEmail = userDAO.getUserByEmail(email);
			if (existingUser != null || existingEmail != null) {
				request.setAttribute("error", "Username or Email already exists");
				request.getRequestDispatcher("/WEB-INF/view/userRegistration.jsp").forward(request, response);
			} else {
				User newUser = new User(username, password, firstName, lastName, email);
				userDAO.insertUser(newUser);
				response.sendRedirect(request.getContextPath() + "/login");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userRegistration.jsp");
		dispatcher.forward(request, response);
	}
}

