<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanat
  Date: 01.12.2015
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-lg-3">
    <div class="list-group">
        <c:forEach var="category" items="${categories}">
            <a href="<c:url value="categories/${category.id}" />"
               class="list-group-item  borderRed">${category.name}</a>
        </c:forEach>
    </div>
</div>

<div class="col-lg-9">
    <div class="goodsCatalog">
        <table class="table table-bordered">
            <c:forEach var="goods" items="${goodsList}">
                <tr>
                    <td>
                            ${goods.name} <br>
                            ${goods.category.name}
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
    </div>
</div>


