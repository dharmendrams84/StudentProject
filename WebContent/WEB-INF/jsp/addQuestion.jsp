<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addQuestionDetailsAction" method="post">
		<table>
			<tbody>
				<tr>
					<td>Select Class</td>
					<td><select name="className" id="className">
							<option value="Class 5">Class 5</option>
							<option value="Class 6">Class 6</option>
							<option value="Class 7">Class 7</option>
							<option value="Class 8">Class 8</option>
							<option value="Class 9">Class 9</option>
							<option value="Class 10">Class 10</option>
					</select></td>
				
				</tr>
				
				<tr>
					<c:if test="${not empty clsList}">

		<ul>
			<c:forEach var="listValue" items="${clsList}">
				<li>${listValue}</li>
			</c:forEach>
		</ul>

	</c:if>
				
				
				</tr>
				
					<tr></tr>
				<tr>
					<td>Select Subject</td>
					<td><select name="subjectName" id="subjectName">
							<option value="Physics">Physics</option>
							<option value="Chemistry">Chemistry</option>
							<option value="Mathematics">Mathematics</option>
							<option value="Botany">Botany</option>
							<option value="Zoology">Zoology</option>
					</select></td>

				</tr>
			</tbody>
		</table>
		<br />
		<br />

		<table>
			<tbody>
				<tr>
					<td>Enter Question name :</td>
					<td><input type="text" name="questionName" id="questionName"></td>
					<td>Marks &nbsp; &nbsp;</td>
					<td><input type="text" name="marks" id="marks" size="3" maxlength="3"/></td>
				</tr>
			</tbody>
		</table>
		<br>
		<br>
		<table>
			<tbody>
				<tr>
					<td><textarea id="questionDetails" name="questionDetails" rows="5" cols="70"></textarea></td>
				</tr>
			</tbody>
		</table>
		<br /> <input type="submit" value="ADD QUESTION">
	</form>
</body>
</html>