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

<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">-->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" rel="stylesheet">

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=3" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="container col-md-12">
        <div class="row">
        <h1 class="text-center">Posts</h1> 
        <c:if test="${requestScope.posts.isEmpty()}">
            <h2 class="title text-center">Hiện tại không có post nào.</h2>
        </c:if>
        <c:if test="${!requestScope.posts.isEmpty()}">
            <div class="col-md-12">
                <div class="card mb-3">
                    <div class="card-header pr-0 pl-0">
                        <div class="row no-gutters align-items-center w-100">
                            <div class="col-1 font-weight-bold pl-4">General</div>
                            <div class="col-8 font-weight-bold pl-3"></div>
                            <div class="d-none d-md-block col-3 text-muted">
                                <div class="row no-gutters align-items-center">
                                    <div class="col-6">Last update</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${requestScope.posts}" var="post">
                        <div class="card-body py-3">
                            <div class="row no-gutters align-items-center">
                                <div class="col-1 pl-1"><img class="" src="${pageContext.request.contextPath}/post/image?id=${post.id}" height="60px" width="60px"/></div>
                                <div class="col overflow-hidden"><a href="detail?id=${post.id}" class="text-big font-weight-semibold" data-abc="true">${post.title}</a></div>
                                <div class="d-none d-md-block col-3">
                                    <div class="row no-gutters align-items-center">
                                        <div class="media col-6 align-items-center"> <img src="https://i.imgur.com/Ur43esv.jpg" alt="" class="d-block ui-w-30 rounded-circle">
                                            <div class="media-body flex-truncate ml-2"> <a href="list?author=${post.author}" class="d-block text-truncate" data-abc="true">${post.author}</a>
                                                <div class="text-muted small text-truncate">2d ago &nbsp;·&nbsp; <a href="javascript:void(0)" class="text-muted" data-abc="true"></a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>            
                </div>
            </div>
            <!--                    <div class="blog-post-area wow fadeIn" data-wow-delay="0.1s">
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
    </div>-->
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