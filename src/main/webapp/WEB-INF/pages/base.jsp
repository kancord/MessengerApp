
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
            <li><a href="/message">My message</a></li>
            <li><a href="/account">People</a></li>
            <li><a href="/sub">Subscribes</a></li>
            <li><a href="/favorite">Favorites</a></li>
            <li><a href="/">Feed</a></li>

        </ul>


</div>