<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="paginationData" type="ua.alex.source.webtester.utils.PaginationData" required="true" %>
<%@ attribute name="url" type="java.lang.String" required="true" %>

<div class="pagination" style="text-align: center;">


  <c:if test="${paginationData.countPage>0 && paginationData.page > 1}">
    <td>
      <a href="${url}?page=${1}">First</a>
    </td>
  </c:if>

  <c:if test="${paginationData.page>2}">
    <td>
      <a href="${url}?page=${paginationData.page-1}">Prev</a>
    </td>
  </c:if>


  <c:forEach begin="${paginationData.startIndex}" end="${paginationData.finishIndex}" var="i">
    <c:choose>
      <c:when test="${paginationData.page eq i}">
        <td>${i}</td>
      </c:when>
      <c:otherwise>
        <td>
          <a href="${url}?page=${i}">${i}</a>
        </td>
      </c:otherwise>
    </c:choose>
  </c:forEach>

  <c:if test="${paginationData.page < paginationData.countPage && paginationData.page > 1}">
    <td>
      <a href="${url}?page=${paginationData.page+1}">Next</a>
    </td>
  </c:if>
  <c:if test="${paginationData.countPage>0 && paginationData.page > 1}">

    <td>
      <a href="${url}?page=${paginationData.countPage}">Last</a>
    </td>
  </c:if>
</div>