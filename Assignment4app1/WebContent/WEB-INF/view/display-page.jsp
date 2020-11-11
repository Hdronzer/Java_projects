<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/display-record.css">
</head>
<body>
<div>
  <div class="header">
    <h3>Employee Details</h3>
  </div>
  
  <div class="container align-right">
  	<form action="logout">
  		<label for="logout"><strong>Welcome ${sessionScope.userName}!!</strong></label>
		<input type="submit" name="logout" value="Logout">
  	</form>
  </div>
  
  <div class="container">
	<c:url var="createLink" value="/createRecord" />
	<a href="${createLink}">Click here to add Employee</a>
	
  </div>
    
	<table width="100%" border="1">
        <tr>
         <th scope="col">Employee Code</th>
         <th scope="col">Employee Name</th>
         <th scope="col">Location</th>
         <th scope="col">Email</th>
         <th scope="col">Date Of Birth</th>
         <th scope="col">Action</th>
        </tr>
        
        <c:forEach var="record" items="${records}" varStatus="loop">
        
     	<!-- construct an delete link with customer id -->
		<c:url var="deleteLink" value="/deleteRecord">
			<c:param name="index" value="${loop.index}"></c:param>
		</c:url>
					
       	<tr>
       	 <td> <c:out value="${record.empCode}"></c:out> </td>
       	 <td> <c:out value="${record.empName}"/> </td>
       	 <td> <c:out value="${record.address}"/> </td>
       	 <td> <c:out value="${record.email}"/> </td>
       	 <td> <c:out value="${record.birthDate}"/> </td>
       	 <td>
 	 	 	<form action="updateRecord" method="post">
	    	    <input type="hidden" name = "index" value="${loop.index}">
	    	    <input type="submit" value="Edit">
    	    </form><br>
    	   
			<!-- display delete link -->
			<a href="${deleteLink}"
			onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
       	 </td>
       	</tr>
        </c:forEach>
	</table><br><br>
	
  	<div class="error">
    	<strong>${errMessage}</Strong><br><br>
  	</div>
  </div>
</body>
</html>