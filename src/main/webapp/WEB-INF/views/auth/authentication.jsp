<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <div class="col-xs-3"></div>
    <div class="col-xs-6">
        <span class="text-danger">${error}</span>
        <spring:form action="/auth/login" method="post">
            <div class="form-group">
                <input class="form-control" type="email" name="email" placeholder="Почтовый ящик">
                <input class="form-control" type="password" name="password" placeholder="Пароль">
            </div>
            <div class="form-group row">
                <div class="col-xs-6 text-center">
                    <a href="<c:url value="/auth/registration"/>">Регистрация</a>
                </div>
                <div class="col-xs-6">
                    <button class="form-control" type="submit">
                        Вход
                    </button>
                </div>
            </div>
        </spring:form>
    </div>
    <div class="col-xs-3"></div>
</div>

