
package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.User;
import dao.UserDao;
import utilities.*;
import java.util.*;

@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("register.jsp").forward(request, response);		
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");

		User user = new User();
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		user.setPasswordConfirmation(passwordConfirmation);
		
		List<String> error = ValidationRequirements.validateRegistration(user);		
		if(error.size() == 0) {
			try {				
				UserDao.register(user);
				UserDao.insertRole(user);
				EmailConfirmation.sendEmail("comp3095java@gmail.com", "comp3095", 
						user.getEmail(), user.getFirstname(), user.getLastname());				
				request.setAttribute("email", email);
				request.getRequestDispatcher("email_confirmation.jsp").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		else {
			request.setAttribute("errorMessages", error);
			request.getRequestDispatcher("register.jsp").forward(request, response);			
		}		
	}
}