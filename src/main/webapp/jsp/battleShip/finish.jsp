<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="viewport" content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
<title>Battle Ship</title>

	<spring:url value="/resources/img/icon.png" var="icon" />
	<link rel="icon" href="${icon}" type="image/gif" sizes="32x32">

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />


<link href="${bootstrapCss}" rel="stylesheet">
<script src="${bootstrapJs}" type="text/javascript"></script>
<script src="${jquery}" type="text/javascript"></script>
</head>
<body class="bg-color">
	  	<jsp:include page="../components/navigationBarBattleShip.jsp"></jsp:include>   

	
<div class="container text-info">
	<% String info = (String) session.getAttribute("info");%>
	<h1><%out.print(info); %></h1>
</div>
 
</body>
</html>