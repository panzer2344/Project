$(document).ready(function () {
    $('#updateBtn').click(function (e) {
        var itemId = parseInt($('#id').val());
        var itemName = $('#name').val();
        var itemPrice = $('#price').val();
        var itemCount = $('#count').val();
        var data = {
            'avatar' : null,
            'name' : itemName === '' ? null : itemName,
            'price' : itemPrice === '' ? null : parseInt(itemPrice),
            'countInStock' : itemCount === '' ? null : parseInt(itemPrice),
            'categories' : null,
        };
        $.ajax({
            url: '/items/update/' + itemId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/";
            }
        });
    });
});