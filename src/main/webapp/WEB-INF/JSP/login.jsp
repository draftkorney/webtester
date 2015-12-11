<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h2>Please login </h2>

    <form method="POST" action="${context}/loginHandler">
        <table style="width:300px;margin:0 auto;">
            <tr>
                <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null }">
                    <td colspan="2" class="errors">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</td>
                </c:if>
            </tr>
            <tr>
                <td><label for="username">Login</label></td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input type="password" name="password"/></td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;padding-top:20px;">
                    <input type="checkbox" value="true" name="_spring_security_remember_me">Remember me
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;padding-top:20px;">
                    <input type="submit" value="Login"/>
                </td>

                <td colspan="2" style="text-align:center;padding-top:20px;">
                    <a href="${context}/registration">Sign Up</a>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;padding-top:20px;">
                    <a href="${context}/fbLogin">
                        <img alt="fbLogin" src="${context}/resources/images/login-facebook.png"/>
                    </a>
                </td>
            </tr>
        </table>
    </form>
</div>