<%-- 
    Document   : list_user
    Created on : Feb 8, 2022, 3:39:20 PM
    Author     : Yankee
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            /*display: flex;*/
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            display: flex;
            /*flex-direction: column;*/
            justify-content: end;
            align-items: end;
        }

        form {
            color: #555;
            display: flex;
            padding: 2px;
            border: 1px solid currentColor;
            border-radius: 5px;
            margin: 0 0 30px;
            width: 300px;
            float: right
        }

        input[type="search"] {
            border: none;
            background: transparent;
            margin: 0;
            padding: 7px 8px;
            font-size: 14px;
            color: inherit;
            border: 1px solid transparent;
            border-radius: inherit;
            width: 250px
        }

        input[type="search"]::placeholder {
            color: #bbb;
        }

        button[type="submit"] {
            text-indent: -999px;
            overflow: hidden;
            width: 40px;
            padding: 0;
            margin: 0;
            border: 1px solid transparent;
            border-radius: inherit;
            background: transparent url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' class='bi bi-search' viewBox='0 0 16 16'%3E%3Cpath d='M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z'%3E%3C/path%3E%3C/svg%3E") no-repeat center;
            cursor: pointer;
            opacity: 0.7;
        }

        button[type="submit"]:hover {
            opacity: 1;
        }

        button[type="submit"]:focus,
        input[type="search"]:focus {
            box-shadow: 0 0 3px 0 #1183d6;
            border-color: #1183d6;
            outline: none;
        }

        form.nosubmit {
            border: none;
            padding: 0;
        }

        input.nosubmit {
            border: 1px solid #555;
            width: 100%;
            padding: 9px 4px 9px 40px;
            background: transparent url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' class='bi bi-search' viewBox='0 0 16 16'%3E%3Cpath d='M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z'%3E%3C/path%3E%3C/svg%3E") no-repeat 13px center;
        }
        .filter{
            width: 300px;
            height: 74px;
        }
    </style>
    <div data-v-0a48d845="" class="all-games">
        <div data-v-3f400e64="" data-v-0a48d845="" class="activity-sub-route-container"><!----><!---->
            <div data-v-3f400e64="" class="completed-games-wrapper">
                <div data-v-3f400e64="" class="completed-games">
                    <div data-v-8d3099dc="" data-v-3f400e64="" class="text-unselectable"><!---->
                        <!--search-->
                        <div class="container">
                            <div class="filter">
                                <form style="border:none;" action="listquiz" method="POST">
                                    <input style="height: 41px;" type="Submit">
                                    <table style="margin: 0;" class="table table-hover">
                                        <th>
                                            Subject
                                            <select name="subject_status" class=" input-sm">
                                                <option ${requestScope.subject_status eq "all" ? "selected=\"selected\"" : ""} value="all">All</option>
                                            <c:forEach items="${requestScope.subs}" var="s">
                                                <option ${requestScope.subject_status eq "Published" ? "selected=\"selected\"" : ""} value="${s.subject_id}">${s.subject_title}</option>
                                            </c:forEach>
                                        </select>
                                    </th>
                                </table>
                            </form>
                        </div>
                        <form action="#" method="POST">
                            <input type="search" placeholder="Search quizzes by the title" name="quiz_title" value="${valueSearch}">
                            <button type="submit">Search</button>
                        </form>

                    </div>

                    <div data-v-8d3099dc="" class="completed-games-list">

                        <!--phan quiz-->
                        <c:forEach items="${requestScope.quiz}" var="q">
                            <div data-v-8d3099dc="" class="completed-game">
                                <div data-v-0a33e012="" data-v-8d3099dc="" class="game-card-container">
                                    <div data-v-0a33e012="" class="route-loader-wrapper">
                                    </div>
                                    <div data-v-0a33e012="">

                                        <div data-v-76782db8="" data-v-0a33e012="" class="curved-edge-container media-dimensions media-wrapper">
                                            <div data-v-76782db8="" class="curve" style="width: 400%; padding-top: 400%; transform: translateX(-37.5%) translateY(-100%); top: 100%;">
                                                <div data-v-76782db8="" class="content-container" style="width: calc(25.02%); left: calc(37.49%);">
                                                    <div data-v-0a33e012="" data-v-76782db8="" role="img" aria-label="Quiz thumbnail" class="media-dimensions media" style="background-image: url(../../img/img_quiz.jpg);">                                                      
                                                    </div>                                                     
                                                </div>                                                
                                            </div>
                                        </div><!---->
                                        <div data-v-0a33e012="" class="game-type-info">
                                            <span data-v-0a33e012="" class="game-type-icon"><i data-v-0a33e012="" class="icon-fas-chalkboard-teacher"></i></span>
                                            &nbsp; ${q.subject.subject_title}
                                        </div>
                                        <div data-v-0a33e012="" class="quiz-info">
                                            <div data-v-0a33e012="" class="questions-length">Level: ${q.level}</div>
                                        </div>
                                        <p data-v-0a33e012="" class="quiz-name" style="display: -webkit-box; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; -webkit-line-clamp: 2;">${q.title}</p>
                                        <div data-v-0a33e012="" class="game-details">
                                            <div data-v-0a33e012="" class="assignment-info">
                                                <span data-v-0a33e012="" class="assigned-by-title">By: </span>
                                                <span data-v-0a33e012="" class="assigned-by">${q.author.fullname}</span>
                                            </div>
                                        </div>
                                        <div data-v-0a33e012="" class="great accuracy-bar">
                                            <span data-v-0a33e012="" class="accuracy-bar-text">95% accuracy</span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>
