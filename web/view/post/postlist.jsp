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

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=3" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>

    <div class="container col-sm-12">
    <c:if test="${sessionScope.account != null}">
        <div class="col-sm-12">
            <a class="btn btn-success col-sm-2" style="float: right;" href="new">Write new post</a>
        </div>
    </c:if>
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
                            <c:if test="${post.content.length() > 100}">
                                <c:set var="shortDesc" value="${post.content.substring(0, 100)}" />
                                <p>${shortDesc} ...</p>.
                            </c:if>
                            <c:if test="${post.content.length() < 100}">
                                <p>${post.content}</p>.
                            </c:if>
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
                    <li><a  onclick="pagging(this, ${requestScope.pageIndex - 1}, 1000)">Previous</a></li>
                    </c:if>
                    <c:if test="${requestScope.posts.size() >= requestScope.pageSize}">
                    <li><a  onclick="pagging(this, ${requestScope.pageIndex + 1}, 1000)">Next</a></li>
                    </c:if>
            </ul>
        </div>
    </div>
</div>

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

        window.location.href = uri + 'pageIndex=' + index;
        console.log(window.location.href);
    }
</script>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>