<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizz</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>

        </head>

        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>

            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-heading" style="display: flex;">
                                            <div style="margin-right: 74%;"> Sliders  List</div>
                                            <div>
                                                <a href="add-slider" style="margin-left: 90px">Add Slider</a>
                                            </div>
                                        </div>
                                    </header>
                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="display: flex;">
                                            <form action="slider" method="GET">
                                                Status <select name="status" onchange="searchStatus(status.value);" class="input-sm">
                                                    <option value="-1" ${getStatus == -1 ? "selected" : ""}>All</option>
                                                <option value="1" ${getStatus == 1 ? "selected" : ""}>Show</option>
                                                <option value="0" ${getStatus == 0 ? "selected" : ""}>Hide</option>
                                            </select>
                                            <input style="margin-left: 710px" type="text" name="search" value="${getKeyword}" placeholder="Search by title or backlink."/>
                                            <input type="submit" value="search"/>
                                        </form>
                                    </div>
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Image</th>
                                            <th>Backlink</th>
                                            <th>Status</th>
                                            <th> </th>
                                        </tr>
                                        <c:forEach items="${requestScope.slider}" var="s">
                                            <tr>
                                                <td>${s.id}</td>
                                                <td>${s.title}</td>
                                                <td><img src="${s.image}" width="200px"></td>
                                                <td><a href="${s.backlink}">${s.backlink}</a></td>
                                                <td>
                                                    <button type="button" onclick="changeStatus(${s.id}, ${s.status})" class="label 
                                                            <c:if test="${s.status eq 'Hide'}"> label-danger</c:if>
                                                            <c:if test="${s.status eq 'Show'}">label-success</c:if>">
                                                        <c:if test="${s.status eq 'Hide'}">Hide</c:if>
                                                        <c:if test="${s.status eq 'Show'}">Show</c:if>
                                                        </button>
                                                    </td>
                                                    <td><a href="#?id=${s.id}"><input type="submit" value="Edit" /></a></td>
                                            </tr>
                                        </c:forEach>                                
                                    </table>
                                </div>
                                <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                </div>
                            </div>
                        </div>
                </section>
            </aside>
        </div>
    </body>
    <script>
        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalpage}, '${requestScope.url}', 1);
        function generatePagger(div, pageindex, totalpage, url, gap) {
            var container = document.getElementById(div);
            if (pageindex - gap > 1) {
                container.innerHTML += '<a href="' + url + '1">1</a>'
            }
            for (var i = pageindex - gap; i < pageindex; i++) {
                if (i > 0)
                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
            }
            container.innerHTML += '<span >' + pageindex + '</span>';
            for (var i = pageindex + 1; i <= pageindex + gap; i++) {
                if (i <= totalpage)
                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
            }
            if (pageindex + gap < totalpage) {
                container.innerHTML += '<a href="' + url + totalpage + '">Last</a>';
            }
        }
        function changeStatus(subject_id, subject_status, url) {
            var c = confirm("Do you sure that change status?");
            if (c) {
                window.location.href = url + "?subject_id=" + subject_id + "&subject_status=" + subject_status;
            }
        }
    </script>
    <script type="text/javascript">
        function searchStatus(status)
        {
            window.location.href = "slider?status=" + status;
        }
        function changeStatus(id, status)
        {
            window.location.href = "slider?idChange=" + id + "&&statusChange=" + status;
        }
        function submitForm(e) {
            let form = e.parentElement;
            form.submit();
        }
    </script>
</html>







