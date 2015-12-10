<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="testPagination" value="${testPaginationData}" scope="session"/>
<c:set var="url" value="${tutor}/home/testslist.html"/>

<%--@elvariable id="test" type="ua.alex.source.webtester.entities.Test"--%>

<div class="container">
    <div class="tests-content">
        <div><a href="<c:url value="${tutor}/createTest.html"/>">Add a new test</a></div>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Time Per Question</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="test" items="${tests}">
                    <tr>
                        <td>${test.name}</td>
                        <td>${test.description}</td>
                        <td>${test.timePerQuestion}</td>
                        <td><a href="<c:url value="${tutor}/home/questions.html?idTest=${test.idTest}"/>">Questions</a>
                        </td>
                        <td><a href="<c:url value="${tutor}/editTest.html?idTest=${test.idTest}"/>">Edit</a></td>
                        <td>
                            <form id="actionForm" action="${tutor}/delete_test" name="action" method="POST">
                                <input type="hidden" name="idTest" value="${test.idTest}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

    <paginations:paginations paginationData="${testPagination}" url="${url}"/>

</div>
<script>
    $(document).ready(function () {
    });
</script>