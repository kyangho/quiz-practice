<%-- 
    Document   : result
    Created on : Mar 12, 2022, 4:21:26 PM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="${pageContext.request.contextPath}/css/result.css?v=1" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap_for_admin/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <title>Quiz Review</title>
    </head>
    <body>
        <div class="quiz_box activeQuiz">
            <header>
                <div class="title">Quiz</div>
            </header>
            <form action="review" method="post" style="margin-left: 25%;">
                <label style="margin-right: 1%">Select by:</label>
                <select name="search">
                    <option value="all" ${search eq "all" ? "selected=\"selected\"":""}>All</option>
                    <option value="correct" ${search eq "correct" ? "selected=\"selected\"":""}>Correct</option>
                    <option value="wrong" ${search eq "wrong" ? "selected=\"selected\"":""}>Wrong</option>
                </select>
                <button type="submit">Search</button>
            </form>
            <section>
                <c:forEach items="${requestScope.ques_Anses}" var="qa">
                    <div class="que_text">
                        <span>${qa.question.content}</span>
                    </div>
                    <div class="que-image">

                    </div>
                    <div class="option_list">
                        <c:forEach items="${qa.question.answers}" var="a">
                            <p class="option" ${a.id eq qa.answer ? (qa.question.correctAnswer eq qa.answer ? "style=\"background-color: green;\"" : "style=\"background-color: red;\"") : ""}>${a.content}</p>
                        </c:forEach>
                    </div>
                </c:forEach>
            </section>
        </div>
    </body>
</html>
