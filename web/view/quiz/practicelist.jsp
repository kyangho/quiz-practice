<%-- 
    Document   : practicelist
    Created on : Feb 24, 2022, 10:40:47 PM
    Author     : conmu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-nowrap">
        <jsp:include page="leftmenu.jsp"></jsp:include>
            <div class="col py-3">
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
                                            <p class="quiz">${q.questions.size()} Qs</p>
                                            <p style="background-color: red; border-radius: 10px; color: white; text-align: center;">Result</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="paging">   
                            <nav
                                <ul class="pager">
                                    <c:if test="${requestScope.pageindex > 1}">
                                        <li class="page-item"><a style="background-color: #026407;" class="page" onclick="pagging(this, ${requestScope.pageindex - 1}, 1000)">Previous</a></li>
                                        </c:if>
                                        <c:if test="${requestScope.quizs.size() >= requestScope.pagesize}">
                                        <li class="page-item"><a style="background-color: #026407;" class="page" onclick="pagging(this, ${requestScope.pageindex + 1}, 1000)">Next</a></li>
                                        </c:if>
                                </ul>
                            </nav>
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
                                margin-left: 94%;
                                color: white;
                                box-shadow: 0px 0px 4px 1px black;
                            }
                        </style>
                        <script language='javascript'>
                            function pagging(a, index, timeout) {
                                console.log(window.location.href);
                                var uri = window.location.href;
                                if (uri.indexOf("&pageindex=") != -1) {
                                    uri = uri.split("&pageIndex=")[0];
                                    uri = uri + '&';
                                } else if (uri.indexOf("?pageindex=") != -1) {
                                    uri = uri.split("?pageindex=")[0];
                                    uri = uri + '?';
                                } else if (uri.indexOf("list?") == -1) {
                                    uri = uri + '?';
                                } else if (uri.indexOf("&pageindex=") == -1) {
                                    uri = uri + '&';
                                }

                                window.location.href = uri + 'pageindex=' + index;
                                console.log(window.location.href);
                            }
                        </script>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>