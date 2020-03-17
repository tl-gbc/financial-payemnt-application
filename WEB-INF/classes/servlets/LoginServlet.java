
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.Recaptcha;
import utilities.ValidationRequirements;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String reCaptchaResponse = request.getParameter("g-recaptcha-response");
		
		String message = "";
		if(Recaptcha.verify_reCaptcha(reCaptchaResponse)) {
			if(!ValidationRequirements.email_validation(username) && (!ValidationRequirements.password_validation(password))){
				message += "Please enter a valid email and/or password";
				request.setAttribute("error_invalid_login", message);
				request.getRequestDispatcher("login.jsp").include(request, response);
				}else {
					request.getRequestDispatcher("Authenticate").forward(request, response);
			}		
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);			
		}

		if(request.getParameter("logout") != null)
		{
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("login.jsp").include(request, response);	
		}
	}
}