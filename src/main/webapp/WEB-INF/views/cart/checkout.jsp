<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <h3 class="text-center">Оформление заказа</h3>
    <c:if test="${empty client}">
        <h5>Только авторизованные пользователи могут оформлять заказ</h5>
        <h4><a href="<c:url value="/auth/login"/>">Авторизоваться</a></h4>
    </c:if>
    <c:if test="${not empty client}">
        <spring:form modelAttribute="cart" method="post" cssClass="checkout">
            <div class="col-xs-6 text-right">
                <strong>Ф.И.О.</strong>
            </div>
            <div class="col-xs-5 text-left">
                 ${client.fio}
            </div>

            <div class="col-xs-6 text-right">
                <strong>Адрес</strong>
            </div>
            <div class="col-xs-5 text-left">
                 ${client.address}
            </div>

            <div class="col-xs-6 text-right">
                <strong>Телефон</strong>
            </div>
            <div class="col-xs-5 text-left">
                ${client.phone}
            </div>

            <div class="col-xs-6 text-right">
                <strong>Адрес доставки</strong>
            </div>
            <div class="col-xs-5 text-left">
                <spring:textarea cssClass="form-control input-sm" path="shippingAddress" cols="30" rows="3"></spring:textarea>
                <span class="text-danger"><spring:errors path="shippingAddress"/></span>
            </div>

            <div class="col-xs-6 text-right">
                <strong>К оплате</strong>
            </div>
            <div class="col-xs-5 text-left">
                ${cart.totalPrice}
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-default">Оформить заказ</button>
            </div>
        </spring:form>
    </c:if>
</div>
