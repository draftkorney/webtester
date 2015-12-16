<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h2 class="text-center">Please login </h2>

    <div class="row">
        <form class="col-md-4 col-md-offset-4" method="POST" action="${context}/loginHandler">
            <div class="form-group">
                <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null }">
                    <span class="errors">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</span>
                </c:if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control input" placeholder="Email" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control input" placeholder="Password" name="password">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <div class="form-group">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="true" name="_spring_security_remember_me"> Remember me
                    </label>
                </div>
            </div>

            <div class="form-group">
                <a href="${context}/fbLogin">
                    <img alt="fbLogin" src="${context}/resources/images/login-facebook.png"/>
                </a>
                <button class="btn btn-primary btn-default" type="submit">Log In</button>
                <span class="pull-right"><a href="${context}/registration" class="btn btn-success btn-default">Sign Up</a></span>
            </div>
            <div class="">
                <span class="pull-right"><a href="${context}/forgot_password.html">Forgot password?</a></span>
            <span class="pull-right">

            </span>
            </div>
        </form>
    </div>
</div>