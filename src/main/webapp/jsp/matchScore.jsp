
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
<div class="container">
    <table>
        <tr>
            <td><strong>PLAYER</strong></td>
            <td><strong>SETS</strong></td>
            <td><strong>GAMES</strong></td>
            <td><strong>POINTS</strong></td>
        </tr>
        <tr>
            <td>${playerOneName}</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>${playerTwoName}</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
    </table>
    <div class="ui-button">
        <button class="first-button">Player 1 wins point!</button>
        <button class="second-button">Player 2 wins point!</button>
    </div>
</div>
</body>
</html>
