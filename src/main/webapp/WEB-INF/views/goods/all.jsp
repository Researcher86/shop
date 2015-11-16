<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 05.10.2015
  Time: 10:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
</head>
<body>
<h2>Товары</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th></th>
    </tr>
    <c:forEach var="goods" items="${goodsAll}">
        <tr>
            <td>${goods.name}</td>
            <td>${goods.category.name}</td>
            <td>${goods.price}</td>
            <td>
                <a href="<c:url value="goods/${goods.id}" />">Details</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
