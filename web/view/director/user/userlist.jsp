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
                                        <div style="margin-right: 74%;"> List of users</div>
                                        <div>
                                            <a class="btn label-danger center-block col-sm-10" style="width: 100%;margin-left: 68%;" href="adduser">Add new user </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="userlist" method="POST">
                                                    <input type="text" name="keySearch" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search users" value="${valueSearch}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <form action="userlist" method="POST">
                                        <c:if test="${requestScope.accounts.isEmpty()}">
                                            <h3>Not Found User</h3>
                                        </c:if>
                                        <c:if test="${!requestScope.accounts.isEmpty()}">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>ID
                                                        <select class=" input-sm" name="id">
                                                            <option ${requestScope.id eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.id eq "asc" ? "selected=\"selected\"" : ""} value="asc">Ascending</option>
                                                            <option ${requestScope.id eq "desc" ? "selected=\"selected\"" : ""} value="desc">Descending</option>
                                                        </select>
                                                    </th>
                                                    <th>Full Name
                                                        <select class=" input-sm" name="fullname">
                                                            <option ${requestScope.fullname eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.fullname eq "asc" ? "selected=\"selected\"" : ""} value="asc">A->Z</option>
                                                            <option ${requestScope.fullname eq "desc" ? "selected=\"selected\"" : ""} value="desc">Z->A</option>
                                                        </select>
                                                    </th>
                                                    <th>Email
                                                        <select class=" input-sm" name="email">
                                                            <option ${requestScope.email eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.email eq "asc" ? "selected=\"selected\"" : ""} value="asc">A->Z</option>
                                                            <option ${requestScope.email eq "desc" ? "selected=\"selected\"" : ""} value="desc">Z->A</option>
                                                        </select>
                                                    </th>
                                                    <th>Phone
                                                        <select class=" input-sm" name="phone">
                                                            <option ${requestScope.phone eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.phone eq "asc" ? "selected=\"selected\"" : ""} value="asc">Ascending</option>
                                                            <option ${requestScope.phone eq "desc" ? "selected=\"selected\"" : ""} value="desc">Descending</option>
                                                        </select>
                                                    </th>
                                                    <th>Role
                                                        <select class=" input-sm" name="roleID">
                                                            <option ${requestScope.roleID eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <c:forEach items="${roles}" var="r">
                                                                <option ${requestScope.roleID eq r.id ? "selected=\"selected\"" : ""} value="${r.id}">${r.roleName}</option>    
                                                            </c:forEach>
                                                        </select>
                                                    </th>
                                                    <th>Status
                                                        <select class=" input-sm" name="status">
                                                            <option ${requestScope.status eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.status eq "ACTIVE" ? "selected=\"selected\"" : ""} value="Active">Active</option>
                                                            <option ${requestScope.status eq "DEACTIVE" ? "selected=\"selected\"" : ""} value="Deactive">Deactive</option>
                                                        </select>
                                                    </th>
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
                                                        <td>${a.status}</td>
                                                        <td><button class="label label-info"><a style="color: white;" href="userdetails?id=${a.id}">Details</a></button></td>
                                                        <td><button 
                                                                onclick="changeStatus(${a.id}, '${a.status}', 'changestatusforuser')" 
                                                                type="button" class="label 
                                                                <c:if test="${a.status == 'ACTIVE'}">
                                                                    label-danger
                                                                </c:if>
                                                                <c:if test="${a.status eq 'DEACTIVE'}">
                                                                    label-success
                                                                </c:if>
                                                                ">
                                                                <c:if test="${a.status eq 'ACTIVE'}">
                                                                    DEACTIVE
                                                                </c:if>
                                                                <c:if test="${a.status eq 'DEACTIVE'}">
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
                                        function generatePagger(div, pageIndex, totalpage, url, gap) {
                                            var container = document.getElementById(div);
                                            if (pageIndex - gap > 0)
                                                container.innerHTML += '<a href="' + url + '1">First</a>';
                                            for (var i = (pageIndex) - gap; i < pageIndex; i++) {
                                                if (i > 0)
                                                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
                                            }
                                            container.innerHTML += '<span >' + pageIndex + '</span>';
                                            for (var i = (pageIndex) + 1; i <= pageIndex + gap; i++) {
                                                if (i <= totalpage)
                                                    container.innerHTML += '<a href="' + url + i + '">' + i + '</a>';
                                            }
                                            if (pageIndex + gap < totalpage)
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
