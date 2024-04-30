
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
            <td>${playerOne.playerScore.point.score}</td>
        </tr>
        <tr>
            <td>${playerTwo.name}</td>
            <td>${playerTwo.playerScore.set}</td>
            <td>${playerTwo.playerScore.game}</td>
            <td>${playerTwo.playerScore.point.score}</td>
        </tr>
    </table>

    <div class="ui-button">
        <form method="post">
            <button class="first-button" name="player_id" type="submit" value="1">
                Player 1 wins point!
            </button>
        </form>
        <form method="post">
            <button class="second-button" name="player_id" type="submit" value="2">
                Player 2 wins point!
            </button>
        </form>
    </div>

</div>
</body>
</html>
