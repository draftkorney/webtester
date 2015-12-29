<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <td> ${testResult.updated}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
