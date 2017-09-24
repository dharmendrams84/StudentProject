<%@page import="com.beans.Subject"%>
<%@page import="java.util.List"%>
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

		function validateForm(){
			
			var classId = document.forms["addQuestionForm"]["classId"].value;
			if(classId=="select class"){
				$('#subjectName').html('');
				
				alert('Please select class');
			return false;
			}
			
			var subjectName = document.forms["addQuestionForm"]["subjectName"].value;
			if(subjectName=="select subject"){
				
				alert('Please select subject');
			return false;
			}
			
			
			var marksEntered = document.forms["addQuestionForm"]["marks"].value;
			marksEntered = marksEntered.trim();
			if(marksEntered.length==0){
				alert('Enter value for marks');
			return false;
			}
			
			if(isNaN(marksEntered)){
				alert('marks must be a number');
			return false;
			}
			
			var name = document.forms["addQuestionForm"]["name"].value;
			
			name = name.trim();
			if(name.length==0){
			  alert('Please assign name for the question');
			return false;
			}
			
			var details = document.forms["addQuestionForm"]["details"].value;
			details = details.trim();
			if(details.length==0){
			 alert('Please enter question');
			return false;
			}
			return true;
		}


       function getSubjects(){
    	   var classId = document.getElementById("classId").value;
    	   if(classId=='select class'){
    		   alert("select class");
    	   }else{
    	     
	   	   $('#subjectName').html('');
    	    $.ajax({
    	    type: "get",
    	    url: "getSubjects",
    	    cache: false,    
    	    beforeSend: function(xhr) {  
                xhr.setRequestHeader("Accept", "application/json");  
                xhr.setRequestHeader("Content-Type", "application/json");  
            },
    	    data:'classId=' + classId, 
    	    success: function(data){
    	    
    	    var subjectName = document.getElementById('subjectName');
    	 
    	    for(var i in data)
    	    {
    			 var id = data[i].id;
    	         var name = data[i].name;
    	         
    	        	 var option = document.createElement('option');
    	        	 option.value = id; 
    	        	 option.innerHTML = name;
    	        	 document.getElementById('subjectName').appendChild(option);

    	        
    	    }},    	    
	   	    error: function(jqXHR, textStatus, errorThrown){      
    	     alert('Error while request..'+jqXHR+ ' '+textStatus+ ' '+errorThrown);
    	    }
    	   });
    	   }
    	  }
</script>
</head>
<body>
	<form name="addQuestionForm" id="addQuestionForm" action="addQuestionDtlsAction" method="post" onsubmit="return validateForm()">
		<table>
			<tbody>
				<tr>
					<td>Select Class</td>
					<td><select name="classId" id="classId"
						onchange="getSubjects()">
						 <option value="select class">select class </option>
							<c:forEach items="${clsList}" var="clsList1">
								<option value="${clsList1.id}">${clsList1.name}</option>
							</c:forEach>
					</select>
					
					</td>

				</tr>

				<tr>
				</tr>

				<tr></tr>
				<tr>
					<td>Select Subject</td>
					<td>
					<select name="subjectName" id="subjectName" onchange="setSubjectId(this.value);">
					<option value="select subject">select subject</option>
		     		</select>
					</td>
			
				</tr>
			</tbody>
		</table>
		<br /> <br />

		<table>
			<tbody>
				<tr>
					<td>Enter Question name :</td>
					<td><input type="text" name="name" id="name"></td>
					<td>Marks &nbsp; &nbsp;</td>
					<td><input type="text" name="marks" id="marks" size="3"
						maxlength="3" /></td>
				</tr>
			</tbody>
		</table>
		<br> <br>
		<table>
			<tbody>
				<tr>
					<td><textarea id="details" name="details"
							rows="5" cols="70"></textarea></td>
				</tr>
			</tbody>
		</table>
		<br /> <input type="submit" value="ADD QUESTION">
		
	</form>
</body>
</html>