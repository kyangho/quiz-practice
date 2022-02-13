<%-- 
    Document   : postlist
    Created on : Jan 29, 2022, 4:00:11 PM
    Author     : ducky
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/price-range.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/post.css?v=1" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
    .blog-post-area 
    .post-meta ul li {
        background: #F0F0E9;
        float: left;
        margin-right: 10px;
        padding: 0 5px;
        font-size: 11px;
        color: #393b3b;
        position: relative;
    }
    ul li {
        list-style: none;

    }
    .row {
        display: flex;
        flex-wrap: wrap;
    }
    .blog-post-area 
    .post-meta ul li i {
        background: #FE980F;
        color: #FFFFFF;
        margin-left: -4px;
        margin-right: 7px;
        padding: 4px 7px;
    }
    .fa, .fas {
        font-weight: 900;
    }
    .fa, .far, .fas {
        font-family: "Font Awesome 5 Free";
    }
    .fa, .fab, .fad, .fal, .far, .fas {
        -moz-osx-font-smoothing: grayscale;
        -webkit-font-smoothing: antialiased;
        display: inline-block;
        font-style: normal;
        font-variant: normal;
        text-rendering: auto;
        line-height: 1;
    }
    .blog-post-area 
    .single-blog-post h3 {
        color: #696763;
        font-size: 16px;
        font-family: 'Roboto',sans-serif;
        text-transform: uppercase;
        font-weight: 500;
        margin-bottom: 17px;
    }
    h2.title {
        color: #FE980F;
        font-family: 'Roboto', sans-serif;
        font-size: 18px;
        font-weight: 700;
        margin: 0 15px;
        text-transform: uppercase;
        margin-bottom: 30px;
        position: relative;
    }
    .text-center {
        text-align: center !important;
    }
    .panel-group {
        margin-bottom: 20px;
    }
    .category-products {
        border: 1px solid #F7F7F0;
        margin-bottom: 35px;
        padding-bottom: 20px;
        padding-top: 15px;
    }

    /* Style to create scroll bar in dropdown */ 
    .scrollable-dropdown{
        height: auto;
        max-height:320px;  /* Increase / Decrease value as per your need */
        overflow-x: hidden;
    }
</style>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=3" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>

    <div class="container col-sm-12">
        <div class="row">
            <div class="col-sm-3">
                <h1>User</h1>
                <div class="left-sidebar">
                    <div class="card col-sm-12">
                        <img src="img_avatar.png" alt="Avatar" style="width:100%">
                        <div class="container">
                            <h4><b>${requestScope.post.author}</b></h4> 
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <h1>Content</h1>
            <c:if test="${requestScope.post == null}">
                <h2 class="title text-center">Can not find post</h2>
            </c:if>
            <c:if test="${requestScope.post != null}">
                <div class="blog-post-area">
                    <h2 class="title text-center">${post.title}</h2>
                    <div class="single-blog-post">
                        <h3>${post.brief}</h3>
                        <div class="post-meta">
                            <ul>
                                <li><i class="fa fa-user"></i><a href="list?author=${post.author}">${post.author}</a></li>
                                <li>
                                    <i class="fa fa-clock-o"></i>
                                    <fmt:formatDate value="${post.dateCreated}" type = "time" timeStyle = "short"></fmt:formatDate>
                                    </li>
                                    <li>
                                        <i class="fa fa-calendar"></i>                                     
                                    <fmt:formatDate value="${post.dateCreated}" type = "date" timeStyle = "short"></fmt:formatDate>
                                    </li>
                                <c:forEach items="${post.categories}" var="category">
                                    <li class="category-label">
                                        <a class="label label-info btn" href="list?category=${category.name}" >${category.value}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <a href="">
                            <img  class="col-sm-12" src="${pageContext.request.contextPath}/post/image?id=${post.id}" alt="">
                        </a>
                        <p>${post.content}</p>.
                        <c:if test="${post.author == sessionScope.account.username}">
                            <a class="btn btn-danger" href="update?id=${post.id}">Edit</a>
                        </c:if>
                            <div>
                                <h6>Attach file:</h6>
                                <a href="file?id=${requestScope.post.id}">${requestScope.fileName}</a>
                            </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>