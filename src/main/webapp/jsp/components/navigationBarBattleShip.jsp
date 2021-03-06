<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
<meta http-equiv="Content-Type" name="viewport" content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
    <title>Battle Ship</title>
    
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

<nav class="navbar navbar-expand-lg navbar-light bg-light navBar">
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-5">
    <li class="nav-item">
    	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
        <a class="nav-link" href="${homeUrl}">Home</a>
      </li>
      <li class="nav-item">
      	<spring:url value="/battleShip/startGame" var="startGame" htmlEscape="true"/>
        <a class="nav-link" href="${startGame}">Play Battle Ship</a>
      </li>
    </ul>
  </div>
</nav>
</body>
</html>
