<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="com.beans.QuestionPaper"%>
<%@page import="com.beans.Question"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
<script>
 function getQuestionDetails(){
	
	 var classId = document.getElementById("classId").value;
	 var subjectId = document.getElementById("subjectId").value;
	 var questionId = document.getElementById("questionId").value;
	 alert('----1---'+classId+ '  : '+subjectId+ ' '+questionId);
	    $.ajax({
    	    type: "get",
    	    url: "getQuestionDetails",
    	    cache: false,    
    	    beforeSend: function(xhr) {  
                xhr.setRequestHeader("Accept", "application/json");  
                xhr.setRequestHeader("Content-Type", "application/json");  
            },
    	    data:{'classId':classId,'subjectId':subjectId,'questionId':questionId}, 
    	    
    	    success: function(data){
    	    
    	      alert('inside success '+data.details);
    	 		document.getElementById("questionDetailsDiv").value= data.details;;
    	     },    	    
	   	    error: function(jqXHR, textStatus, errorThrown){      
    	     alert('Error while request..'+jqXHR+ ' '+textStatus+ ' '+errorThrown);
    	    }
    	   });
 }
 
 function populateQuestions(){
	alert("11111"+'${questionPaper.id}');	

 }
</script>
</head>
<body onload="populateQuestions()">
 <form action="addQuestionToPaperAction" method="post">
  <table border="1" bordercolor="red" style="border-collapse: collapse;">
			<tr>
				<td>Class </td>
				<td><input type="text" name="classId" id="classId" value="${questionPaper.classId}" readonly="readonly"> </td>
			</tr>

			<tr>
				<td>Subject Name </td>
				<td><input type="text" name="subjectId" id="subjectId" value="${questionPaper.subjectId}" readonly="readonly">  </td>
			</tr>
			
			<tr>
				<td>Total Marks </td>
				<td><input type="text" name="totalMarks" id="totalMarks" value="${questionPaper.totalMarks}" readonly="readonly">  </td>
			</tr>
			
			<tr>
			 <td>Select Question</td>
			 <td>
			<%--  <% 
			   
			 QuestionPaper questionPaper = (QuestionPaper)request.getAttribute("questionPaper");
			 List<Question> questions= questionPaper.getQuestionsList();
			 for(Question q : questions){
				// PrintWriter out = response.getWriter();
				 out.println(q.getName()+ q.getDetails());
			 }
			 %> --%>
			 <select name ="questionId" id ="questionId" onchange="getQuestionDetails()">
			  <c:forEach items="${questionPaper.questionsList}" var="questionsList1">
				 <option value="${questionsList1.id}">${questionsList1.name}</option>
			   </c:forEach>
			 </select>
			 
			 </td>
			</tr>
			<tr>
			<td>
			 
			
			</td>
			</tr>
			
		</table><br><br>
		
 	<textarea id="questionDetailsDiv" style="height: 200px; width: 300px;"></textarea>
 	<br>
 	<input type="submit" value="add question">
 </form>
</body>
</html>