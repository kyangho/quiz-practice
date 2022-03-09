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
                                        <div style="margin-right: 74%;"> List of quiziz</div>
                                        <div>
                                            <!--<a href="../quiz/newquiz">Add new quiz </a>-->
                                            <div style="margin: 0 0 1% 90%"; >
                                                <a href="../quiz/newquiz">
                                                    <button class="btn btn-sm btn-default" style="color: #fdfdfd;font-weight: bolder;background-color: #00a6ff;" type="submit">Add new subject</button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="input-group">
                                                <form action="listquiz" method="POST">
                                                    <input type="text" name="search_quiz_title" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search quizzes by the title" value="${requestScope.search_quiz_title}" />
                                                <button class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i> </button>                                             
                                            </form>
                                        </div>
                                    </div>
                                    <form action="listquiz" method="POST">
                                        <c:if test="${requestScope.subjects.isEmpty()}">
                                            <h3>Not Found Settings</h3>
                                        </c:if>
                                        <div style="margin: 0 0 1% 75%; ">
                                            <button class="btn btn-sm btn-default" style="color: white;font-weight: bolder;background-color: #94a8ff;width: 90px;" type="submit">Apply</button>
                                        </div>
                                        <c:if test="${!requestScope.quiz.isEmpty()}">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Quiz title</th>
                                                    <th>
                                                        Subject
                                                        <select name="subject" class=" input-sm" style="border-radius: 7px; margin-left: 10px">
                                                            <option ${s.subject_title eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <c:forEach items="${requestScope.subs}" var="s">
                                                                <option ${s.subject_title eq subject ? "selected=\"selected\"" : ""} value="${s.subject_title}">${s.subject_title}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </th>
                                                    <th>Quiz Author</th>
                                                    <th>
                                                        Quiz Category
                                                        <select name="category" class=" input-sm" style="border-radius: 7px; margin-left: 10px">
                                                            <option ${cate.category_name eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <c:forEach items="${requestScope.cates}" var="cate">
                                                                <option ${cate.category_name eq category? "selected=\"selected\"" : ""} value="${cate.category_name}">${cate.category_name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </th>
                                                    <th>Level</th>
                                                    <th>
                                                        Type
                                                        <select name="type" class=" input-sm" style="border-radius: 7px; margin-left: 10px">
                                                            <option ${requestScope.type eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                                            <option ${requestScope.type eq "Free" ? "selected=\"selected\"" : ""} value="Free">Free</option>
                                                            <option ${requestScope.type eq "User Practices" ? "selected=\"selected\"" : ""} value="User Practices">User Practices</option>
                                                        </select>
                                                    </th>
                                                    <th>View Detail</th>
                                                </tr>                     
                                                <c:forEach items="${requestScope.quiz}" var="q">
                                                    <tr>
                                                        <td><a href="../quiz/detail?id=${q.id}">${q.id}</a></td>
                                                        <td><a href="../quiz/detail?id=${q.id}">${q.title}</a></td>
                                                        <td>${q.subject.subject_title}</td>
                                                        <td>${q.author.fullname}</td>
                                                        <td>${q.category.category_name}</td>
                                                        <td>${q.level}</td>
                                                        <td>${q.type}</td>
                                                        <td>
                                                            <button class="label label-info"><a style="color:white;" href="../quiz/detail?id=${q.id}">Details</a></button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tr>
                                            </table>
                                        </c:if>    
                                    </form>
                                    <div id="paggingBottom" class="pageLine" style=" margin: 1%; float: right;">
                                    </div>
                                </div>
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
</html>






