<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <div class="col-xs-4 col-xs-offset-4">
        <c:url var="login" value="/auth/login"/>
        <spring:form action="${login}" method="post">
            <div class="form-group">
                <label>Почтовый ящик</label>
                <input class="form-control" type="email" name="email">
            </div>
            <div class="form-group">
                <label>Пароль</label>
                <input class="form-control" type="password" name="password">

                <span class="text-danger">${error}</span>
            </div>
            <div>
                <button class="btn btn-default" type="submit">
                    <span class="glyphicon glyphicon-off"></span> Вход
                </button>
            </div>
        </spring:form>
    </div>
</div>

