<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>
<c:set var="testResultPagination" value="${testResultPaginationData}" scope="session"/>
<c:set var="url" value="/show_result.html"/>
<%--@elvariable id="testResult" type="ua.alex.source.webtester.entities.TestResult"--%>
<div class="container">
    <div class="tests-content">
        <table class="table">
            <thead>
            <tr>
                <th>Test Name</th>
                <th>correct answers</th>
                <th>all answers</th>
                <th>passed</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${testResults}" var="testResult">
                <tr>
                    <td> ${testResult.testName}</td>
                    <td> ${testResult.correctCount}</td>
                    <td>${testResult.allCount}</td>
                    <td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${testResult.updated}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <paginations:paginations paginationData="${testResultPagination}" url="${url}"/>
</div>
