<%-- 
    Document   : adduser
    Created on : Feb 10, 2022, 9:25:30 PM
    Author     : conmu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quizz</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>
        </head>
        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="panel">
                        <header class="panel-heading">
                            Question Details
                        </header>
                        <div style="display: flex;">
                            <div class="panel-body col-lg-8">
                                <form id="updateQues" class="form-horizontal tasi-form add-form" enctype="multipart/form-data" action="details" method="POST">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Quiz *:</label>
                                        <div class="col-sm-10">
                                            <select style="width: 100%;" name="quiz">
                                            <c:forEach items="${quizs}" var="q">
                                                <option ${quizId == q.id ? "selected=\"selected\"" : ""} value="${q.id}">${q.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Subject *:</label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="questionId" value="${requestScope.question.id}">
                                        <select style="width: 100%;" name="subject">
                                            <c:forEach items="${subjects}" var="s">
                                                <option ${s.subject_id eq question.subject.subject_id ? "selected=\"selected\"" : ""} value="${s.subject_id}">${s.subject_title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Category *:</label>
                                    <div class="col-sm-10">
                                        <select id="cate" style="width: 100%;" name="category" onchange="findSubcategory(${question.id})">
                                            <c:forEach items="${categories}" var="c">
                                                <option ${c.category_id eq question.category.category_id ? "selected=\"selected\"" : ""} value="${c.category_id}">${c.category_value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Sub-category *:</label>
                                    <div class="col-sm-10">
                                        <select style="width: 100%;" name="subcategory">
                                            <c:forEach items="${subcate}" var="sc">
                                                <option ${sc.id eq question.subCategory.id ? "selected=\"selected\"" : ""} value="${sc.id}">${sc.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Content *:</label>
                                    <div class="col-sm-10">
                                        <textarea class="ckeditor" name="content"cols="55" rows="5">${question.content}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Level *: </label>
                                    <div class="col-sm-10" style="display: flex;">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" <c:if test="${question.level eq 'Easy'}">checked="checked"</c:if> name="level" value="Easy"> Easy 
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="level" <c:if test="${question.level eq 'Medium'}">checked="checked"</c:if> value="Medium"> Medium
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="level" <c:if test="${question.level eq 'Difficult'}">checked="checked"</c:if> value="Difficult"> Difficult
                                                </label>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Status *:</label>
                                        <div class="col-sm-10" style="display: flex;">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="status" value="publish" 
                                                    <c:if test="${question.status eq 'publish'}">checked="checked"</c:if> > Publish
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="status" value="unpublish"
                                                    <c:if test="${question.status eq 'unpublish'}">checked="checked"</c:if>> Un publish
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Media : </label>
                                        <div class="col-sm-10">
                                        <c:if test="${question.media != null}">
                                            <img id="media" src="${pageContext.servletContext.contextPath}/question/media?questionid=${question.id}" class="img-fluid" >
                                        </c:if>
                                        <img id="image" style="padding-bottom: 10px" class="img-fluid" >
                                        <input type="file" name="media" id="files" />
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Answers *: </label>
                                    <div id="answer" class="col-sm-10">
                                        <c:forEach items="${question.answers}" var="a">
                                            <div style="margin-bottom: 1%; display: flex;">
                                                <span hidden="">${a.content} ${question.correctAnswer}</span>
                                                <input onclick="checkCorrect()"
                                                       <c:if test="${a.id eq question.correctAnswer}"> checked="checked" </c:if>
                                                       style="margin: 10px 5px 0 0;" name="correctAnswer" value="${a.content}" type="radio">
                                                <input class="form-control" name="answer" value="${a.content}">
                                                <button style="margin-left: -5%;" class="btn btn-success" type="button" onclick="location.href = 'delete?anId=${a.id}&quesid=${question.id}'"><i class="fa fa-trash"></i></button>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button style="margin-left: 55%;" class="btn btn-danger" type="button" onclick="addQues()"><i class="fa fa-plus"></i></button>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2"></div>
                                    <div class="col-sm-10" style="color: red;">
                                        Note: (*) is required.
                                    </div>
                                </div>
                                <c:if test="${tag eq 'done'}">
                                    <div class="col-sm-12" style="color: green; margin: 1% 38% 2% 38%;">
                                        Update successful.
                                    </div>
                                </c:if>
                                <div class="col-sm-12">
                                    <div class="col-sm-4">
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-danger center-block col-sm-10"
                                                onclick="location.href = 'list'">
                                            Discard
                                        </button>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="submit" class="btn btn-info center-block col-sm-10">Save</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <script>
            function findSubcategory(questid) {
                var cateid = document.getElementById('cate').value;
                window.location.href = "details?questionId=" + questid + "&cateid=" + cateid;
            }
            function submitForm() {
                document.getElementById('updateQues').submit();
            }
            function addQues() {
                var div = document.getElementById('answer');
                div.innerHTML += '<div id="add" style="margin-bottom: 1%; display: flex;">' +
                        '<input onclick="checkCorrect()" name="correctAnswer" style="margin: 10px 5px 0 0;" class="form-check-input" value="no" type="radio">' +
                        '<input class="form-control" name="answer">' +
                        '<button style="margin-left: -5%;" class="btn btn-success" onclick="deleteQues()" type="button"><i class="fa fa-trash"></i></button>' +
                        '</div>';
            }
            function deleteQues() {
                document.getElementById('add').remove();
            }
            
            document.getElementById("files").onchange = function () {
                var reader = new FileReader();
                reader.onload = function (e) {
                    document.getElementById("image").src = e.target.result;
                };
                reader.readAsDataURL(this.files[0]);
                var media = document.getElementById('media');
                if (media != null) {
                    media.remove();
                }
            };
            
            function checkCorrect() {
                var corrects = document.getElementsByName('correctAnswer');
//                var answers = document.getElementsByName('answer');
                for (var i = 0; i < corrects.length; i++) {
                    if (corrects[i].checked) {
                        corrects[i].value = i;
                    }
                }
            }
            
        </script>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>
