<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>
    <%@include file="/css/common.css"%>
    <%@include file="/css/newMatch.css"%>
</style>
<head>
    <title>TENNIS - NEW MATCH</title>
</head>
<body>
<h1>Create new match</h1>
<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error-message">
    <%= request.getAttribute("errorMessage") %>
</div>
<% } %>

<form method="post" action="${pageContext.request.contextPath}new-match">
    <div class="input-container">
        <div>
            <p>Player 1</p>
            <label>
                <input type="text" name="player-1" placeholder="Enter Player 1 name">
            </label>
        </div>
        <div>
            <p>Player 2</p>
            <label>
                <input type="text" name="player-2" placeholder="Enter Player 2 name">
            </label>
        </div>
    </div>
    <button type="submit">Create match</button>
</form>
</body>
</html>
