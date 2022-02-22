/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $('.search-panel .dropdown-menu').find('a').on("click", function (e) {
        console.log("a");
        e.preventDefault();
        var param = $(this).attr("href").replace("#", "");
        var concept = $(this).text();
        $("span[id^='search_concept']").text(concept);
        $('.input-group #search_param').val(param);
    });

    $('.input-group-btn .btn-search').on("click", function (e) {
        var inputSearch = document.getElementById("search");
        if (inputSearch.value === "") {
            inputSearch.setCustomValidity("Please fill this!");
            inputSearch.reportValidity();
        } else {
            window.location.href = "list?search=" + inputSearch.value;
        }
    });

    $('input#search').keypress(function (e) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var inputSearch = document.getElementById("search");
            if (inputSearch.value === "") {
                inputSearch.setCustomValidity("Please fill this!");
                inputSearch.reportValidity();
            } else {
                window.location.href = "list?search=" + inputSearch.value;
            }
        }
    });

    $(".select-category").on("change", function (e) {
        console.log("a");
        window.location.href = "list?category=" + $(this).val();
    });
    $("form[class*='update-forma']").on('submit', function (e) {
        e.preventDefault();
        console.log('a');

        var formdata = $(this).serialize();

        $.ajax({
            type: 'post',
            url: 'update',
            data: formdata,
            contentType: 'multipart/form-data',
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
    $("form[class*='upload-thumbnail-form']").on('submit', function (e) {
        e.preventDefault();
        console.log('a');

        var formdata = $(this).serialize();

        $.ajax({
            type: 'post',
            url: 'thumbnail',
            data: formdata,
            contentType: 'multipart/form-data',
            success: function (responseText) {
                var noti = $('.notification-add');
                if (responseText == "success") {
                    noti.prop("class", "notification-add text-center text-success");
                    noti.text("Add successfully!");
                } else if (responseText == "fail") {
                    noti.prop("class", "notification-add text-center text-danger");
                    noti.text("Add fail!");
                }
            }
        });
    });
    $(".pagination a").on("click", function (e) {
        console.log(window.location.href);
    })

    $("#file-thumbnail").on("change", function (e) {
        var name = e.target.files[0].name;
        var type = e.target.files[0].type;
        var newForm = new FormData();
        var id = $('.update-form').children('input').val();
        newForm.append('thumbnail', e.target.files[0]);
        newForm.append('id', id);
        if (type.toString().startsWith('image')) {
            $.ajax({
                url: 'thumbnail',
                type: 'post',
                data: newForm,
                mimeType: "multipart/form-data",
                contentType: false,
                cache: false,
                processData: false,
                success: function (response) {
                    console.log(response)
                },
                timeout: 100000
            });
        }
        $("label.file-thumbnail")[0].innerHTML = name;
//        $("#image-thumbnail")[0].src = "/QuizPractice/post/image?id=" + id + "&" + new Date().getTime();
        console.log($('#image-thumbnail'));
    })

    $("#file-attach").on("change", function (e) {
        var name = e.target.files[0].name;
        var type = e.target.files[0].type;
        var newForm = new FormData();
        var id = $('.update-form').children('input').val();
        newForm.append('file', e.target.files[0]);
        newForm.append('type', type);
        newForm.append('id', id);
        $.ajax({
            url: 'file',
            type: 'post',
            data: newForm,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function (response) {
                console.log(response)
            }
        });
        $("a.file-href")[0].innerHTML = name;
    })
});