<%-- 
    Document   : settingdetails
    Created on : Jan 11, 2022, 7:23:39 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/postajax.js?v=6" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ajax/setting/setting.js?v=2" type="text/javascript"></script>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Practice</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>

        </head>

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
                                        <form class="form-horizontal tasi-form new-form" action="new" method="POST" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <div class="col-sm-11">
                                                    Title<p class="text-danger text-bold" style="display: inline"> *</p>:
                                                    <input type="text" name="postTitle" class="form-control" required placeholder="Post title">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-11">
                                                    Brief<p class="text-danger" style="display: inline"> *</p>:
                                                    <textarea class="form-control rounded-2" name="postBrief" 
                                                              placeholder="Write brief-information here" 
                                                              rows="3" required></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-11">
                                                    Content<p class="text-danger" style="display: inline"> *</p>:
                                                    <textarea class="form-control rounded-2" name="postContent" 
                                                              placeholder="Write content here" 
                                                              rows="20" required></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-11">
                                                    Category<p class="text-danger" style="display: inline"> *</p>
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
                                        <div class="form-group">
                                            <div class="col-sm-5">
                                                Thumbnail
                                                <input class="form-control" id="file-thumbnail" type="file" name="thumbnail" accept="image/*" required="" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-5">
                                                File attach:
                                                <input class="form-control" id="file-attach" type="file" name="files"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                            <div class="col-sm-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" required name="status" id="post-status" value="PUBLISH" checked>
                                                        Publish
                                                    </label>
                                                </div>
                                                <label class="col-sm-2 col-sm-2 control-label"></label>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" required name="status" id="post-status" value="UNPUBLISH">
                                                        Unpublish
                                                    </label>
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
                                                        onclick="location.href = 'settinglist'">
                                                    Discard
                                                </button>
                                            </div>
                                            <div class="col-sm-2">
                                                <button type="submit" class="btn btn-info center-block col-sm-12 btn-update">Post</button>
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
</html>