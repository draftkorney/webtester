<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="logout" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div>
        <h2>Log in</h2>

        You are currently logged in as ${login}.
        Click here for the <a href="${context}/myInfo">home</a>.
        To log in as another user, you must first <logout:logout/>.
    </div>
</div>