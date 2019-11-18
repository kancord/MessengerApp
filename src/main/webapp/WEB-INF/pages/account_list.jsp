<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Peoples</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <script src="/static/jquery-3.4.1.min.js"></script>
</head>
<body>

<%@ include file="base.jsp" %>
<%--<script type="text/javascript">--%>
    <%--function subscribe(id, subId, btn) {--%>
        <%--$.ajax({--%>
            <%--url: '/addSub',--%>
            <%--type: 'GET',--%>
            <%--dataType: 'json',--%>
            <%--contentType: 'application/json',--%>
            <%--mimeType: 'application/json',--%>
            <%--data: ({--%>
                <%--id: id,--%>
                <%--subId: subId--%>
            <%--})--%>
        <%--});--%>
        <%--($(btn).parentNode).find("#unfollow").show();--%>
        <%--$(btn).hide();--%>
    <%--}--%>
    <%--function unfollow(subId, btn) {--%>
        <%--$.ajax({--%>
            <%--url: '/delSub/' + subId,--%>
            <%--type: 'DELETE',--%>
            <%--dataType: 'json'--%>
        <%--});--%>
        <%--($(btn).parentNode).find("#subscribe").show();--%>
        <%--$(btn).hide();--%>
    <%--}--%>
<%--</script>--%>
<div id="accounts" class="contentDiv">
    <table>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>   </th>

        <c:forEach var="account" items="${accountList}">
            <tr>
                <td><a href="/account/${account.id}">${account.firstName}</a></td>
                <td><a href="/account/${account.id}">${account.lastName}</a></td>
                <td>
                    <%--<button id="subscribe" onclick="subscribe(${pageContext.request.userPrincipal.principal.id}, ${account.id}, this)">Subscribe</button>--%>
                    <%--<button id="unfollow" onclick="unfollow(${account.id}, this)" hidden>Unfollow</button>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
