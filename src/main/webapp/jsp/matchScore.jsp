
<%@ page import="by.bsuir.kostyademens.tennisscoreboard.model.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/css/common.css"%>
    <%@include file="/css/matchScore.css"%>
</style>

<head>
    <title>Match score</title>
</head>
<body>
    <table>
        <tr>
            <td>PLAYER</td>
            <td>SETS</td>
            <td>GAMES</td>
            <td>POINTS</td>
        </tr>
        <tr>
            <td>${player-1}</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td></td>
            <td>4</td>
            <td>0</td>
            <td>0</td>
        </tr>
    </table>
</body>
</html>
