<%-- 
    Document   : settingdetails
    Created on : Jan 11, 2022, 7:23:39 PM
    Author     : ducky
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=6" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ajax/setting/setting.js?v=2" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/post.css?v=1" rel="stylesheet">

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Practice</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>

        </head>

        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>

            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                        <div class="row">
                            <div class="col-md-12">
                                <section class="panel">
                                    <header class="panel-heading">
                                        Post content
                                    </header>
                                    <div class="panel-body">
                                        <div class="blog-post-area">
                                            <h2 class="title text-center">${requestScope.post.title}</h2>
                                        <div class="single-blog-post">
                                            <h3>${requestScope.post.brief}</h3>
                                            <div class="post-meta">
                                                <ul>
                                                    <li><i class="fa fa-user"></i><a href="list?author=${post.author}">${post.author}</a></li>
                                                    <li>
                                                        <i class="fa fa-clock-o"></i>
                                                    <fmt:formatDate value="${requestScope.post.dateCreated}" type = "time" timeStyle = "short"></fmt:formatDate>
                                                    </li>
                                                    <li>
                                                        <i class="fa fa-calendar"></i>                                     
                                                    <fmt:formatDate value="${requestScope.post.dateCreated}" type = "date" timeStyle = "short"></fmt:formatDate>
                                                    </li>
                                                    <c:forEach items="${requestScope.post.categories}" var="category">
                                                        <li class="category-label">
                                                            <a class="label label-info btn" href="list?category=${category.name}" >${category.value}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="col-sm-12">
                                                <a href="">
                                                    <img  class="col-sm-12 rounded img-fluid w-100 img-responsive pt-2" src="${pageContext.request.contextPath}/post/image?id=${post.id}" alt="">
                                                </a>
                                            </div>
                                            <div style="white-space: pre-line;">
                                                <p>
                                                    ${requestScope.post.content}
                                                </p>
                                            </div>
                                            <c:if test="${requestScope.post.author == sessionScope.account.username}">
                                                <a class="btn btn-danger" href="update?id=${requestScope.post.id}">Edit</a>
                                            </c:if>
                                            <div>
                                                <h6>Attach file:</h6>
                                                <a href="file?id=${requestScope.post.id}">${requestScope.fileName}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>