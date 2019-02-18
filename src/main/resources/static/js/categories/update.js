$(document).ready(function () {
    $('#updateBtn').click(function (e) {
        var categoryName = $('#categoryName').val();
        var categoryId = parseInt($('#categoryId').val());
        var data = {
            'id' : categoryId,
            'name' : categoryName
        };
        $.ajax({
            url: '/categories/' + categoryId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/account";
            }
        });
    });
});