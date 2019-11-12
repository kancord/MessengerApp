
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
</head>
<body>

    <%@ include file="base.jsp" %>
    <div id="login" class="contentDiv">
        <h2>Login</h2>
        <c:if test="${pageContext.request.getParameter('error') == 'true'}">
            <p>Login Failed! Please try again</p>
        </c:if>
        <p></p>
        <form name="login"  action="/login" method="POST">
            <div class="inputForm">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" value="" required/>
                <p></p>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required/>
                <p></p>
                <label for="rememberMe">Remember</label>
                <input type="checkbox" id="rememberMe" name="rememberMe"/>
            </div>
            <input style="margin-top: 10px" type="submit" value="Login"/>
        </form>
        <div style="margin-top: 35px;">
            <a href="/new_account">Create new account</a>
        </div>
    </div>

</body>
</html>
