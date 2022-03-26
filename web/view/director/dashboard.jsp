<%-- 
  Document   : dashboard
  Created on : Mar 13, 2022, 5:25:06 PM
  Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quizz</title>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link href="../../css/dashboard.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="header/linkcss.jsp"></jsp:include>
        </head>
        <body class="skin-black">
        <jsp:include page="header/headerforadmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="header/leftmenuforadmin.jsp"></jsp:include>
                <style>

                    .tab_navigation{
                        padding: 0;
                        margin: 0;
                        list-style: none;
                        display: flex;
                        justify-content: center;
                    }
                    .tab_navigation li{
                        display: inline-block;
                        background-color: cadetblue;
                        padding: 5px 10px;
                        min-width: 100px;
                        text-align: center;
                        cursor: pointer;
                        transition: all 0.3 ease-in-out;
                        line-height: 80px;
                        margin: 5px;
                    }
                    .tab_navigation li h3{
                        padding-bottom: 5px;
                    }
                    .tab_navigation li.active{
                        background-color: #F05050;
                    }

                    .tab_container{  
                        display: none;
                    }

                </style>
                <aside class="right-side">
                    <section class="panel">
                        <div class="panel-body">
                            <div class="col-md-3">
                                <div class="sm-st clearfix">
                                    <span class="sm-st-icon st-red"><i class="fa fa-check-square-o"></i></span>
                                    <div class="sm-st-info">
                                        <span>${totalSubject}</span>
                                    Total Subjects
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="sm-st clearfix">
                                <span class="sm-st-icon st-blue"><i class="fa fa-question"></i></span>
                                <div class="sm-st-info">
                                    <span>${totalQuestion}</span>
                                    Total Questions
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="sm-st clearfix">
                                <span class="sm-st-icon st-green"><i class="fa fa-trophy"></i></span>
                                <div class="sm-st-info">
                                    <span>${totalQuiz}</span>
                                    Total Quiz
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="sm-st clearfix">
                                <span class="sm-st-icon st-blue"><i class="fa-solid fa-rectangle-list"></i></span>
                                <div class="sm-st-info">
                                    <span>${totalTest}</span>
                                    Total Test
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <ul class="tab_navigation mb-5">
                                <li class="rounded-2"><h3>Practice Quiz</h3></li>
                                <li class="rounded-2"><h3>Subject</h3></li>
                                <li class="rounded-2"><h3>Question</h3></li>
                            </ul>
                            <div class="tab_container_area">
                                <div class="tab_container">
                                     <h2 class="mb-5">Trends of user's quiz practices</h2>
                                    <form action="/QuizPractice/director/dashboard" method="POST">
                                        <div class="row ml-2">
                                            <div class="col-3">
                                                <select name="filter" style="font-size: 18px;" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                                                    <option selected>Select</option>
                                                    <option value="1">All</option>
                                                    <option value="2">Last 7 days</option>
                                                </select>
                                            </div>
                                            <div class="col-3">
                                                <button style="font-size: 18px;" class="btn btn-success">Filter</button>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="container-fluid">
                                        <table id="table-1" class="table table-striped table-bordered" style="width:100%">
                                            <thead>
                                                <tr style="background-color: #28a745 !important;">
                                                    <th>Quiz Name</th>
                                                    <th>Subject Name</th>
                                                    <th>Category Name</th>
                                                    <th>Views</th>
                                                    <th>Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="item" items="${listQuiz}">
                                                    <tr>
                                                        <td>${item.quizName}</td>
                                                        <td>${item.subjectName}</td>
                                                        <td>${item.subCategoryName}</td>
                                                        <td>${item.views}</td>
                                                        <td>${item.dateCreated}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>                              
                                        </table>
                                    </div>
                                </div>
                                <div class="tab_container">
                                    <h2 class="mb-5">List Subject</h2>
                                    <div class="container-fluid">
                                        <table id="table-2" class="table table-striped table-bordered" style="width:100%">
                                            <thead>
                                                <tr style="background-color: #28a745 !important;">
                                                    <th>Subject Name</th>
                                                    <th>Author Name</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="item" items="${listSubject}">
                                                    <tr>
                                                        <td>${item.subjectName}</td>
                                                        <td>${item.authorName}</td>
                                                        <td>${item.status}</td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>                              
                                        </table>
                                    </div>
                                </div>
                                <div class="tab_container">
                                     <h2 class="mb-5">List Question</h2>
                                    <div class="container-fluid">
                                        <table id="table-3" class="table table-striped table-bordered" style="width:100%">
                                            <thead>
                                                <tr style="background-color: #28a745 !important;">
                                                    <th>Question Name</th>
                                                    <th>Subject Name</th>
                                                    <th>Category Name</th>
                                                    <th>Question Level</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="item" items="${listQuestion}">
                                                    <tr>
                                                        <td>${item.questionName}</td>
                                                        <td>${item.subjectName}</td>
                                                        <td>${item.subjectCategoryName}</td>
                                                        <td>${item.level}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>                              
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <%--<jsp:include page="header/linkjavascript.jsp"></jsp:include>--%>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#table-1').DataTable();
                $('#table-2').DataTable();
                $('#table-3').DataTable();
            });
            $(document).ready(function () {
                $('.tab_container:first').show();
                $('.tab_navigation li:first').addClass('active');

                $('.tab_navigation li').click(function (event) {
                    index = $(this).index();
                    $('.tab_navigation li').removeClass('active');
                    $(this).addClass('active');
                    $('.tab_container').hide();
                    $('.tab_container').eq(index).show();
                });
            });
        </script>
    </body>
</html>
<script>

</script>
