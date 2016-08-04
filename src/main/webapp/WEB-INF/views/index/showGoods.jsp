<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-12">
    <div class="col-xs-4">
        <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="250"
             border="0" alt="">
    </div>

    <div class="col-xs-8">
        <dl class="dl-horizontal">
            <dt>Наименование:</dt>
            <dd>${goods.name}</dd>

            <dt>Категория:</dt>
            <dd>${goods.category.name}</dd>

            <dt>Характеристики:</dt>
            <dd>
                ${goods.description.replaceAll('\\n', '<br />')}
            </dd>

            <dt>Цена:</dt>
            <dd>
                <span style="font-size: 18px">${goods.price} т</span>
            </dd>

            <dt></dt>
            <dd>
                <button class="buy btn btn-danger" goodsId="${goods.id}">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Купить
                </button>
            </dd>
        </dl>
    </div>
    <div class="col-xs-12">
        <br>
    </div>
    <div class="col-xs-12">
        <h3>Комментарии: <span class="badge">${goods.comments.size()}</span></h3>
        <h4 class="text-success">${msg}</h4>
    </div>

    <div class="col-xs-12">
        <hr>
    </div>

    <div class="col-xs-12">
        <c:if test="${not empty client}">
            <spring:form modelAttribute="comment" method="post">
                <div class="form-group">
                    <spring:textarea path="text" data-provide="markdown" rows="10"></spring:textarea>
                    <spring:errors path="text" cssClass="text-danger"/>
                    <input type="hidden" name="client.id" value="${client.id}"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Отправить</button>
                </div>
            </spring:form>
        </c:if>
        <c:if test="${empty client}">
            <div class="text-center">
                <h5>Только авторизованные пользователи могут оставлять комментарии</h5>
                <h4><a href="<c:url value="/auth/login"/>">Авторизоваться</a></h4>
            </div>
        </c:if>
    </div>

    <c:if test="${goods.comments.size() != 0}">
        <div class="col-xs-12">
            <hr>
        </div>
    </c:if>

    <div class="col-xs-12 comments">
        <c:forEach var="comment" items="${goods.comments}">
            <div class="col-xs-12 comment">
                <div class="col-xs-1 row text-center">
                    <img class="img-circle" width="50" src="<c:url value="/resources/img/anonymous.png"/>" alt="Фото">
                </div>
                <div class="col-xs-11">
                    <div class="col-xs-12">
                        <c:out value="${comment.text}"/>
                    </div>
                    <div class="col-xs-12 row text-muted small ">
                        <i class="glyphicon glyphicon-user"></i> ${comment.client.fio}&nbsp;&nbsp;
                        <i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${comment.date.time}" type="date" pattern="dd.MM.yyyy HH:MM"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
