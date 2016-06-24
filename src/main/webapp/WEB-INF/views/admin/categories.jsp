<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Категории</h3>

<table class="table table-hover table-bordered categoriesTable">
    <th>№</th>
    <th>Название</th>
    <th style="width: 150px;text-align: center">Кол-во товаров</th>
    <th></th>

    <c:forEach var="category" items="${categories}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${category.name}</td>
            <td style="text-align: center">${category.goodsList.size()}</td>
            <td>
                <a href="<c:url value="/admin/categories/${category.id}"/>" class="btn btn-xs btn-default" title="Редактировать">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>
                <button goodsId="${category.id}" class="btn btn-xs btn-danger deleteCategoryButton" title="Удалить">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
