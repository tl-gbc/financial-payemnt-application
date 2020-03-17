
package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import beans.User;
import dao.UserDao;

public class ValidationRequirements {
	
	public static boolean name_validation(String name) {
		boolean check_name = Pattern.matches("^[a-zA-Z]*$", name);
		return check_name;
	}
	
	public static boolean email_validation(String email) {
		boolean check_email = Pattern.matches("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])+([-\\w]*[0-9a-zA-Z])*\\.)+[a-zA-Z]{2,9})", email);
		return check_email;
	}
	
	public static boolean password_validation(String password) {
		boolean check_password = Pattern.matches("(?=.*[0-9])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{6,12}", password);
		return check_password; 
	}	

	public static List<String> validateRegistration(User user) {
		List<String> errorMessages = new ArrayList<String>();
		try {
			if(!UserDao.check_duplicate(user.getEmail())) {
				errorMessages.add("There is already a user with this email");
			}
			else {
				if(!name_validation(user.getFirstname()) || !name_validation(user.getLastname())) {
					errorMessages.add("First and last name must contain only alphabets");
				}
				if(!email_validation(user.getEmail())) {
					errorMessages.add("Please enter a valid email form");
				}				
				if(!password_validation(user.getPassword())) {
					errorMessages.add("Passwords must <br>" + "&#183; be 6-12 characters in length <br>" + "&#183; contain at least 1 uppercase letter <br>" + "&#183; contain at least 1 special character");
				}				
				if(!Objects.equals(user.getPassword(), user.getPasswordConfirmation())) {
					errorMessages.add("Passwords do not match");
				}				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}					
		return errorMessages;
	}
}
