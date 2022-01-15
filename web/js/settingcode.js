function changeStatus(id, status, pageindex) {
    var c = confirm("Do you sure that change status?");
    if (c) {
        window.location.href = "changestatus?setting_id=" + id + "&status=" + status + "&pageindex=" + pageindex;
    }
}