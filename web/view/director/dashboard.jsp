<%-- 
    Document   : dashboard
    Created on : Mar 13, 2022, 5:25:06 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quizz</title>
        <jsp:include page="header/linkcss.jsp"></jsp:include>
        </head>
        <body class="skin-black">
        <jsp:include page="header/headerforadmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="panel">

                        <div class="panel-body">
                            <div class="col-md-3">
                                <div class="sm-st clearfix">
                                    <span class="sm-st-icon st-red"><i class="fa fa-check-square-o"></i></span>
                                    <div class="sm-st-info">
                                        <span>3200</span>
                                        Total Subjects
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="sm-st clearfix">
                                    <span class="sm-st-icon st-blue"><i class="fa fa-question"></i></span>
                                    <div class="sm-st-info">
                                        <span>3200</span>
                                        Total Questions
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="sm-st clearfix">
                                    <span class="sm-st-icon st-green"><i class="fa fa-trophy"></i></span>
                                    <div class="sm-st-info">
                                        <span>4567</span>
                                        Total Pratices
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <header class="panel-heading">
                                    Trend quiz practice
                                </header>
                                <div class="panel-body">
                                    <div class="daterange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 25%">
                                        <i class="fa fa-calendar"></i>&nbsp;
                                        <span></span> <i class="fa fa-caret-down"></i>
                                        <p class="daterange-text inline" style="padding-left: 10px"></p>
                                    </div>
                                    <canvas id="linechart" width="1053" height="501" style="width: 843px; height: 401px;"></canvas>
                                </div> 
                            </div>
                        </div>


                    </section>
                </aside>
            </div>
        <jsp:include page="header/linkjavascript.jsp"></jsp:include>
    </body>
</html>

