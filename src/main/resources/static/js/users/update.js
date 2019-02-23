$(document).ready(function () {
    $('#updateBtn').click(function (e) {
        var userName = $('#userName').text();
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var age = parseInt($('#age').val());
        var data = {
            'name' : null,
            'password' : null,
            'firstName' : firstName === '' ? null : firstName,
            'lastName' : lastName === '' ? null : lastName,
            'age' : age
        };
        $.ajax({
            url: '/users/update/' + userName,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data, textStatus, jqXHR) {
                alert('Response: data = { ' + data +
                    ' }\n textStatus = { ' + textStatus +
                    ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                window.location.href = "/account";
            },
            error : function (request, status, error) {
                var response = JSON.parse(request.responseText);
                alert(response['error'] + '\n' + response['message']);
            }
        });
    });
});