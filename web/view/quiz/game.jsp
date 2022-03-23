<%-- 
    Document   : game
    Created on : Mar 9, 2022, 6:12:37 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/quizgamestyle.css?v=1" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap_for_admin/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <title>Quiz Practice</title>
    </head>
    <body>
        <div class="quiz_box">
            <header>
                <div class="title">Quiz</div>
                <div class="timer">
                    <div class="time_left_txt">Time Left</div>
                    <div class="timer_sec">15</div>
                </div>
                <div class="time_line"></div>
            </header>
            <section>
                <i class="fa fa-flag" style="color: red; font-size: 1.5rem; display: none" ></i>
                <div class="que_text" style="display: inline">

                </div>
                <div class="que-image">

                </div>
                <div class="option_list">

                </div>
                <div class="popup">
                    <div class="popup-content">
                        <span class="close-btn">&times;</span>
                        <div class="shortcut-questions">
                            <c:forEach items="${sessionScope.quiz.questions}" var="q" varStatus="i">
                                <div class="shortcut-question" value="${i.index}">${i.index + 1}</div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
            <footer>
                <div class="total_que">
                </div>
                <div>
                    <button class="btn-mark show">Mark question</button>
                    <button class="btn-show-question show" id="popup-btn" style="margin-left: 100px">Show all question</button>
                </div>
                <div class="buttons">
                    <button class="back_btn">Back</button>
                    <button class="next_btn show">Next</button>
                    <button class="submit_btn">Submit</button>
                </div>
            </footer>
        </div>
        <div class="container">
            <div class="start_btn"><button>Start Quiz</button></div>
            <div class="info_box">
                <div class="info-title"><span>Some Rules of this Quiz</span></div>
                <div class="info-list">
                    <div class="info">1. You will have only <span>15 seconds</span> per each question.</div>
                    <div class="info">2. Once you select your answer, it can't be undone.</div>
                    <div class="info">3. You can't select any option once time goes off.</div>
                    <div class="info">4. You can't exit from the Quiz while you're playing.</div>
                    <div class="info">5. You'll get points on the basis of your correct answers.</div>
                </div>
                <div class="buttons">
                    <button class="quit">Exit Quiz</button>
                    <button class="restart">Continue</button>
                </div>
            </div>

            <div class="result_box">
                <div class="icon">
                    <i class="fas fa-crown"></i>
                </div>
                <div class="complete_text">You've completed the Quiz!</div>
                <div class="score_text">
                </div>
                <div class="buttons">
                    <button class="restart">Replay Quiz</button>
                    <button class="quit">Quit Quiz</button>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/quizgame.js?v=1"></script>
            <script></script>
        </div>

        <script>
        </script>
    </body>


</html>

