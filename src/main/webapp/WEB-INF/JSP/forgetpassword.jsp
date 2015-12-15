<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h2>Please enter your email</h2>

    <form:form method="POST" action="${context}/forgot_password" commandName="form">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-group">
            <form:errors path="*" />
        </div>

        <div class="form-group">
            <form:input path="email" type="text" cssClass="form-control input" placeholder="Email"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Send"/>
        </div>
    </form:form>
</div>