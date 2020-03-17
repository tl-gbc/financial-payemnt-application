<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<head>
<script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<h1 align="center">LOGIN</h1>
<div style="color:red;">
<% if(request.getAttribute("error")!= null){ %>
<%= request.getAttribute("error") %>
<% } %> <br>
</div>
<div style="color:red;">
<% if(request.getAttribute("message_unauthoried_access")!= null){ %>
<%= request.getAttribute("message_unauthoried_access") %>
<% } %> <br>
<h3>${error_invalid_login}</h3>
</div>
<form action="Login" method="post">
	<label for="username"> Username: </label>
	<input type="email" name="username"> <br><br>
	<label for="password">Password: </label>
	<input type="password" name="password"> <br><br>
	<div class="g-recaptcha"
	data-sitekey="6LcVcL0UAAAAAAYNFPpasXQf_EgZqzxvc0UwC8eg"></div> <br><br>
	<button type=submit> Submit</button>
	<input type="button" value="Register" onclick="window.location.href='register.jsp'" /><br><br>
	<a href="forgot_password.jsp">Forgot your password?</a>
</form>
</body>
</html>