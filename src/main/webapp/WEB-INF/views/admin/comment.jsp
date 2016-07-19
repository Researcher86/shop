<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Редактировать комментарий</h3>

<div class="col-xs-12">
    <spring:form modelAttribute="comment" method="post">
        <div class="form-group">
            <spring:label path="text">Текст</spring:label>
            <spring:textarea path="text" data-provide="markdown" rows="10"/>
        </div>
        <div class="form-group">
            <label>Автор</label>
            <span>${comment.client.fio}</span>
        </div>
        <div class="form-group">
            <spring:checkbox path="active"/>
            <label for="active1">Активировать</label>
        </div>
        <div class="form-group">
            <spring:errors path="*" cssClass="text-danger"/>
        </div>

        <button class="btn btn-sm btn-default" type="submit">Сохранить</button>
    </spring:form>
</div>

