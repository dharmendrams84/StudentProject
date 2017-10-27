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
	   //alert('classId selected '+classId);
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
	    var option = document.createElement('option');
   	 	option.value = 'select subject'; 
   	 	option.innerHTML = 'select subject';
   	 	document.getElementById('subjectId').appendChild(option);
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





function setSubjectName(){
	
	 var classId = document.getElementById("classId").value;
	 
	 var subjectId = document.getElementById("subjectId").value;
	   //alert('classId selected '+classId);
	   if(classId=='select class'){
		   alert("select class");
	   }else if(subjectId=='select subject'){
		   alert("select class");
	   }else{
	   
	    $.ajax({
	    type: "get",
	    url: "getSubjectDetails",
	    cache: false,    
	    beforeSend: function(xhr) {  
       xhr.setRequestHeader("Accept", "application/json");  
       xhr.setRequestHeader("Content-Type", "application/json");  
  		 },
	    data:'subjectId=' + subjectId, 
	    success: function(data){
	    document.getElementById('subjectName').value = data.name;
	 
	   },    	    
	    error: function(jqXHR, textStatus, errorThrown){      
	     alert('Error while request..'+jqXHR+ ' '+textStatus+ ' '+errorThrown);
	    }
	   });
	   }

}



function validateForm(){
	
	var classId = document.forms["classSubjectForm"]["classId"].value;
	if(classId=="select class"){
		alert('Please select class');
	return false;
	}
	
	var subjectId = document.forms["classSubjectForm"]["subjectId"].value;
	if(subjectId=="select subject"){
		alert('Please select subject');
	return false;
	}
	
	var totalMarks = document.forms["classSubjectForm"]["totalMarks"].value;
	totalMarks = totalMarks.trim();
	if(totalMarks.length==0){
		alert('Enter value for total marks');
	return false;
	}
	
	if(isNaN(totalMarks)){
		alert('total marks must be a number');
		document.forms["classSubjectForm"]["totalMarks"].value=0;
	return false;
	}else if(parseInt(totalMarks)<=0){
		alert('total marks must be greater than zero');
		document.forms["classSubjectForm"]["totalMarks"].value=0;
		return false;
	}
	
	var total1 = document.forms["classSubjectForm"]["total1"].value;
	var total2 = document.forms["classSubjectForm"]["total2"].value;
	var total3 = document.forms["classSubjectForm"]["total3"].value;
	var total4 = document.forms["classSubjectForm"]["total4"].value;
	var  sumTotal = parseInt(total1)+parseInt(total2)+parseInt(total3)+parseInt(total3);
	if(parseInt(totalMarks)!=sumTotal){
	alert('sum of marks for all questions must be equal to total marks ');
	return false;
	}
	return true;
}


function enableMarks1(){
	//alert('--1--'+document.getElementById("check1").checked );
	if(document.getElementById("check1").checked){
		document.getElementById("marks1").disabled= false;
		document.getElementById("quantity1").disabled= false;
	}else{
		document.getElementById("marks1").disabled= true;
		document.getElementById("quantity1").disabled= true;
		document.getElementById("marks1").value = "0";
		document.getElementById("quantity1").value = "0";
		document.getElementById("total1").value = "0";
		
	}
	
}

function enableMarks2(){
	//alert('--1--'+document.getElementById("check1").checked );
	if(document.getElementById("check2").checked){
		document.getElementById("marks2").disabled= false;
		document.getElementById("quantity2").disabled= false;
	}else{
		document.getElementById("marks2").disabled= true;
		document.getElementById("quantity2").disabled= true;
		document.getElementById("marks2").value = "0";
		document.getElementById("quantity2").value = "0";
		document.getElementById("total2").value = "0";
	}
	
}

