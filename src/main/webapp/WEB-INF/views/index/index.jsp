<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-3 goodsCatalog">

    <%--<div class="panel panel-danger">--%>
        <%--<div class="panel-heading"><i class="glyphicon glyphicon-filter"></i> Фильтр</div>--%>
        <%--<div class="panel-body">--%>
            <%--<div class="list-group">--%>

            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="panel panel-danger">
        <div class="panel-heading"><i class="glyphicon glyphicon-list-alt"></i> Категории</div>
        <div class="panel-body">
            <div class="list-group">
                <a href="<c:url value="/"/>" class="list-group-item">Все</a>
                <c:forEach var="goods" items="${categories}">
                    <a href="<c:url value="/categories/${goods.id}"/>"
                       class="list-group-item">${goods.name}</a>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

<div class="col-xs-9 goodsCatalog">
    <div class="row">
        <c:forEach var="goods" items="${goodsList}">
            <div class="col-xs-4 text-center">
                <div class="thumbnail">
                    <a href="<c:url value="/goods/${goods.id}"/>">
                        <img src="data:image/${goods.image.ext};base64,${goods.image.base64}">
                    </a>
                    <div class="caption">
                        <h5>
                            <a href="<c:url value="/goods/${goods.id}"/>">
                                ${goods.name}
                            </a>
                        </h5>
                        <p>
                            <h4>${goods.price} т</h4>
                        </p>
                        <p>
                            <button class="buy btn btn-danger" goodsId="${goods.id}">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Купить
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${goodsLog.totalPages > 0}">
        <nav class="text-right">
            <c:set var="currentUrl"
                   value="/${requestScope['javax.servlet.forward.request_uri'].replaceAll('/pages/[0-9]+', '')}"/>
            <c:set var="baseURL" value="${currentUrl.replaceAll('/{2,}', '/')}"/>

            <c:url var="firstUrl" value="/pages/1" context="${baseURL}"/>
            <c:url var="lastUrl" value="/pages/${goodsLog.totalPages}" context="${baseURL}"/>
            <c:url var="prevUrl" value="/pages/${currentIndex - 1}" context="${baseURL}"/>
            <c:url var="nextUrl" value="/pages/${currentIndex + 1}" context="${baseURL}"/>

            <ul class="pagination pagination-sm">
                <c:choose>
                    <c:when test="${currentIndex == 1}">
                        <li class="disabled"><a href="#">&Lt;</a></li>
                        <li class="disabled"><a href="#">&lt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${firstUrl}">&Lt;</a></li>
                        <li><a href="${prevUrl}">&lt;</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                    <c:url var="pageUrl" value="/pages/${i}" context="${baseURL}"/>
                    <c:choose>
                        <c:when test="${i == currentIndex}">
                            <li class="active"><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${currentIndex == goodsLog.totalPages}">
                        <li class="disabled"><a href="#">&gt;</a></li>
                        <li class="disabled"><a href="#">&Gt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${nextUrl}">&gt;</a></li>
                        <li><a href="${lastUrl}">&Gt;</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </c:if>

</div>



