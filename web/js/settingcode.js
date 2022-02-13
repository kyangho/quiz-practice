/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function changeStatus(id, status, url) {
    var c = confirm("Do you sure that change status?");
    if (c) {
        window.location.href = url + "?id=" + id + "&status=" + status;
    }
}
