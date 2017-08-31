<%@page import="com.utils.StudentsUtility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter,java.util.*,com.beans.*,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! List<Subject> subjectList = new ArrayList<Subject>(); %>
<%
 
            String classid = request.getParameter("classid");
//System.out.println("classid "+classid);
PrintWriter out1 = response.getWriter();
  
  subjectList = StudentsUtility.getSubjectsList(classid);
 
%>


	<select name="subjectName" id="subjectName">
		<%for(Subject s :subjectList){ %>
			<option value="<%s.getId(); %>"><%=s.getName() %></option>
		<%} %>
	</select>

</body>
</html>