<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Категории</h3>

<spring:form method="post" cssClass="navbar-form" cssStyle="padding: 0">
    <div class="form-group-sm">
        <input class="form-control" type="text" name="name" value="${category.name}" placeholder="Название">
        <button class="btn btn-sm btn-default" type="submit">Добавить</button>
    </div>
    <span class="text-danger">${error}</span>
</spring:form>

<table class="table table-hover input-sm table-bordered categoriesTable">
    <th>№</th>
    <th>Название</th>
    <th style="width: 150px;text-align: center">Кол-во товаров</th>
    <th></th>

    <c:if test="${empty categories}">
        <tr>
            <td>1</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
        </tr>
    </c:if>

    <c:forEach var="goods" items="${categories}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${goods.name}</td>
            <td style="text-align: center">${goods.goodsList.size()}</td>
            <td>
                <a href="<c:url value="/admin/categories/${goods.id}"/>" class="btn btn-xs btn-default" title="Редактировать">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>
                <button categoryId="${goods.id}" class="btn btn-xs btn-danger deleteCategoryButton" title="Удалить">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
