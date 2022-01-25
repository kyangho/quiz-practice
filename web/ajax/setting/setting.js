$(document).ready(function () {
    $("form[class*='update-form']").on('submit', function (e) {

        e.preventDefault();
        var formdata = $(this).serialize();
        $.ajax({
            type: 'post',
            url: 'update',
            data: formdata,
            success: function (responseText) {
                var noti = $('.notification-update');
                if (responseText == "success") {
                    noti.prop("class", "notification-update text-center text-success");
                    noti.text("Update Successfully!");
                    setTimeout(function () {
                        window.location.href = "settinglist";
                    }, 2000);
                } else if (responseText == "fail") {
                    noti.prop("class", "notification-update text-center text-danger");
                    noti.text("Update Fail!");
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
                    noti.prop("class", "notification-add text-center text-success");
                    noti.text("Add successfully!");
                    setTimeout(function () {
                        window.location.href = "settinglist";
                    }, 2000);
                } else if (responseText == "fail") {
                    noti.prop("class", "notification-add text-center text-danger");
                    noti.text("Add fail!");
                }
            }
        });
    });
});