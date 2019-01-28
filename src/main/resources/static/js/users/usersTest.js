/*
*
* Tests for user controller
*
*
* */

//==========================================
//==========================================
//==========================================

/*
*
*  get one user
*
* */

$(document).ready(function () {
    $('#btnGetOne').click(function () {
        $.get("/users/" + $('#tbGetOneId').val(), function (data) {
            $('#tdGetOneResult').attr("hidden", false);
            $('#taGetOneName').text(data["name"]);
            $('#taGetOnePassword').text(data["password"]);
            $('#taGetOneFirstName').text(data["firstName"]);
            $('#taGetOneLastName').text(data["lastName"]);
            $('#taGetOneAge').text(data["age"]);
        });
    });
});

/*
*
* get all users
*
*
* */

$(document).ready(function () {
    $('#btnGetAll').click(function () {
        $.get("/users/", function (data) {
            $('#tdGetAllResult').empty();
            data.forEach(function (user, index, users) {
                $('#tdGetAllResult').append('<div>' +
                    '<p><b>User #' + user["id"] + '</b></p>' +
                    '<label><b>Name: </b></label> <label id="taGetOneName' + index + '">' + user["name"] + '</label>' +
                    '<label><b>Password: </b></label> <label id="taGetOnePassword' + index + '">' + user["password"] + '</label>' +
                    '<label><b>First name: </b></label> <label id="taGetOneFirstName' + index + '">' + user["firstName"] + '</label>' +
                    '<label><b>Last name: </b></label> <label id="taGetOneLastName' + index + '">' + user["lastName"] + '</label>' +
                    '<label><b>Age: </b></label> <label id="taGetAllAge' + index + '">' + user["age"] + '</label>' +
                    '</div>'
                );
            });
        });
    });
});

/*
*
* delete one
*
* */

$(document).ready(function () {
    $('#btnDeleteOne').click(function () {
        $.ajax({
            url: "/users/" + $('#tbDeleteOneId').val(),
            type: 'DELETE',
            success: function(data){
                $('#tdDeleteOneResult').text(data);
            }
        });
    });
});

$(document).ready(function () {
    $('#btnDeleteAll').click(function () {
        $.ajax({
            url: "/users/",
            type: 'DELETE',
            success: function(data){
                $('#tdDeleteAllResult').text(data);
            }
        });
    });
});

$(document).ready(function () {
    $('#btnAddNewOne').click(function () {
        var user = {
            "name" : $('#tbName').val(),
            "password" : $('#tbPassword').val(),
            "firstName" : $('#tbFirstName').val(),
            "lastName" : $('#tbLastName').val(),
            "age" : $('#tbAge').val()
        };
        $.ajax({
            url : "users",
            contentType : 'application/json',
            type : 'POST',
            data : JSON.stringify(user)
        });
    });
});