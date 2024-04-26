<%@ page import="by.bsuir.kostyademens.tennisscoreboard.model.Player" %>
<%@ page import="by.bsuir.kostyademens.tennisscoreboard.model.PlayerScore" %>
<%@ page import="by.bsuir.kostyademens.tennisscoreboard.model.PlayerScore" %>
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
<% PlayerScore playerScore = new PlayerScore(); %>
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
            <td>
                <%= playerScore.getSet() %>
            </td>
            <td>
                <%= playerScore.getGame() %>
            </td>
            <td>
                <%= playerScore.getPoint() %>
            </td>
        </tr>
        <tr>
            <td>${playerTwoName}</td>
            <td>7</td>
            <td>7</td>
            <td>7</td>
        </tr>
    </table>

        <div class="ui-button">
            <button class="first-button">
                Player 1 wins point!
            </button>
            <button class="second-button">
                Player 2 wins point!
            </button>
        </div>

</div>
</body>
</html>
