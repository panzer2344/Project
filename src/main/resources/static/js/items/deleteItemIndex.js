$(document).ready(function () {
    $('#deleteItemBtn').click(function (e) {
        $.ajax({
            url: '/items/' + $('#deleteItemItemId').val(),
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