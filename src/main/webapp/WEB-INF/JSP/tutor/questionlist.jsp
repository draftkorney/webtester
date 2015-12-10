<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="questionPagination" value="questionPaginationData" scope="session"/>

<%--@elvariable id="question" type="ua.alex.source.webtester.entities.Question"--%>
<%--@elvariable id="answer" type="ua.alex.source.webtester.entities.Answer"--%>

<div class="container">
    <div class="tests-content">
        <div><a href="<c:url value="${tutor}/addQuestion.html"/>">Add a new question</a></div>

        <div class="">
            <c:forEach items="questions" var="question">
                <div>${question.name}</div>
                <span><a href="<c:url value="${tutor}/editQuestion.html?idQuestion=${question.idQuestion}"/>">Edit</a></span> <span>delete</span>
                <c:forEach items="questions.answers" var="answer">
                    <div>
                        <c:choose>
                            <c:when test="answer.correct">
                                <strong>${answer.name}</strong>
                            </c:when>
                            <c:otherwise>
                                ${answer.name}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
</div>