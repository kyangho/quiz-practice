/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function changeStatus(id, status, pageindex) {
    var c = confirm("Do you sure that change status?");
    if (c) {
        window.location.href = "changestatus?setting_id=" + id + "&status=" + status + "&pageindex=" + pageindex;
    }
}
