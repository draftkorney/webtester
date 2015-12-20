<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="testToPassPagination" value="${testToPassPaginationData}" scope="session"/>
<c:set var="url" value="/home/tests.html"/>

<%--@elvariable id="test" type="ua.alex.source.webtester.entities.Test"--%>

<div class="container">
    <div class="tests-content">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Time Per Question</th>
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
                    <td>
                        <a href="<c:url value="/passTest.html?idTest=${test.idTest}"/>">
                           pass online
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/passTestOffline.html?idTest=${test.idTest}"/>">
                           pass offline
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <paginations:paginations paginationData="${testToPassPagination}" url="${url}"/>

</div>
<script>
    $(document).ready(function () {
    });
</script>