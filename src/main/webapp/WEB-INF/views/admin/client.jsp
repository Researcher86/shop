<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="page-header">Информация о клиенте</h3>

<dl class="dl-horizontal">
    <dt>Ф.И.О.</dt>
    <dd>${client.fio}</dd>

    <dt>Телефон</dt>
    <dd>${client.phone}</dd>

    <dt>Адрес</dt>
    <dd>${client.address}</dd>

    <dt>E-mail</dt>
    <dd>${client.email}</dd>
</dl>



