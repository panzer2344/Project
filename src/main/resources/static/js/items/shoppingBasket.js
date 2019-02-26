$(document).ready(function () {
    $('.deleteButton').click(function (e) {
        var itemId = parseInt(e.target.id.replace('formBtn', ''));
        var shoppingBasketIdLabel0 = $('.shoppingBasketIdLabel')[0];
        var sbId = shoppingBasketIdLabel0 === undefined ? null : parseInt(shoppingBasketIdLabel0.id.replace('sbIdLabel', ''));
        var data = {
            'itemId': itemId,
            'shoppingBasketId': sbId
        };
        $.ajax({
            url: '/shoppingBasket',
            type: 'DELETE',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/shoppingBasket";
            }
        });
    });
});