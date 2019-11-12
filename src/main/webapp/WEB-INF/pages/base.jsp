
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="horizontal-menu" class="horizMenu">
    <c:if test="${pageContext.request.userPrincipal != null}">
        ${pageContext.request.userPrincipal.name}, <a href="/logout">Logout</a>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal == null}">
        <a href="/login">Login</a>
    </c:if>
</div>
<div  class="vertMenu">
        <ul>
            <li><a href="/">My message</a></li>
            <li><a href="/">Subscribes</a></li>
            <li><a href="/">Favorites</a></li>
            <li><a href="/">News</a></li>

        </ul>


</div>