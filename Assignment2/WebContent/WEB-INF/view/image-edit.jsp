<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/css/login.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Image</title>
</head>
<body>
  <div style="background-color: #4CAF50;
  	color: white; margin: 15px 0;padding: 0 10px; border: 1px double #ccc;
  	box-sizing: border-box; border-radius: 20px">
    <h3>Edit Image Properties:</h3>
  </div>
  
  <div style="padding-left: 20px; padding-top: 10px">
  
    <label for="fileToUpload"><b>Please Select an image file to upload (Max size 1 MB)</b></label>
    <div style="padding: 10px 0">
   	  <form action="editImage" method="post" enctype="multipart/form-data">
	  <input type="file" accept='image/*' name="fileToUpload">
	  <span style="color:red" >${errMessage}</span><br><br><br>
	  
	  <div>
	    <label for="imaegName"><strong>Image name:</strong>(*)&nbsp;&nbsp;</label>
        <input type="text" placeholder="Enter Image Name" name="imaegName" value="${sessionScope.images[index].imageName}" required />
        <input type="hidden" name = "index" value="${index}">
	  </div><br><br>
	  
	  <div>
   	    <input type="submit" value="Submit">
 	    <input type="reset" value="Reset">
	  </div>
	  </form>
    </div>
  </div>
</body>
</html>