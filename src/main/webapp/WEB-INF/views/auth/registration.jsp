<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12">
    <div class="col-xs-3"></div>
    <div class="col-xs-6">
        <spring:form modelAttribute="client">
            <div class="form-group">
                <label for="fio">Ф.И.О.</label>
                <spring:input path="fio" cssClass="form-control"/>
                <spring:errors path="fio" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="fio">Телефон</label>
                <spring:input path="phone" cssClass="form-control"/>
                <spring:errors path="phone" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="fio">Адрес</label>
                <spring:input path="address" cssClass="form-control"/>
                <spring:errors path="address" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="fio">Почтовый ящик</label>
                <spring:input path="email" cssClass="form-control"/>
                <spring:errors path="email" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="fio">Пароль</label>
                <spring:password path="password" cssClass="form-control"/>
                <spring:errors path="password" cssClass="text-danger"/>
            </div>
            <div class="form-group pull-right">
                <button type="submit" class="btn btn-default">
                    Зарегистрироваться
                </button>
            </div>
        </spring:form>
    </div>
    <div class="col-xs-3"></div>
</div>

