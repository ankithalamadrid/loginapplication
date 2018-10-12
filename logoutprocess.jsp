<%@ page language="java" import="java.io.*,java.sql.*,org.apache.log4j.Logger" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>logoutprocess</title>
</head>
<body>
		<% 
		Logger log=Logger.getLogger("LogFile");
    log.info("Logging out at:"); 
    request.getSession().invalidate();
    RequestDispatcher rd=request.getRequestDispatcher("/loginForm.html");
	rd.forward(request, response);
    %>
	

</body>
</html>