<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Клиенты</h3>

<table class="table table-hover input-sm table-bordered clientsTable">
    <th>№</th>
    <th>Ф.И.О.</th>
    <th>Телефон</th>
    <th>Адрес</th>
    <th></th>

    <c:if test="${empty clients}">
        <tr>
            <td>1</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
        </tr>
    </c:if>

    <c:forEach var="client" items="${clients}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td><c:out value="${client.fio}"/></td>
            <td><c:out value="${client.phone}"/></td>
            <td><c:out value="${client.address}"/></td>
            <td>
                <a href="<c:url value="/admin/clients/${client.id}"/>" class="btn btn-xs btn-default" title="Подробно">
                    <i class="glyphicon glyphicon-list-alt"></i>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
