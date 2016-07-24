$(document).ready(function () {
    
    $("#cartItems input").change(function () {
        $.ajax({
            type: "PUT",
            url: 'cart/goods',
            data: {goodsId: $(this).attr("goodsId"), amount: $(this).val()},
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function () {
                alert("Произошла ошибка при изменении количества товара!");
            }
        });
    });    
    
    $("#cartItems button").click(function () {
        $.ajax({
            type: "DELETE",
            url: 'cart/goods/' + $(this).attr("goodsId"),
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function () {
                alert("Произошла ошибка при удалении товара из корзины!");
            }
        });
    });

    $("button.buy").click(function () {
        $.ajax({
            type: "POST",
            url: 'cart/goods',
            data: {goodsId: $(this).attr("goodsId"), amount: 1},
            timeout: 3000,
            success: function () {
                location.reload();
            },
            error: function () {
                alert("Произошла ошибка при добавлении товара в корзину!");
            }
        });
    });
});
