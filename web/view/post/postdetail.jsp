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
<link href="${pageContext.request.contextPath}/css/post.css?v=4" rel="stylesheet"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/documentAjax.js?v=3" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>

    <div class="container-fluid col-sm-11 pt-50 post-content">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <div class="row card col-sm-12" id="filterbar">
                        <div id="search"> 
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
        <div class="col-sm-9">
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
                            <img  class="col-sm-12 rounded img-fluid w-100 img-responsive pt-2" src="${pageContext.request.contextPath}/post/image?id=${post.id}" alt="">
                        </a>
                        <div style="white-space: pre-line;">
                            ${post.content}
                        </div>
                        <div style="padding-top: 30px">
                            <h6>Attach file:</h6>
                            <c:if test="${sessionScope.account == null}">
                                <c:if test="${requestScope.post.postFiles.size() != 0}">
                                    <div>You must <a href="${pageContext.request.contextPath}/login">Login</a> or <a href="${pageContext.request.contextPath}/register">Register</a> to download this attach.</div>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                                <c:if test="${requestScope.post.postFiles.size() != 0}">
                                    <a href="file?id=${requestScope.post.id}">${requestScope.fileName}</a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>