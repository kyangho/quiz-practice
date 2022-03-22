<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <script>
        function doDelete(id) {
            var cf = confirm("Are you sure delete this subject ?");
            if (cf) {
                window.location.href = "deletequiz?quiz_id=" + id;
            }
        }
    </script>
    <div class="row" style="margin: 2%;">
        <div class="left col-lg-3 col-md-3">
            <div class="col-lg-12 col-sm-3">
                <a class="btn btn-circle btn-success" href="listquiz"><i class="bi bi-arrow-return-left"></i> List Quiz</a>
            </div>
        <%--</c:if>--%>
    </div>
    <div class="righ col-lg-8 row">
        <div class="col-lg-10 col-sm-6" data-wow-delay="0.1s">
            <div class="service-item" style="box-shadow: 0px 0px 6px 1px grey;">
                <div class="img-responsive ImagePreviewLoader">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/img/quiz2.jpg" alt=""/>
                </div>
                <div style="text-align: left; margin: 2%;">
                    <h6 class="mb-3">ID: ${requestScope.quiz.id}</h6>
                    <h5 class="mb-3">${requestScope.quiz.title}</h5>
                    <p>Subject: ${requestScope.quiz.subject.subject_title}</p>
                    <p>Category: ${requestScope.quiz.category.category_value}</p>
                    <p>Level: ${requestScope.quiz.level}</p>
                    <p>Type quiz: ${requestScope.quiz.type}</p>
                    <p>Author: ${requestScope.quiz.author.fullname}</p>
                    <p>Time start: ${requestScope.quiz.startTime}</p>
                    <p>Time end: ${requestScope.quiz.endTime}</p>
                    <p>Rate: ${requestScope.quiz.rate}</p>
                    <p>Sample questions: </p> 
                    <div style="background-color: #47424233;margin:0 1%;padding: 1% 5%;color: gray;border-radius: 8px;">
                        <c:forEach items="${requestScope.quiz.questions}" var="q">
                            <p>${q.content}</p>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${sessionScope.account != null  && sessionScope.account.fullname eq requestScope.quiz.author.fullname}">
                    <div class="button" style="margin: 0 30%;">
                        <button style="width: 300px; height: 66px; border-radius: 8px; font-size: 26px;" class="btn btn-success btn-circle a">
                            <a style="color: white;" href="editquiz?id=${quiz.id}">Edit</a>
                        </button>
                    </div>
                    <div class="button" style="margin: 20px 30%;">
                        <button onclick="doDelete(${requestScope.quiz.id});" style="width: 300px; height: 66px; border-radius: 8px; font-size: 26px;" class="btn btn-success btn-circle a">
                            Delete
                        </button>
                    </div>
                </c:if>
                <br>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>