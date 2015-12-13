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
    <div class="question-content">
        <div><a href="<c:url value="${tutor}/addQuestion.html?idTest=${idTest}"/>">Add a new question</a></div>

        <div class="question-list">
            <c:forEach items="${questions}" var="question">
                <div class="question-item">
                    <div class="question-name">${question.name}
                        <span>
                            <a href="<c:url value="${tutor}/editQuestion.html?idQuestion=${question.idQuestion}&idTest=${idTest}"/>">Edit</a>
                        </span>

                        <span>
                            <form id="actionQuestionForm" action="${tutor}/delete_question" name="action" method="POST">
                                <input type="hidden" name="idTest" value="${idTest}">
                                <input type="hidden" name="idQuestion" value="${question.idQuestion}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn" type="submit">Delete</button>
                            </form>
                        </span>

                        <c:if test="${question.answers.size() < 5}">
                        <span>
                            <a href="<c:url value="${tutor}/addAnswer.html?idQuestion=${question.idQuestion}&idTest=${idTest}"/>">
                                Add a new answer
                            </a>
                        </span>
                        </c:if>
                    </div>
                    <c:forEach items="${question.answers}" var="answer">
                        <div>
                            <a href="<c:url value="${tutor}/editAnswer.html?idAnswer=${answer.idAnswer}&idQuestion=${question.idQuestion}&idTest=${idTest}"/>">edit</a>
                            <form id="actionAnswerForm" action="${tutor}/delete_answer" name="action" method="POST">
                                <input type="hidden" name="idTest" value="${idTest}">
                                <input type="hidden" name="idAnswer" value="${answer.idAnswer}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn" type="submit">Delete</button>
                            </form>
                            <c:choose>
                                <c:when test="${answer.correct}">
                                    <strong>${answer.name}</strong>
                                </c:when>
                                <c:otherwise>
                                    ${answer.name}
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>

                </div>
            </c:forEach>
        </div>
    </div>

    <paginations:paginations paginationData="${questionPagination}" url="${url}"/>
</div>