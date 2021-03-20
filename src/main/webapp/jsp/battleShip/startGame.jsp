<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@page import="com.maistruks.portfolio.battleShip.service.TableCreater"%>
<%@page import="com.maistruks.portfolio.battleShip.service.FieldChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="viewport"
    content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
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
    FieldChecker fieldChecker = (FieldChecker) session.getAttribute("fieldChecker");
    TableCreater tableCreater = (TableCreater) session.getAttribute("tableCreater");
    %>
    <jsp:include page="../components/navigationBarBattleShip.jsp"></jsp:include>

    <spring:url value="/resources/img/ships_example.jpg" var="ships_example" />

    <div class="container text-danger mt-0">
        <%
        String exception = (String) session.getAttribute("exception");
        if (exception != null) {
            out.print("<p><b>" + exception + "</b></p>");
        }
        %>

    </div>
    <div class="container-fluid p-3">
        <form action="../battleShip/startGame" method="POST">
            <div class="row justify-content-between">
                <div class="col-sm-12 col-lg-8 col-xl-6">
                    <table>
                        <%
                        for (int i = 0; i <= 10; i++) {
                            out.print("<tr>");
                            for (int j = 0; j <= 10; j++) {
                                if (i == 0 || j == 0) {
                            out.print("<td class=\"table_data_start\">" + tableCreater.createFrame(i, j) + "</td>");
                                } else {
                            String fieldName = tableCreater.getLatter(i) + j;
                            String[] isChecked = new String[] { "label_square_start", "", "blue_orange_square_start" };
                            out.print("<td class=\"table_data_start\">" + tableCreater.getFirstTablePart(i, j, isChecked) + "</td>");
                                }
                            }
                            out.print("<tr>");
                        }
                        %>
                    </table>
                </div>
                <div class="col-sm-12 col-lg-4 col-xl-2">
                    <input type="submit" class="btn btn-success btn-lg startBtn font-weight-bold" value="Start Game">
                </div>
                <div class="col-lg-12 col-xl-4 tutorial_image_block">
                    <img class="tutorial_image" id="tutorial_image" alt="" src="${ships_example}">
                </div>
            </div>
        </form>
        <button class="how_play_btn" id="how_play_btn" onclick="howToPlay()">How to play?</button>
    </div>
    <div class="tutorial_image_block_off" id="tutorial_image_block">
        <img class="tutorial_image_off" id="tutorial_image_off" alt="" src="${ships_example}">
        <button class="close_btn" id="close_btn" onclick="closeTutorial()">Close</button>
    </div>
    

   <script type="text/javascript">
       function howToPlay(){
    	      var tutorial_image_block = document.getElementById("tutorial_image_block");
    	      tutorial_image_block.className = "tutorial_image_block_on";
       }
       function closeTutorial(){
    	   var tutorial_image_block = document.getElementById("tutorial_image_block");
    	   tutorial_image_block.className = "tutorial_image_block_off";
       }
   </script>


</body>
</html>