$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    // $("#cartItems input").change(function (e) {
    //     // Ensure that it is a number and stop the keypress
    //     if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
    //         e.preventDefault();
    //     } else {
    //         return;
    //     }
    // });

    $("#cartItems input").change(function (e) {

        $.ajax({
            type: "PUT",
            url: '/cart/goods',
            // dataType: "json", // тип загружаемых данных
            data: {goodsId: $(this).attr("goodsId"), quality: $(this).val()},
            timeout: 3000,
            success: function (data, status, xhr) { // вешаем свой обработчик на функцию success
                location.reload();
            },
            error: function (xhr, status, error) {
                // alert("Error");
                // location.reload();
            }
        });
    });    
    
    $("#cartItems button").click(function (e) {
        $.ajax({
            type: "DELETE",
            url: '/cart/goods/' + $(this).attr("goodsId"),
            timeout: 3000,
            success: function (data, status, xhr) { // вешаем свой обработчик на функцию success
                location.reload();
            },
            error: function (xhr, status, error) {
                alert("Error");
                location.reload();
            }
        });
    });
});
