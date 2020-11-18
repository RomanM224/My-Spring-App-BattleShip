<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List" %>
<%@ page import="com.maistruks.portfolio.model.gallery.Painter"%>
<%@ page import="com.maistruks.portfolio.model.gallery.Style"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create Painting</title>
    
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
  <spring:url value="/resources/img/galery_background.jpg" var="galery_background" />
  <body class="bg-image-galery" style="background-image: url('${galery_background}');">
  <% 
  List<Painter> painters = (List<Painter>) request.getAttribute("painters");
  List<Style> styles = (List<Style>) request.getAttribute("styles");
  String info = (String) request.getAttribute("info");
  %>
  	<jsp:include page="../../components/navigationBarGalery.jsp"></jsp:include>   
  	
  	<div class="container m-2">
		<form action="../painting/create" method="post" enctype="multipart/form-data">
			<div class="form-group col-sm-8 row m-5">
			
			<label for="name" class="col-sm-4 col-form-label mt-3"><b>Input Painting name: </b></label> 
			<div class="col-sm-8 mt-3">
				<input class="form-control <%if(info != null && info.equals("Painting Name is invalid")){out.print("is-invalid");} %>" id="name" type="text" name="name" />
			</div>
			
			<label for="painting" class="col-sm-4 col-form-label mt-3"><b>Select Style: </b></label> 
			<div class="col-sm-8 mt-3" >
	 			<select class="form-control" id="painting" name="style">
				<%for(Style style : styles) { %>
					<option value=<%out.print("\"" + style + "\""); %>><%out.print(style.getStyle()); %></option>
				<%} %>
				</select> 
			</div>
			
			<label for="year" class="col-sm-4 col-form-label mt-3"><b>Input Painting year: </b></label> 
			<div class="col-sm-8 mt-3">
			<input class="form-control  <%if(info != null && info.equals("Year is invalid")){out.print("is-invalid");} %>" id="year" type="number" name="year" />
			</div>
			
			<label for="file" class="col-sm-4 col-form-label mt-3"><b>Chose Painting: </b></label> 
			<div class="col-sm-8 mt-3">
			<input type="file"  name="image" class="form-control" id="image" required>
			</div>
			
			<label for="painter" class="col-sm-4 col-form-label mt-3"><b>Select Painter: </b></label> 
			<div class="col-sm-8 mt-3">
			<select class="form-control <%if(info != null && info.equals("Painter not exist")){out.print("is-invalid");} %>" id="painter"  name="painterId">
			<%for(Painter painter : painters) { %>
				<option value=<%out.print("\"" + painter.getId() + "\""); %>><%out.print(painter.getFullName()); %></option>
			<%} %>
			</select> 
			</div>
			<input class="btn btn-primary mt-5 px-5 py-3" type="submit" value="Create Painter" />
			</div>
		</form>
	<%
	if(info != null){
	%>
	<p class="container m-2 text-danger"><b><% out.print(info);%></b></p>
	<%} %>
	</div>

  </body>
</html>
