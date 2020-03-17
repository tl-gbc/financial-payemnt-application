<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<h1 align="center">REGISTRATION PAGE</h1> <br> 

<div style="color:red;">
<%if (request.getAttribute("errorMessages") != null) {
		List<String> errors = (ArrayList<String>) request.getAttribute("errorMessages");
		if (errors.size() != 0) {
			Iterator<String> iterator = errors.iterator();
			while (iterator.hasNext()) {
				String error = iterator.next();%>
<p><%=error%></p>
<%} } }%>

</div><br><br> 

<form action="Register" method="post">
	<label>First Name*</label> 
	<input type="text" name="firstName" placeholder="Enter your first name" required> <br><br> 
	<label>Last Name*</label>
	<input type="text" name="lastName" placeholder="Enter your last name" required> <br><br> 
	<label>Address*</label> 
	<input type="text" name="address" placeholder="Enter your address" required> <br><br> 
	<label>Email*</label> 
	<input type="email" name="email" placeholder="email@example.com" required> <br><br> 
	<label>Password*</label>
	<input name="password" type="password" placeholder="Enter your password" required> <br><br> 
	<label>Confirm Password*</label> 
	<input name="passwordConfirmation" type="password" placeholder="Re-enter your password" required> <br><br> 
	<label> <input type="checkbox" name="term" value="true" required> 
	I agree to the <a href="#">terms of service</a> </label> <br><br>
	<button type="submit">Submit</button>
	<button type="reset">Cancel</button>
</form>
</body>
</html>