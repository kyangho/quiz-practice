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
        <c:if test="${requestScope.post == null}">
            <h2 class="title text-center">Can not find post</h2>
        </c:if>
        <c:if test="${requestScope.post != null}">
            <div class="single-blog-post">
                <form class="update-form" action="update" method="POST" enctype="multipart/form-data">
                    <input type="text" name="id" hidden class="form-control" value="${requestScope.post.id}">
                    <div class="form-group row">
                        <label for="postTitle" class="col-sm-2 col-form-label">Title</label>
                        <div class="col-sm-10">
                            <input type="text" name="postTitle" class="form-control" id="postTitle" placeholder="Title" value="${requestScope.post.title}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="postBrief" class="col-sm-2 col-form-label">Brief</label>
                        <div class="col-sm-10">
                            <input type="text" name="postBrief" class="form-control" id="postBrief" placeholder="Brief information for post" value="${requestScope.post.brief}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="postContent" class="col-sm-2 col-form-label">Content</label>
                        <div class="col-sm-10">
                            <textarea class="form-control rounded-2" name="postContent" id="postContent" placeholder="Write content here" rows="10" value="">${requestScope.post.content}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">Category</div>
                        <div class="col-sm-10">
                            <c:forEach items="${requestScope.categories}" var="category">
                                <c:set var="isHasCategory" scope="request" value="false"></c:set>
                                <c:forEach items="${requestScope.post.categories}" var="postCategory">
                                    <c:if test="${category.name == postCategory.name}">
                                        <c:set var="isHasCategory" scope="request" value="true"></c:set>
                                    </c:if>
                                </c:forEach>
                                <div class="form-check">
                                    <input name="categories" class="form-check-input" type="checkbox" id="${category.name}" value="${category.id}" ${isHasCategory == "true" ? "checked" : ""}>
                                    <label class="form-check-label" for="${category.name}">
                                        ${category.value}
                                    </label>
                                </div>
                            </c:forEach>


                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">Update thumbnail</div>
                        <div class="col-sm-5">
                            <input class="form-control" type="file" name="thumbnail"  />
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">Attach files</div>
                        <div class="col-sm-5">
                            <input class="form-control" type="file" name="files"  />
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary update-btn">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>