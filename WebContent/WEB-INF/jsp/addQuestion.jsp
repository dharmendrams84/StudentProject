<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

var  xmlHttp=GetXmlHttpObject();

function showSubjects(job_id)
{
         //alert('class selected  '+job_id);

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


</script>
</head>
<body>
	<form action="addQuestionDetailsAction" method="post">
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
					<td><select name="className" id="className"
						onchange="showSubjects(this.value)">
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
					<td><!-- <select name="subjectName" id="subjectName" >
							<option value="Physics">Physics</option>
							<option value="Chemistry">Chemistry</option>
							<option value="Mathematics">Mathematics</option>
							<option value="Botany">Botany</option>
							<option value="Zoology">Zoology</option>
					</select> -->
					<div id="inner_div" name="inner_div"></div>
					</td>

				</tr>
			</tbody>
		</table>
		<br /> <br />

		<table>
			<tbody>
				<tr>
					<td>Enter Question name :</td>
					<td><input type="text" name="questionName" id="questionName"></td>
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
					<td><textarea id="questionDetails" name="questionDetails"
							rows="5" cols="70"></textarea></td>
				</tr>
			</tbody>
		</table>
		<br /> <input type="submit" value="ADD QUESTION">
		
	</form>
</body>
</html>