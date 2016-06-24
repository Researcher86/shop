$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    $(".deleteCategoryButton").click(function (e) {
        $.ajax({
            type: "DELETE",
            url: '/admin/categories/' + $(this).attr("goodsId"),
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