function enableMarks3(){
	//alert('--1--'+document.getElementById("check1").checked );
	if(document.getElementById("check3").checked){
		document.getElementById("marks3").disabled= false;
		document.getElementById("quantity3").disabled= false;
	}else{
		document.getElementById("marks3").disabled= true;
		document.getElementById("quantity3").disabled= true;
		document.getElementById("marks3").value = "0";
		document.getElementById("quantity3").value = "0";
		document.getElementById("total3").value = "0";
	}
	
}

function enableMarks4(){
	//alert('--1--'+document.getElementById("check1").checked );
	if(document.getElementById("check4").checked){
		document.getElementById("marks4").disabled= false;
		document.getElementById("quantity4").disabled= false;
	}else{
		document.getElementById("marks4").disabled= true;
		document.getElementById("quantity4").disabled= true;
		document.getElementById("marks4").value = "0";
		document.getElementById("quantity4").value = "0";
		document.getElementById("total4").value = "0";
	}
	
}

function calculateTotal(marksId,quantityId,totalId){
	var marks = document.getElementById(marksId).value;
	var quantity = document.getElementById(quantityId).value;
	//alert('marks  '+marks+' : quantity '+quantity+ ' total  : '+quantity*marks);
	var total = quantity*marks;
	document.getElementById(totalId).value = total;
	/* if(marks='marks'){
		alert('22');
		//document.getElementById(marksId).value = "0";
	}
	else if(quantity='quantity'){
		alert('33');
		document.getElementById(quantityId).value = "0";
	}else{
		alert('1');
	}
	 */
}
</script>



</head>
<body>
<form name="classSubjectForm" id="classSubjectForm" method="post" action="prepareQuestionPaperAction" onsubmit="return validateForm()">


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
					<select name="subjectId" id="subjectId" onchange="setSubjectName()">
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

				<tr> <td></td> <td>Marks</td> <td>Quantity</td></tr>
				<tr>
					<td><input type="checkbox" name="check1" id="check1"
						onchange="enableMarks1();"></td>
					<td><input type="number" name="marks1" id="marks1"
						disabled="disabled" onchange="calculateTotal('marks1','quantity1','total1')" value="0"/></td>
					<td><input type="number" name="quantity1" id="quantity1"
						disabled="disabled" onchange="calculateTotal('marks1','quantity1','total1')" value="0" /></td>
					
					<td><input type="number" name="total1" id="total1" disabled="disabled" value="0" > </td>
				</tr>

				<tr>
					<td><input type="checkbox" name="check2" id="check2"
						onchange="enableMarks2();"></td>
					<td><input type="number" name="marks2" id="marks2"
						disabled="disabled" onchange="calculateTotal('marks2','quantity2','total2')" value="0"/></td>
					<td><input type="number" name="quantity2" id="quantity2"
						disabled="disabled" onchange="calculateTotal('marks2','quantity2','total2')"  value="0"/></td>
					<td><input type="number" name="total2" id="total2" disabled="disabled" value="0"/> </td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="check3" id="check3"
						onchange="enableMarks3();"></td>
					<td><input type="number" name="marks3" id="marks3"
						disabled="disabled" onchange="calculateTotal('marks3','quantity3','total3')" value="0"/></td>
					<td><input type="number" name="quantity3" id="quantity3"
						disabled="disabled" onchange="calculateTotal('marks3','quantity3','total3')" value="0"/></td>
					<td><input type="number" name="total3" id="total3" disabled="disabled" value="0"/> </td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="check4" id="check4"
						onchange="enableMarks4();"></td>
					<td><input type="number" name="marks4" id="marks4"
						disabled="disabled" onchange="calculateTotal('marks4','quantity4','total4')" value="0"/></td>
					<td><input type="number" name="quantity4" id="quantity4"
						disabled="disabled" onchange="calculateTotal('marks4','quantity4','total4')" value="0"/></td>
					<td><input type="number" name="total4" id="total4" disabled="disabled" value="0"/> </td>
				</tr>
				<tr>
					
					<td>
					<input type="hidden" name="subjectName" id="subjectName" >
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