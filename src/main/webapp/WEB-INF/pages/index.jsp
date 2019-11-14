<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Feed</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>

    <%@ include file="base.jsp" %>
    <div id="feed" class="contentDiv">
        <h2>Feed</h2>
        <c:forEach var="message" items="${feedList}">
            <h4>${message[0]} ${message[1]}</h4>
            <h5><fmt:formatDate value="${message[2]}" pattern="HH:mm dd.MM.yyyy "/></h5>
            <div class="message">
                <textarea cols="50" rows="5" readonly style="resize: none; border: none;">${message[3]}</textarea>
            </div>
        </c:forEach>


</body>
</html>
