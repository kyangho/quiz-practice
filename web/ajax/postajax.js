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

    $('#search').keypress(function (e) {
        console.log('a')
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

    $('input.postTitle').on("focus", function (e) {
        var postTitle = document.getElementById("postTitle");
        if (!postTitle.value.match("\\w+")) {
            postTitle.setCustomValidity("Error!");
            postTitle.reportValidity();
            console.log(postTitle.value)
        } else {
        }
    })

    $(".select-category").on("change", function (e) {
        console.log("a");
        window.location.href = "list?category=" + $(this).val();
    });
    $("form[class='update-foraaam']").on('submit', function (e) {
        e.preventDefault();
        console.log('a');

        var formdata = $(this).serialize();


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
        var id = $('.update-form').children('input')[0].value;
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
})


$(document).ready(function () {
    function GetURLParameter(sParam) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam)
            {
                return sParameterName[1];
            }
        }
    }
    $('.sort-icon a').on('click', function () {


    })
    console.log('a')
    console.log(GetURLParameter('title'));
    if (GetURLParameter('title') === 'asc') {
        $('.sort-icon a').not($('.sort-icon a')[0]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('title') === 'desc') {
        $('.sort-icon a').not($('.sort-icon a')[1]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('category') === 'asc') {
        $('.sort-icon a').not($('.sort-icon a')[2]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('category') === 'desc') {
        $('.sort-icon a').not($('.sort-icon a')[3]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('author') === 'asc') {
        $('.sort-icon a').not($('.sort-icon a')[4]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('author') === 'desc') {
        $('.sort-icon a').not($('.sort-icon a')[5]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('feature') === 'asc') {
        $('.sort-icon a').not($('.sort-icon a')[6]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('feature') === 'desc') {
        $('.sort-icon a').not($('.sort-icon a')[7]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('status') === 'asc') {
        $('.sort-icon a').not($('.sort-icon a')[8]).prop("style", "color: gray; opacity: 0.2");
    } else if (GetURLParameter('status') === 'desc') {
        $('.sort-icon a').not($('.sort-icon a')[9]).prop("style", "color: gray; opacity: 0.2");
    } else {
        $('.sort-icon a').prop("style", "color: gray; opacity: 0.2");
    }

    $('.button-show-post').on("click", function () {
        var status = ($(this).attr("status") == "PUBLISH") ? "UNPUBLISH" : "PUBLISH";
        if (confirm("Do you want change to " + status + "!")) {
            $.ajax({
                url: 'changestatus',
                type: 'post',
                data: {id: $(this).attr("value")},
                success: function (response) {
                    window.location.href = 'list';
                }
            });
        } else {
        }
    });
    $('.button-change-feature').on("click", function () {
        var feature = ($(this).attr("feature") == "true") ? "off" : "on";
        if (confirm("Do you want change turn " + feature + " feature this post!")) {
            $.ajax({
                url: 'changefeature',
                type: 'post',
                data: {id: $(this).attr("value")},
                success: function (response) {
                    window.location.href = 'list';
                }
            });
        } else {
        }
    });
});
