$(document).ready(function () {
    
    $(".deleteCategoryButton").click(function () {
        $.ajax({
            type: "DELETE",
            url: 'admin/categories/' + $(this).attr("categoryId"),
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });

    $(".deleteGoodsButton").click(function () {
        $.ajax({
            type: "DELETE",
            url: 'admin/goods/' + $(this).attr("goodsId"),
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });

    $(".deleteCommentButton").click(function () {
        $.ajax({
            type: "DELETE",
            url: 'admin/comments/' + $(this).attr("commentId"),
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });
});
