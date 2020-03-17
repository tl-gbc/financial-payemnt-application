
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseAccess {
	
	 //connect to database COMP3095
	 public static Connection getConnection() throws Exception {
		  try {
				String url = "jdbc:mysql://localhost:3306/COMP3095?useTimezone=true&serverTimezone=UTC";
				String username = "root";
				String password = "password1";
				String driver = "com.mysql.cj.jdbc.Driver";
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("Connected to Database COMP3095");
				return conn;
			} catch (Exception e) {
				System.out.println(e);
			}
			return null;   
	  }   
	  
	 //create table USERS
	  public static void createTable() throws Exception {
			try {
				Connection conn = getConnection();
				PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS USERS(id int(11) AUTO_INCREMENT PRIMARY KEY, "
						+ "firstname varchar(255),lastname varchar(255), email varchar(255), role varchar(20), "
						+ "created timestamp default current_timestamp, password varchar(20))");
				create.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println("Table USERS is created!");
			}
	  } 
 }