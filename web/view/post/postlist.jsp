<%-- 
    Document   : postlist
    Created on : Jan 29, 2022, 4:00:11 PM
    Author     : ducky
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/post.css?v=4" rel="stylesheet"/>

<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">-->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/ajax/documentAjax.js?v=3" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="container-fluid pt-50 post-list-container col-md-11 text-center ">
        <div class="d-flex flex-wrap justify-content-between mb-4">
            <!--<div> <button type="button" class="btn btn-shadow btn-wide btn-primary"> <span class="btn-icon-wrapper pr-2 opacity-7"> <i class="fa fa-plus fa-w-20"></i> </span> New thread </button> </div>-->
            <div class="col-12 col-md-3 p-0 mb-3">  </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <div class="row card col-sm-12" id="filterbar">
                        <div id="search" style="margin-top: 20px"> 
                            <input id="input-search" placeholder="Search..." /> 
                            <button><i class="fa fa-search"></i></button>
                        </div>
                        <div class="form-group text-center">
                            <div class="btn-group" data-toggle="buttons"> 
                                <label class="btn btn-success form-check-label"> 
                                    <input class="form-check-input" type="radio"> Reset 
                                </label> 
                                <!--                                <label class="btn btn-success form-check-label active"> 
                                                                    <input class="form-check-input" type="radio" checked=""> Apply 
                                                                </label> -->
                            </div>
                        </div>
                        <div class="box border-bottom">
                            <div class="box-label text-uppercase d-flex align-items-center">Category <button class="btn ml-auto" type="button" data-toggle="collapse" data-target="#inner-box" aria-expanded="false" aria-controls="inner-box" id="out" onclick="outerFilter()"> <span class="fas fa-plus"></span> </button> </div>
                            <div id="inner-box" class="collapse mt-2 mr-1">
                            <c:forEach items="${requestScope.categories}" var="category">
                                <div class="my-1 category-check"> 
                                    <label class="tick">
                                        ${category.value} 
                                        <input type="radio" name="category" value="${category.name}"> 
                                        <span class="check"></span> 
                                    </label> 
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <c:forEach items="${requestScope.featurePosts}" var="featurePost">
                                    <div class="feature-post">
                                        <img class="" src="image?id=${featurePost.id}" style="max-width: 100px">
                                        <div style="text-align: left; padding-left: 5px">
                                            <a href="detail?id=${featurePost.id}" >${featurePost.title}</a>
                                        </div>
                                    </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!--                    <div class="box border-bottom">
                                            <div class="box-label text-uppercase d-flex align-items-center">Status <button class="btn ml-auto" type="button" data-toggle="collapse" data-target="#inner-box-status" aria-expanded="false" aria-controls="inner-box-status" id="out" onclick="outerFilter()"> <span class="fas fa-plus"></span> </button> </div>
                                            <div id="inner-box-status" class="collapse mt-2 mr-1">
                                                <div class="my-1"> <label class="tick">Publish <input type="radio"  name="status" value="PUBLISH"> <span class="check"></span> </label> </div>
                                                <div class="my-1"> <label class="tick">Private <input type="radio"  name="status" value="PRIVATE"> <span class="check"></span> </label> </div>
                                            </div>    
                                        </div>-->
                </div>
            </div>
        </div>
        <div class="col-sm-9 cards">
            <c:if test="${requestScope.posts.size() == 0}">
                <h1>Nothing to show</h1>
            </c:if>
            <c:if test="${requestScope.posts.size() > 0}">
                <c:forEach items="${requestScope.posts}" var="post">
                    <div class="card p-3 post-card">
                        <div class="row">
                            <div class="col-md-2 d-flex align-items-center" style="padding: 0px">
                                <div class="position-relative snipimage"> 
                                    <a href="detail?id=${post.id}"><img src="image?id=${post.id}" class="rounded img-fluid w-100 img-responsive"></a> 
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="mt-2">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <h5 class="mb-1"><a href="detail?id=${post.id}">${post.title}</a> </h5>
                                        <c:if test="${post.isFeature}">
                                            <span><i class="fa fa-star" style="color: yellow; font-size: 1.5rem"></i> </span>
                                        </c:if>
                                    </div>
                                    <div style="text-align: left">
                                        <span class="days-ago" >
                                            ${post.brief}
                                        </span>
                                    </div>

                                    <div class="d-flex justify-content-md-start justify-content-between views-content mt-2">
                                        <div class="d-flex flex-row align-items-center"> 
                                            <c:forEach items="${post.categories}" var="category">
                                                <a class="badge badge-success align-text-bottom ml-1" href="list?category=${category.name}">${category.value}</a> 
                                            </c:forEach>
                                        </div>
                                        <div class="d-flex flex-row align-items-center ms-2">
                                            <c:if test="${post.status == 'PUBLISH'}">
                                                <span class="badge badge-primary align-text-bottom ml-1" href="list?status=${post.status}">${post.status}</span> 
                                            </c:if>
                                            <c:if test="${post.status != 'PUBLISH'}">
                                                <span class="badge badge-danger align-text-bottom ml-1" href="list?status=${post.status}">${post.status}</span> 
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row mt-3"> 
                                        <!--<img src="https://i.imgur.com/rvQ3LAt.jpg" width="50" class="rounded-circle">-->
                                        <div class="ms-2 d-flex flex-column">
                                            <div class="d-flex flex-row align-items-center">
                                                <h6 class="text-muted pr-2">Post by </h6><a href="list?author=${post.author}"><h6 style="color: #06BBCC"> ${post.author}</h6></a> <span class="dots"></span>
                                            </div> 
                                            <span class="days-ago" style="text-align: left">
                                                Last update:
                                                <fmt:formatDate value="${post.dateModified}" type = "date" timeStyle = "short"></fmt:formatDate>,
                                                <fmt:formatDate value="${post.dateModified}" type = "time" timeStyle = "short"></fmt:formatDate>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </c:forEach>
                <nav>
                    <ul class="pagination pb-2">
                        <li class="page-item"><a class="page-link" href="#" onclick="pagging(this, 1, 10000)" data-abc="true">«</a></li>
                            <c:forEach begin="${requestScope.pageIndex - 1 > 0 ? requestScope.pageIndex - 1 : 1}" end="${requestScope.pageIndex + 1 >= pageTotal ? pageTotal : requestScope.pageIndex + 1}" var="i">
                            <li class="page-item ${i == requestScope.pageIndex ? "active" : ""}"><a class="page-link" href="#" onclick="pagging(this, ${i}, 10000)" data-abc="true">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a class="page-link" href="#" onclick="pagging(this, ${requestScope.pageTotal}, 10000)"  data-abc="true">»</a></li>
                    </ul>
                </nav>
            </c:if>
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

        window.location.href = uri + `pageIndex=` + index;
        console.log(window.location.href);
    }
</script>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>
