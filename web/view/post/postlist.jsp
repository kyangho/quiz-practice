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
        <div class="">
            <a class="btn btn-success pull-right" href="new">Write new post</a>
        </div>
        <div class="row col-sm-12">
            <div class="col-sm-3">
                <h1>Filter</h1>
                <div class="left-sidebar">
                    <div class="input-group col-sm-12">
                        <!--                        <div class="input-group-btn search-panel col-sm-3">
                                                    <button class="btn btn-secondary dropdown-toggle overflow-hidden col-sm-12" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <span id="search_concept">All</span> <span class="caret"></span>
                                                    </button>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                        <a class="dropdown-item" href="#Action">Action</a>
                                                        <a class="dropdown-item" href="#Action">Something else here</a>
                                                    </div>
                                                </div>
                                                <input type="hidden" name="search_param" value="all" id="search_param">-->
                        <input type="text" class="form-control" required name="postSearch" id="search" placeholder="Search">
                        <span class="input-group-btn">
                            <button class="btn btn-default btn-search" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                        <div class="col-sm-12"> 
                            <fieldset class="sort-border">
                                <legend class="sort-border">Category</legend>
                                <legend class="sort-border hidden">Category</legend>
                                <div class="control-group">
                                    <select class="select select-initialized col-sm-12 select-category" id="filter-sort-select">
                                        <option value="" disabled="" selected="">${requestScope.currentCategory == null ? "All categories" : requestScope.currentCategory.value}</option>
                                    <c:if test="${requestScope.currentCategory != null}">
                                        <option class="category-select" value="">All categories</option>
                                    </c:if>

                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <option class="category-select" value="${category.name}">${category.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <div class="shipping text-center"><!--shipping-->
                    <img src="/images/home/shipping.jpg" alt="">
                </div><!--/shipping-->
            </div>
        </div>
        <div class="col-sm-9">
            <h1 class="text-center">Posts                
            </h1>   


            <c:if test="${requestScope.posts.isEmpty()}">
                <h2 class="title text-center">Hiện tại không có post nào.</h2>
            </c:if>
            <c:if test="${!requestScope.posts.isEmpty()}">
                <c:forEach items="${requestScope.posts}" var="post">
                    <div class="blog-post-area wow fadeIn" data-wow-delay="0.1s">
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
                            <a href="detail?id=${post.id}" class="col-sm-12">
                                <img  class="col-sm-12" src="${pageContext.request.contextPath}/post/image?id=${post.id}" alt="">
                            </a>
                            <p>${post.content}</p>.
                            <a class="btn btn-primary" href="detail?id=${post.id}">Read More</a>
                            <c:if test="${post.author == sessionScope.account.username}">
                                <a class="btn btn-danger" href="update?id=${post.id}">Edit</a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>            
            </c:if>
            <ul class="pager">
                <c:if test="${requestScope.pageIndex > 1}">
                    <li><a href="list?pageIndex=${requestScope.pageIndex - 1}">Previous</a></li>
                    </c:if>
                    <c:if test="${requestScope.posts.size() >= requestScope.pageSize}">
                    <li><a href="list?pageIndex=${requestScope.pageIndex + 1}">Next</a></li>
                    </c:if>
            </ul>
        </div>
    </div>
</div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>