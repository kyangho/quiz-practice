<%-- 
    Document   : result
    Created on : Mar 12, 2022, 9:17:28 PM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/result.css?v=1" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap_for_admin/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap_for_admin/css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <title>Quiz Result</title>
    </head>
    <body style="background-color: #682564;">
        <div style="margin: 10% 25%;background-color: #210820; border-radius: 8px;">
            <div style="text-align: center; color: white; font-size: 40px; padding: 3% 0;">Quiz Result</div>
            <div class="result_lable">
                <div class="num">${percent}%</div>
                <div class="title_under">Percent</div>
            </div>
            <c:if test="${pass}">
                <div class="result_lable" style="background-color: green; font-size: 30px; text-align: center; color: white;">
                    PASS
                </div>
            </c:if>
            <c:if test="${!pass}">
                <div class="result_lable" style="background-color: red; font-size: 30px; text-align: center; color: white;">
                    <div style="margin: 4% auto;">NOT PASS</div>
                </div>
            </c:if>
            <div class="result_lable" style="background-color: #546939">
                <div class="num">${numCorrect}</div>
                <div class="title_under">Correct</div>
            </div>
            <div class="result_lable" style="background-color: #493307">
                <div class="num">${numWrong}</div>
                <div class="title_under">Wrong</div>
            </div>
            <div class="button" style="background-color: #210820">
                <a href="${pageContext.request.contextPath}/quiz/game/review" class="btn btn-danger center-block col-sm-2">Done</a>
                <a href="${pageContext.request.contextPath}/quiz/game/review" class="btn btn-info center-block col-sm-2">Review</a>
            </div>
        </div>
        <style>
            .button{

                width: 35%;
                border-radius: 8px;
                padding: 6px 0;
                -webkit-box-sizing: border-box;
                position: relative;
                overflow: hidden;
                margin: 0 auto;
                display: flex;
                padding: 30px 0px;
            }
            .button a{
                width: 100px;
            }
            .result_lable{
                display: inline-block;
                vertical-align: top;
                width: 40%;
                height: 80px;
                border-radius: 8px;
                padding: 6px 0;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                position: relative;
                border: 1px solid #000;
                overflow: hidden;
                margin: 1% 4% 1% 5%;
                background-color: black;
            }
            .num{
                height: 60%;
                text-align: center;
                color: white;
                font-size: 30px;
            }
            .title_under{
                height: 40%;
                text-align: center;
                color: white;  
                font-size: 20px;
            }
        </style>
    </body>
</html>