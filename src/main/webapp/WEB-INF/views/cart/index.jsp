<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <c:if test="${empty cart.orders}">
        <div class="text-center">
            <h2>Моя корзина</h2>
            <p>
                Ваша корзина сейчас пуста. <br>
                Воспользуйтесь нашим каталогом, чтобы ее заполнить. <br>
                Помните, что полная корзина олицетворяет изобилие!
            </p>
        </div>
    </c:if>
    <c:if test="${not empty cart.orders}">
        <table id="cartItems" class="table table-bordered table-hover text-center input-sm">
            <th class="text-center">№</th>
            <th class="text-center">Изображение</th>
            <th class="text-left" style="width: 500px">Наименование</th>
            <th class="text-center">Стоимость</th>
            <th class="text-center">Количество</th>
            <th class="text-center">Сумма</th>
            <th class="text-center"></th>

            <c:forEach var="order" items="${cart.orders}" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>
                        <img src="data:image/${order.goods.image.ext};base64,${order.goods.image.base64}" height="30" border="0" alt="">
                    </td>
                    <td class="text-left">${order.goods.name}</td>
                    <td>${order.goods.price} т</td>
                    <td style="width: 120px">
                        <input class="form-control text-center input-sm" type="number" value="${order.amount}" goodsId="${order.goods.id}">
                    </td>
                    <td>${order.totalPrice} т</td>
                    <td>
                        <button class="btn btn-danger btn-xs glyphicon glyphicon-trash" goodsId="${order.goods.id}"></button>
                    </td>
                </tr>
            </c:forEach>

            <tr class="warning">
                <td colspan="4"></td>
                <td><strong>К оплате:</strong></td>
                <td><strong>${cart.totalPrice} т</strong></td>
                <td></td>
            </tr>
        </table>
        <div>
            <c:if test="${empty client}">
                <div class="text-center">
                    <h5>Только авторизованные пользователи могут оставлять комментарии</h5>
                    <h4><a href="<c:url value="/auth/login"/>">Авторизоваться</a></h4>
                </div>
            </c:if>
            <c:if test="${not empty client}">
                <a class="btn btn-default pull-right" href="<c:url value="/cart/checkout"/>">Оформить заказ</a>
            </c:if>
        </div>
    </c:if>
</div>
