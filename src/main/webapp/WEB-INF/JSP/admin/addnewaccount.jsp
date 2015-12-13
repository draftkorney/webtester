<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <h2>Add a New User </h2>

  <form:form method="POST" action="${context}/admin/add_new_account"  commandName="newAccount">
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
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
    <div class="form-group">
      <form:checkbox path="roles" value="0"/>Admin
      <form:checkbox path="roles" value="1"/>Advance Tutor
      <form:checkbox path="roles" value="2"/>Tutor
      <form:checkbox path="roles" value="3"/>Student
    </div>

    <div class="form-group">
      <button class="btn btn-primary btn-lg btn-block" type="submit">Create</button>
    </div>
  </form:form>
</div>