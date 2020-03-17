
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;


@WebServlet("/Authenticate")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public AuthenticateServlet() {
        super();
   }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {		

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = "";
		if(username == "" || password == "") {
			request.getRequestDispatcher("Login").forward(request, response);
		}else {
			try {
				String authenticate_user = UserDao.authenticate(username,password);
				if(authenticate_user != null){					
					HttpSession session = request.getSession();					
					session.setAttribute("username", username);
					session.setAttribute("firstName", authenticate_user);
					session.setMaxInactiveInterval(60 * 20);
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);					
				}
				else {
					message += "Invaid username and/or password";
					request.setAttribute("error_invalid_login", message);
					request.getRequestDispatcher("Login").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
}