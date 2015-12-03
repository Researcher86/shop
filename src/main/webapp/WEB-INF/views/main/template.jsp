<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">

    <link rel="icon" href="<c:url value="/resources/img/eshop_icon.png"/>">
</head>
<body>

<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-3 logo"></div>

            <div class="col-xs-6">
                <div class="search">
                    <div class="input-group has-error">
                        <input type="text" class="form-control" placeholder="Поиск">
                        <span class="input-group-btn">
                            <button class="btn btn-danger glyphicon glyphicon-search" type="button"/>
                        </span>
                    </div>
                </div>
            </div>

            <div class="col-xs-3">
                <div class="cart text-left">
                    <a href="javascript:void(0);" class="yen-bs-count_link">В корзине</a>
                    <strong>0</strong><span> товаров</span><br>
                    <span>на сумму </span><strong>0 тг.</strong>

                    <a href="javascript:void(0);" class="yen-bs-count_link" title="Корзина">
                        <div class="icon"></div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row headerMenu">
            <div class="col-xs-2">
                <a href="#">Главная</a>
            </div>

            <div class="col-xs-2">
                <a href="#">О компании</a>
            </div>

            <div class="col-xs-2">
                <a href="#">Оплата и доставка</a>
            </div>

            <div class="col-xs-2">
                <a href="#">Контакты</a>
            </div>

            <div class="col-xs-2">
                <a href="#">Прайс-лист</a>
            </div>

            <div class="col-xs-2">
                <a href="#">Отзывы</a>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <div class="row">
        <jsp:include page="${content}.jsp"/>
    </div>
</div>

<footer>
    <div class="container">
        E-Shop 2015
    </div>
</footer>

<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>

