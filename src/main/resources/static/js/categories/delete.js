$(document).ready(function () {
    $('#deleteBtn').click(function (e) {
        var category = parseInt($('input[name=category]:checked').val());
        $.ajax({
            url: '/categories/' + category,
            type: 'DELETE',
            contentType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/account";
            }
        });
    });
});