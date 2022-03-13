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
            <script>
                function doUpdate(id) {
                    window.location.href = "../subject/editsubject?subject_id=" + id;
                }
                function doDelete(id) {
                    var cf = confirm("Are you sure delete this subject ?");
                    if (cf) {
                        window.location.href = "deletesubject?subject_id=" + id;
                    }
                }
            </script>
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
                                                <tr>
                                                    <td>${requestScope.subject.subject_id}</td>
                                                    <td>${requestScope.subject.subject_title}</td>
                                                    <td>${requestScope.subject.subject_Author.fullname}</td>
                                                    <td>${requestScope.subject.subject_status}</td>
                                                </tr>
                                                </tr>
                                            </table>
                                        </c:if>    
                                    </form>
                                    <div style="margin: 0 0 1% 80%"; >
                                        <button onclick="doUpdate(${requestScope.subject.subject_id});" class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Edit</button>
                                        <button onclick="doDelete(${requestScope.subject.subject_id});" class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Delete</button>
                                        <a href="../subject/newsubject">
                                            <button class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Add new subject</button>
                                        </a>
                                    </div>
                                    <!--                                    <div style="margin: -2% 0 1% 70%"; >
                                                                            <a href="#">
                                                                                <button class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Edit</button>
                                                                            </a>
                                                                        </div>-->
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>

        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>





