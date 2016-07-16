<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Товары</h3>

<table class="table table-hover table-bordered categoriesTable">
    <th>№</th>
    <th>Название</th>
    <th style="width: 150px;text-align: center">Категория</th>
    <th></th>

    <c:forEach var="goods" items="${goodsList}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${goods.name}</td>
            <td style="text-align: center">${goods.category.name}</td>
            <td>
                <a href="<c:url value="/admin/goods/${goods.id}"/>" class="btn btn-xs btn-default" title="Редактировать">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>
                <button goodsId="${goods.id}" class="btn btn-xs btn-danger deleteCategoryButton" title="Удалить">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
