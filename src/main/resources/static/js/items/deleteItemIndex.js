$(document).ready(function () {
    $('.deleteItemBtn').click(function (e) {
        itemId = parseInt($(this)[0].id.replace('deleteItemBtn', ''));
        $.ajax({
            url: '/items/' + itemId,
            type: 'DELETE',
            contentType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/";
            }
        });
    });
});