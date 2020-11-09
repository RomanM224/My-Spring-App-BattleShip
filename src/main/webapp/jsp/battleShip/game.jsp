<%@page import="com.maistruks.portfolio.service.battleShip.TableCreater"%>
<%@page import="com.maistruks.portfolio.service.battleShip.FieldChecker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.maistruks.portfolio.model.battleShip.Fleet"%>
<%@ page import="com.maistruks.portfolio.model.battleShip.Field"%>
<%@ page import="com.maistruks.portfolio.service.battleShip.ComputerAI"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Battle Ship</title>
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

	  	<jsp:include page="../components/navigationBarBattleShip.jsp"></jsp:include>   

	<%
	    Fleet myFleet = (Fleet) session.getAttribute("myFleet");
				Fleet enemyFleet = (Fleet) session.getAttribute("enemyFleet");
				FieldChecker fieldChecker = (FieldChecker) session.getAttribute("fieldChecker");
				TableCreater tableCreater = (TableCreater) session.getAttribute("tableCreater");
				List<Field> enemyShots = (List<Field>) session.getAttribute("enemyShots");
				List<Field> myShipsFields = (List<Field>) session.getAttribute("myShipsFields");
				List<Field> myShots = (List<Field>) session.getAttribute("myShots");
				List<Field> enemyShipsFields = (List<Field>) session.getAttribute("enemyShipsFields");
	%>
	<div class ="container-fluid mt-2 ml-5">
	<div class ="row">
	 <div class="col-lg-5 col-xl-4 border border-warning">
	 <h3>My Ships</h3>
	 <%for(int i = 1; i <=5; i++){
	     int shipSize = myFleet.getShipFieldsBySize(i);
		 if(shipSize != 0){
	     	out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
		 } else {
		     out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
		 }
	}
	%>
	</div>
	<div class="col-lg-5 col-xl-4 border border-info">
	 <h3>Enemy Ships</h3>
	 <%for(int i = 1; i <=5; i++){
	     int shipSize = enemyFleet.getShipFieldsBySize(i);
		 if(shipSize != 0){
	     	out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
		 } else {
		     out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
		 }
	}
	%>

	</div>
	<div class="col-lg-2 col-xl-2">
		<label class="blueMark">sss<input type="checkbox" class="unclicable">See<span class="greyMark"></span></label><br>
 		<label class="blueMark">sss<input type="checkbox" class="unclicable" checked>Miss<span class="greyMark"></span> </label><br>
		<label class="blueMark">sss<input type="checkbox" class="unclicable" checked>Hit<span class="redMark"></span> </label><br>
		<label class="blueMark">sss<input type="checkbox" class="unclicable" checked>My ships<span class="oragneMark"></span> </label>
		</div>
	</div>
	</div>
		<div class="container-fluid mt-1 ml-5">
		<form action="../battleShip/game" method="POST">
		<div class ="row">
		<div class="col-sm-12 col-lg-5 col-xl-4 tbl">
		<b>My Ships</b>
			<table>
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.ifExistFieldMyFleet(fieldName, enemyShots, myShipsFields);
										out.print("<td>" + tableCreater.getMyTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>
		<div class="col-sm-12 col-lg-5 col-xl-4 tbl">
		<b>Enemy Ships</b>
			<table >
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.ifExistFieldEnemyFleet(fieldName, myShots, enemyShipsFields);
										out.print("<td>" + tableCreater.getEnemyTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>
		
		
<%--  		<div class="tbl">
			<table>
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.showEnemyFleet(fieldName, myShots, enemyShipsFields);
										out.print("<td>" + tableCreater.showEnemyShipsTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>  --%>
		<div class="col-sm-12 col-lg-2 col-xl-2">
		<input type="submit" class="btn btn-warning btn-lg shotBtn font-weight-bold" value="Shot">
		</div>
		</div>
	</form>
	</div>

	<script type="text/javascript">
    $('.product-list').on('change', function() {
        $('.product-list').not(this).prop('checked', false);  
    });
    $(".unclicable").click(function() { return false; });
  </script>

</body>
</html>