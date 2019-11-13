<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>
<%@ include file="base.jsp" %>
<div id="messages" class="contentDiv">
    <h2>My messages</h2>
    <a href="/messages/add"><button><b>+</b></button></a>
    <c:forEach var="message" items="${messageList}">
        <div class="message">
            <p></p>
            <p>${message.text}</p>
            <p></p>
        </div>
    </c:forEach>
</div>
</body>
</html>
