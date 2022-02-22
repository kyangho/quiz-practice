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
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=4" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>

    <div class="container">
        <div class="">
            <h1>Content</h1>
            <div class="single-blog-post">
                <form class="add-form" action="new" method="POST" enctype="multipart/form-data">
                    <div class="form-group row">
                        <label for="postTitle" class="col-sm-2 col-form-label">Title<p class="text-danger" style="display: inline"> *</p></label>
                        <div class="col-sm-10">
                            <input type="text" name="postTitle" class="form-control" 
                                   id="postTitle" placeholder="Title" required="">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="postBrief" class="col-sm-2 col-form-label">Brief<p class="text-danger" style="display: inline"> *</p></label>
                        <div class="col-sm-10">
                            <input type="text" name="postBrief" class="form-control" 
                                   id="postBrief" placeholder="Brief information for post" required="">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="postContent" class="col-sm-2 col-form-label">Content<p class="text-danger" style="display: inline"> *</p></label>
                        <div class="col-sm-10">
                            <textarea class="form-control rounded-2" name="postContent" 
                                      id="postContent" placeholder="Write content here" rows="10" required=""></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">Category<p class="text-danger" style="display: inline"> *</p></div>
                        <div class="col-sm-10">
                        <c:forEach items="${requestScope.categories}" var="category">
                            <div class="form-check">
                                <input name="categories" class="form-check-input" type="checkbox" 
                                       id="${category.name}" value="${category.id}">
                                <label class="form-check-label" for="${category.name}">
                                    ${category.value}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-2">Thumbnail<p class="text-danger" style="display: inline"> *</p></div>
                    <div class="col-sm-5">
                        <input class="form-control" id="file-thumbnail" type="file" name="thumbnail" accept="image/*" required="" />
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-2">Attach files</div>
                    <div class="col-sm-5">
                        <input class="form-control" id="file-attach" type="file" name="files"  />
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary update-btn">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>