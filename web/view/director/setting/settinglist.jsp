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

                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="settinglist" method="POST">
                                                    <input type="text" name="setting_name" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search by setting name" value="${valueSearch}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <form action="settinglist" method="POST">
                                        <c:if test="${requestScope.settings.isEmpty()}">
                                            <h3>Not Found Settings</h3>
                                        </c:if>
                                        <c:if test="${!requestScope.settings.isEmpty()}">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Setting Name</th>
                                                    <th>Description</th>
                                                    <th>
                                                        Type:
                                                        <select name="type" class=" input-sm">
                                                            <option  ${requestScope.type eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>

                                                            <c:forEach items="${requestScope.types}" var="type">
                                                                <option  ${requestScope.type eq type.name ? "selected=\"selected\"" : ""} value="${type.name}">${type.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <!--<button class="btn btn-sm btn-default" style="color: black;font-weight: bolder;" type="submit">Apply</button>-->
                                                    </th>
                                                    <th>
                                                        Status:
                                                        <select name="status" class=" input-sm">
                                                            <option ${requestScope.status eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <option ${requestScope.status eq "Active" ? "selected=\"selected\"" : ""} value="Active">Active</option>
                                                            <option ${requestScope.status eq "Deactive" ? "selected=\"selected\"" : ""} value="Deactive">Deactive</option>
                                                        </select>
                                                    </th>
                                                    <th>View</th>
                                                    <th>Change status</th>
                                                </tr>
                                                <c:forEach items="${requestScope.settings}" var="s">
                                                    <tr>
                                                        <td>${s.id}</td>
                                                        <td>${s.name}</td>
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
                                            <div style="margin: -2% 0 1% 93%;">
                                                <button class="btn btn-info center-block col-sm-10" type="submit">Apply</button>
                                            </div>
                                        </c:if>    
                                    </form>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalPage}, '${requestScope.url}', 2);
                                        function generatePagger(div, pageindex, totalpage, url, gap) {
                                            var container = document.getElementById(div);
                                            if (pageindex - gap > 1)
                                                container.innerHTML += '<a href="' + url + '1">First</a>';
                                            for (var i = pageindex - gap; i < pageindex; i++) {
                                                if (i > 0)
                                                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
                                            }
                                            container.innerHTML += '<span >' + pageindex + '</span>';
                                            for (var i = pageindex + 1; i <= pageindex + gap; i++) {
                                                if (i <= totalpage)
                                                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
                                            }
                                            if (pageindex + gap < totalpage)
                                                container.innerHTML += '<a href="' + url + totalpage + '">Last</a>';
                                        }
                                        function changeStatus(id, status, url) {
                                            var c = confirm("Do you sure that change status?");
                                            if (c) {
                                                window.location.href = url + "?id=" + id + "&status=" + status;
                                            }
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
