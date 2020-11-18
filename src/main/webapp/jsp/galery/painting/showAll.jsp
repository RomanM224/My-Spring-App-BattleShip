<%@page import="com.maistruks.portfolio.model.gallery.Painting"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show All Paintings</title>

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
	List<Painting> paintings = (List<Painting>) request.getAttribute("paintings");
	Map<Integer, String> painterNames = (Map<Integer,String>) request.getAttribute("painterNames");
%>

  	<jsp:include page="../../components/navigationBarGalery.jsp"></jsp:include>   



 	<div class="row row-cols-1 row-cols-lg-1 row-cols-xl-2 ml-2">
	<%for(Painting painting : paintings){ %>
		<div class="col mb-4">
			<div class="card my-3 myCard" style="width: 37rem;">
			<img alt="img" class="card-img-top" src="data:image/jpeg;base64,<%out.println(painting.getImageBase64Encoded()); %>"/>
				<div class="card-body card-bg-color">
					<p class="card-text"><b>Name: </b><%out.print(painting.getName()); %></p>
				    <p class="card-text"><b>Painter: </b><%out.print(painterNames.get(painting.getId())); %></p>
				    <p class="card-text"><b>Style: </b><%out.print(painting.getStyle().getStyle()); %></p>
				    <p class="card-text"><b>Year: </b><%out.print(painting.getYear()); %></p>
				</div>
			</div>
		</div>
	<%} %>
	</div> 
	
</body>
</html>