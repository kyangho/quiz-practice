<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="row" style="margin: 2%;">
        <div class="left col-lg-3 col-md-3"><div class="col-lg-12 col-sm-3">
                <a class="btn btn-circle btn-success" href="list"><i class="bi bi-arrow-return-left"></i> Practice List</a>
                 <a class="btn btn-circle btn-danger" href="add">Create Practice</a>
            </div>
            <div class="col-lg-10" style="margin-top: 1%;">
                <h5 class="mb-3">Choose Quiz:</h5>
            </div>
            <form action="details" method="GET" id="frmQuiz">
                <select name="quizID" class="form-select form-select-sm mb-3" onchange="submitForm()">
                <c:forEach items="${requestScope.practices}" var="p">
                    <option ${p.quiz.id == requestScope.quiz.id ? "selected=\"selected\"" : ""} value="${p.quiz.id}">${p.quiz.name}</option>
                </c:forEach>
            </select>
        </form>
        <script language='javascript'>
            function submitForm() {
                document.getElementById("frmQuiz").submit();
            }
        </script>
    </div>
    <div class="righ col-lg-8 row">
        <div class="col-lg-10 col-sm-6" data-wow-delay="0.1s">
            <div class="service-item" style="box-shadow: 0px 0px 6px 1px grey;">
                <div class="img-responsive ImagePreviewLoader">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/img/quiz2.jpg" alt=""/>
                </div>
                <div style="text-align: left; margin: 2%;">
                    <h5 class="mb-3">${requestScope.practice.quiz.name}</h5>
                    <p>By: ${requestScope.practice.quiz.author.fullname}</p>
                    <p class="quiz">Number of question: ${requestScope.practice.quiz.questions.size()} Qs</p>
                    <p>Subject: ${requestScope.practice.quiz.subject.subject_title}</p>
                    <p>Level: ${requestScope.practice.quiz.level}</p>
                    <p>Status: ${requestScope.practice.quiz.type}</p>
                    <p>Result: ${requestScope.practice.rate}</p>
                    <p>Sample questions:</p>
                    <div style="background-color: #47424233;margin:0 1%;padding: 1% 5%;color: gray;border-radius: 8px;">
                        <ol>
                            <c:forEach items="${requestScope.practice.quiz.questions}" var="q">
                                <li>${q.content}</li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
                <div class="button" style="margin: 0 10%;">
                    <button style="width: 300px; height: 66px; border-radius: 8px; font-size: 26px;" class="btn btn-success btn-circle a">
                        <a style="color: white;" href="quiz/join?quizId=${requestScope.quiz.id}">Practice</a></button>
                    <button style="width: 300px; height: 66px; border-radius: 8px; font-size: 26px;" class="btn btn-danger btn-circle">
                        <a style="color: white;" href="#">Review</a></button>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>