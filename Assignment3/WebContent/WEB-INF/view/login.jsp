<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/login.css" type="text/css" /> 
<title>Sign In</title>
<style type="text/css">
.header {
  background-color: #4CAF50; color: white; 
  margin: 8px 0;padding: 0 20px; 
  border: 1px double #ccc; box-sizing: border-box; 
  border-radius: 20px;
}

.container {
  margin: 20px 5px;
  padding: 16px 20px; 
  background-color:#E2F3FC
}

.submit {
  border: 1px double #ccc; box-sizing: border-box;
  padding: 5px 0; text-align:center;
  background-color: #C2E5F8; width: 7.5%
}

.error {
  color: #EE3E1E;
}
</style>
</head>
<body>
<div>
  <div class="header">
    <h3>Sign In:</h3>
  </div>
  
  <div class="container">
    <form:form action="authentication" method="post" modelAttribute="user"> 
	    <form:label path="userName"><strong>User name:</strong>(*)</form:label>
	    <form:input path="userName" placeholder="Enter Username" required="required" />
	    <form:errors path="userName" cssClass="error"/><br><br>
	    
	    <form:label path="password"><strong>Password:</strong>(*)&nbsp;&nbsp;</form:label>
	    <form:password path="password" placeholder="Enter Password" required="required" />
	    <form:errors path="password" cssClass="error"/><br><br>
	        
	    <div class="submit">
	      <input type="submit" value="Login"/>
	    </div>
    </form:form>
  </div><br><br>
  
  <div>
    <span class="error" >${errMessage}</span><br><br>
  </div>
  
</div>
</body>
</html>