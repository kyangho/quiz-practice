<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="row" style="margin: 2%;">
    <c:if test="${sessionScope.account!= null}">
        <div class="col-lg-4 col-sm-3 row">
            <a style="margin-bottom: 2%;" class="col-lg-7 btn btn-circle btn-success" href="list"><i class="bi bi-arrow-return-left"></i> Practice List</a>
            <a class="col-lg-7  btn btn-circle btn-danger" href="#"><i class="bi bi-bookmark-plus"></i></i> Create new Quiz</a>
        </div>
    </c:if>
    <c:if test="${sessionScope.account == null}">
        <div class="col-lg-3 col-sm-3">
        </div>
    </c:if>
    <div class="col-sm-9 col-lg-7">
        <form action="details" method="POST">
            <div class="form-outline mb-4" style="display: flex;">
                <input type="text" value="${requestScope.keySearch}" name="keySearch" class="form-control"placeholder="Find a quiz">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </form>
    </div>
</div>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col py-3">
            <!-- Service Start -->
            <div class="container-xxl py-5" style="margin-left: 7%;">
                <c:if test="${keySearch == null}">
                    <div class="row" style="margin-bottom: 1%; margin-left: 2%;">
                        <div class="col-lg-10 col-sm-10">
                            <h5 class="mb-3">Some outstanding quizzes</h5>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row g-4">
                            <c:forEach items="${requestScope.quizs}" var="q">
                                <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.1s">
                                    <div class="service-item text-center pt-3" style="box-shadow: 0px 0px 6px 1px grey;">
                                        <a href="details?quizID=${q.id}">
                                            <div class="p-4" style="text-align: left;">
                                                <div class="img-responsive ImagePreviewLoader">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/img/quiz1.jpg" alt=""/>
                                                </div>
                                                <h5 class="mb-3">${q.title}</h5>
                                                <p>By: ${q.author.fullname}</p>
                                                <p class="quiz">${q.questions.size()} Qs</p>
                                                <!--<p style="background-color: red; border-radius: 10px; color: white; text-align: center;">Result</p>-->
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <c:if test="${keySearch != null}">
                    <div class="container">
                        <div class="row g-4">
                            <c:forEach items="${requestScope.quizs}" var="q">
                                <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.1s">
                                    <div class="service-item text-center pt-3" style="box-shadow: 0px 0px 6px 1px grey;">
                                        <a href="details?quizID=${q.id}">
                                            <div class="p-4" style="text-align: left;">
                                                <div class="img-responsive ImagePreviewLoader">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/img/quiz1.jpg" alt=""/>
                                                </div>
                                                <h5 class="mb-3">${q.title}</h5>
                                                <p>By: ${q.author.fullname}</p>
                                                <p class="quiz">${q.questions.size()} Qs</p>
                                                <!--<p style="background-color: red; border-radius: 10px; color: white; text-align: center;">Result</p>-->
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="paging">   
                                <nav style="margin: 0 75%;">
                                    <ul class="pager" style="display: flex;">
                                        <c:if test="${requestScope.pageindex > 1}">
                                            <li class="page-item"><a style="background-color: #026407;" class="page" onclick="pagging('${requestScope.url}', ${requestScope.pageindex - 1}, '${requestScope.keySearch}')">Previous</a></li>
                                            </c:if>
                                            <c:if test="${requestScope.quizs.size() >= requestScope.pagesize}">
                                            <li class="page-item"><a style="background-color: #026407;" class="page" onclick="pagging('${requestScope.url}', ${requestScope.pageindex + 1}, '${requestScope.keySearch}')">Next</a></li>
                                            </c:if>
                                    </ul>
                                </nav>
                            </div>
                            <script language='javascript'>
                                function pagging(url, index, key) {
//                                    console.log(window.location.href);
//                                    var uri = window.location.href;
//                                    if (uri.indexOf("&pageindex=") != -1) {
//                                        uri = uri.split("&pageIndex=")[0];
//                                        uri = uri + '&';
//                                    } else if (uri.indexOf("?pageindex=") != -1) {
//                                        uri = uri.split("?pageindex=")[0];
//                                        uri = uri + '?';
//                                    } else if (uri.indexOf("details?") == -1) {
//                                        uri = uri + '?';
//                                    } else if (uri.indexOf("&pageindex=") == -1) {
//                                        uri = uri + '&';
//                                    }

                                    window.location.href = url + index;
//                                    uri = uri + '?keySearch=' + key +'&pageindex=' + index;
//                                    console.log(uri);
                                }
                            </script>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<style>
    .quiz{
        width: 66px;
        background-color: #999c9b;
        color: white;
        text-align: center;
        border-radius: 11px;
        box-shadow: 0px 0px 6px 2px #5a5757;
    }

    .page{
        /*margin-left: 94%;*/
        color: white;
        width: 100px;
        box-shadow: 0px 0px 4px 1px black;
    }
</style>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>