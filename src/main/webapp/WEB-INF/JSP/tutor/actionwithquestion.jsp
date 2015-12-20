<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="tests-content">
        <div class="row">

            <form:form method="POST" action="${context}${tutor}/action_with_question" commandName="questionForm"
                       cssClass="col-md-12 form-horizontal">
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-4">
                        <form:errors path="*"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4">Question name </label>

                    <div class="col-sm-4">
                        <form:input type="text" class="form-control input" name="name" placeholder="name"
                                    path="name"/>
                    </div>

                </div>

                <div class="form-group">
                    <form:hidden class="form-control input-lg" name="test"
                                 path="test.idTest"/>
                    <form:hidden class="form-control input-lg" name="question"
                                 path="idQuestion"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-4">
                        <button class="btn btn-primary btn" type="submit">Save</button>
                        <a href="<c:url value="${tutor}/home/questions.html?page=${questionPaginationData.page}&idTest=${questionForm.test.id}"/>">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>