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

function setSubjectId(value){
	alert('--'+value);
	
}


var  xmlHttp=GetXmlHttpObject();

function showSubjects(job_id)
{
         alert('class selected  '+job_id);

    if(document.getElementById("className").value!="-1")
    {

        if (xmlHttp==null)
        {
            alert ("Browser does not support HTTP Request");
            return;
        }

        var url="getSubjects.jsp";
        url=url+"?classid="+ job_id ;
      	
        xmlHttp.onreadystatechange = stateChanged ;
        xmlHttp.open("GET",url,true);
        xmlHttp.send(null);
    }
    else
    {
        alert("Please Select Manufacture ");
    }

}

function stateChanged()
{
   
   
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    {       
          
        var showdata = xmlHttp.responseText;
   
        var ele = document.getElementById("inner_div");
        ele.innerHTML=showdata;                                    
    }        
   
}


function GetXmlHttpObject()
{
    var xmlHttp=null;
    try
    {
        // Firefox, Opera 8.0+, Safari
        xmlHttp=new XMLHttpRequest();
    }
    catch (e)
    {
        //Internet Explorer
        try
        {
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e)
        {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}    
 
       function validateIncentiveLogin(){
                           
        var mainmoduleid = document.getElementById("mainmoduleid").value ;
      
     
        
         if(mainmoduleid == "SELECT MAIN MODULE"){
            
        	 
               alert('Please select main module');
              
               return false ;
           }
          
            if(mainmoduleid == '0004'){
              
               return true ;
           }
             var submoduleid =  document.getElementById('submodid').value ;
            if(submoduleid == 'SELECT'){
               alert('Please select task type');
               return false ;
           }
          
          
           return true ;
       }

       
       function getSubjects(){
    	   alert('--inside get subjects--'+document.getElementById("classId").value);
    	   $('#subjectName').html('');

    	   var classId = document.getElementById("classId").value;
    	   /* var subjectNames = document.getElementById('subjectName');
   	  		  for (var i = 0; i < subjectNames.length; i++) {
   	    	
   	    	alert(''+subjectNames[i].name+ );
   	    	subjectNames.remove(i);
   	    } */

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
    	    alert('response obtained'+data);
    	    var subjectName = document.getElementById('subjectName');
    	    /*
    	    for (var i = 0; i < subjectName.length; i++) {
    	    	
    	    	alert(''+subjectName[i].name+ );
    	    	subjectName.remove(i);
    	    }
 */

    	    for(var i in data)
    	    {
    	    	
    	    	
    	         var id = data[i].id;
    	         var name = data[i].name;
    	         alert(name +  ' '+id)
    	         /* for (var i = 0; i < dd.options.length; i++) { */
    	        	/*  dd.options[i].value  = name;
    	        	 dd.options[i].text  = name;
    	        	 alert('--- '+dd.options[i].value); */
    	           /*   if (dd.options[i].text === textToFind) {
    	                 dd.selectedIndex = i;
    	                 break;
    	             } */
    	        // }
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

       
       
       
</script>
</head>
<body>
	<form action="addQuestionDtlsAction" method="post">
		<%-- <table>
			<tr>
				<td><c:forEach items="${clsList}" var="clsList1">
						<c:out value="${clsList1.id}" />
						<c:out value="${clsList1.name}" />
					</c:forEach></td>
			</tr>
		</table> --%>
		<table>
			<tbody>
				<tr>
					<td>Select Class</td>
					<td><select name="classId" id="classId"
						onchange="getSubjects()">
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
					<!-- <option value="select subject">select subject</option> -->
		
		
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