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
<script type="text/javascript">
    function unfollow(subId, btn) {
        $.ajax({
            url: '/delSub/' + subId,
            type: 'DELETE',
            dataType: 'json'
        });
        (btn.parentNode).parentNode.remove();
    }
</script>
<div id="subs" class="contentDiv">
    <div id="left" style="float: left;">
        <h3>Subscribers</h3>
        <table>
            <th>Firstname</th>
            <th>Lastname</th>

            <c:forEach var="account" items="${subscriberAccountList}">
                <tr>
                    <td><a href="/account/${account.id}">${account.firstName}</a></td>
                    <td><a href="/account/${account.id}">${account.lastName}</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="right" style="float: right;">
        <h3>Follow</h3>
        <table>
            <th>Firstname</th>
            <th>Lastname</th>
            <th> </th>
            <c:forEach var="account" items="${followAccountList}">
                <tr>
                    <div>
                        <td><a href="/account/${account.id}">${account.firstName}</a></td>
                        <td><a href="/account/${account.id}">${account.lastName}</a></td>
                        <td>
                            <button id="unfollow" onclick="unfollow(${account.id}, this)">Unfollow</button>
                        </td>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

</body>
</html>
