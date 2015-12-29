<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h3 class="text-center">Create a new Account </h3>

    <div class="row">
        <form:form method="POST" action="${context}/create_new_account" commandName="signUpForm" class="col-md-4 col-md-offset-4">
            <div class="form-group">
                <form:errors path="*"/>
            </div>

            <div class="form-group">
                <form:input type="text" class="form-control input" name="login" placeholder="Login" path="login"/>
            </div>

            <div class="form-group">
                <form:input type="text" class="form-control input" name="email" placeholder="Email" path="email"/>
            </div>

            <div class="form-group">
                <form:input type="text" class="form-control input" name="fio" placeholder="FIO" path="fio"/>
            </div>

            <div class="form-group">
                <form:input type="password" class="form-control input" name="password" placeholder="Password"
                            path="password"/>
            </div>

            <div class="form-group">
                <form:input type="password" class="form-control input" name="confirmPass"
                            placeholder="Confirm Password" path="confirmPass"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn pull-right" type="submit" >Sign In</button>
            </div>
        </form:form>
    </div>
</div>