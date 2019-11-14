<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Messages</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>
<%@ include file="base.jsp" %>
<div id="messages" class="contentDiv">
    <h2>My messages</h2>
    <a href="/message/add"><button><b>+</b></button></a>
    <c:forEach var="message" items="${messageList}">
        <h4><fmt:formatDate value="${message.createDate}" pattern="HH:mm dd.MM.yyyy "/></h4>
        <div class="message">
            <textarea cols="50" rows="5" readonly style="resize: none; border: none;">${message.text}</textarea>
        </div>
    </c:forEach>
</div>
</body>
</html>
