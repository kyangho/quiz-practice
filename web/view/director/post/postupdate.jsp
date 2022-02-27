<%-- 
    Document   : settingdetails
    Created on : Jan 11, 2022, 7:23:39 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script language='javascript'>
    function imageRefresh(img, timeout) {
        setTimeout(function () {
            var d = new Date;
            var http = img.src;
            if (http.indexOf("&d=") != -1) {
                http = http.split("&d=")[0];
            }

            img.src = http + '&d=' + d.getTime();
        }, timeout);
    }
    function fileRefresh(a, timeout) {
        setTimeout(function () {
            var d = new Date;
            var http = a.href;
            if (http.indexOf("&d=") != -1) {
                http = http.split("&d=")[0];
            }

            a.href = http + '&d=' + d.getTime();
        }, timeout);
    }
</script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=6" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ajax/setting/setting.js?v=2" type="text/javascript"></script>

<title>Quiz Practice</title>
<jsp:include page="../header/linkcss.jsp"></jsp:include>

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
                                    Write new post
                                </header>
                                <div class="panel-body">
                                    <form class="" action="update" method="POST" enctype="multipart/form-data">  
                                        <input type="text" name="id" hidden required value="${requestScope.post.id}">
                                    <div class="form-group">
                                        <div class="col-sm-11">
                                            Title<p class="text-danger text-bold" style="display: inline"> *</p>:
                                            <input type="text" name="postTitle" class="form-control" 
                                                   required placeholder="Post title"
                                                   value="${requestScope.post.title}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <img class="col-sm-10" id="image-thumbnail" src="${pageContext.request.contextPath}/director/post/image?id=${requestScope.post.id}" onload="imageRefresh(this, 1000)"  height="200px"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="file-thumbnail" class="col-sm-2 col-form-label">Update thumbnail</label>
                                        <div class="col-sm-10">
                                            <input class="file thumbnail" id="file-thumbnail" accept="image/*" type="file" 
                                                   name="thumbnail" style="color: transparent"/>
                                            <label for="file-thumbnail" class="file-thumbnail">
                                                ${requestScope.post.thumbnail == null ? "Select file" : requestScope.post.title}</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-11">
                                            Brief<p class="text-danger" style="display: inline"> *</p>:
                                            <textarea class="form-control rounded-2" name="postBrief" 
                                                      placeholder="Write brief-information here" 
                                                      rows="3" required value="">${requestScope.post.brief}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-11">
                                            Content<p class="text-danger" style="display: inline"> *</p>:
                                            <textarea class="form-control rounded-2" name="postContent" 
                                                      placeholder="Write content here" 
                                                      rows="20" required value="">${requestScope.post.content}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-11">
                                            Category<p class="text-danger" style="display: inline"> *</p>
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
                                    <div class="form-group">
                                        <div class="col-sm-2">Attach files</div>
                                        <div class="col-sm-10">
                                            <input class="file" id="file-attach" type="file" name="files" style="color: transparent"   />
                                            <a class="file-href" href="file?id=${requestScope.post.id}" onload="fileRefresh(this, 1000)">${requestScope.fileName}</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label" style="text-align: right; padding-top: 8px">Status</label>
                                        <div class="col-sm-10">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" required name="status" id="post-status" value="PUBLISH" ${requestScope.post.status == "PUBLISH" ? "checked" : ""}>
                                                    Publish
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" required name="status" id="post-status" value="UNPUBLISH">
                                                    Unpublish
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" style="text-align: right; padding-top: 8px">Feature</label>
                                        <div class="col-sm-10">
                                            <div class="radio">
                                                <input type="radio" required name="feature" id="post-feature" value="true" ${requestScope.post.isFeature ? "checked" : ""}>
                                                True
                                            </div>
                                            <div class="radio">
                                                <input type="radio" required name="feature" id="post-feature" value="false">
                                                False
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 pad-20">
                                        <div class="notification-update col-sm-6 text-center center-block">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="col-sm-4">
                                        </div>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-danger center-block col-sm-12"
                                                    onclick="location.href = 'list'">
                                                Discard
                                            </button>
                                        </div>
                                        <div class="col-sm-2">
                                            <button type="submit" class="btn btn-info center-block col-sm-12 btn-update">Update</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>
                    </div>
                </div>
            </section>
        </aside>
    </div>
    <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
</body>