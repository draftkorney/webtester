<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="logoutUrl" value="/logout"/>
<form class="navbar-form navbar-left" action="${logoutUrl}" method="post">
    <div class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
    <button type="submit" class="btn btn-default">Log out</button>
</form>