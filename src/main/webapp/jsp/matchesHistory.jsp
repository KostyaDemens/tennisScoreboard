<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    <%@include file="/css/common.css"%>
    <%@include file="/css/matchesHistory.css"%>
</style>
<head>
    <title>HELLO WORLD</title>
</head>
<body>
<h1>Matches history</h1>
<div class="search-bar">
    <label>
        <input type="text" placeholder="Write player name">
    </label>
</div>
<div class="container">
    <table>
        <tr>
            <td><strong>Player one name</strong></td>
            <td><strong>Player two name</strong></td>
            <td><strong>Winner</strong></td>
        </tr>
        <c:forEach var="match" items="${requestScope.matches}">
            <tr>
                <td>${match.player1.name}</td>
                <td>${match.player2.name}</td>
                <td>${match.winner.name}</td>
            </tr>
        </c:forEach>
    </table>

    <table>
        <tr>
            <c:forEach begin="1" end="${noOfPage}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/matches?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>


</div>
</body>
</html>
