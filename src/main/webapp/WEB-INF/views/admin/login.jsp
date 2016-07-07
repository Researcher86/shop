<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="icon" href="<c:url value="/resources/img/admin.png"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
</head>
<body onload="document.loginForm.username.focus();">

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3><img src="<c:url value="/resources/img/login-icon.png"/>" height="32"> Авторизация</h3>
            <hr>
            <form name="loginForm" method="POST">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <span class="glyphicon glyphicon-ban-circle"></span> ${error}
                    </div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="alert alert-info alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <span class="glyphicon glyphicon-exclamation-sign"></span> ${msg}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="login"><span class="glyphicon glyphicon-user"></span> Логин</label>
                    <input id="login" class="form-control" type="text" name="username" value="xor"/>
                </div>

                <div class="form-group">
                    <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Пароль</label>
                    <input id="password" class="form-control" type="password" name="password" value="123456"/>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div class="col-md-8">
                        <label class="checkbox-inline"><input type="checkbox" value="">Запомнить меня</label>
                    </div>

                    <div class="col-md-4 text-right">
                        <button type="submit" class="btn btn-default btn-block"><span class="glyphicon glyphicon-off"></span> Вход</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>