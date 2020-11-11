<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Result</title>
<style type="text/css">
.header {
background-color: #4CAF50;
color: white; margin: 15px 0;
text-align: center; border: 1px double #ccc;
box-sizing: border-box; border-radius: 20px;
}

.container {
	padding: 10px 20px;
}

th {
	border-bottom:1px solid gray;
	background:none repeat scroll 0 0 #09c332;
	padding:10px;
	color: #FFFFFF;
}

td {
	padding: 0 10px;
	text-align: center;
}

tr {
	border-top:1px solid gray;
	text-align:center;	
}

tr:nth-child(even) {background: #FFFFFF}
tr:nth-child(odd) {background: #BBBBBB}

.form {
	padding-bottom: 20px;
}
</style>
</head>
<body>
<div>
  <div class="header">
    <h3>Available Flights</h3>
  </div>
  
  <div class="container">
    <div class="form">
      <form action="redirect">
        <label for="search"><strong>Click here to search for another flight -></strong></label>
	    <input type="submit" value="Search" name="search"/>
      </form>
    </div>
    
	<table width="100%" border="1">
        <tr>
         <th scope="col">FLIGHT_NO</th>
         <th scope="col">DEP_LOC</th>
         <th scope="col">ARR_LOC</th>
         <th scope="col">FLIGHT_DATE</th>
         <th scope="col">FLIGHT_TIME</th>
         <th scope="col">FLIGHT_DUR</th>
         <th scope="col">FARE</th>
         <th scope="col">SEAT_AVAILABILITY</th>
         <th scope="col">CLASS</th>
        </tr>
        
        <c:forEach var="record" items="${records}" varStatus="loop">
       	<tr>
       	 <td> <c:out value="${record.flightNo}"></c:out> </td>
       	 <td> <c:out value="${record.departure}"/> </td>
       	 <td> <c:out value="${record.arrival}"/> </td>
       	 <td> <c:out value="${record.flightDate}"/> </td>
       	 <td> <c:out value="${record.flightTime}"/> </td>
       	 <td> <c:out value="${record.flightDuration}"/> </td>
       	 <td> <c:out value="${record.fare}"/> </td>
       	 <td> <c:out value="${record.seatAvailablity}"/> </td>
       	 <td> <c:out value="${record.flightClass}"/> </td>
       	</tr>
        </c:forEach>
	</table>
  
  </div>
</div>
</body>
</html>