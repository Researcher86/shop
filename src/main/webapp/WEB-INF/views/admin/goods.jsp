<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Редактировать товар</h3>

<spring:form modelAttribute="goods" action="?${_csrf.parameterName}=${_csrf.token}" method="post"
             enctype="multipart/form-data">
    <div class="col-xs-4 text-center">
        <div class="form-group">
            <c:if test="${not empty goods.image}">
                <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="195" border="0" alt="">
            </c:if>
            <c:if test="${empty goods.image}">
                <img src="<c:url value="/resources/img/nophoto.jpg"/>" height="195" border="0" alt="">
            </c:if>
        </div>
    </div>

    <div class="col-xs-8">
        <div class="form-group">
            <spring:label path="name">Наименование</spring:label>
            <spring:input path="name" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group">
            <spring:label path="description">Краткое описание</spring:label>
            <spring:textarea path="description" cssClass="form-control input-sm" cssStyle="height: 150px; resize: vertical"/>
        </div>
        <div class="form-group">
            <spring:label path="price">Цена</spring:label>
            <spring:input path="price" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group">
            <spring:label path="category.id">Категория</spring:label>
            <spring:select path="category.id" items="${categories}" itemLabel="name" itemValue="id"
                           cssClass="form-control input-sm"/>
        </div>

        <div class="form-group">
            <label>Изображение</label>
            <div class="navbar-form" style="padding: 0">
                <label class="btn btn-sm btn-primary" for="my-file-selector">
                    <input id="my-file-selector" type="file" name="file" style="display:none;"
                           onchange="$('#upload-file-info').val($(this).val());">
                    Обзор
                </label>
                <input class="form-control input-sm" id="upload-file-info" disabled placeholder="Файл не выбран"/>
            </div>
        </div>

        <div class="form-group">
            <spring:errors path="*" cssClass="text-danger"/>
        </div>

        <button class="btn btn-sm btn-default" type="submit">Сохранить</button>
    </div>

</spring:form>


