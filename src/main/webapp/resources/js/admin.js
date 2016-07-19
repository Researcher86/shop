$(document).ready(function () {
    
    $(".deleteCategoryButton").click(function (e) {
        $.ajax({
            type: "DELETE",
            url: 'admin/categories/' + $(this).attr("categoryId"),
            timeout: 3000,
            success: function (data, status, xhr) { // вешаем свой обработчик на функцию success
                location.reload();
            },
            error: function (xhr, status, error) {
                alert(xhr.responseText);
                // location.reload();
            }
        });
    });

    $(".deleteGoodsButton").click(function (e) {
        $.ajax({
            type: "DELETE",
            url: 'admin/goods/' + $(this).attr("goodsId"),
            timeout: 3000,
            success: function (data, status, xhr) { // вешаем свой обработчик на функцию success
                location.reload();
            },
            error: function (xhr, status, error) {
                alert(xhr.responseText);
                // location.reload();
            }
        });
    });

    $(".deleteCommentButton").click(function (e) {
        $.ajax({
            type: "DELETE",
            url: 'admin/comments/' + $(this).attr("commentId"),
            timeout: 3000,
            success: function (data, status, xhr) { // вешаем свой обработчик на функцию success
                location.reload();
            },
            error: function (xhr, status, error) {
                alert(xhr.responseText);
                // location.reload();
            }
        });
    });
});
