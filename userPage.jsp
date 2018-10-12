<%@ page language="java" import="java.io.*,java.sql.*,org.apache.log4j.Logger" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css" >
body{
	background:#323232;
}
body{
	height: 100vh;
 background: url("https://i.imgur.com/HgflTDf.jpg") 50% fixed;  
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
<script type="text/javascript">
var check = function() {
	  if (document.getElementById('password').value ==document.getElementById('confirm_password').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'matching';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'not matching';
	  }
	}
</script>


<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>
	<div class="wrap">
<form method="post" action="UpdateServlet" >
<h2>Update Information</h2>
<%
String user1=(String)session.getAttribute("user");
request.setAttribute("username", user1);
//System.out.println(user1);
%>
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
<%
    if(null!=request.getAttribute("updateMessage"))
    {
        out.println(request.getAttribute("updateMessage"));
    }
%>
	<input type="hidden" name="username" placeholder="User Name" value=<%=session.getAttribute("user") %> ><br> 
	
    <input type="text" name="FirstName" placeholder="First Name" pattern="[a-zA-z]+" oninvalid="this.setCustomValidity('Enter only Alphabets')"><br>
 
    <input type="text" name="LastName" placeholder="Last Name"><br>
  
    <!-- <input type="Password" name="Password" id="password" onkeyup='check();' placeholder="Password" ><br>

    <input type="Password"  name="Confirm"id="confirm_password" onkeyup='check();' placeholder="Confirm Password" ><br>
     <span id='message'></span> -->
    <input type="Email" name="Email" placeholder="Email" ><br><br>
    department:<br>
    <select name="dept">
   <!--  <option value="dept" selected>DEPT</option> -->
    <option value="IT">IT</option>
	<option value="HR">HR</option>
	<option value="OPERATION">OPERATION</option>
	<option value="RESOURCES">RESOURCES</option>
	<option value="DEVELOPMENT">DEVELOPMENT</option>
	</select><br>
    <input type="reset"  />
    <input type="submit" value="Update"/>
    </div>
    
    <h3>Click <a href="logoutprocess.jsp">here</a>to log out</h3>
    <%-- <%  Logger log=Logger.getLogger("LogFile");
    log.info("Logging out"+" "+user1); 
    request.getSession().invalidate(); %> --%>
 
</form></div>





</body>
</html>