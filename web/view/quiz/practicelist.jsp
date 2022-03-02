<%-- 
    Document   : practicelist
    Created on : Feb 24, 2022, 10:40:47 PM
    Author     : conmu
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-nowrap">
        <jsp:include page="leftmenu.jsp"></jsp:include>
            <div class="col py-3 col-lg-9">
                <!-- Service Start -->
                <div class="container-xxl py-5">
                    <div class="row" style="margin-bottom: 1%;">
                        <div class="col-lg-10">
                            <h5 class="mb-3">Practice List</h5>
                        </div>
                        <div class="col-lg-2">
                            <a class="btn btn-danger" href="details">Add practice</a>
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
                                            <p>Start date: <fmt:formatDate pattern="dd-MM-yyyy" value="${q.startTime}"/> </p>
                                            <p class="quiz">${q.questions.size()} Qs</p>
                                            <p style="<c:if test="${q.rate < 30}">
                                               background-color: red;
                                                </c:if>
                                                <c:if test="${q.rate > 30 && q.rate < 75}">
                                                    background-color: #e09519;
                                                </c:if>
                                                <c:if test="${q.rate > 75}">
                                                    background-color: #086404;
                                                </c:if> border-radius: 10px; color: white; text-align: center;">${q.rate}%</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                        <div>
                            <nav aria-label="Page navigation example">
                                <ul id="paggingBottom" class="pagination page">
                                </ul>
                            </nav>
                        </div>
                        <script>
                            generatePagger('paggingBottom',${requestScope.pageindex},${requestScope.totalPage}, '${requestScope.url}', 1);
                            function generatePagger(div, pageIndex, totalpage, url, gap) {
                                var container = document.getElementById(div);
                                if (pageIndex - gap > 0)
                                    container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + (pageIndex - 1) + '">Previous</a></li>';
                                for (var i = (pageIndex) - gap; i < pageIndex; i++) {
                                    if (i > 0)
                                        container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + i + '">' + i + '</a></li>';
                                }
                                container.innerHTML += '<li class="page-item active"><span class="page-link">' + pageIndex + '</span></li>';
                                for (var i = (pageIndex) + 1; i <= pageIndex + gap; i++) {
                                    if (i <= totalpage)
                                        container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + i + '">' + i + '</a></li>';
                                }
                                if (pageIndex + gap < totalpage)
                                    container.innerHTML += '<li class="page-item"><a class="page-link" href="' + url + (pageIndex + 1) + '">Next</a></li>';
                            }
                        </script>
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
                                float: right;
                                color: white;
                                box-shadow: 0px 0px 4px 1px black;
                            }
                        </style>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>