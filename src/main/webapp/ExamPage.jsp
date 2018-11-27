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
			<h1>Level 1 Exam</h1>
	
	Question: ${questionData.getQuestion()}
0
	<div align="center">

		<form action="nextQn" method="post">
					<input type="radio" name="op" value="${questionData.getOp1Ans()}">${questionData.getOp1()}<br>
					<input type="radio" name="op" value="${questionData.getOp2Ans()}">${questionData.getOp2()}<br>
					<input type="radio" name="op" value="${questionData.getOp3Ans()}">${questionData.getOp3()}<br>
					<input type="radio" name="op" value="${questionData.getOp4Ans()}">${questionData.getOp4()}<br>

			<input type="submit" value="Next Question">

		</form>
	</div>

</body>
</html>