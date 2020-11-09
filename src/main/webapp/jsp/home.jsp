<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Main</title>
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
  
  	<div class="container">
  	<div class="row">
  		<div class="col-6">
  		<h1 class="m-5">Hei!</h1>
  		<div class="m-5">
		Det er min portfolio applikasjon. Jeg har skrevet denne applikasjon for å vise hvordan jeg kan kode.<br><br>
		Jeg har skrevet 2 små applikasjoner, spill "Battleship" og applikasjon "Gallery".<br><br>
		I denne applikasjon brukte jeg disse teknologiene: Java, Spring, Spring Mvc, Spring JdbcTemplate, SQL, 
		PostgreSQL, Bootstrap 5, Jsp, Html, CSS, JUnit, Mockito.
		</div>
  		
  		</div>
  		<div class="col-4">
    <spring:url value="/resources/img/battleship_btn1.jpg" var="galery_btn" />
	<spring:url value="/battleShip/startGame" var="startGame" htmlEscape="true"/>
    <a class="btn mainBtn border border-primary" href="${startGame}" style="background-image: url('${galery_btn}');">
    	<span class="btn_gallery_text">Play<br>Battleship</span>
    </a>
    
	<spring:url value="/painting/showAll" var="showAll" htmlEscape="true"/>
	<spring:url value="/resources/img/galery_btn.jpg" var="galery_btn" />
	<a class="btn mainBtn border border-warning" href="${showAll}"  style="background-image: url('${galery_btn}');">
		<span class="btn_gallery_text">View<br>Gallery</span>
	</a>
	
	<spring:url value="/cv" var="cv" htmlEscape="true"/>
	<spring:url value="/resources/img/cv_btn.jpg" var="cv_btn" />
	<a class="btn mainBtn border border-success" href="${cv}"  style="background-image: url('${cv_btn}');">
		<span class="btn_gallery_text"><br>CV</span>
	</a>
	</div>
	</div>
	</div>
	
  </body>
</html>
