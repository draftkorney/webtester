<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h3>Edit Profile</h3>

    <div class="tests-content">
        <form:form method="POST" action="${context}/edit_prodile" commandName="profile">
            <div class="form-group">
                <form:errors class="form-control input-lg" path="*"/>
            </div>

            <div class="form-group">
                <form:input type="text" cssClass="form-control input" placeholder="email" path="email"/>
            </div>

            <div class="form-group">
                <form:input type="text" cssClass="form-control input" placeholder="fio" path="fio"/>
            </div>



            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit">Save</button>
                <a href="<c:url value="${context}/profile.html"/>">Cancel</a>
            </div>
        </form:form>

    </div>
</div>