<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="tests-content">
        <form:form method="POST" action="${context}${tutor}/action_with_answer" commandName="answerForm">
            <div class="form-group">
                <form:errors class="form-control input-lg" path="*"/>
            </div>

            <div class="form-group">
                <form:checkbox path="correct" name="correct"/>
                <form:input type="text" class="form-control input-lg" name="name" placeholder="name" path="name"/>
            </div>

            <div class="form-group">
                <form:hidden  class="form-control input-lg" name="question"
                             path="question.idQuestion"/>
                <form:hidden  class="form-control input-lg" name="question"
                             path="question.test.idTest"/>
                <form:hidden  class="form-control input-lg" name="answer"
                             path="idAnswer"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <div class="form-group">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
                <a href="<c:url value="${tutor}/home/questions.html?page=${questionPaginationData.page}&idQuestion=${answerForm.question.idQuestion}&idTest=${answerForm.question.test.idTest}"/>">Cancel</a>
            </div>
        </form:form>

    </div>
</div>