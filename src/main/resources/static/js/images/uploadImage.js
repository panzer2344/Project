/*
*
*
*
* uploading only one image as multipart file
*
*
* */
$(document).ready(function () {
    $("form").submit(function(evt){
        evt.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: '/images/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (response) {
                alert(response);
            }
        });
        return false;
    });
});