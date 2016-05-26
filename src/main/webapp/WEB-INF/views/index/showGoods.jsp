<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-12">
    <div class="col-xs-4">
        <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="250"
             border="0" alt="">
    </div>

    <div class="col-xs-8">
        <h4>${goods.name}</h4>
        <h5>${goods.category.name}</h5>
        <h5>${goods.description}</h5>
        <strong>Код товара: </strong><span>15364</span>
        <a href="javascript:void(0);">
            <button class="btn btn-danger">
                <span class="glyphicon glyphicon-shopping-cart"></span> Купить
            </button>
        </a>
    </div>
    <div class="col-xs-12">
        <br>
    </div>
    <div class="col-xs-12">
        <h3>Комментарии: <span class="badge">${goods.comments.size()}</span></h3>
    </div>

    <div class="col-xs-12">
        <hr>
    </div>
    <div class="col-xs-12 comments">
        <c:forEach var="comment" items="${goods.comments}">
            <div class="col-xs-12 comment">
                <div class="col-xs-1 row text-center">
                    <img class="img-circle" width="50" src="<c:url value="/resources/img/anonymous.png"/>" alt="Фото">
                </div>
                <div class="col-xs-11">
                    <div class="col-xs-12">
                        ${comment.text}
                    </div>
                    <div class="col-xs-12 row text-muted small ">
                        <i class="glyphicon glyphicon-user"></i> ${comment.client.fio}&nbsp;&nbsp;
                        <i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${comment.date.time}" type="date" pattern="dd.MM.yyyy HH:MM"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${goods.comments.size() != 0}">
        <div class="col-xs-12">
            <hr>
        </div>
    </c:if>

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
            <h5>Только авторизованные пользователи могут оставлять комментарии</h5>
            <h4><a href="<c:url value="/auth/login"/>">Авторизоваться</a></h4>
        </c:if>
    </div>
</div>
