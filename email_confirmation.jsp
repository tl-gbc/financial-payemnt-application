<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email confirmation</title>
</head>
<body>
<h2 align="center">Successfully Registered</h2>
<br> A confirmation email has been sent to <%= request.getAttribute("email") %>.
<br><br> Please check your email. 
<br><br> Thank you!
<br><br> 
<input type="button" value="Back to Login page" onclick="window.location.href='login.jsp'"/>
</body>
</html>