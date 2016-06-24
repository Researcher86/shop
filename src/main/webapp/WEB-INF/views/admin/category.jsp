<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Редактировать категорию</h3>

<div class="col-xs-6">
    <spring:form method="post" cssClass="navbar-form">
        <div class="form-group-sm">
            <input class="form-control" type="text" name="name" value="${category.name}">
            <button class="btn btn-sm btn-default" type="submit">Сохранить</button>
        </div>
        <span class="text-danger">${error}</span>
    </spring:form>
</div>

<div class="col-xs-6">
    <table class="table table-hover table-bordered">
        <th>№</th>
        <th>Название</th>

        <c:forEach var="goods" items="${category.goodsList}" varStatus="count">
            <tr>
                <td>${count.count}</td>
                <td>${goods.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>


