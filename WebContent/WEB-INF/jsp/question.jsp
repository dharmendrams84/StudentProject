<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.beans.QuestionPaper"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- <style>
  #centerTable{
  	top: 200px;
 
  
  }



</style>
 -->
</head>
<body>
			<table style="border-spacing: 10px;  float:left; margin-left:20%;margin-top:5%; border-collapse: collapse;" border="1"  bordercolor="red" width="70%">
			 <tbody>
			  <tr> <td>Class</td><td>${questionPaper.classId}</td> </tr>
			  <tr><td>Subject </td><td>${questionPaper.subjectName}</td></tr>
			  <tr><td>Full Marks</td><td>${questionPaper.totalMarks}</td></tr>
			 </tbody>
			</table>
			
			  <table style="border-spacing: 10px;  float:left; margin-left:10%;margin-top:5%; position: relative; border-collapse: collapse;" border="1"  bordercolor="red">
			  <c:set var="count" value="0" scope="page" />
			  
                <c:forEach items="${questionPaper.questionsList}" var="questionsList1">
                    <tr style=""><c:set var="count" value="${count + 1}" scope="page"/> 
                        <td>${count}.     </td><td width="84%">${questionsList1.details}</td> <td width="15%">Marks  ${questionsList1.marks}</td>
                    </tr>
                </c:forEach>

            </table>
		 
</body>
</html>