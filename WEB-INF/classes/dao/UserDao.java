
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import beans.User;
import utilities.DatabaseAccess;

public class UserDao {

	//authenticate user and return user's first name
	  public static String authenticate(String username, String password) throws Exception {
		 
		  try {
			  Connection conn = DatabaseAccess.getConnection();
			  String query_authenticate = "SELECT * FROM USERS WHERE email = ? AND password = ?";
			  java.sql.PreparedStatement st = conn.prepareStatement(query_authenticate);
			  st.setString(1, username);
			  st.setString(2, password);
			  ResultSet rs = st.executeQuery();
			  if(rs.next()) {
				  return rs.getString("firstname");
			  }
			  return null;
		  }
		  catch (Exception e){
			  throw e;
		  }
	  }
	  
	  //check if the email is used or not
	  public static boolean check_duplicate(String username) throws Exception{
		  try {
			  Connection conn = DatabaseAccess.getConnection();
			  String query_check_duplicate = "SELECT COUNT(*) FROM USERS WHERE email = (?)";			  
			  java.sql.PreparedStatement st = conn.prepareStatement(query_check_duplicate);
			  st.setString(1, username);		  
			  ResultSet rs = st.executeQuery();			  
			  if(rs.next()) {
				  if(Integer.parseInt(rs.getString(1)) == 0) {
					  return true;
				  }
			  }
			  return false;
		  }
		  catch(Exception e) {
			  throw e;
		  }
	  }
	  
	  //take password from database and send email to user
	  public static String forgot_password(String username) throws Exception {
			 
		  try {
			  Connection conn = DatabaseAccess.getConnection();
			  String query_forgot_password = "SELECT * FROM USERS WHERE email = ?";
			  java.sql.PreparedStatement st = conn.prepareStatement(query_forgot_password);
			  st.setString(1, username);
			  ResultSet rs = st.executeQuery();
			  if(rs.next()) {
				  return rs.getString("password");				  
			  }
			  return null;
		  }
		  catch (Exception e){
			  throw e;
		  }
	  }
	  public static int insertRole(User user) {
			int result = 0;
			try {
				Connection conn = DatabaseAccess.getConnection();
				java.sql.PreparedStatement st  = conn.prepareStatement("INSERT INTO USER_ROLE (userId,roleId) values" + "((SELECT id FROM Users WHERE email='" + user.getEmail() + "'), 2);");				
				st.execute();		
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	  //insert new user into the USERS table
	  public static boolean register(User user)throws Exception {
		  try {
			  Connection conn = DatabaseAccess.getConnection();
			  String query_register = "INSERT INTO USERS (firstname, lastname, email, address, password) VALUES (?,?,?,?,?)";
			  java.sql.PreparedStatement st = conn.prepareStatement(query_register);
			  st.setString(1, user.getFirstname());
			  st.setString(2, user.getLastname());
			  st.setString(3, user.getEmail());
			  st.setString(4, user.getAddress());
			  st.setString(5, user.getPassword());	 
			  st.execute();
			  return true;
		}
		  catch (Exception e){
			  throw e;
		  }
	  }  
}
