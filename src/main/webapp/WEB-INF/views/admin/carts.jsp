<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Заказы</h3>

<table class="table table-hover input-sm table-bordered cartsTable">
    <th>№</th>
    <th>Клиент</th>
    <th>Адрес доставки</th>
    <th class="text-center">Сумма</th>
    <th style="width: 150px;text-align: center">Дата заказа</th>
    <th></th>

    <c:if test="${empty carts}">
        <tr>
            <td>1</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
        </tr>
    </c:if>

    <c:forEach var="cart" items="${carts}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${cart.client.fio}</td>
            <td><c:out value="${cart.shippingAddress}"/></td>
            <td class="text-center">${cart.totalPrice}</td>
            <td class="text-center">
               <fmt:formatDate value="${cart.orderDate.time}" type="date" pattern="dd.MM.yyyy HH:MM"/>
            </td>
            <td>
                <a href="<c:url value="/admin/carts/${cart.id}"/>" class="btn btn-xs btn-default" title="Подробно">
                    <i class="glyphicon glyphicon-list-alt"></i>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
