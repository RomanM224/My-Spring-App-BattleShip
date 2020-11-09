<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start Game</title>

	<spring:url value="/resources/css/stylesGeneral.css" var="stylesGeneral" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />

    <link href="${stylesGeneral}" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet">
    <script src="${bootstrapJs}" type="text/javascript"></script>
    <script src="${jquery}" type="text/javascript"></script>

</head>
<body class="bg-color">

	<nav class="navbar navbar-expand-lg navbar-light bg-light navBar">
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav ml-5">
	    <li class="nav-item">
	    	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
	        <a class="nav-link" href="${homeUrl}">Home</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<spring:url value="/resources/img/cv.jpg" var="cv" />
	<div class="container m-5">
		<div class="row">
			<div class="col-5">
			<img alt="" src="${cv}" width="800">
			</div>
		</div>	
	</div>
	
	
	
	
</body>
</html>