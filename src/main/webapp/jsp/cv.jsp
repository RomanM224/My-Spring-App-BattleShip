<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CV</title>

	<spring:url value="/resources/img/icon.png" var="icon" />
	<link rel="icon" href="${icon}" type="image/gif" sizes="32x32">

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

	<nav class="navbar navbar-expand-sm navbar-light bg-light navBar">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
   			<span class="navbar-toggler-icon"></span>
    </button>
	  <div class="collapse navbar-collapse" id="navbarNavDropdown">
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
			<div class="col-lg-12 col-xl-9">
			<img alt="" src="${cv}" width="800">
			</div >
			
		</div>	
	</div>
	
	
	
	
</body>
</html>