<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Management</title>
</head>
<body>
  <div style="background-color: #4CAF50;
  	color: white; margin: 15px 0;text-align: center; border: 1px double #ccc;
  	box-sizing: border-box; border-radius: 20px">
    <h3>Image Management Utility</h3>
  </div>
  
  <div style="padding-left: 20px; padding-top: 10px">
  
    <label for="fileToUpload"><b>Please Select an image file to upload (Max size 1 MB)</b></label>
    <div style="padding: 10px 0">
   	  <form action="uploadFile" method="post" enctype="multipart/form-data">
	  <input type="file" accept='image/*' name="fileToUpload" required>
	  <span style="color:red" >${errMessage}</span><br><br>
	  
	  <div>
   	    <input type="submit" value="Submit">
 	    <input type="reset" value="Cancel">
	  </div>
	  </form>
    </div>

    <div style="padding-top: 20px">
      <h3>Uploaded Images:</h3>
      
      <div>
        <table width="100%" border="1">
        <tr>
         <th style="width:5%" scope="col">S.No</th>
         <th style="width:40%" scope="col">Name</th>
         <th style="width:10%" scope="col">Size</th>
         <th style="width:20%" scope="col">Preview</th>
         <th style="width:30%" scope="col">Action</th>
        </tr>
        
        <c:forEach var="image" items="${sessionScope.images}" varStatus="loop">
       	<tr>
       	 <td style="text-align: center"> <c:out value="${loop.index + 1}"></c:out> </td>
       	 <td style="text-align: center"> <c:out value="${image.imageName}"/> </td>
       	 <td style="text-align: center"> <c:out value="${image.imageSize} MB"/> </td>
       	 <td style="text-align: center"> <img alt="Preview Not available" 
       	 	src="data:image/png;base64,${image.base64Image}" width="100" height="100"/> </td>
       	 <td style="text-align: center">
       	   <form action="updateImage">
       	     <input type="submit" name = "submit" value="Update">
       	     <input type="submit" name = "submit" value="Delete">
       	     <input type="hidden" name = "index" value="${loop.index}">
    	   </form>
       	 </td>
       	</tr>
        </c:forEach>
        </table>
      </div>
    </div>
  </div>
</body>
</html>