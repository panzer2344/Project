$(document).ready(function () {
    $('#updateBtn').click(function (e) {
        var itemId = parseInt($('#id').val());
        var itemName = $('#name').val();
        var itemPrice = $('#price').val();
        var itemCount = $('#count').val();
        var categories = [];
        $('input[name = categories]:checked').each(function () {
            categories.push({
                'id' : $(this).val()
            });
        });
        //var categories = $('input[name = categories]:checked').val();
        var data = {
            'avatar' : null,
            'name' : itemName === '' ? null : itemName,
            'price' : itemPrice === '' ? null : parseInt(itemPrice),
            'countInStock' : itemCount === '' ? null : parseInt(itemCount),
            'categories' : categories,
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
            },
            error : function (request, status, error) {
                var response = JSON.parse(request.responseText);
                alert(response['error'] + '\n' + response['message']);
            }
        });
    });
});