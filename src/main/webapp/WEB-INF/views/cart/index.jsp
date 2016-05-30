<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <c:if test="${true}">
        <table class="table table-bordered table-hover text-center input-sm">
            <th class="text-center">№</th>
            <th class="text-center">Наименование</th>
            <th class="text-center">Стоимость</th>
            <th class="text-center">Количество</th>
            <th class="text-center">Сумма</th>
            <th class="text-center"></th>
            <tr>
                <td>1</td>
                <td>Бумага</td>
                <td>5555</td>
                <td style="width: 150px">
                    <input class="form-control text-center" type="number">
                </td>
                <td>565656</td>
                <td>
                    <a href="javascript:void(0)">Удалить</a>
                </td>
            </tr>
            <tr>
                <td>1</td>
                <td>Бумага</td>
                <td>5555</td>
                <td style="width: 100px">
                    <input class="form-control text-center" type="number">
                </td>
                <td>565656</td>
                <td>
                    <a href="javascript:void(0)">Удалить</a>
                </td>
            </tr>
            <tr class="warning">
                <td colspan="3"></td>
                <td><strong>К оплате:</strong></td>
                <td><strong>999999</strong></td>
                <td></td>
            </tr>
        </table>
        <div>
            <button class="btn btn-default pull-right">Оформить заказ</button>
        </div>
    </c:if>
    <c:if test="${false}">
        <div class="text-center">
            <h2>Моя корзина</h2>
            <p>
                Ваша корзина сейчас пуста. <br>
                Воспользуйтесь нашим каталогом, чтобы ее заполнить. <br>
                Помните, что полная корзина олицетворяет изобилие!
            </p>
        </div>
    </c:if>
</div>
