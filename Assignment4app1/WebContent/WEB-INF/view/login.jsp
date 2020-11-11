<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/login.css">
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