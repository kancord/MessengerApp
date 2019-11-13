<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Peoples</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<%@ include file="base.jsp" %>
<p></p>
<script type="text/javascript">
    function subscribe(id, subId) {
        $.ajax({
            url: '/addSub',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: ({
                id: id,
                subId: subId
            })
        });
    }
</script>
<div id="accounts" class="contentDiv">
    <table>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Sub</th>

        <c:forEach var="account" items="${accountList}">
            <tr>
                <td>${account.firstName}</td>
                <td>${account.lastName}</td>
                <td>
                    <button id="sub" onclick="subscribe(${pageContext.request.userPrincipal.principal.id}, ${account.id})">Subscribe</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
