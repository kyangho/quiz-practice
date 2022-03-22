<%-- 
    Document   : subjectlist
    Created on : Feb 10, 2022, 4:24:14 PM
    Author     : Yankee
--%>
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
                                        <div style="margin-right: 74%;"> List of subjects</div>
                                        <div>
                                            <a href="../subject/newsubject">Add new subject </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">

                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="subjectlist" method="POST">
                                                    <input type="text" name="subject_name" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search by subject name" value="${requestScope.valueSearch}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <form action="subjectlist" method="POST">
                                        <c:if test="${requestScope.subjects.isEmpty()}">
                                            <h3>Not Found Settings</h3>
                                        </c:if>
                                        <div style="margin: 0 0 1% 75%; ">
                                            <button class="btn btn-sm btn-default" style="color: white;font-weight: bolder;background-color: #94a8ff;width: 90px;" type="submit">Apply</button>
                                        </div>
                                        <c:if test="${!requestScope.subjects.isEmpty()}">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Subject Name</th>
                                                    <th>Author</th>
                                                    <th>
                                                        Status
                                                        <select name="subject_status" class=" input-sm">
                                                            <option ${requestScope.subject_status eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <option ${requestScope.subject_status eq "Published" ? "selected=\"selected\"" : ""} value="Published">Published</option>
                                                            <option ${requestScope.subject_status eq "Unpublished" ? "selected=\"selected\"" : ""} value="Unpublished">Unpublished</option>
                                                        </select>
                                                    </th>
                                                    <th>View Detail</th>
                                                    <th>Change status</th>
                                                </tr>                     
                                                <c:forEach items="${requestScope.subjects}" var="s">
                                                    <tr>
                                                        <td>${s.subject_id}</td>
                                                        <td>${s.subject_title}</td>
                                                        <td>${s.subject_Author.fullname}</td>
                                                        <td>${s.subject_status}</td>
                                                        <td>
                                                            <button class="label label-info"><a style="color:white;" href="../subject/subjectdetail?subject_id=${s.subject_id}">Details</a></button>
                                                        </td>
                                                        <td>
                                                            <button onclick="changeStatus(${s.subject_id}, '${s.subject_status}', 'ChangeStatusSubject')" type="button" class="label 
                                                                    <c:if test="${s.subject_status == 'Published'}">
                                                                        label-danger
                                                                    </c:if>
                                                                    <c:if test="${s.subject_status eq 'Unpublished'}">
                                                                        label-success
                                                                    </c:if>
                                                                    ">
                                                                <c:if test="${s.subject_status eq 'Published'}">
                                                                    Unpublished
                                                                </c:if>
                                                                <c:if test="${s.subject_status eq 'Unpublished'}">
                                                                    Published
                                                                </c:if>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tr>
                                            </table>
                                        </c:if>    
                                    </form>
                                    <div style="margin: 0 0 1% 90%"; >
                                        <a href="../subject/newsubject">
                                            <button class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Add new subject</button>
                                        </a>
                                    </div>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalpage}, '${requestScope.url}', 1);
                                        function generatePagger(div, pageindex, totalpage, url, gap) {
                                            var container = document.getElementById(div);
//                                            container.innerHTML = pageindex + ' ' + totalpage + ' ' + gap;
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
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </aside>

        </div>
        <%--<jsp:include page="../header/linkjavascript.jsp"></jsp:include>--%>
    </body>
</html>





