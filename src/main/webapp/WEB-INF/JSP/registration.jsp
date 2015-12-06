<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h2>Create a new Account </h2>

    <form:form method="POST" action="${context}/create_new_account"  commandName="signUpForm">
        <div class="form-group">
            <form:errors  class="form-control input-lg"  path="*" />
        </div>

        <div class="form-group">
            <form:input type="text" class="form-control input-lg"  name="login" placeholder="Login" path="login"/>
        </div>

        <div class="form-group">
            <form:input type="text" class="form-control input-lg"  name="email" placeholder="Email" path="email" />
        </div>

        <div class="form-group">
            <form:input type="text" class="form-control input-lg"  name="fio" placeholder="FIO" path="fio"/>
        </div>

        <div class="form-group">
            <form:input type="password" class="form-control input-lg" name="password" placeholder="Password" path="password" />
        </div>

        <div class="form-group">
            <form:input type="password" class="form-control input-lg" name="confirmPass" placeholder="Confirm Password" path="confirmPass" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Sign In</button>
        </div>
    </form:form>
</div>