<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.maistruks.portfolio.model.gallery.Painter"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show All Painters</title>
    <spring:url value="/resources/css/stylesGeneral.css" var="stylesGeneral" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />

	
    <link href="${stylesGeneral}" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet">
    <script src="${bootstrapJs}" type="text/javascript"></script>
    <script src="${jquery}" type="text/javascript"></script>
    
  </head>
  <spring:url value="/resources/img/galery_background.jpg" var="galery_background" />
  <body class="bg-image-galery" style="background-image: url('${galery_background}');">
  
  	<jsp:include page="../../components/navigationBarGalery.jsp"></jsp:include>   

	<%
	List<Painter> painters = (List<Painter>) request.getAttribute("painters");
	%>
	
	<div class="container bg-light mt-5">
		<h3>Painters:</h3>
		<table class="table" id="each">
			<thead class="thead-dark">
				<tr>
					<th scope="col">id</th>
					<th scope="col">First name</th>
					<th scope="col">Last name</th>
					<th scope="col">Info</th>
				</tr>
			</thead>
			<%for(Painter painter: painters){ %>
			<tr>
				<td ><%out.println(painter.getId()); %></td>
				<td ><%out.println(painter.getFirstName()); %></td>
				<td ><%out.println(painter.getLastName()); %></td>
				<td ><%out.println(painter.getInfo()); %></td>
			</tr>
			<%} %>
		</table>
	</div>
  </body>
</html>
