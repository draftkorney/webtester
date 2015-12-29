<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="profile" type="ua.alex.source.webtester.entities.Account"--%>

<div class="container">
    <div class="personal">
        <div class="login">
            ${profile.login}
            <div class="btn-group">&nbsp;&nbsp;<a href="${context}/edit_profile.html" class="btn btn-default btn-xs">edit</a>
            </div>
        </div>

        <dl id="personal-contacts" class="contacts">
            <dt>email</dt>
            <dd>${profile.email}</dd>
            <dt>fio</dt>
            <dd>${profile.fio}</dd>
        </dl>
        <a href="/show_result.html">Test Results</a>
    </div>
</div>