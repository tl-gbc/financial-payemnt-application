
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import utilities.ForgotPasswordEmail;

@WebServlet("/ForgotPassword")

public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");

		try {
			String password_fromDB = UserDao.forgot_password(username);			
			ForgotPasswordEmail.sendPassword("comp3095java@gmail.com", "comp3095", username, password_fromDB);
			request.getRequestDispatcher("login.jsp").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}