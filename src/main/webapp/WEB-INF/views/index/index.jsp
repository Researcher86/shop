<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 01.12.2015
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-3 goodsCatalog">

    <div class="panel panel-danger">
        <div class="panel-heading"><i class="glyphicon glyphicon-user"></i> Авторизация</div>
        <div class="panel-body">
            <spring:form action="/auth/login" method="post">
                <div class="form-group">
                    <input class="form-control" type="email" name="email" placeholder="Почтовый ящик">
                    <input class="form-control" type="password" name="password" placeholder="Пароль">
                </div>
                <div class="form-group row">
                    <div class="col-xs-6 text-center">
                        <a href="#">Регистрация</a>
                    </div>
                    <div class="col-xs-6">
                        <button class="form-control" type="submit" >
                            Вход
                        </button>
                    </div>

                </div>
            </spring:form>
        </div>
    </div>

    <div class="panel panel-danger">
        <div class="panel-heading"><i class="glyphicon glyphicon-list-alt"></i> Категории</div>
        <div class="panel-body">
            <div class="list-group">
                <a href="<c:url value="/"/>" class="list-group-item">Все</a>
                <c:forEach var="category" items="${categories}">
                    <a href="<c:url value="/categories/${category.id}"/>"
                       class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

<div class="col-xs-9 goodsCatalog">
    <table class="table table-hover table-bordered">
        <c:forEach var="goods" items="${goodsList}">
            <tr>
                <td>
                    <div class="row">
                        <div class="col-xs-4 goodsImg">
                            <div>
                                <img src="data:image/${goods.image.ext};base64,${goods.image.base64}" height="133"
                                     border="0" alt="">
                            </div>
                            <strong>Код товара: </strong><span>15364</span>
                        </div>

                        <div class="col-xs-8 goodsDetails">
                            <h4>${goods.name}</h4>
                            <h5>${goods.category.name}</h5>
                            <h5>${goods.description}</h5>
                        </div>
                    </div>
                </td>
                <td class="text-center">
                    <div>
                        <h4>${goods.price} тг.</h4>
                    </div>

                    <div>
                        <a href="javascript:void(0);">
                            <button class="btn btn-danger">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Купить
                            </button>
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>

    <nav class="text-right">
        <ul class="pagination pagination-sm">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

</div>



