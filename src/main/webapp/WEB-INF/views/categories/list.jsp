<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Comp
  Date: 25.11.2015
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
</head>
<body>
<h2>Категории товаров</h2>
<a href="/categories/new">Добавить</a>
<table>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.name}</td>
            <td>
                <a href="<c:url value="categories/${category.id}" />">Details</a>
                <a href="<c:url value="categories/edit/${category.id}" />">Edit</a>
                <a href="<c:url value="categories/delete/${category.id}" />">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
