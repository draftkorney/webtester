<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>
<c:set var="questionPagination" value="${questionPaginationData}" scope="session"/>
<c:set var="url" value="${tutor}/home/questions.html"/>

<%--@elvariable id="question" type="ua.alex.source.webtester.entities.Question"--%>
<%--@elvariable id="answer" type="ua.alex.source.webtester.entities.Answer"--%>

<div class="container">
    <a class="btn btn-info" href="${tutor}/home/testslist.html?page=${testPagination.page}">Back to test list</a>
    <a href="<c:url value="${tutor}/addQuestion.html?idTest=${idTest}"/>" class="btn btn-success pull-right">
        Add a new question
    </a>

    <div class="question-content clearfix">
        <div class="question-list">
            <c:forEach items="${questions}" var="question">
                <div class="question-item">
                    <div class="question text-center">
                        <span class="question-name">${question.name}</span>
                        <span>
                            <a href="<c:url value="${tutor}/editQuestion.html?idQuestion=${question.idQuestion}&idTest=${idTest}"/>">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                        </span>

                        <form id="actionQuestionForm" action="${tutor}/delete_question" name="action"
                              class="form-inline" method="POST">
                            <input type="hidden" name="idTest" value="${idTest}">
                            <input type="hidden" name="idQuestion" value="${question.idQuestion}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-sm btn-default" type="submit">
                                <span class="glyphicon glyphicon-remove " aria-hidden="true"></span>
                            </button>
                        </form>

                        <c:if test="${question.answers.size() < 4}">
                        <span>
                            <a href="<c:url value="${tutor}/addAnswer.html?idQuestion=${question.idQuestion}&idTest=${idTest}"/>">
                                Add a new answer
                            </a>
                        </span>
                        </c:if>
                    </div>

                    <ul>
                        <c:forEach items="${question.answers}" var="answer">

                                <div class="answer">
                                    <li>
                                    <c:choose>
                                        <c:when test="${answer.correct}">
                                           <span> <strong>${answer.name}</strong></span>
                                        </c:when>
                                        <c:otherwise>
                                        <span> ${answer.name}</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="<c:url value="${tutor}/editAnswer.html?idAnswer=${answer.idAnswer}&idQuestion=${question.idQuestion}&idTest=${idTest}"/>">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </a>

                                    <form id="actionAnswerForm" action="${tutor}/delete_answer" name="action"
                                                  method="POST">
                                        <input type="hidden" name="idTest" value="${idTest}">
                                        <input type="hidden" name="idAnswer" value="${answer.idAnswer}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <button type="submit" class="btn btn-sm btn-default" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    </form>
                                    </li>
                                </div>

                        </c:forEach>
                    </ul>


                </div>
            </c:forEach>
        </div>
    </div>

    <paginations:paginations paginationData="${questionPagination}" url="${url}"/>
</div>