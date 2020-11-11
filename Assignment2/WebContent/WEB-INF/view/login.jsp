<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/login.css" type="text/css" /> 
<title>Sign In</title>
</head>
<body>
<div>
  <div style="background-color: #4CAF50;
  color: white; margin: 8px 0;padding: 0 20px; border: 1px double #ccc;
  box-sizing: border-box; border-radius: 20px">
    <h3>Sign In:</h3>
  </div>

  
  <div style="padding: 16px; background-color:#E2F3FC">
    <form action="authentication" method="post"> 
	    <label for="uname"><strong>User name:</strong>(*)</label>
	    <input type="text"  placeholder="Enter username" name="uname" required />
	    <span style="color:red" >${errName}</span><br><br>
	    
	    <label for="pass"><strong>Password:</strong>(*)</label>
	    <input type="password" placeholder="Enter Password" name="pass" required />
	    <span style="color:red" >${errPass}</span><br><br>
	        
	    <div style="border: 1px double #ccc;box-sizing: border-box;padding: 5px 0;
	     text-align:center;background-color: #C2E5F8; width: 7.5%">
	      <input type="submit" value="Login"/> 
	    </div>
    </form>
  </div>
  <br><br>
  <div>
    <span style="color:red" >${errMessage}</span><br><br>
  </div>
</div>
</body>
</html>