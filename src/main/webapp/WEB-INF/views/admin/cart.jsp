<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Информация о заказе: ${cart.id}</h3>

<dl class="dl-horizontal">
    <dt>Ф.И.О.</dt>
    <dd>${cart.client.fio}</dd>

    <dt>Адрес доставки</dt>
    <dd>${cart.shippingAddress}</dd>

    <dt>Дата заказа</dt>
    <dd><fmt:formatDate value="${cart.orderDate.time}" type="date" pattern="dd.MM.yyyy HH:MM"/></dd>
</dl>

<table id="cartItems" class="table table-hover text-center input-sm">
    <th class="text-center">№</th>
    <th class="text-center">Изображение</th>
    <th class="text-left" style="width: 500px">Наименование</th>
    <th class="text-center">Стоимость</th>
    <th class="text-center">Количество</th>
    <th class="text-center">Сумма</th>

    <c:forEach var="order" items="${cart.orders}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>
                <img src="data:image/${order.goods.image.ext};base64,${order.goods.image.base64}" height="30" border="0" alt="">
            </td>
            <td class="text-left">${order.goods.name}</td>
            <td>${order.goods.price}</td>
            <td style="width: 120px">
                ${order.amount}
            </td>
            <td>${order.totalPrice}</td>
        </tr>
    </c:forEach>

    <tr class="warning">
        <td colspan="4"></td>
        <td><strong>К оплате:</strong></td>
        <td><strong>${cart.totalPrice}</strong></td>
    </tr>
</table>

<c:url var="url" value="/admin/carts/${cart.id}/processed"/>

<spring:form action="${url}" method="post">
    <button type="submit" class="btn btn-success pull-right">
        <i class="glyphicon glyphicon-ok"></i> Обработан
    </button>
</spring:form>


