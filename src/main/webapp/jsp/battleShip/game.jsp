<%@page import="com.maistruks.portfolio.battleShip.service.TableCreater"%>
<%@page import="com.maistruks.portfolio.battleShip.service.FieldChecker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.maistruks.portfolio.battleShip.model.Fleet"%>
<%@ page import="com.maistruks.portfolio.battleShip.model.Field"%>
<%@ page import="com.maistruks.portfolio.battleShip.service.ComputerAI"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="viewport"
    content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
<title>Battle Ship</title>

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
    Fleet myFleet = (Fleet) session.getAttribute("myFleet");
    Fleet enemyFleet = (Fleet) session.getAttribute("enemyFleet");
    FieldChecker fieldChecker = (FieldChecker) session.getAttribute("fieldChecker");
    TableCreater tableCreater = (TableCreater) session.getAttribute("tableCreater");
    List<Field> enemyShots = (List<Field>) session.getAttribute("enemyShots");
    List<Field> myShipsFields = (List<Field>) session.getAttribute("myShipsFields");
    List<Field> myShots = (List<Field>) session.getAttribute("myShots");
    List<Field> enemyShipsFields = (List<Field>) session.getAttribute("enemyShipsFields");
    %>

    <jsp:include page="../components/navigationBarBattleShip.jsp"></jsp:include>

    <div class="container-fluid p-3 ships_result_top">
        <div class="row">
            <div class="col-lg-5 col-xl-4 border border-warning ">
                <h3>My Ships</h3>
                <%
                for (int i = 1; i <= 5; i++) {
                	int shipSize = myFleet.getShipFieldsBySize(i);
                	if (shipSize != 0) {
                		out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
                	} else {
                		out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
                	}
                }
                %>
            </div>
            <div class="col-lg-5 col-xl-4 border border-info">
                <h3>Enemy Ships</h3>
                <%
                for (int i = 1; i <= 5; i++) {
                	int shipSize = enemyFleet.getShipFieldsBySize(i);
                	if (shipSize != 0) {
                		out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
                	} else {
                		out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
                	}
                }
                %>

            </div>
            <div class="col-lg-2 col-xl-2">
                <span class="grey_square"></span>
                Miss <br>
                <span class="blue_square"></span>
                See <br>
                <span class="red_square"></span>
                Hit <br>
                <span class="orange_square"></span>
                My ships

            </div>
        </div>
    </div>

    <div class="container-fluid">
        <form action="../battleShip/game" method="POST">
            <div class="row">
                <div class="col-sm-12 col-lg-5 col-xl-4 my_ships_block">
                    <b>My Ships</b>
                    <table>
                        <%
                        for (int i = 0; i <= 10; i++) {
                        	out.print("<tr>");
                        	for (int j = 0; j <= 10; j++) {
                        		if (i == 0 || j == 0) {
                        			out.print("<td class=\"table_data_my\">" + tableCreater.createFrame(i, j) + "</td>");
                        		} else {
                        			String fieldName = tableCreater.getLatter(i) + j;
                        			String[] isChecked = fieldChecker.ifExistFieldMyFleet(fieldName, enemyShots, myShipsFields);
                        			out.print("<td class=\"table_data_my\">" + tableCreater.getMyTablePart(i, j, isChecked) + "</td>");
                        		}
                        	}
                        	out.print("<tr>");
                        }
                        %>
                    </table>
                </div>
                <div class="col-12 col-lg-5 col-xl-4">
                    <b>Enemy Ships</b>
                    <table>
                        <%
                        for (int i = 0; i <= 10; i++) {
                        	out.print("<tr>");
                        	for (int j = 0; j <= 10; j++) {
                        		if (i == 0 || j == 0) {
                        			out.print("<td class=\"table_data_enemy\">" + tableCreater.createFrame(i, j) + "</td>");
                        		} else {
                        			String fieldName = tableCreater.getLatter(i) + j;
                        			String[] isChecked = fieldChecker.ifExistFieldEnemyFleet(fieldName, myShots, enemyShipsFields);
                        			out.print("<td class=\"table_data_enemy\">" + tableCreater.getEnemyTablePart(i, j, isChecked) + "</td>");
                        		}
                        	}
                        	out.print("<tr>");
                        }
                        %>
                    </table>
                </div>
                <div class="col-7 col-lg-5 col-xl-4 my_ships_block_mobile">
                    <b>My Ships</b>
                    <table>
                        <%
                        for (int i = 0; i <= 10; i++) {
                        	out.print("<tr>");
                        	for (int j = 0; j <= 10; j++) {
                        		if (i == 0 || j == 0) {
                        			out.print("<td class=\"table_data_my\">" + tableCreater.createFrame(i, j) + "</td>");
                        		} else {
                        			String fieldName = tableCreater.getLatter(i) + j;
                        			String[] isChecked = fieldChecker.ifExistFieldMyFleet(fieldName, enemyShots, myShipsFields);
                        			out.print("<td class=\"table_data_my\">" + tableCreater.getMyTablePart(i, j, isChecked) + "</td>");
                        		}
                        	}
                        	out.print("<tr>");
                        }
                        %>
                    </table>
                </div>
                <div class="col-5 col-lg-2 col-xl-2">
                    <input type="submit" class="btn btn-warning btn-lg shotBtn font-weight-bold" value="Shot">
                    <span class="infoBtn" onclick="toogleInfo()"><b>Info</b></span>
                </div>
            </div>
        </form>
    </div>

    <div class="container-fluid p-3 ships_result_down_off" id="ships_info">
        <div class="row">
            <div class="col-lg-5 col-xl-4 border border-warning ">
                <h3>My Ships</h3>
                <%
                for (int i = 1; i <= 5; i++) {
                	int shipSize = myFleet.getShipFieldsBySize(i);
                	if (shipSize != 0) {
                		out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
                	} else {
                		out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
                	}
                }
                %>
            </div>
            <div class="col-lg-5 col-xl-4 border border-info">
                <h3>Enemy Ships</h3>
                <%
                for (int i = 1; i <= 5; i++) {
                	int shipSize = enemyFleet.getShipFieldsBySize(i);
                	if (shipSize != 0) {
                		out.print("<span class=\"text-success\">Ship " + i + " | Ship size: " + shipSize + " blocks</span><br>");
                	} else {
                		out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
                	}
                }
                %>

            </div>
            <div class="col-lg-2 col-xl-2">
                  <span class="grey_square"></span>
                Miss <br>
                <span class="blue_square"></span>
                See <br>
                <span class="red_square"></span>
                Hit <br>
                <span class="orange_square"></span>
                My ships
            </div>
            <button class="close_btn" onclick="closeInfo()">Close</button>
        </div>
    </div>

    <script type="text/javascript">
					$('.product-list').on('change', function() {
						$('.product-list').not(this).prop('checked', false);
					});
					$(".unclicable").click(function() {
						return false;
					});
					
					function toogleInfo() {
						var shipsInfo = document.getElementById("ships_info");
						shipsInfo.className = "ships_result_down_on";
					}
					function closeInfo(){
						var shipsInfo = document.getElementById("ships_info");
					      shipsInfo.className = "ships_result_down_off";
					}
				</script>

</body>
</html>