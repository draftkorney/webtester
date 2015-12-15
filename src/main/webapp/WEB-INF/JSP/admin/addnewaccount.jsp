<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <h2>Add a New User </h2>

  <form:form method="POST" action="${context}${admin}/action_with_account"  commandName="accountForm">
    <div class="form-group">
      <form:errors  class="form-control input"  path="*" />
    </div>

    <div class="form-group">
      <form:input type="text" cssClass="form-control input-sm" name="login" placeholder="Login" path="login"/>
    </div>

    <div class="form-group">
      <form:input type="text" cssClass="form-control input-sm"  name="email" placeholder="Email" path="email" />
    </div>

    <div class="form-group">
      <form:input type="text" cssClass="form-control input-sm"  name="fio" placeholder="FIO" path="fio"/>
      <form:input type="hidden"   path="idAccount"/>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
    <div class="form-group">
      <form:checkbox path="roles" value="1"/>Admin
      <form:checkbox path="roles" value="2"/>Advance Tutor
      <form:checkbox path="roles" value="3"/>Tutor
      <form:checkbox path="roles" value="4"/>Student
    </div>

    <div class="form-group">
      <button class="btn btn-primary btn-block" type="submit">Save</button>
      <a href="<c:url value="${admin}/home/accountsList.html?page=${adminPagination.page}"/>">Cancel</a>
    </div>
  </form:form>
</div>