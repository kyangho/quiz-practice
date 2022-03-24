<%-- 
    Document   : postlist
    Created on : Jan 29, 2022, 4:00:11 PM
    Author     : kienct
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
<style>
    .cke_top, .cke_bottom{
        display: none !important;
    }
    .cke_chrome{
        outline: none;
        border: none !important;
    }


</style>

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
                            <div id="inner-box" class="collapse mt-2 mr-1 show">
                            <c:forEach items="${requestScope.categories}" var="category">
                                <div class="my-1 category-check"> 
                                    <label class="tick">
                                        ${category.value} 
                                        <input type="radio" name="category" value="${category.value}"> 
                                        <span class="check"></span> 
                                    </label> 
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <c:forEach items="${requestScope.featureTests}" var="featureTest">
                                <div class="feature-post">
                                    <img class="" src="${pageContext.request.contextPath}/img/quiz2.jpg" style="max-width: 100px">
                                    <div style="text-align: left; padding-left: 5px">
                                        <a href="detail?id=${featureTest.id}" >${featureTest.name}</a>
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
            <c:if test="${requestScope.test == null}">
                <h2 class="title text-center">Can not find test</h2>
            </c:if>
            <c:if test="${requestScope.test != null}">
                <div class="blog-post-area ">
                    <h2 class="title text-center">${requestScope.test.name}</h2>
                    <div class="single-blog-post">
                        <div class="post-meta">
                            <ul>
                                <li><i class="fa fa-user"></i><a href="list?author=${requestScope.test.author.username}">${requestScope.test.author.username}</a></li>
                                <li>
                                <li class="category-label">
                                    <a class="label label-info btn" href="list?category=${test.category.category_value}" >${test.category.category_value}</a>
                                </li>
                            </ul>
                        </div>
                        <a href="">
                            <img  class="col-sm-12 rounded img-fluid w-100 img-responsive pt-2" src="${pageContext.request.contextPath}/img/quiz2.jpg" alt="">
                        </a>
                        <div class="col-sm-12" style="margin-left: -20px">
                            <textarea name="postContent"  id="postContent" contenteditable="false" rows="10" class="col-sm-12">
                                ${requestScope.test.description}
                            </textarea>

                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>
    <script>
        CKEDITOR.replace('postContent', {
            customConfig: '${pageContext.request.contextPath}/ckeditor/emptyButtonConfig.js'
        });
</script>
