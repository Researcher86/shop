<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 08.12.2015
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">

    <link rel="icon" href="<c:url value="/resources/img/login-icon.png"/>">
</head>
<body>

<div class="container">
    <div class="row">
        <form class="form-group-sm">
            <input class="form-control" type="text" value="" placeholder="login">
            <input class="form-control" type="password" value="" placeholder="password">
        </form>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>
