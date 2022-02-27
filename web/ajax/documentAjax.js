/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    function load() {
        $("#search i").removeClass("fa fa-search");
        $("#search i").addClass("fa fa-circle-o-notch fa-spin");
        setTimeout(function () {
            $("#search i").removeClass("fa fa-circle-o-notch fa-spin");
            $("#search i").addClass("fa fa-search");
        }, 1000);
        var search = $("#search input");
        console.log(search.val())
        window.location.href = "list?search=" + search.val();
    }
    $("#button").on('click', load);
    $("div[id='search'] input").on('keydown', function () {
        if (event.keyCode == 13) {
            load();
        }
    });
    $(".tick input").on("change", function () {
        var para = $(this).attr('name') + '=' + $(this).val();
        var href = window.location.href.split("/");
        href = href[href.length - 1];
        href = href.replace('detail', 'list');
        if (href == 'list' || href == 'detail') {
            if (href.includes("?")) {
                if (href[href.length - 1] == '&') {
                    window.location.href = href + para;
                } else {
                    window.location.href = href + '&' + para;
                }
            } else {
                window.location.href = href + '?' + para;
            }
            return;
        }
        var paras = href.split("?")[1].split("&");
        var uri = href.split("?")[0] + '?';
        for (var i = 0; i < paras.length; i++) {
            if (paras[i].includes($(this).attr('name') + '=')) {
                continue;
            }
            console.log(paras[i])
            uri += paras[i];
            uri += (i !== paras.length - 1) ? '&' : '';
        }
        href = uri;
        if (href.includes("?")) {
            if (href[href.length - 1] == '&' || href[href.length - 1] == '?') {
                window.location.href = href + para;
            } else {
                window.location.href = href + '&' + para;
            }
        } else {
            window.location.href = href + '?' + para;
        }
    })
    $(".form-check-input[type='radio']").on("change", function () {
        window.location.href = "list";
    })

    var href = window.location.href;
    for (var i = 0; i < $(".tick input").length; i++) {
        if (href.includes($(".tick input")[i].value)) {
            const att = document.createAttribute("checked");
            $(".tick input")[i].setAttributeNode(att);
        }
    }
    if (href.includes("category")) {
        const att = document.createAttribute("class");
        att.value = $("#inner-box")[0].getAttribute("class") + " show";
        $("#inner-box")[0].setAttributeNode(att)
    }
    if (href.includes("status")) {
        const att = document.createAttribute("class");
        att.value = $("#inner-box-status")[0].getAttribute("class") + " show";
        $("#inner-box-status")[0].setAttributeNode(att)
    }


    $(".pagination a").on("click", function (e) {
        console.log(window.location.href);
    })

    $(".select-category").on("change", function (e) {
        console.log("a");
        window.location.href = "list?category=" + $(this).val();
    });

});
