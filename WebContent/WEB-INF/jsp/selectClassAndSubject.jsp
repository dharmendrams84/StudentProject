<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select class and Subject</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
<script type="text/javascript">

function getSubjects(){
	   var classId = document.getElementById("classId").value;
	   alert('classId selected '+classId);
	   if(classId=='select class'){
		   alert("select class")
	   }else{
	    $('#subjectId').html('');
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
	    
	    var subjectId = document.getElementById('subjectId');
	 
	    for(var i in data)
	    {
			 var id = data[i].id;
	         var name = data[i].name;
	         
	        	 var option = document.createElement('option');
	        	 option.value = id; 
	        	 option.innerHTML = name;
	        	 document.getElementById('subjectId').appendChild(option);

	        
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
<form name="classSubjectForm" id="classSubjectForm" method="post" action="addNewQuestionsAction">


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
					<select name="subjectId" id="subjectId">
					<option value="select subject">select subject</option>
					</select>
				   </td>
			
				</tr>
				
				<tr>
					<td>Enter Total Marks</td>
					<td>
					<input type="text" name="totalMarks" id="totalMarks">
				   </td>
				</tr>
				<tr>
					
					<td>
					<input type="submit" value="NEXT">
				   </td>
			
				</tr>
			</tbody>
		</table>
		<br /> <br />

</form>
</body>
</html>