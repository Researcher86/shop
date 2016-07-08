$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var baseURL = $("meta[name='baseURL']").attr("content");
    
    $(document).ajaxSend(function(e, xhr, options) {
        options.url = baseURL + options.url;
        xhr.setRequestHeader(header, token);
    });
    
    $("#cartItems input").change(function (e) {

        $.ajax({
            type: "PUT",
            url: 'cart/goods',
            // dataType: "json", // тип загружаемых данных
            data: {goodsId: $(this).attr("goodsId"), amount: $(this).val()},
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
            url: 'cart/goods/' + $(this).attr("goodsId"),
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

    $("button.buy").click(function (e) {
        $.ajax({
            type: "POST",
            url: 'cart/goods',
            data: {goodsId: $(this).attr("goodsId"), amount: 1},
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
