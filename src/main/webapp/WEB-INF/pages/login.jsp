<%--
  Created by IntelliJ IDEA.
  User: dkanishchev
  Date: 27.08.2019
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="base.jsp" %>
    <div id="login">
        <h2>Login</h2>
        <c:if test="${pageContext.request.getParameter('error') == 'true'}">
            <p>Login Failed! Please try again</p>
        </c:if>
        <p></p>
        <form name="login" action="/login" method="POST">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" value=""/>
            <p></p>
            <label for="password">Password</label>
            <input type="password" name="password" id="password"/>
            <p></p>
            <label for="rememberMe">Remember</label>
            <input type="checkbox" id="rememberMe" name="rememberMe"/>
            <p></p>
            <input type="submit" value="Login"/>
        </form>
    </div>
    <div style="margin-top: 35px;">
        <a href="/new_account">Create new account</a>
    </div>
</body>
</html>
