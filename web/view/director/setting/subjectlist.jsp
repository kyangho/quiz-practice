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
                                        <div style="margin-right: 74%;"> List of settings</div>
                                        <div>
                                            <a href="add">Add new subject </a>
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
                                                    <th>Subject Name</th>
                                                    <th>Author</th>
                                                    <th>Status</th>
                                                    <th></th>
                                                </tr>                     
                                                <c:forEach items="${requestScope.subjects}" var="s">
                                                    <tr>
                                                        <td>${s.subject_id}</td>
                                                        <td>${s.subject_title}</td>
                                                        <td>${s.subject_author}</td>
                                                        <td>${s.subject_status}</td>
                                                        <td>
                                                            <a href="../subject/subjectdetail?subject_id=${s.subject_id}">Details</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tr>
                                            </table>
                                        </c:if>    
                                    </form>
                                    <div style="margin: -2% 0 1% 90%"; >
                                        <a href="../subject/newsubject">
                                            <button class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Add new subject</button>
                                        </a>
                                    </div>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalpage}, 1);
                                        function generatePagger(div, pageindex, totalpage, gap) {
                                            var container = document.getElementById(div);
//                                            container.innerHTML = pageindex + ' ' + totalpage + ' ' + gap;
                                            if (pageindex - gap > 1) {
                                                container.innerHTML += '<a href="subjectlist?page=1">1</a>'
                                            }
                                            for (var i = pageindex - gap; i < pageindex; i++) {
                                                if (i > 0)
                                                    container.innerHTML += '<a href="subjectlist?page=' + i + '">' + i + '</a>';
                                            }
                                            container.innerHTML += '<span >' + pageindex + '</span>';
                                            for (var i = pageindex + 1; i <= pageindex + gap; i++) {
                                                if (i <= totalpage)
                                                    container.innerHTML += '<a href="subjectlist?page=' + i + '">' + i + '</a>';
                                            }
                                            if (pageindex + gap < totalpage) {
                                                container.innerHTML += '<a href="subjectlist?page=' + totalpage + '">Last</a>';
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





