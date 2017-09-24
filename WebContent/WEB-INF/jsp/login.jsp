<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function validateForm(){
	
	var username = document.forms["loginForm"]["username"].value;
	if(username==""){
		alert('Please enter user name');
	return false;
	}
	
	var password = document.forms["loginForm"]["password"].value;
	if(password==""){
		alert('Please enter password');
	return false;
	}
	return true;
}	
	
	
	

</script>

</head>
<body>
 <form  name="loginForm" id= "loginForm" action="loginAction"  onsubmit="return validateForm()">
   <div>
    <table>
     <tr> <td>User name</td> <td> <input type="text" name="username" id="username"/> </td> </tr>
     <tr> <td>Password</td> <td> <input type="password" name="password" id="password"> </td> </tr>
     <tr> <td colspan="2"> <input type="submit" value="submit"/> </td> </tr>
    </table>
   </div>
   </form>
</body>
</html>