<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Flight</title>
<style type="text/css">
.header {
  background-color: #4CAF50;
  color: white; margin: 15px 0;
  text-align: center; border: 1px double #ccc;
  box-sizing: border-box; border-radius: 20px;
}

.container {
  padding: 10px 20px;
  background-color:#CDF5CE;
}

.submit {
  border: 1px double #ccc;box-sizing: border-box;
  padding: 5px 0;text-align:center;
  background-color: #91E192; width: 7.5%
}

.arrival {
  margin-right:20px;
}

.date {
  margin-right:58px;
}

.flightClass {
  margin-right:54px;
}

.preference {
  margin-right:6px;
}

.error {
  color: #EE3E1E;
}
</style>
</head>
<body>
<div>
  <div class="header">
    <h3>Flight Search Program</h3>
  </div>
  
  <div style="padding-left: 20px">
    <h3>Welcome ${sessionScope.userName}!!</h3>
  </div>
  
  <div class="container">
  
    <div>
      <p>Please Enter the following details to perform search:-</p>
    </div>
  
    <form:form action="processInput" method="post" modelAttribute="flightDetails"> 
	    <form:label path="departure"><strong>Departure Location:</strong>(*)</form:label>
	    <form:input path="departure" placeholder="Enter Departure (MUB)" required="required" />
	    <form:errors path="departure" cssClass="error"/><br><br>
	    
	    <form:label path="arrival" cssClass="arrival"><strong>Arrival Location:</strong>(*)</form:label>
	    <form:input path="arrival" placeholder="Enter Arrival (MUB)" required="required" />
	    <form:errors path="arrival" cssClass="error"/><br><br>
	    
  	    <form:label path="flightDate" cssClass="date"><strong>Flight Date:</strong>(*)</form:label>
	    <form:input path="flightDate" placeholder="Format - dd-mm-yyyy" required="required" />
	    <form:errors path="flightDate" cssClass="error"/><br><br>
	    
	    <form:label path="flightClass" cssClass="flightClass"><strong>Flight Class:</strong>(*)</form:label>
	    <form:input path="flightClass" placeholder="Enter seat class (E/B)" required="required" />
	    <form:errors path="flightClass" cssClass="error"/><br><br>
	    
   	    <form:label path="sortPreference" cssClass="preference"><strong>Output Preference:</strong>(*)</form:label>
	    <form:input path="sortPreference" placeholder="fare=1/fare&duration=2" required="required" />
	    <form:errors path="sortPreference" cssClass="error"/><br><br>
	        
	    <div class="submit">
	      <input type="submit" value="Submit"/>
	    </div>
    </form:form>
  </div><br><br>
  
  <div>
    <p><strong>"${result}"</strong></p>
  </div>
  
</div>
</body>
</html>