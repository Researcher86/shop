<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <hr>
    </div>
    <div class="col-xs-12">
        <h3>Комментарии: <span class="badge">${goods.comments.size()}</span></h3>
    </div>
    <div class="col-xs-12">
        <form>
            <div class="form-group">
                <textarea name="content" data-provide="markdown" rows="5" ></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-default">Отправить</button>
            </div>
        </form>
    </div>

    <div class="col-xs-12">
        <c:forEach var="comment" items="${goods.comments}">
            <div class="col-xs-12">
                ${comment.text}
            </div>
        </c:forEach>
    </div>
</div>
