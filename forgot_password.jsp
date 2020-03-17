<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
<h2 align="center">Forget Your Password?</h2>
<form action="ForgotPassword" method="post">
	<p>Enter your user name (email address) below, we will send you your password.</p>
	<input type="text" name="username" placeholder ="Enter your username"> <br><br>
	<button type=submit> Submit </button>
	<input type="button" value="Back to login" onclick="window.location.href='login.jsp'" />
</form>
</body>
</html>