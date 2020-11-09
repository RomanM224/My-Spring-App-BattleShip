<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
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

<nav class="navbar navbar-expand-lg navbar-light bg-light navBar">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-5">
    <li class="nav-item">
    	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
        <a class="nav-link" href="${homeUrl}">Home</a>
    </li>
    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"> Painter </a>
    	<div class="dropdown-menu">
	      	<spring:url value="/painter/create" var="create" htmlEscape="true"/>
	        <a class="dropdown-item" href="${create}">Create</a>
	      	<spring:url value="/painter/update" var="update" htmlEscape="true"/>
	        <a class="dropdown-item" href="${update}">Update</a>
	      	<spring:url value="/painter/showAll" var="showAll" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showAll}">Show All</a>
	      	<spring:url value="/painter/showById" var="showById" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showById}">Show Painter Info</a>
	      	<spring:url value="/painter/delete" var="delete" htmlEscape="true"/>
	        <a class="dropdown-item" href="${delete}">Delete</a>
        </div>
    </li>
    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"> Painting </a>
    	<div class="dropdown-menu">
	      	<spring:url value="/painting/create" var="create" htmlEscape="true"/>
	        <a class="dropdown-item" href="${create}">Create</a>
	      	<spring:url value="/painting/updateFullPaintingInfo" var="updateFullPaintingInfo" htmlEscape="true"/>
	        <a class="dropdown-item" href="${updateFullPaintingInfo}">Update Full Painting Information</a>
	        <spring:url value="/painting/updatePaintingInfo" var="updatePaintingInfo" htmlEscape="true"/>
	        <a class="dropdown-item" href="${updatePaintingInfo}">Update Painting Information</a>
	      	
	      	<spring:url value="/painting/delete" var="delete" htmlEscape="true"/>
	        <a class="dropdown-item" href="${delete}">Delete</a>
        </div>
    </li>
    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"> Show Paintings </a>
    	<div class="dropdown-menu">
	      	<spring:url value="/painting/showAll" var="showAll" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showAll}">Show All</a>
	      	<spring:url value="/painting/showPainting" var="showPainting" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showPainting}">Show Painting</a>
	        <spring:url value="/painting/showByPainter" var="showByPainter" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showByPainter}">Show By Painter</a>
	        <spring:url value="/painting/showByStyle" var="showByStyle" htmlEscape="true"/>
	        <a class="dropdown-item" href="${showByStyle}">Show By Style</a>
        </div>
    </li>
    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"> Sort Paintings </a>
    	<div class="dropdown-menu">
	      	<spring:url value="/painting/sortByNameAsc" var="sortByNameAsc" htmlEscape="true"/>
	        <a class="dropdown-item" href="${sortByNameAsc}">Sort By Name(ascending)</a>
	      	<spring:url value="/painting/sortByNameDsc" var="sortByNameDsc" htmlEscape="true"/>
	        <a class="dropdown-item" href="${sortByNameDsc}">Sort By Name(descending)</a>
	        <spring:url value="/painting/sortByYearAsc" var="sortByYearAsc" htmlEscape="true"/>
	        <a class="dropdown-item" href="${sortByYearAsc}">Sort By Year(ascending)</a>
	        <spring:url value="/painting/sortByYearDsc" var="sortByYearDsc" htmlEscape="true"/>
	        <a class="dropdown-item" href="${sortByYearDsc}">Sort By Year(descending)</a>
        </div>
    </li>
        
    </ul>
  </div>
</nav>
</body>
</html>
