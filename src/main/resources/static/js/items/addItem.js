/*
*
* add item to shop
*
*
* */

/*
*
* переделать здесь отправку картинки, либо попробовать сделать без js
* будет даже лучше, так как дальше будет таким способом быстрее
*
* */
/*
$(document).ready(function () {
    $("#addItem").submit(function(evt){
        evt.preventDefault();
        alert('1');
        //var formData = new FormData($('#avatar'));
        alert('2');
        $.ajax({
            url: '/images/upload',
            type: 'POST',
            data: $(#avatar)//formData,
            async: false,
            cache: false,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (imageResp) {
                alert('3');
                $.post("/items", JSON.stringify({
                    avatar : imageResp,
                    name : $('#name'),
                    price : $('#price')
                }))
                    .done(function (response) {
                        alert('4');
                        alert(response);
                    });
            }
        });
        alert('5');
        return false;
    });
});*/
