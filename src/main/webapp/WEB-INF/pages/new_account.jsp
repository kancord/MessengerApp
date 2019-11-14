
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>

    <%@ include file="base.jsp" %>
    <div class="contentDiv">
        <h3> New Account</h3>

        <form  method="POST">
            <div class="inputForm">
                <label for="nickname">Login</label>
                <input type="text" name="nickname" id="nickname" value="${account.nickname}" required>
                <p></p>
                <label for="firstName">Firstname</label>
                <input type="text" name="firstName" id="firstName" value="${account.firstName}" required>
                <p></p>
                <label for="lastName">Lastname</label>
                <input type="text" name="lastName" id="lastName" value="${account.lastName}" required>
                <p></p>
                <label for="about">About</label>
                <input type="text" name="about" id="about" value="${account.about}">
                <p></p>
                <label for="encPassword">Password</label>
                <input type="password" name="encPassword" id="encPassword" value="${account.encPassword}" required>
            </div>
            <input type="submit" style="margin-top: 10px" value="Save">
        </form>
    </div>

</body>
</html>
