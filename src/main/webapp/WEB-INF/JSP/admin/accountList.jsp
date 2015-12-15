<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="paginations" tagdir="/WEB-INF/tags" %>
<c:set var="adminPagination" value="${adminPaginationData}" scope="session"/>
<c:set var="url" value="/admin/home/accountsList.html"/>
<%--@elvariable id="user" type="ua.alex.source.webtester.entities.Account"--%>

<div class="container">
  <h2>User List</h2>
    <a href="<c:url value="${admin}/addNewAccount.html"/>">Add a new User</a>
  <div class="user-list">

      <div class="user-content">
          <table class="table">
              <thead>
              <tr>
                  <th>login</th>
                  <th>email</th>
                  <th>fio</th>
                  <th>active</th>
                  <th></th>
                  <th></th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="user" items="${users}">
                  <tr>
                      <td>${user.login}</td>
                      <td>${user.email}</td>
                      <td>${user.fio}</td>
                      <td>

                          <form id="actionForm" action="${admin}/update_user_activity" name="action" method="POST">
                              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                              <input type="hidden" value="${user.idAccount}" name="idAccount">
                              <c:choose>
                                  <c:when test="${user.active}">
                                      <button class="btn btn-danger" type="submit">Deactivate user</button>
                                  </c:when>
                                  <c:otherwise>
                                      <button class="btn btn-success" type="submit">Activate user</button>
                                  </c:otherwise>
                              </c:choose>

                          </form>
                      </td>
                      <td><a href="<c:url value="${admin}/editAccount.html?idAccount=${user.idAccount}"/>">Edit</a></td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
      </div>
  </div>

    <paginations:paginations paginationData="${adminPagination}" url="${url}" />

</div>