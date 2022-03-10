<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="row" style="margin: 2%;">
        <div class="left col-lg-3 col-md-3">
            <div class="col-lg-12 col-sm-3">
                <a class="btn btn-circle btn-success" href="listquiz"><i class="bi bi-arrow-return-left"></i> List Quiz</a>
            </div>
        </div>
        <div class="righ col-lg-8 row">
            <div class="col-lg-10 col-sm-6" data-wow-delay="0.1s">
                <div class="service-item" style="box-shadow: 0px 0px 6px 1px grey;">
                    <div class="img-responsive ImagePreviewLoader">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/img/quiz2.jpg" alt=""/>
                </div>
                <div style="text-align: left; margin: 2%;">
                    <form action="editquiz" method="POST">
                        <table>
                            <tr>
                                <td>Quiz ID</td>
                                <td style="padding-left: 32px;">
                                    ${requestScope.quiz.id}
                                    <input name="id"  type="hidden" value=" ${requestScope.quiz.id}">
                                </td>
                            </tr>
                            <tr>
                                <td>Quiz Name</td>
                                <td>
                                    <input name="quiz_title" style="width: 501px;  margin-left: 32px;"  type="text" value="${requestScope.quiz.title}">
                                </td>
                            </tr>
                            <tr>
                                <td>Subject</td>
                                <td>
                                    <select name="subject" style="width: 501px;  margin-left: 32px;">
                                        <c:forEach items="${requestScope.subs}" var="a">
                                            <option 
                                                <c:if test="${a.subject_title eq requestScope.quiz.subject.subject_title}">
                                                    selected="selected"
                                                </c:if> value="${a.subject_id}">${a.subject_title}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td>
                                    <select name="category" style="width: 501px;  margin-left: 32px;">
                                        <c:forEach items="${requestScope.category}" var="a">
                                            <option 
                                                <c:if test="${a.category_value eq requestScope.quiz.category.category_value}">
                                                    selected="selected"
                                                </c:if> value="${a.category_id}">${a.category_value}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Level </td>
                                <td>
                                    <select name="level" style="width: 501px;  margin-left: 32px;">
                                        <option 
                                            <c:if test="${(requestScope.quiz.level == 'Easy')}">
                                                selected="selected"
                                            </c:if>
                                            value="Easy">Easy
                                        </option>
                                        <option 
                                            <c:if test="${(requestScope.quiz.level == 'Medium')}">
                                                selected="selected"
                                            </c:if>
                                            value="Medium">Medium
                                        </option>
                                        <option 
                                            <c:if test="${(requestScope.quiz.level == 'Hard')}">
                                                selected="selected"
                                            </c:if>
                                            value="Hard">Hard
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Type quiz </td>
                                <td>
                                    <select name="type" style="width: 501px;  margin-left: 32px;">
                                        <option 
                                            <c:if test="${(requestScope.quiz.type == 'Free')}">
                                                selected="selected"
                                            </c:if>
                                            value="Free">Free
                                        </option>
                                        <option 
                                            <c:if test="${(requestScope.quiz.type == 'User Practices')}">
                                                selected="selected"
                                            </c:if>
                                            value="User Practices">User Practices
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td> Sample questions</td>
                                <td>
                                    <ol>
                                        <c:forEach items="${requestScope.quiz.questions}" var="q">
                                            <li>
                                                <textarea name="question" rows="3" cols="60">${q.content} </textarea>
                                            </li>
                                        </c:forEach>
                                    </ol>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                        <input style="width: 70px" type="submit" value="Save"><br>
                    </form>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>