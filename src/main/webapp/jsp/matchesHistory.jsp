<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    <%@include file="/css/common.css"%>
    <%@include file="/css/matchesHistory.css"%>
</style>
<head>
    <title>TENNIS - HISTORY</title>
</head>
<body>
<h1>Matches history</h1>
<form id="search-form" method="get" action="${pageContext.request.contextPath}matches">
    <div class="search-bar">
        <label>
            <input id="player-name-input" type="text" name="filter_by_player_name" placeholder="Write player name">
        </label>
    </div>
    <div class="button-container">
        <button type="button" onclick="submitForm()">Search</button>
        <button type="button" onclick="resetForm()">Reset</button>
    </div>
    <div class="button-container">
        <button type="button" onclick="forwardToTheMainPage()">To the main page</button>
    </div>
</form>

<script>
    function submitForm() {
        let playerName = document.getElementById("player-name-input").value;
        if (playerName.trim() === "") {
        } else {
            document.getElementById("search-form").submit();
        }
    }

    function resetForm() {
        window.location.href="${pageContext.request.contextPath}matches"
    }

    function forwardToTheMainPage() {
        window.location.href="${pageContext.request.contextPath}/"
    }
</script>

<div class="container">
    <table>
        <tr>
            <th><strong>Player one name</strong></th>
            <th><strong>Player two name</strong></th>
            <th><strong>Winner</strong></th>
        </tr>
        <c:forEach var="match" items="${requestScope.matches}">
            <tr>
                <td>${match.playerOne.playerName}</td>
                <td>${match.playerTwo.playerName}</td>
                <td>${match.playerWinner.playerName}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="pagination">

        <c:forEach begin="1" end="${noOfPage}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <a href="#" class="active">${i}</a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${empty filter_by_player_name or filter_by_player_name eq null}">
                            <a class="page-link" href="${pageContext.request.contextPath}matches?page=${i}">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link"
                               href="${pageContext.request.contextPath}matches?page=${i}&filter_by_player_name=${filter_by_player_name}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>
</body>
</html>
