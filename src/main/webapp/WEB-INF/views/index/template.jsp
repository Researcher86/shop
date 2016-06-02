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
</head>
<body>

<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-3 logo"></div>

            <div class="col-xs-6">
                <div class="search">
                    <spring:form action="/" method="post">
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
                    <strong><c:out value="${empty cart.goodsCount ? 0 : cart.goodsCount}"/></strong><span> товаров</span><br>
                    <span>на сумму </span><strong>${empty cart.totalPrice ? 0 : cart.totalPrice} тг.</strong>

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
    </div>
</header>

<div class="container">
    <div class="row">
        <jsp:include page="${content}"/>
        <div class="col-xs-12">
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        E-Shop 2015
    </div>
</footer>

<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-markdown.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-markdown.ru.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>

