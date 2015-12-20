<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="test_info text-center col-md-6 col-md-offset-3">
        <div class="content">
            <table class="report">
                <tbody>
                <tr class="title">
                    <td colspan="2"><span>Info about test</span></td>
                </tr>
                <tr>
                    <td>test name</td>
                    <td>
                        ${test.name}
                    </td>
                </tr>
                <tr class="even">
                    <td>test description</td>
                    <td>${test.description}</td>
                </tr>
                <tr class="even">
                    <td>author</td>
                    <td>${test.account.login}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

