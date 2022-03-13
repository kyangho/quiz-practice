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
                                <div class="popup">
                                    <div class="popup-content">
                                        <form action="import" method="POST" enctype='multipart/form-data'>
                                            <span class="close-btn">&times;</span>
                                            <h3>Import Question</h3>
                                            <input type="file" name="file" required="" accept=".csv">
                                            <p>You can download template import <a href="">Here</a></p>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="panel">
                                    <div class="panel-heading" style="display: flex;">
                                        <div style="margin-right: 74%;"> List of questions</div>
                                        <div>
                                            <a class="btn label-danger center-block col-sm-10" id="popup-btn" style="width: 100%;margin-left: 6%;" href="#">Import question </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="list" method="GET">
                                                    <input type="text" name="key" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search" value="${key}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <form action="list" method="GET">
                                        <c:if test="${requestScope.questions.isEmpty()}">
                                            <h3>Not Found Questions</h3>
                                        </c:if>
                                        <div style="margin: 0 0 1% 75%; ">
                                            <button class="btn btn-sm btn-default" style="color: white;font-weight: bolder;background-color: #94a8ff;width: 90px;" type="submit">Apply</button>
                                        </div>
                                        <c:if test="${!requestScope.questions.isEmpty()}">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Content</th>
                                                    <th>Subject
                                                        <select class=" input-sm" style="border-radius: 7px; margin-left: 10px" name="subject">
                                                            <option ${requestScope.subject eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <c:forEach items="${requestScope.subjects}" var="s">
                                                                <option ${s.subject_id eq subject ? "selected=\"selected\"" : ""} value="${s.subject_id}">${s.subject_title}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </th>
                                                    <!--<th>Category</th>-->
                                                    <th>Sub category
                                                        <select class=" input-sm" style="border-radius: 7px; margin-left: 10px" name="subcategory">
                                                            <option ${requestScope.subcategory eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <c:forEach items="${requestScope.subcate}" var="s">
                                                                <option ${s.id eq subcategory ? "selected=\"selected\"" : ""} value="${s.id}">${s.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </th>
                                                    <th>Level
                                                        <select class=" input-sm" style="border-radius: 7px; margin-left: 10px" name="level">
                                                            <option ${requestScope.level eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.level eq "Easy" ? "selected=\"selected\"" : ""} value="Easy">Easy</option>
                                                            <option ${requestScope.level eq "Medium" ? "selected=\"selected\"" : ""} value="Medium">Medium</option>
                                                            <option ${requestScope.level eq "Difficult" ? "selected=\"selected\"" : ""} value="Difficult">Difficult</option>
                                                        </select>
                                                    </th>
                                                    <th>Status
                                                        <select class=" input-sm" style="border-radius: 7px; margin-left: 10px" name="status">
                                                            <option ${requestScope.status eq "all" ? "selected=\"selected\"" : ""} value="all">ALL</option>
                                                            <option ${requestScope.status eq "public" ? "selected=\"selected\"" : ""} value="publish">Public</option>
                                                            <option ${requestScope.status eq "unpublic" ? "selected=\"selected\"" : ""} value="unpublic">Un public</option>
                                                        </select>
                                                    </th>
                                                    <th>Active</th>
                                                    <!--<th>Change status</th>-->
                                                </tr>
                                                <c:forEach items="${requestScope.questions}" var="q">
                                                    <tr>
                                                        <td>${q.id}</td>
                                                        <td>${q.content}</td>
                                                        <td>${q.subject.subject_title}</td>
                                                        <!--<td>${q.category.category_value}</td>-->
                                                        <td>${q.subCategory.name}</td>
                                                        <td>${q.level}</td>
                                                        <td>${q.status}</td>
                                                        <td>
                                                            <button type="button" style="border: none;" onclick="confirmDelete(${q.id})" class="label btn-danger">Delete</button>
                                                            <a href="details?questionId=${q.id}" class="label label-primary">Edit</a>
                                                            <a onclick="changeStatus(${q.id}, '${q.status}')">
                                                                <i <c:if test="${q.status eq 'publish'}">
                                                                        class="fa fa-eye-slash"
                                                                    </c:if>
                                                                    <c:if test="${q.status != 'publish'}">
                                                                        class="fa fa-eye"
                                                                    </c:if>>  
                                                                </i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:if>
                                    </form>
                                    <div>
                                        <nav aria-label="Page navigation example">
                                            <ul id="paggingBottom" class="pagination" style="float: right;">
                                            </ul>
                                        </nav>
                                    </div>
                                    <script>
                                        generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalPage}, '${requestScope.url}', 1);
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
                                        function confirmDelete(id) {
                                            var c = confirm("Are you delete question?");
                                            if (c) {
                                                window.location.href = "delete?quesid=" + id;
                                            }
                                        }
                                        function changeStatus(id, status) {
                                            var c = confirm("Are you sure that change status?");
//                                            var a = "changestatus?id=" + id + "&status=" + status;
//                                            console.log(a);
                                            if (c) {
                                                window.location.href = "changestatus?id=" + id + "&status=" + status;
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
        <script>
            let modalBtn = document.getElementById("popup-btn");
            let modal = document.querySelector(".popup");
            let closeBtn = document.querySelector(".close-btn");
            modalBtn.onclick = function () {
                modal.style.display = "block"
            }
            closeBtn.onclick = function () {
                modal.style.display = "none"
            }
            window.onclick = function (e) {
                if (e.target == modal) {
                    modal.style.display = "none"
                }
            }
            var file = new File(["Content,Category,Correct Answer,Answer 2,Answer 3,Answer 4,Answer 5,Answer 6,Answer 7,Answer 8,Answer 9,Answer 10"], "Template import question.csv", {type: "application/vnd.ms-excel"});
            document.querySelector(".popup-content a").href = window.URL.createObjectURL(file);
            document.querySelector(".popup-content a").download = 'Template import question.csv';
        </script>
    </body>
</html>
