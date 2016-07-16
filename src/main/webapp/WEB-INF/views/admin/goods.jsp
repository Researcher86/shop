<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Редактировать товар</h3>

<spring:form modelAttribute="goods" action="?${_csrf.parameterName}=${_csrf.token}" method="post" cssClass="navbar-form" enctype="multipart/form-data">
    <div class="col-xs-4">
        <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="250" border="0" alt="">
        <input type="file" name="file" />
    </div>

    <div class="col-xs-8">
        <div class="form-group-sm">
            <spring:input path="name" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group-sm">
            <spring:textarea path="description" cssClass="form-control input-sm" cols="5"/>
        </div>
        <div class="form-group-sm">
            <spring:input path="price" cssClass="form-control input-sm"/>
        </div>
        <div class="form-group-sm">
            <spring:select path="category.id" items="${categories}" itemLabel="name" itemValue="id" cssClass="form-control input-sm"/>
        </div>
        <spring:errors path="*" cssClass="text-danger"/>
        <button class="btn btn-sm btn-default" type="submit">Сохранить</button>
    </div>

</spring:form>


