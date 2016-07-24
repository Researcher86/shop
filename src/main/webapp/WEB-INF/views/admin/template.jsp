<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>E-Shop Admin page</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" href="<c:url value="/resources/img/admin.png"/>" type="image/x-icon">
    <link rel="shortcut icon" href="<c:url value="/resources/img/admin.png"/>" type="image/x-icon">

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-markdown.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/admin.css"/>">

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="baseURL" content="<c:url value="/"/>"/>
</head>

<body>

<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">
                <img width="24" title="E-Shop" alt="Brand" src="<c:url value="/resources/img/eshop_icon.png"/>">
            </a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <form action="<c:url value="/admin/logout"/>" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:document.getElementById('logoutForm').submit()">Выход</a></li>
            </ul>
        </div>
    </nav>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="<c:url value="/admin/categories"/>"><i class="glyphicon glyphicon-menu-hamburger"></i> Категории<span class="sr-only">(current)</span></a></li>
                <li><a href="<c:url value="/admin/goods"/>"><i class="glyphicon glyphicon-apple"></i> Товары</a></li>
                <li><a href="<c:url value="/admin/carts"/>"><i class="glyphicon glyphicon-shopping-cart"></i> Заказы</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-user"></i> Клиенты</a></li>
                <li><a href="<c:url value="/admin/comments"/>"><i class="glyphicon glyphicon-comment"></i> Комментарии</a></li>
            </ul>
        </div>
        <div class="col-sm-10">
            <jsp:include page="${content}"/>
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
<script src="<c:url value="/resources/js/settings.js"/>"></script>
<script src="<c:url value="/resources/js/admin.js"/>"></script>
</body>
</html>
