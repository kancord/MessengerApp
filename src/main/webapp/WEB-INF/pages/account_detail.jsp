<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <script src="/static/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@ include file="base.jsp" %>
<script type="text/javascript">
    function subscribe(subId, btn) {
        $.ajax({
            url: '/addSub/' + subId,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
        });
        btn.remove();
    }

    function addToFav(mesId, btn) {
        $.ajax({
            url: '/addFav/' + mesId,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json'
        });
        btn.remove();
    }
</script>
<div id="account" class="contentDiv">
    <div style="text-align: center">
        <h2>${account.firstName} ${account.lastName}</h2>
        <c:if test="${not isFollowed}">
            <button id="sub"
                    onclick="subscribe(${account.id}, this)">
                Subscribe
            </button>
        </c:if>

    </div>
    <h3>About</h3>
    <textarea id="about" cols="60" rows="5" style="resize: none; border: none;" readonly>${account.about}</textarea>
    <h3>Messages:</h3>
    <c:forEach var="message" items="${messageList}">
        <h4>${message.createDate}</h4>
        <div class="message">
            <textarea cols="50" rows="5" readonly style="resize: none; border: none;">${message.text}</textarea>
        </div>
        <button id="fav" onclick="addToFav(${message.id}, this)">☆</button>
    </c:forEach>
</div>

</body>
</html>
