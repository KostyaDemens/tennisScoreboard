<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<style>
    <%@include file="/css/common.css"%>
    <%@include file="/css/matchScore.css"%>
</style>

<head>
    <title>TENNIS - SCORE</title>
</head>
<body>
<c:choose>
    <c:when test="${match.playerWinner eq null}">
        <div class="container">
            <table>
                <tr>
                    <th><strong>PLAYER</strong></th>
                    <th><strong>SETS</strong></th>
                    <th><strong>GAMES</strong></th>
                    <th><strong>POINTS</strong></th>
                </tr>
                <tr>
                    <td>${match.playerOne.playerName}</td>
                    <td>${match.playerOne.playerScore.set}</td>
                    <td>${match.playerOne.playerScore.game}</td>
                    <td>
                        <c:choose>
                            <c:when test="${match.status eq 'TIE_BREAK'}">
                                <c:out value="${match.playerOne.playerScore.tieBreakPoint}"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${match.playerOne.playerScore.point.score}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>${match.playerTwo.playerName}</td>
                    <td>${match.playerTwo.playerScore.set}</td>
                    <td>${match.playerTwo.playerScore.game}</td>
                    <td>
                        <c:choose>
                            <c:when test="${match.status eq 'TIE_BREAK'}">
                                <c:out value="${match.playerTwo.playerScore.tieBreakPoint}"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${match.playerTwo.playerScore.point.score}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>

            <div class="ui-button">
                <form method="post" action="${pageContext.request.contextPath}match-score?uuid=${param.uuid}">
                    <button class="first-button" name="playerId" type="submit" value="1">
                        Player 1 wins point!
                    </button>
                </form>
                <form method="post" action="${pageContext.request.contextPath}match-score?uuid=${param.uuid}">
                    <button class="second-button" name="playerId" type="submit" value="2">
                        Player 2 wins point!
                    </button>
                </form>
            </div>

        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
            <p class="text">${match.playerWinner.playerName} IS WINNER !</p>
            <a href="${pageContext.request.contextPath}/" class="link">To the main page</a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
