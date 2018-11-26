<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Question: ${questionData.getQuestion()}

	<div align="center">
		<h1>Level 1 Exam</h1>
		<form:form action="startExam" method="post">


				<form>
					<input type="radio" name="op1" value="${questionData.getOp1()}">${questionData.getOp1()}<br>
					<input type="radio" name="op2" value="${questionData.getOp2()}">${questionData.getOp2()}<br>
					<input type="radio" name="op3" value="${questionData.getOp3()}">${questionData.getOp3()}<br>
					<input type="radio" name="op3" value="${questionData.getOp4()}">${questionData.getOp4()}<br>
				</form>


			<input type="submit" value="Save">

		</form:form>
	</div>

</body>
</html>