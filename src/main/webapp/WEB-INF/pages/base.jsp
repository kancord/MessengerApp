<div  style="float:left; margin-left:5px; margin-right:30px; margin-top:15px; height:100%;">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <ul>
        <li><a href="/">Home</a></li>
        <div style="margin-top: 30px;">
            <c:if test="${pageContext.request.userPrincipal != null}">
                ${pageContext.request.userPrincipal.name}
                <li><a href="/logout">Logout</a></li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal == null}">
                <li><a href="/login">Login</a></li>
            </c:if>
        </div>
    </ul>


</div>