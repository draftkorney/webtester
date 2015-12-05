<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <h2>Create a new Account </h2>

    <form method="POST" action="${context}/create_new_account"  commandName="signUpForm">
        <div class="form-group">
            <input type="text" class="form-control input-lg"  name="login" placeholder="Login" required>
        </div>

        <div class="form-group">
            <input type="text" class="form-control input-lg"  name="email" placeholder="Email" required>
        </div>

        <div class="form-group">
            <input type="text" class="form-control input-lg"  name="fio" placeholder="FIO" required>
        </div>

        <div class="form-group">
            <input type="password" class="form-control input-lg" name="password" placeholder="Password" required>
        </div>

        <div class="form-group">
            <input type="password" class="form-control input-lg" name="confirm" placeholder="Confirm Password" required>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Sign In</button>
        </div>
    </form>
</div>