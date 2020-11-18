<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.maistruks.portfolio.model.gallery.Painter"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show Painter By Id</title>
    
	<spring:url value="/resources/img/icon.png" var="icon" />
	<link rel="icon" href="${icon}" type="image/gif" sizes="32x32">
	
    <spring:url value="/resources/css/stylesGallery.css" var="stylesGallery" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />

	
    <link href="${stylesGallery}" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet">
    <script src="${bootstrapJs}" type="text/javascript"></script>
    <script src="${jquery}" type="text/javascript"></script>
    
  </head>
  <spring:url value="/resources/img/galery_background.jpg" var="galery_background" />
  <body class="bg-image-galery" style="background-image: url('${galery_background}');">
	<%
  	List<Painter> painters = (List<Painter>) request.getAttribute("painters");
	String info = (String) request.getAttribute("info");
  	%>
  
  	<jsp:include page="../../components/navigationBarGalery.jsp"></jsp:include>   
	<div class="container m-5">
		<form class="form-group" action="../painter/showById" method="post">
			<select class="form-control col-3  <%if(info != null && info.equals("Painter not exist")){out.print("is-invalid");} %>" name="id">
			<%for(Painter painter : painters) { %>
				<option value=<%out.print("\"" + painter.getId() + "\""); %>><%out.print(painter.getFullName()); %></option>
			<%} %>
			</select> 
			<input class="btn btn-primary mt-2" type="submit" value="Find Painter" />
		</form>
		
	<%
	if(info != null){
	%>
	<p class="container m-2 text-danger"><b><% out.print(info);%></b></p>
	<%} %>
	</div>
	
  </body>
</html>
