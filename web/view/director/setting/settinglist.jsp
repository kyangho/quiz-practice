<%-- 
    Document   : settinglist
    Created on : Jan 12, 2022, 7:42:08 PM
    Author     : conmu
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
                                    <div class="panel-heading" style="display: flex;">
                                        <div style="margin-right: 70%;"> List of settings</div>
                                        <div>
                                            <a class="btn label-danger center-block col-sm-10" style="width: 100%;margin-left: 36%;" href="add">Add new setting </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15 row">
                                            <div class="col-lg-10">
                                                <form action="settinglist" method="GET" id="filter">
                                                    <div style="margin: 6px 15px;">
                                                        <strong>Filter By:</strong>
                                                        <!--<button class="btn btn-sm btn-default" style="color: white;font-weight: bolder;background-color: #94a8ff;width: 90px;" type="submit">Apply</button>-->
                                                    </div>
                                                    <div class="row" style="margin: 8px;">
                                                        <div class="col-lg-4">
                                                            Type:
                                                            <select name="type" class="form-control input-sm m-b-10" onchange="submitForm()">
                                                                <option  ${requestScope.type eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>

                                                            <c:forEach items="${requestScope.types}" var="type">
                                                                <option  ${requestScope.type eq type.name ? "selected=\"selected\"" : ""} value="${type.name}">${type.name}</option>
                                                            </c:forEach>
                                                        </select>                                                    </div>
                                                    <div class="col-lg-4">
                                                        Status:
                                                        <select name="status" class="form-control input-sm m-b-10" onchange="submitForm()">
                                                            <option ${requestScope.status eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <option ${requestScope.status eq "Active" ? "selected=\"selected\"" : ""} value="Active">Active</option>
                                                            <option ${requestScope.status eq "Deactive" ? "selected=\"selected\"" : ""} value="Deactive">Deactive</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-lg-4"></div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="input-group col-lg-2 row">
                                            <div style="margin: 6px 0;"><strong>Search:</strong></div><br>
                                            <div>
                                                <form action="settinglist" method="GET" style="padding: 0 13px;">
                                                    <input type="text" name="key" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search setting" value="${valueSearch}" />
                                                    <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${requestScope.settings.isEmpty()}">
                                        <h3>Not Found Settings</h3>
                                    </c:if>
                                    <c:if test="${!requestScope.settings.isEmpty()}">
                                        <table class="table table-hover">
                                            <tr>
                                                <th>ID</th>
                                                <th>Setting Name</th>
                                                <th>Setting Value</th>
                                                <th>Description</th>
                                                <th>Type</th>
                                                <th>Status:</th>
                                                <th>View</th>
                                                <th>Change status</th>
                                            </tr>
                                            <c:forEach items="${requestScope.settings}" var="s">
                                                <tr>
                                                    <td>${s.id}</td>
                                                    <td>${s.name}</td>
                                                    <td>${s.value}</td>
                                                    <td>${s.description}</td>
                                                    <td>${s.type}</td>
                                                    <td>${s.status}</td>
                                                    <td><button class="label label-info"><a style="color: white;" href="details?id=${s.id}">Details</a></button></td>
                                                    <td><button onclick="changeStatus(${s.id}, '${s.status}', 'changestatusforsetting')" type="button" class="label 
                                                                <c:if test="${s.status == 'ACTIVE'}">
                                                                    label-danger
                                                                </c:if>
                                                                <c:if test="${s.status eq 'DEACTIVE'}">
                                                                    label-success
                                                                </c:if>
                                                                ">
                                                            <c:if test="${s.status eq 'ACTIVE'}">
                                                                DEACTIVE
                                                            </c:if>
                                                            <c:if test="${s.status eq 'DEACTIVE'}">
                                                                ACTIVE
                                                            </c:if>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div>
                                            <nav aria-label="Page navigation example">
                                                <ul id="paggingBottom" class="pagination" style="float: right;">
                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>    
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalPage}, '${requestScope.url}', 2);
                                        function generatePagger(div, pageIndex, totalpage, url, gap) {
                                            var container = document.getElementById(div);
                                            if (pageIndex - gap > 0)
                                                container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + '1">First</a></li>';
                                            for (var i = (pageIndex) - gap; i < pageIndex; i++) {
                                                if (i > 0)
                                                    container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + i + '">' + i + '</a></li>';
                                            }
                                            container.innerHTML += '<li class="page-item active"><span class="page-link">' + pageIndex + '</span></li>';
                                            for (var i = (pageIndex) + 1; i <= pageIndex + gap; i++) {
                                                if (i <= totalpage)
                                                    container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + i + '">' + i + '</a></li>';
                                            }
                                            if (pageIndex + gap < totalpage)
                                                container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + totalpage + '">Last</a></li>';
                                        }
                                        function changeStatus(id, status, url) {
                                            var c = confirm("Do you sure that change status?");
                                            if (c) {
                                                window.location.href = url + "?id=" + id + "&status=" + status;
                                            }
                                        }
                                        function submitForm() {
                                            document.getElementById('filter').submit();
                                        }
                                    </script>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </aside>

        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>
