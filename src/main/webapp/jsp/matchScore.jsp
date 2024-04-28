
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
            <td>${playerOne.name}</td>
            <td>${playerOne.playerScore.set}</td>
            <td>${playerOne.playerScore.game}</td>
            <td>${playerOne.playerScore.point}</td>
        </tr>
        <tr>
            <td>${playerTwo.name}</td>
            <td>${playerTwo.playerScore.set}</td>
            <td>${playerTwo.playerScore.game}</td>
            <td>${playerTwo.playerScore.point.numericValue}</td>
        </tr>
    </table>

    <div class="ui-button">
        <form method="post" action="/match-score?uuid=${match.uuid}">
            <button class="first-button">
                Player 1 wins point!
            </button>
        </form>
        <form method="post" action="/match-score?uuid=${match.uuid}">
            <button class="second-button">
                Player 2 wins point!
            </button>
        </form>
    </div>

</div>
</body>
</html>
