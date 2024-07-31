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
import service.UserDAO;

/**
 * Servlet implementation class EditUserInfoServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/editUser" })
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public EditUserInfoServlet() {
		this.userDAO = new UserDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		User existingUser;
		try {
			existingUser = userDAO.getUserByUsername(username);
			request.setAttribute("currentUser", existingUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userEdit.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int user_id = (int)request.getSession().getAttribute("loggedInUserId"); 

		User existingUser;
		User existingEmail;
		try {
			User newUser = new User(user_id,username, firstName, lastName, email);
			userDAO.editUser(newUser);
			request.getSession().setAttribute("username",username);
			response.sendRedirect(request.getContextPath() + "/home");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
