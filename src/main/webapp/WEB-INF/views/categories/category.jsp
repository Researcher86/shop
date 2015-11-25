<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 16.11.2015
  Time: 11:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
</head>
<body>
<h2>${category.name}</h2>
<a href="javascript:window.history.back()">Back</a>

<h3>Товары</h3>
<table>
    <c:forEach var="goods" items="${category.goodsList}">
        <tr>
            <td>${goods.name}</td>
            <td>
                <a href="<c:url value="/goods/${goods.id}" />">Details</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
