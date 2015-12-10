<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="testPagination" value="testPaginationData" scope="session"/>

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
                <tr>
                    <c:forEach items="tests" var="test">
                        <td>${test.name}</td>
                        <td>${test.description}</td>
                        <td>${test.timePerQuestion}</td>
                        <td><a href="<c:url value="${tutor}/home/questions.html?idTest=${test.idTest}"/>">Questions</a></td>
                        <td><a href="<c:url value="${tutor}/editTest.html?idTest=${test.idTest}"/>">Edit</a></td>
                        <td><a href="<c:url value="${tutor}/delete_test?idTest=${test.idTest}"/>">Delete</a></td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>

    </div>


</div>