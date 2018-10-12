<%@ page language="java" import="java.io.*,java.sql.*,org.apache.log4j.Logger" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
	height: 100vh;
 background: url("https://i.imgur.com/HgflTDf.jpg") 50% fixed;  background-size: cover;
  background-size: cover;
  opacity: 0.9;
}
.wrap{
	margin:auto;
	width:350px;
	background: #00adee;
	margin-top:50px;
	padding:5px;
}
form{
padding:10px;
font-family:Verdana;
border: 1px dotted white;
margin:10px;
	
}
h2{
	text-align:center;
	background: orange;
	color:white;
	padding:10px;
	bordef-radius:10px;
}
input {
  padding:10px;
 margin: 5px;
 border-radius:5px;
 border:none;
}
input[type=text],input[type=password],input[type=Email],input[type=number]{
width:90%;

}

input[type=text]:focus,input[type=password]:focus,input[type=Email]:focus  {
    background-color: lightblue;
}

input[type=button], input[type=submit], input[type=reset]{
 width:40%;
 background:orange;
 cursor:pointer;
 font-size:18px;
 font-weight: bold;
 color:white;
}
input[type=submit]:hover,input[type=reset]:hover{
background:yellow;
}
select{
padding:10px;
width:325;
border-radius:5px;
}

	
</style>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
<%
String user1=(String)session.getAttribute("user");
request.setAttribute("username", user1);
//System.out.println(user1);
%>

<div class="wrap">
<form method="post" action="uploadUser" >
<h2>Admin Account</h2>
<div align="center">
<%
if((String)session.getAttribute("user")!=null)
{
%>
	<h3>hello <%=session.getAttribute("user") %></h3>
<%
}
else
{
%>
	<h3>hello,please<a href="loginForm.html"> log in</a></h3>
<%
}%>

	

    <input type="file" name="file" id="file" />
    <input type="submit" name="submit" value="Bulk Upload" />
    
</div>   
 

</form>
<form method="post" action="csvExport" >

<div align="center">
<input type="submit" value="CSV" name="view" />
</div>


</form>
<form method="post" action="viewUser" >

<div align="center">
<input type="submit" value="view all" name="view" />
</div>
<div align="right">
<h3><a href="logoutprocess.jsp">Log Out</a></h3></div>


</form>
</div>

</body>
</html>