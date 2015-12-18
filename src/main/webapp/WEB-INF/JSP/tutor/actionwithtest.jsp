<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="container">
    <div class="tests-content">
        <div class="row">
            <form:form method="POST" action="${context}${tutor}/action_with_test" commandName="testForm"
                       cssClass="form-horizontal col-md-12">
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-4">
                        <form:errors path="*"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4">Test name </label>

                    <div class="col-sm-4">
                        <form:input type="text" class="form-control input" name="name" placeholder="name" path="name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4">Description </label>

                    <div class="col-sm-4">
                        <form:input type="text" class="form-control input" name="description" placeholder="description"
                                    path="description"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4">time per question </label>

                    <div class="col-sm-4">
                        <form:input type="text" class="form-control input" name="timePerQuestion"
                                    placeholder="timePerQuestion" path="timePerQuestion"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:hidden class="form-control input-lg" name="account" placeholder="account"
                                 path="account.idAccount"/>
                    <form:hidden class="form-control input-lg" name="account" placeholder="account"
                                 path="idTest"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-4">
                        <button class="btn btn-primary" type="submit">Save</button>
                        <a href="<c:url value="${tutor}/home/testslist.html?page=${testPagination.page}"/>">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>