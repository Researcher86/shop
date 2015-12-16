<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="icon" href="<c:url value="/resources/img/login-icon.png"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
</head>
<body onload="document.loginForm.username.focus();">

<div class="container">
    <form name="loginForm" method="POST">
        <c:if test="${not empty error}">
            <div class="bg-danger">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="bg-info">${msg}</div>
        </c:if>
        <div class="form-group">
            <label for="login">Login</label>
            <input id="login" class="form-control" type="text" name="username" value="xor"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" class="form-control" type="password" name="password" value="123456"/>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input class="btn btn-default" name="submit" type="submit" value="Вход" />
    </form>
</div>

</body>
</html>