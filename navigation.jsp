<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navigation</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
if(session.getAttribute("username")==null){
	request.setAttribute("message_unauthoried_access", "UNAUTHORIZED ACCESS!");
	request.getRequestDispatcher("login.jsp").forward(request, response);}
%>
<ul>
	<li><a href="dashboard_tab1.jsp">Tab1</a></li>
	<li><a href="dashboard_tab2.jsp">Tab2</a></li>
	<li><a href="dashboard_tab3.jsp">Tab3</a></li>
	<li><a href="dashboard_tab4.jsp">Tab4</a></li>
	<li style="float: right">Welcome,&nbsp; <%=session.getAttribute("firstName")%>! 	
		<form action="Login" method="post"><input type="submit" name="logout" value="Logout"></form>
	</li>
</ul>
</body>
</html>