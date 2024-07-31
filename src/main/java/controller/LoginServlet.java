package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.ProductDAO;
import service.UserDAO;
import utils.PasswordHash;


@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public LoginServlet() {
		this.userDAO = new UserDAO();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user;
		try {
			user = userDAO.getUserByUsername(username);
			if (user != null) {
				if(PasswordHash.verifyPassword(password, user.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("username", user.getUsername());
					session.setAttribute("loggedInUserId", user.getId());
					System.out.println("Is Admin : "+ user.isAdmin());
					if (user.isAdmin()) {
						response.sendRedirect(request.getContextPath() + "/list");
					} else {
						response.sendRedirect(request.getContextPath() + "/home");
					}
				}

				else {
					request.setAttribute("error", "Password is incorrect");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
					dispatcher.forward(request, response);
				}

			}
			else if(user == null) {
				// Authentication failed
				request.setAttribute("error", "User is not registered, please register by clicking the link below.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatcher.forward(request, response);
			}
			else {
				// Authentication failed
				request.setAttribute("error", "Invalid username or password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatcher.forward(request, response);
	}
}

