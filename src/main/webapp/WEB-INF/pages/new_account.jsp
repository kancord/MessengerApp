
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<%@ include file="base.jsp" %>

<h3> New Account</h3>

    <form  method="POST">
        <label for="nickname">login</label>
        <input type="text" name="nickname" id="nickname" value="${account.nickname}">
        <p></p>
        <label for="firstName">Firstname</label>
        <input type="text" name="firstName" id="firstName" value="${account.firstName}">
        <p></p>
        <label for="lastName">Lastname</label>
        <input type="text" name="lastName" id="lastName" value="${account.lastName}">
        <p></p>
        <label for="about">about</label>
        <input type="text" name="about" id="about" value="${account.about}">
        <p></p>
        <label for="encPassword">Password</label>
        <input type="password" name="encPassword" id="encPassword" value="${account.encPassword}">
        <p></p>
        <input type="submit" value="Save">
    </form>
</body>
</html>
