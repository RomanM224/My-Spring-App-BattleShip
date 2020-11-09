<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List" %>
<%@ page import="com.maistruks.portfolio.model.gallery.Painter"%>
<%@ page import="com.maistruks.portfolio.model.gallery.Painting"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show By Painting</title>
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
  List<Painting> paintings = (List<Painting>) request.getAttribute("paintings");
  String info = (String) request.getAttribute("info");
  %>
  	<jsp:include page="../../components/navigationBarGalery.jsp"></jsp:include>   
  	
  	<div class="container m-2">
		<form  action="../painting/showPainting" method="post">
		
			<div class="form-group col-sm-5 row m-5">
				<label for="painting" class="col-sm-4 col-form-label mt-3">Select Painting: </label>
				<div class="col-sm-8 mt-3" >
				<select class="form-control <%if(info != null && info.equals("Painting not exist")){out.print("is-invalid");} %>" id="painting" name="paintingName">
				<%for(Painting painting : paintings) { %>
					<option value=<%out.print("\"" + painting.getName() + "\""); %>><%out.print(painting.getName()); %></option>
				<%} %>
				</select> 
				</div>
				<input class="btn btn-primary  mt-3 ml-2 px-4 py-2" type="submit" value="Show" />
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
