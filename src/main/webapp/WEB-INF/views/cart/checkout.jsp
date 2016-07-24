<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="text-center">Оформление заказа</h3>
<c:if test="${empty client}">
    <h5>Только авторизованные пользователи могут оформлять заказ</h5>
    <h4><a href="<c:url value="/auth/login"/>">Авторизоваться</a></h4>
</c:if>
<c:if test="${not empty client}">
    <spring:form modelAttribute="cart" method="post" cssClass="checkout">
        <dl class="dl-horizontal col-xs-6 col-xs-offset-3">
            <dt>Ф.И.О.</dt>
            <dd>${client.fio}</dd>

            <dt>Адрес</dt>
            <dd>${client.address}</dd>

            <dt>Телефон</dt>
            <dd>${client.phone}</dd>

            <dt>Адрес доставки</dt>
            <dd>
                <spring:textarea cssClass="form-control input-sm" path="shippingAddress" cols="30" rows="3"></spring:textarea>
                <span class="text-danger"><spring:errors path="shippingAddress"/></span>
            </dd>

            <dt>К оплате</dt>
            <dd>${cart.totalPrice}</dd>
        </dl>

        <div class="clearfix"></div>

        <div class="text-center">
            <button type="submit" class="btn btn-default">Оформить заказ</button>
        </div>
    </spring:form>
</c:if>

