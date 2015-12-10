<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>

<c:set var="url" value="/admin/home/accountsList.html"/>

<div class="container">
  <h2>User List</h2>

  <div class="user-list">

    <c:forEach var="user" items="${users}">
      <div class="user-content">
          ${user.fio}
          ${user.email}
      </div>
    </c:forEach>
  </div>

    <paginations:paginations paginationData="${adminPaginationData}" url="${url}" />

</div>