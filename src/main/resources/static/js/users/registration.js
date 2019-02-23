$(document).ready(function () {
    $('#btnSubmit').click(function (e) {
        var userName = $('#tbUsername').val();
        var userPassword = $('#tbPassword').val();
        var userFirstName = $('#tbFirstName').val();
        var userLastName = $('#tbLastName').val();
        var userAge = parseInt($('#tbAge').val());
        var userRoles = [];
        $('input[name = roles]:checked').each(function () {
            var id = $(this).val();
            userRoles.push({
                'id': id,
                'name': $('lRoleName' + id).text()
            });
        });

        if (!Number.isNaN(userAge)) {
            var data = {
                'name' : userName,
                'password' : userPassword,
                'firstName' : userFirstName,
                'lastName' : userLastName,
                'age' : userAge,
                'roles' : userRoles
            };
            $.ajax({
                url: '/users',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (data, textStatus, jqXHR) {
                    /*alert('Response: data = { ' + data +
                        ' }\n textStatus = { ' + textStatus +
                        ' }\n jqXHR.status = { ' + jqXHR.status + ' }');
                    window.location.href = "/";*/
                    alert('User successfully added');
                    window.location.href = "/login"
                },
                error: function (request, status, error) {
                    var response = JSON.parse(request.responseText);
                    alert(response['error'] + '\n' + response['message']);
                }
            });
        }else{
            alert("age mustn't be empty!");
        }

        /*var itemId = parseInt($('#id').val());
        var itemName = $('#name').val();
        var itemPrice = $('#price').val();
        var itemCount = $('#count').val();
        var categories = [];
        $('input[name = categories]:checked').each(function () {
            categories.push({
                'id': $(this).val()
            });
        });
        //var categories = $('input[name = categories]:checked').val();
        var data = {
            'avatar': null,
            'name': itemName === '' ? null : itemName,
            'price': itemPrice === '' ? null : parseInt(itemPrice),
            'countInStock': itemCount === '' ? null : parseInt(itemCount),
            'categories': categories,
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
            error: function (request, status, error) {
                var response = JSON.parse(request.responseText);
                alert(response['error'] + '\n' + response['message']);
            }
        });*/
    });
});