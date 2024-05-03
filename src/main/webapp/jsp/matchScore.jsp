<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<c:choose>
    <c:when test="${match.winner eq null}">
        <div class="container">
            <table>
                <tr>
                    <td><strong>PLAYER</strong></td>
                    <td><strong>SETS</strong></td>
                    <td><strong>GAMES</strong></td>
                    <td><strong>POINTS</strong></td>
                </tr>
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player1.playerScore.set}</td>
                    <td>${match.player1.playerScore.game}</td>
                    <td>
                        <c:choose>
                            <c:when test="${match.matchStatus eq 'TIE_BREAK'}">
                                <c:out value="${match.player1.playerScore.tieBreakPoint}"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${match.player1.playerScore.point.score}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>${match.player2.name}</td>
                    <td>${match.player2.playerScore.set}</td>
                    <td>${match.player2.playerScore.game}</td>
                    <td>
                        <c:choose>
                            <c:when test="${match.matchStatus eq 'TIE_BREAK'}">
                                <c:out value="${match.player2.playerScore.tieBreakPoint}"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${match.player2.playerScore.point.score}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
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
    </c:when>
    <c:otherwise>
        <div class="container">
            <p class="text">${match.winner.name} IS WINNER !</p>
            <a href="${pageContext.request.contextPath}/" class="link">To the main page</a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
