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
        <title>Settings List</title>
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
                                        <div style="margin-right: 74%;"> List of users</div>
                                        <div>
                                            <a href="add">Add new user </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">

                                        <div class="box-tools m-b-15" style="display: flex;">
                                            <div style="margin-right: 25%;">
                                                <form action="settinglist" method="POST">
                                                    <input type="hidden" name="filter" value="setting_type">
                                                    <input type="hidden" name="lastID" value="${requestScope.lastID}">
                                                Type:
                                                <select name="type" class=" input-sm">
                                                    <option ${requestScope.value eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                    <option ${requestScope.value eq "role" ? "selected=\"selected\"" : ""} value="role">Role</option>
                                                    <option ${requestScope.value eq "tag" ? "selected=\"selected\"" : ""} value="tag">Tag</option>
                                                    <option ${requestScope.value eq "subject" ? "selected=\"selected\"" : ""} value="subject">Subject</option>
                                                    <option ${requestScope.value eq "category" ? "selected=\"selected\"" : ""} value="category">Category</option>
                                                </select>
                                                <button class="btn btn-sm btn-default" style="color: black;font-weight: bolder;" type="submit">Apply</button>
                                            </form>

                                        </div>
                                        <div style="margin-right: 25%;">
                                            <form action="settinglist" method="POST">
                                                <input type="hidden" name="filter" value="setting_status">
                                                <input type="hidden" name="lastID" value="${requestScope.lastID}">
                                                Status:
                                                <select name="status" class=" input-sm">
                                                    <option ${requestScope.value eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                    <option ${requestScope.value eq "Active" ? "selected=\"selected\"" : ""} value="Active">Active</option>
                                                    <option ${requestScope.value eq "Deactive" ? "selected=\"selected\"" : ""} value="Deactive">Deactive</option>
                                                </select>
                                                <button class="btn btn-sm btn-default" style="color: black;font-weight: bolder;" type="submit">Apply</button>
                                            </form>
                                        </div>
                                        <div class="input-group">
                                            <form action="settinglist" method="POST">
                                                <input type="hidden" name="filter" value="setting_name">
                                                <input type="hidden" name="lastID" value="${requestScope.lastID}">
                                                <input type="text" name="setting_name" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search by setting name" value="${valueSearch}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Role</th>
                                            <th>Status</th>
                                            <th>View</th>
                                            <th>Change status</th>
                                        </tr>
                                        <c:forEach items="${requestScope.accounts}" var="a">
                                            <tr>
                                                <td>${a.id}</td>
                                                <td>${a.fullname}</td>
                                                <td>${a.email}</td>
                                                <td>${a.phone}</td>
                                                <td><c:forEach items="${a.role}" var="r">
                                                        ${r.roleName} <br>
                                                    </c:forEach>
                                                </td>
                                                <td>${a.status eq true? "Active":"De-active"}</td>
                                                <td><button class="label label-info"><a style="color: white;" href="details?id=${s.id}">Details</a></button></td>
<!--                                                <td><button onclick="changeStatus(${s.id}, '${s.status}', ${requestScope.pageindex})" type="button" class="label 
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
                                        </td>-->
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <!--                                    <script>
                                                                            generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalPage}, '${requestScope.url}', 2);
                                                                            function generatePagger(div, pageIndex, totalpage, url, gap) {
                                                                                var container = document.getElementById(div);
                                                                                if (pageIndex - gap > 0)
                                                                                    container.innerHTML += '<a href="' + url + '1">First</a>';
                                                                                for (var i = (pageIndex) - gap; i < pageIndex; i++) {
                                                                                    if (i > 0)
                                                                                        container.innerHTML += '<a href="' + url.replace('>', '<') + i + '">' + i + '</a>';
                                                                                }
                                                                                container.innerHTML += '<span >' + pageIndex + '</span>';
                                                                                for (var i = (pageIndex) + 1; i <= pageIndex + gap; i++) {
                                                                                    if (i <= totalpage)
                                                                                        container.innerHTML += '<a href="' + url.replace('<', '>') + i + '">' + i + '</a>';
                                                                                }
                                                                                if (pageIndex + gap < totalpage)
                                                                                    container.innerHTML += '<a href="' + url + totalpage + '">Last</a>';
                                                                            }
                                                                        </script>-->
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
