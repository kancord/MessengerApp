<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Feed</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <script src="/static/jquery-3.4.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function deleteFavorite(mesId, btn) {
        $.ajax({
            url: '/delFav/' + mesId,
            type: 'DELETE',
            dataType: 'json'
        });
        (btn.parentNode).remove();
    }
</script>
<%@ include file="base.jsp" %>
<div id="feed" class="contentDiv">
    <h2>Favorites</h2>
    <c:forEach var="favorite" items="${favoriteList}">
    <div id="singleMessage">
        <h4>${favorite[0]} ${favorite[1]}</h4>
        <h5><fmt:formatDate value="${favorite[2]}" pattern="HH:mm dd.MM.yyyy "/></h5>
        <div class="message">
            <textarea cols="50" rows="5" readonly style="resize: none; border: none;">${favorite[3]}</textarea>
        </div>
        <button id="deleteFav" onclick="deleteFavorite(${favorite[4]}, this)">delete from favorites</button>
    </div>
    </c:forEach>

</body>
</html>
