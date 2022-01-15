$(document).ready(function () {
    $("form[class*='update-form']").on('submit', function (e) {

        e.preventDefault();
        var formdata = $(this).serialize();
        $.ajax({
            type: 'post',
            url: 'details',
            data: formdata,
            success: function (responseText) {
                var noti = $('.notification-update');
                if (responseText == "success") {
                    noti.prop("class", ".notification-update text-success");
                    noti.text("Success");
                } else if (responseText == "fail") {
                    noti.prop("class", ".notification-update text-danger");
                    noti.text("Failed");
                }
            }
        });
    });
    $("form[class*='add-form']").on('submit', function (e) {

        e.preventDefault();
        var formdata = $(this).serialize();

        $.ajax({
            type: 'post',
            url: 'add',
            data: formdata,
            success: function (responseText) {
                var noti = $('.notification-add');
                if (responseText == "success") {
                    noti.prop("class", ".notification-add text-success");
                    noti.text("Success");
                } else if (responseText == "fail") {
                    noti.prop("class", ".notification-add text-danger");
                    noti.text("Failed");
                }
            }
        });
    });
});