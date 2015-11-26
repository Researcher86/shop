<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 25.11.2015
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Shop</title>
</head>
<body>
<form:form modelAttribute="goods" action="/goods/save">
    <form:hidden path="id"/>
    <form:input path="name"/> <br>
    <form:input path="price"/> <br>
    <form:input path="description"/> <br>
    <form:select path="category.id" items="${categories}" itemValue="id" itemLabel="name"/> <br>
    <input type="submit" value="Сохранить">
</form:form>
<hr>
<a href="/goods">Back</a>
</body>
</html>
