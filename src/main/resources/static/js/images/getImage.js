

$(document).ready(function () {
    $('#btnGetImage').click( function () {
        $.get("/images/" + $('#tbImageId').val(), function (data) {
            $('#imgResultImage').attr("src", "data:/jpeg;base64," + data);
        });
    });
});