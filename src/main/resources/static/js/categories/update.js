$(document).ready(function () {
    $('#updateBtn').click(function (e) {
        var categoryName = $('#categoryName').val();
        var categoryId = parseInt($('#categoryId').val());
        var data = {
            'id': categoryId,
            'name': categoryName
        };
        if (!Number.isNaN(categoryId)) {
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
                },
                error: function (request, status, error) {
                    var response = JSON.parse(request.responseText);
                    alert(response['error'] + '\n' + response['message']);
                }
            });
        } else {
            alert("categoryId не должен быть пустым!");
        }
    });
});