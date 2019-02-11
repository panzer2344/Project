$(document).ready(function () {
    $('.deleteButton').click(function (e) {
        itemId = parseInt(e.target.id.replace('formBtn', ''));
        sbId = parseInt($('.shoppingBasketIdLabel')[0].id.replace('sbIdLabel', ''));
        data = {
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