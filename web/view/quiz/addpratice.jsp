<jsp:include page="header.jsp"></jsp:include>
    <div class="row" style="margin: 1% 0;">
        <div class="col-lg-4 col-sm-3">
            <a class="btn btn-circle btn-success" href="list"><i class="bi bi-arrow-return-left"></i> Practice List</a>
        </div>
        <div class="col-sm-9 col-lg-7">
            <form action="details" method="POST">
                <div class="form-outline mb-4" style="display: flex;">
                    <input type="search" name="keySearch" class="form-control"placeholder="Find a quiz">
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
                <div class="container-xxl py-5">
                    <div class="row" style="margin-bottom: 1%;">
                        <div class="col-lg-10 col-sm-10">
                            <h5 class="mb-3">Subject Name</h5>
                        </div>
                        <div class="col-lg-2 col-sm-2">
                            <a href="details" style="float: right;"><h5>See more</h5></a>
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
                    </div>
                </div>
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
        margin-left: 94%;
        color: white;
        box-shadow: 0px 0px 4px 1px black;
    }
</style>
<jsp:include page="footer.jsp"></jsp:include>