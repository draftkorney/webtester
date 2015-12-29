<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c2" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="questionForAnswer" type="ua.alex.source.webtester.forms.QuestionData"--%>
<div>
    <div>${questionForAnswer.questionName}</div>
    <form:form method="post" action="/answered" commandName="questionForAnswer">
        <form:hidden path="idQuestion"/>
        <ul>
            <c:forEach items="${questionForAnswer.answerList}" var="answer">
                <li><form:checkbox path="answerList" label="${answer.name}" value="${answer.idAnswer}"/></li>
            </c:forEach>
        </ul>


        <button type="submit">Next</button>
    </form:form>
</div>

