<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="logout" tagdir="/WEB-INF/tags" %>
<c:set var="tutor" value="/tutor" scope="application"/>
<c:set var="advanced_tutor" value="/advanced_tutor" scope="application"/>
<c:set var="admin" value="/admin" scope="application"/>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7 "> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8 br-ie7"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9 br-ie8"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Web tester</title>
    <link rel="stylesheet" type="text/css" href="${context}/resources/css/normalize.css?v=${CSS_JS_VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${context}/resources/css/styles.css?v=${CSS_JS_VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css?v=${CSS_JS_VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css.map?v=${CSS_JS_VERSION}"/>
    <script src="${context}/resources/js/jquery-1.10.2.js?v=${CSS_JS_VERSION}"></script>
    <script src="${context}/resources/js/bootstrap.min.js?v=${CSS_JS_VERSION}"></script>
</head>


<body class="container">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Web Tester</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav links">
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                    <li><a href="${admin}/home/accountsList.html">All Users</a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ADVANCED_TUTOR')">
                    <li><a href="${advanced_tutor}/home/testslist.html">All Tests</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('TUTOR')">
                    <li><a href="${tutor}/home/testslist.html">Owner Test</a></li>
                </sec:authorize>
                <li><a href="#">Test to Pass</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <p class="navbar-text"><a href="${context}/profile.html" class="navbar-link"><sec:authentication
                            property="principal.username"/></a></p>
                    <logout:logout/>
                </sec:authorize>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<section class="main">
    <decorator:body/>
</section>
<footer class="footer navbar-fixed-bottom">
    <div class="container">Footer</div>

</footer>
</body>


<script>
    $(document).ready(function () {
        $(".links li").removeClass("active");


        var admin = '${admin}';
        var advanced_tutor = '${advanced_tutor}';
        var tutor = '${tutor}';
        var path = location.pathname;
        var li;

        if (path.indexOf(admin) > -1) {
            li = $(".links li").get(0);
        } else if (path.indexOf(advanced_tutor) > -1) {
            li = $(".links li").get(1);
        } else if (path.indexOf(tutor) > -1) {
            li = $(".links li").get(2);
        }
        $(li).addClass("active");

    });
</script>

</html>