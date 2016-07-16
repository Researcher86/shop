<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Редактировать товар</h3>

<spring:form modelAttribute="goods" action="?${_csrf.parameterName}=${_csrf.token}" method="post"
             enctype="multipart/form-data">
    <div class="col-xs-4 text-center">
        <div class="form-group">
            <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="195" border="0" alt="">
        </div>
    </div>

    <div class="col-xs-8">
        <div class="form-group">
            <spring:input path="name" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group">
            <spring:textarea path="description" cssClass="form-control input-sm" cols="5"/>
        </div>
        <div class="form-group">
            <spring:input path="price" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group">
            <spring:select path="category.id" items="${categories}" itemLabel="name" itemValue="id"
                           cssClass="form-control input-sm"/>
        </div>

        <div class="form-group">
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


