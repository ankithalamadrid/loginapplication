<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
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
/* select {
	font-size:20px;
    
    padding: 5px;
    border: none;
    border-radius: 4px;
    background: #e0d3e8;
} */
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
}</style>
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
<title>Reset Password</title>
</head>
<body>	
	<div class="wrap">
<form method="post" action="resetServlet">
<h2>Reset Password</h2>
	
	<div align="center">
	
    <input type="text" name="UserName" placeholder="Username" required><br>
   
   <input type="Password" name="Password" id="password" onkeyup='check();' placeholder="Password" required><br>

    <input type="Password"  name="confirm_password" id="confirm_password" onkeyup='check();' placeholder="Confirm Password" required>
    <span id='message'></span>
    <br>
   <a href="signUp.html">not a member yet?</a>
    <input type="submit" value="Reset" name="submit" />
    </div>
	</form></div>
</body>
</html>