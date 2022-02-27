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

    <div class="container-fluid col-sm-12 post-content">
        
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