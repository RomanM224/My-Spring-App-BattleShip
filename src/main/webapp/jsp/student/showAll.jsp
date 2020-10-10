<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.List"%>
<%@ page import="com.maistruk.springapp.model.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JavaSpringWebApp</title>
	<spring:url value="/resources/css/stylesGeneral.css" var="stylesGeneral" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />

    <link href="${stylesGeneral}" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet">
    <script src="${bootstrapJs}" type="text/javascript"></script>
    <script src="${jquery}" type="text/javascript"></script>
</head>
<body>
  	<jsp:include page="../components/navigationBar.jsp"></jsp:include>   

	<%
	List<Student> students = (List<Student>) request.getAttribute("students");
	%>
	
	<div class="container">
		<h3>Students:</h3>
		<table class="table col-6" id="each">
			<thead class="thead-dark">
				<tr>
					<th>id</th>
					<th>First name</th>
					<th>Last name</th>
				</tr>
			</thead>
			<%for(Student student: students){ %>
			<tr>
				<td><%out.println(student.getId()); %></td>
				<td><%out.println(student.getFirstName()); %></td>
				<td><%out.println(student.getLastName()); %></td>
			</tr>
			<%} %>
		</table>
	</div>
</body>
</html>