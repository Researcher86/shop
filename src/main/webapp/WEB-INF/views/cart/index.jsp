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
            <th class="text-center">Наименование</th>
            <th class="text-center">Стоимость</th>
            <th class="text-center">Количество</th>
            <th class="text-center">Сумма</th>
            <th class="text-center"></th>

            <c:forEach var="order" items="${cart.orders}" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>${order.goods.name}</td>
                    <td>${order.goods.price}</td>
                    <td style="width: 150px">
                        <input class="form-control text-center" type="number" value="${order.goodsCount}" goodsId="${order.goods.id}">
                    </td>
                    <td>${order.totalPrice}</td>
                    <td>
                        <button class="btn btn-danger btn-sm" goodsId="${order.goods.id}"><i class="glyphicon glyphicon-trash"></i> Удалить</button>
                    </td>
                </tr>
            </c:forEach>

            <tr class="warning">
                <td colspan="3"></td>
                <td><strong>К оплате:</strong></td>
                <td><strong>${cart.totalPrice}</strong></td>
                <td></td>
            </tr>
        </table>
        <div>
            <button class="btn btn-default pull-right">Оформить заказ</button>
        </div>
    </c:if>
</div>
