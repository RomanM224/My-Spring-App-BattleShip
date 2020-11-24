<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@page import="com.maistruks.portfolio.battleShip.service.TableCreater"%>
<%@page import="com.maistruks.portfolio.battleShip.service.FieldChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start Game</title>

	<spring:url value="/resources/img/icon.png" var="icon" />
	<link rel="icon" href="${icon}" type="image/gif" sizes="32x32">

	<spring:url value="/resources/css/stylesGeneral.css" var="stylesGeneral" />
	<spring:url value="/resources/css/stylesBattleShip.css" var="stylesBattleShip" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jquery" />

    <link href="${stylesGeneral}" rel="stylesheet">
    <link href="${stylesBattleShip}" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet">
    <script src="${bootstrapJs}" type="text/javascript"></script>
    <script src="${jquery}" type="text/javascript"></script>

</head>
<body class="bg-color">
	<%
		FieldChecker fieldChecker = (FieldChecker)session.getAttribute("fieldChecker");
		TableCreater tableCreater = (TableCreater)session.getAttribute("tableCreater");
	%>
  	<jsp:include page="../components/navigationBarBattleShip.jsp"></jsp:include>   
	
	<spring:url value="/resources/img/ships_example.jpg" var="ships_example" />

	<div class="container text-danger mt-5">
	<% 
	String exception = (String) session.getAttribute("exception");
	if(exception != null){
	    out.print("<p><b>" + exception + "</b></p>");
	}%>
	
	</div>
	<div class="container-fluid m-5">
	<form action="../battleShip/startGame" method="POST">
	<div class="row">
	<div class="col-lg-8 col-xl-4">
	<table>
	<%
 	    for(int i = 0; i <= 10; i++){
		    out.print("<tr>");
		    for(int j = 0; j <=10; j++){
		        if(i == 0 || j == 0){
		            out.print("<td>" + tableCreater.createFrame(i, j) +"</td>");
		        } else {
		        String fieldName = tableCreater.getLatter(i) + j;
		        String[] isChecked = new String[] {"blueMark", "", "oragneMark"};
		        out.print("<td>" + tableCreater.getFirstTablePart(i, j, isChecked) +"</td>"); 
		        }
		    }
		    out.print("<tr>");
		} 
	%>
	</table>
	</div>
	<div class="col-lg-4 col-xl-2">
	<input type="submit" class="btn btn-success btn-lg shotBtn font-weight-bold" value="Start Game">
	</div>
	<div class="col-5">
	<img alt="" src="${ships_example}" width="500" height="600">
	</div>
	</div>	
	</form>
	</div>
	
	
	
	
</body>
</html>