<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
    <div>${question.name}</div>
    <form:form method="post" action="/answered" commandName="question">
        <form:checkboxes path="checkAnswer" itemValue="correct" itemLabel="name" items="${question.answers}"/>

        <button type="submit">Next</button>
    </form:form>
</div>

