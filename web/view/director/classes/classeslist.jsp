<%-- 
    Document   : classlist
    Created on : Feb 25, 2022, 3:28:06 PM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Classes List</title>
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
                                        <div style="margin-right: 74%;"> List of classes</div>
                                        <div>
                                            <a class="btn label-danger center-block col-sm-10" style="width: 100%;margin-left: 68%;" href="addnewclass">Add new class</a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="classeslist" method="get">
                                                    <input type="text" name="key" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search" value="${requestScope.key}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <c:if test="${requestScope.classes.isEmpty()}">
                                        <h3>Not Found Any Order</h3>
                                    </c:if>
                                    <c:if test="${!requestScope.classes.isEmpty()}">
                                        <table class="table table-hover">
                                            <tr>
                                                <th>ID</th>
                                                <th>Class Name</th>
                                                <th>Author</th>
                                                <th>Note</th>
                                                <th>Status</th>
                                                <th>Detail</th>
                                                <th>Change status</th>
                                            </tr>
                                            <c:forEach items="${requestScope.classes}" var="c">
                                                <tr>
                                                    <td>${c.classID}</td>
                                                    <td>${c.className}</td>
                                                    <td>${c.author.fullname}</td>
                                                    <td>${c.note}</td>
                                                    <td>${c.status}</td>
                                                    <td><button class="label label-info"><a style="color: white;" href="classdetail?classID=${c.classID}">Details</a></button></td>
                                                    <td><button 
                                                            onclick="changeStatus('${c.classID}'', '${c.status}', 'changeclassstatus')" 
                                                            type="button" class="label 
                                                            <c:if test="${c.status eq 'ACTIVE'}">
                                                                label-danger
                                                            </c:if>
                                                            <c:if test="${c.status eq 'DEACTIVE'}">
                                                                label-success
                                                            </c:if>
                                                            ">
                                                            <c:if test="${c.status eq 'ACTIVE'}">
                                                                DEACTIVE
                                                            </c:if>
                                                            <c:if test="${c.status eq 'DEACTIVE'}">
                                                                ACTIVE
                                                            </c:if>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageIndex},${requestScope.totalPages}, '${requestScope.url}', 2);
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
