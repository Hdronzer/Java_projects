<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Employee</title>
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/edit-record.css">
</head>
<body>
<div>
  <div class="header">
    <h3>Edit Employee Details</h3>
  </div>
  
  <div class="container align-right">
  	<form action="logout">
  		<label for="logout"><strong>Welcome ${sessionScope.userName}!!</strong></label>
		<input type="submit" name="logout" value="Logout">
  	</form>
  </div>
  
  <div class="container">
  
    <div>
      <p>Please update the following employee details:-</p>
    </div>
  
    <form:form action="processEmployee" method="post" modelAttribute="employee"> 
	    <form:label path="empCode" cssClass="code"><strong>Employee Code:</strong></form:label>
	    <form:label path="empCode">${employee.empCode}</form:label>
	    <form:hidden path="empCode" />
	    <br><br>
	    
	    <form:label path="empName"><strong>Employee Name:</strong>(*)</form:label>
	    <form:input path="empName" placeholder="Enter Name" required="required" />
	    <form:errors path="empName" cssClass="error"/><br><br>
	    
	    <form:label path="address" cssClass="address"><strong>Location:</strong>(*)</form:label>
	    <form:input path="address" placeholder="Enter Location" required="required" />
	    <form:errors path="address" cssClass="error"/><br><br>
	    
   	    <form:label path="email" cssClass="email"><strong>Email:</strong>(*)</form:label>
	    <form:input path="email" placeholder="Enter Email" required="required" />
	    <form:errors path="email" cssClass="error"/><br><br>
	    
   	    <form:label path="birthDate" cssClass="date"><strong>Date of Birth:</strong>(*)</form:label>
	    <form:input path="birthDate" placeholder="Format - dd-mm-yyyy" required="required" />
	    <form:errors path="birthDate" cssClass="error"/><br><br>
	        
	    <div class="submit">
	      <input type="submit" value="Submit"/>
	      <input type="hidden" name = index value="${index}" />
	    </div>
    </form:form>
  </div><br><br>
  
  <div>
    <p><strong>${result}</strong></p>
  </div>
  
</div>
</body>
</html>