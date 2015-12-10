<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="tests-content">
        <form:form method="POST" action="${context}${tutor}/action_with_question" commandName="questionForm">
            <div class="form-group">
                <form:errors class="form-control input-lg" path="*"/>
            </div>

            <div class="form-group">
                <form:input type="text" class="form-control input-lg" name="name" placeholder="name" path="name"/>
            </div>

            <div class="form-group">
                <form:hidden type="hidden" class="form-control input-lg" name="test"
                             path="test"/>
                <form:hidden type="hidden" class="form-control input-lg" name="question"
                             path="idQuestion"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <div class="form-group">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
                <a href="<c:url value="${tutor}/home/questions.html?page=${questionPaginationData.page}"/>">Cancel</a>
            </div>
        </form:form>

    </div>
</div>