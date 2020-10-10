<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

	<div class="container m-2">
		<form class="form-group" action="../student/delete" method="post">
			<label for="firstName">Delete Student by id: </label> 
			<input class="form-control col-3" id="id" type="number" name="id" />
			<input class="btn btn-primary mt-2" type="submit" value="Delete Student" />
		</form>
	</div>
	
	<%String info = (String) request.getAttribute("info");
	if(info != null){
	%>
	<p class="container m-2 text-danger"><% out.print(info);%></p>
	<%} %>
</body>
</html>