
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new message</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>
    <%@ include file="base.jsp" %>
    <div  class="contentDiv">
        <form method="POST">
            <label><b>Your message:</b></label>
            <div class="inputForm">
                <textarea id="text" name="text" cols="50" rows="5" value="${message.text}" required></textarea>
                <p></p>
            </div>
            <input type="submit" value="Add"style="margin-top: 20px">
        </form>
    </div>
</body>
</html>
