<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 01.12.2015
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-markdown.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">

    <link rel="icon" href="<c:url value="/resources/img/eshop_icon.png"/>" type="image/x-icon">
    <link rel="shortcut icon" href="<c:url value="/resources/img/eshop_icon.png"/>" type="image/x-icon">

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="baseURL" content="<c:url value="/"/>"/>
</head>
<body>

<div class="container body">
    <header>
        <div class="row login">
            <div class="col-xs-4 pull-right text-right">
                <c:if test="${empty client.email}">
                    <a href="<c:url value="/auth/registration"/>">Регистрация</a>
                    <span>|</span>
                    <a href="<c:url value="/auth/login"/>">Вход</a>
                </c:if>
                <c:if test="${not empty client.email}">
                    <span>${client.email}</span>
                    <span>|</span>
                    <a href="<c:url value="/auth/logout"/>">Выход</a>
                </c:if>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row">
            <div class="col-xs-3 logo"></div>

            <div class="col-xs-6">
                <div class="search">
                    <c:url value="/" var="search"/>
                    <spring:form action="${search}" method="post">
                        <div class="input-group has-error">
                            <input type="text" class="form-control" name="str" placeholder="Поиск">
                            <span class="input-group-btn">
                                <button class="btn btn-danger glyphicon glyphicon-search" type="submit"/>
                            </span>
                        </div>
                    </spring:form>
                </div>
            </div>

            <div class="col-xs-3">
                <div class="cart text-left">
                    <a href="<c:url value="/cart"/>" class="yen-bs-count_link">В корзине</a>
                    <strong><c:out
                            value="${empty cart.totalAmount ? 0 : cart.totalAmount}"/></strong><span> товаров</span><br>
                    <span>на сумму </span><strong>${empty cart.totalPrice ? 0 : cart.totalPrice} т</strong>

                    <a href="<c:url value="/cart"/>" class="yen-bs-count_link" title="Корзина">
                        <div class="icon"></div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="btn-group btn-group-justified headerMenu">
                <a href="<c:url value="/"/>" class="btn btn-danger">Главная</a>
                <a href="<c:url value="/aboutCompany"/>" class="btn btn-danger">О компании</a>
                <a href="<c:url value="/shipping"/>" class="btn btn-danger">Доставка</a>
                <a href="<c:url value="/contacts"/>" class="btn btn-danger">Контакты</a>
                <a href="<c:url value="/priceList"/>" class="btn btn-danger">Прайс-лист</a>
            </div>
        </div>
    </header>

    <div class="row">
        <jsp:include page="${content}"/>
    </div>
</div>

<footer>
    <div class="container">
        E-Shop 2015-2016
    </div>
</footer>

<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-markdown.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-markdown.ru.js"/>"></script>
<script src="<c:url value="/resources/js/settings.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>

