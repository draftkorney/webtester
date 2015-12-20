<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="test_info.jsp"/>


<div>
    <c:if test="${questions.size() == 0}">
        <div>
            Test does not have questions!!!
        </div>
    </c:if>

    <c:forEach items="${questions}" varStatus="q" var="question">
        <div>
                ${q.count})<strong>${question.name}</strong>
            <c:forEach items="${question.answers}" var="answer" varStatus="a">
                <div>
                        ${a.count}) ${answer.name}
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
