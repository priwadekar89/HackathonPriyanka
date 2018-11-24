<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="save" method="post">
Full Name:<input type="text"  name="fname"><br><br>
Email:<input type="text" name="email"><br><br>
Mobile no:<input type="text" name="mobile"><br><br>
City<input type="text"  name="city" required><br><br>
State<select name="state">
        <option value="Andhra Pradesh">Andhra Pradesh</option>
        <option value="Arunachal Pradesh">Arunachal Pradesh</option>
        <option value="Assam">Assam</option>
        <option value="Bihar">Bihar</option>
        <option value="Chattisgarh">Chattisgarh</option>
        <option value="Goa">Goa</option>
        <option value="Gujrat">Gujrat</option>
        <option value="Haryana">Haryana</option>
 <option value="Himachal Pradesh">Himachal Pradesh</option>
        <option value="Jammu & Kashmir">Jammu & Kashmir</option>
        <option value="Jharkhand">Jharkhand</option>
        <option value="Karnatka">Karnatka</option>
<option value="Kerala">Kerala</option>
        <option value="Madhya Pradesh">Madhya Pradesh</option>
        <option value="Maharashtra">Maharashtra</option>
        <option value="Manipur">Manipur</option>
<option value="Meghalaya">Meghalaya</option>
        <option value="Mizoram">Mizoram</option>
        <option value="Nagaland">Nagaland</option>
        <option value="Orissa">Orissa</option>
<option value="Punjab">Punjab</option>
        <option value="Rajasthan">Rajasthan</option>
        <option value="Sikkim">Sikkim</option>
        <option value="Tamil Nadu">Tamil Nadu</option>
<option value="Telangana">Telangana</option>
        <option value="Tripura">Tripura</option>
        <option value="Uttarakhand">Uttarakhand</option>
        <option value="Uttar Pradesh">Uttar Pradesh</option>
<option value="West Bengal">West Bengal</option> 
<br>
DOB<input type="text" name="dob"><br><br>

<input type="submit" value="Register">
</form>
</body>
</html>