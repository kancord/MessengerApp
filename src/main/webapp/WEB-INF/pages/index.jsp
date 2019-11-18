<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Feed</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <script src="/static/jquery-3.4.1.min.js"></script>
</head>
<body>
<script>
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
    <%@ include file="base.jsp" %>
    <div id="feed" class="contentDiv">
        <h2>Feed</h2>
        <c:forEach var="message" items="${feedList}">
        <div id="msg">
            <h4>${message[0]} ${message[1]}</h4>
            <h5><fmt:formatDate value="${message[2]}" pattern="HH:mm dd.MM.yyyy "/></h5>
            <div class="message">
                <textarea cols="50" rows="5" readonly style="resize: none; border: none;">${message[3]}</textarea>
            </div>
            <c:if test="${message[5].equals('FALSE')}">
                <button id="fav" onclick="addToFav(${message[4]}, this)">☆</button>
            </c:if>
        </div>
        </c:forEach>


</body>
</html>
