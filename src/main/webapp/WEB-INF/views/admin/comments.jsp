<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Комментарии</h3>

<table class="table table-hover input-sm table-bordered commentsTable">
    <th>№</th>
    <th>Текст</th>
    <th>Автор</th>
    <th style="width: 150px;text-align: center">Дата</th>
    <th></th>

    <c:if test="${empty comments}">
        <tr>
            <td>1</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
        </tr>
    </c:if>

    <c:forEach var="comment" items="${comments}" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${comment.text}</td>
            <td>${comment.client.fio}</td>
            <td style="text-align: center">
               <fmt:formatDate value="${comment.date.time}" type="date" pattern="dd.MM.yyyy HH:MM"/>
            </td>
            <td>
                <a href="<c:url value="/admin/comments/${comment.id}"/>" class="btn btn-xs btn-default" title="Редактировать">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>
                <button commentId="${comment.id}" class="btn btn-xs btn-danger deleteCommentButton" title="Удалить">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
