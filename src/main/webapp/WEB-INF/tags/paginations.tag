<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="paginationData" type="ua.alex.source.webtester.utils.PaginationData" required="true" %>
<%@ attribute name="url" type="java.lang.String" required="true" %>

<div>
    <ul class="pagination">


        <c:if test="${paginationData.countPage>0 && paginationData.page > 1}">
            <li><a href="${url}?page=${1}">First</a></li>
        </c:if>

        <c:if test="${paginationData.page>=2}">
            <li>
                <a href="${url}?page=${paginationData.page-1}">Prev</a>
            </li>
        </c:if>


        <c:forEach begin="${paginationData.startIndex}" end="${paginationData.finishIndex}" var="i">
            <c:choose>
                <c:when test="${paginationData.page eq i}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${url}?page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${paginationData.page < paginationData.countPage && paginationData.page >= 1}">
            <li>
                <a href="${url}?page=${paginationData.page+1}">Next</a>
            </li>
        </c:if>
        <c:if test="${paginationData.countPage>1}">

            <li>
                <a href="${url}?page=${paginationData.countPage}">Last</a>
            </li>
        </c:if>
    </ul>
</div>