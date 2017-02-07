<%-- 
    Document   : display
    Created on : 7 Feb, 2017, 2:08:28 PM
    Author     : zeroadmin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "rps.ResultHistory" %>
<%@page import = "rps.Result" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RockPaperScissors-Results</title>
    </head>
    <body>
        <h3>Results</h3>
        <b>Winner is </b> ${winner}
        <br><br>
        <a href="index.html">Back</a>
        <br>
        <br>
        <h2>History</h2>
        ${result}
        <br><br>
        <a href="${pageContext.request.contextPath}/player?history=clear"><h4>Clear History</h4></a>
        
    </body>
</html>
