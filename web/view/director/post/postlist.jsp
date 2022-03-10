<%-- 
    Document   : settinglist
    Created on : Jan 12, 2022, 7:42:08 PM
    Author     : conmu
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Practice</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/ajax/postajax.js?v=6" type="text/javascript"></script>
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
                                        <div class="col-sm-10">Post List</div>
                                        <a class="btn label-danger center-block col-sm-2" href="new">Write new post </a>
                                    </div>
                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15" style="float: right;">
                                            <div class="box-tools m-b-15">
                                                <div class="input-group">
                                                    <input type="text" name="search" id="search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <table class="table table-hover table-condensed" id="post-table">
                                                <thead>
                                                    <tr class="table-header">
                                                        <th style="width: 3%">ID</th>
                                                        <th style="width: 15%">Thumbnail</th>
                                                        <th style="width: 34%">
                                                            <div>
                                                                <div>Title</div>
                                                                <div class="sort-icon">
                                                                    <a href="list?title=asc&sort"><i class="fa fa-angle-up"></i></a> 
                                                                    <a href="list?title=desc&sort"><i class="fa fa-angle-down"></i></a> 
                                                                </div>
                                                            </div>
                                                        </th>   
                                                        <th style="width: 10%">
                                                            <div>
                                                                <div>Category</div>
                                                                <div class="sort-icon">
                                                                    <a href="list?category=asc&sort"><i class="fa fa-angle-up"></i></a> 
                                                                    <a href="list?category=desc&sort"><i class="fa fa-angle-down"></i></a> 
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th style="width: 10%">
                                                            <div>
                                                                <div>Author</div>
                                                                <div class="sort-icon">
                                                                    <a href="list?author=asc&sort"><i class="fa fa-angle-up"></i></a> 
                                                                    <a href="list?author=desc&sort"><i class="fa fa-angle-down"></i></a> 
                                                                </div> 
                                                            </div>
                                                        </th>
                                                        <th style="width: 10%">
                                                            <div>
                                                                <div>Featured</div>
                                                                <div class="sort-icon">
                                                                    <a href="list?feature=asc&sort"><i class="fa fa-angle-up"></i></a> 
                                                                    <a href="list?feature=desc&sort"><i class="fa fa-angle-down"></i></a> 
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th  style="width: 10%">
                                                            <div>
                                                                <div>Status</div>
                                                                <div class="sort-icon">
                                                                    <a href="list?status=asc&sort"><i class="fa fa-angle-up"></i></a> 
                                                                    <a href="list?status=desc&sort"><i class="fa fa-angle-down"></i></a> 
                                                                </div> 
                                                            </div>
                                                        </th>
                                                        <th style="width: 8%">
                                                            <div>
                                                                <div>Action</div>
                                                            </div>
                                                        </th>
                                                    </tr>
                                                </thead>
                                            <c:forEach items="${requestScope.posts}" var="post">
                                                <tr>
                                                    <td>${post.id}</td>
                                                    <td><img src="image?id=${post.id}" class="thumbnail" style="max-width: 150px !important"></td>
                                                    <td>${post.title}</td>
                                                    <td><c:forEach items="${post.categories}" var="category"><a href="list?category=${category.name}" class="label label-success">${category.value}</a></c:forEach></td>
                                                    <td>${post.author}</td>
                                                    <td><a href="list?feature=${post.isFeature}" ${post.isFeature ? "class='label label-success'" : "class='label label-danger'"}>${post.isFeature}  </a>
                                                        <a class="button-change-feature" value="${post.id}" feature="${post.isFeature}" style="padding: 5px"><i class="fa-solid fa-arrows-rotate" ></i></a></td>
                                                    <td><a href="list?status=${post.status}" ${post.status == "PUBLISH" ? "class='label label-success'" : "class='label label-danger'"}>${post.status}</a></td>
                                                    <td>
                                                        <a href="view?id=${post.id}" class="label label-primary">View</a>
                                                        <a href="update?id=${post.id}" class="label label-success">Update</a><br>
                                                        <a class="button-show-post" value="${post.id}" status="${post.status}"><i ${post.status eq 'PUBLISH' ? "class='fa fa-eye'" : "class='fa fa-eye-slash'"}></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <nav>
                                            <ul class="pagination pb-2">
                                                <li class="page-item"><a class="page-link" href="#" onclick="pagging(this, 1, 10000)" data-abc="true">«</a></li>
                                                    <c:forEach begin="${requestScope.pageIndex - 1 > 0 ? requestScope.pageIndex - 1 : 1}" end="${requestScope.pageIndex + 1 >= pageTotal ? pageTotal : requestScope.pageIndex + 1}" var="i">
                                                    <li class="page-item ${i == requestScope.pageIndex ? "active" : ""}"><a class="page-link" href="#" onclick="pagging(this, ${i}, 10000)" data-abc="true">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item"><a class="page-link" href="#" onclick="pagging(this, ${requestScope.pageTotal}, 10000)"  data-abc="true">»</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
    <script language='javascript'>
        function pagging(a, index, timeout) {
            console.log(window.location.href);
            var uri = window.location.href;
            if (uri.indexOf("&pageIndex=") != -1) {
                uri = uri.split("&pageIndex=")[0];
                uri = uri + '&';
            } else if (uri.indexOf("?pageIndex=") != -1) {
                uri = uri.split("?pageIndex=")[0];
                uri = uri + '?';
            } else if (uri.indexOf("list?") == -1) {
                uri = uri + '?';
            } else if (uri.indexOf("&pageIndex=") == -1) {
                uri = uri + '&';
            }

            window.location.href = uri + `pageIndex=` + index;
            console.log(window.location.href);
        }
    </script>

</html>
