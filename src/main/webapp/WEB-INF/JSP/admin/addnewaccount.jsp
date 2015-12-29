<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h3 class="text-center">${headerAction}</h3>

    <div class="row">
        <form:form method="POST" action="${context}${admin}/action_with_account" commandName="accountForm"
                   cssClass="form-horizontal col-md-6 col-md-offset-3">
            <div class="form-group">
                <form:errors path="*"/>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Login</label>

                <div class="col-sm-10">
                    <form:input type="text" cssClass="form-control input" name="login" placeholder="Login"
                                path="login"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Email</label>

                <div class="col-sm-10">
                    <form:input type="text" cssClass="form-control input" name="email" placeholder="Email"
                                path="email"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Fio</label>

                <div class="col-sm-10">
                    <form:input type="text" cssClass="form-control input" name="fio" placeholder="FIO" path="fio"/>
                    <form:input type="hidden" path="idAccount"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="roles" value="1" id="adminRole"/>Admin
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="roles" value="2" id="advanceTutorRole"/>Advance Tutor
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="roles" value="3" id="tutorRole"/>Tutor
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="roles" value="4"/>Student
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-primary" type="submit">Save</button>
                    <a href="<c:url value="${admin}/home/accountsList.html?page=${adminPagination.page}"/>">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>

</div>
<script>
    $(document).ready(function () {
        $("#advanceTutorRole").on("change", function (e) {
            e.preventDefault();
            if (this.checked) {
                $("#tutorRole").prop("checked", true);
            }
        });
    });
</script>