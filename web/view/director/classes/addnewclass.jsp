<%-- 
    Document   : addnewclass
    Created on : Feb 26, 2022, 4:22:43 PM
    Author     : Vu Duc Tien
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
                            Add new class
                        </header>
                        <div class="panel-body">
                            <form class="form-horizontal tasi-form add-form" action="addnewclass" method="POST">
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Class Name *:</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="className" class="form-control" required placeholder="Class Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Author *:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="author" id="author">
                                            <option value="-1">Choose author</option>
                                        <c:forEach items="${requestScope.teachers}" var="t">
                                            <option value="${t.id}">${t.fullname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label"></label>
                                <div class="col-sm-4" id="author_alert"></div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Note: </label>
                                <div class="col-sm-4">
                                    <textarea name="note" cols="50" rows="10" placeholder="Class note"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status *:</label>
                                <div class="col-sm-5" style="display: flex;">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="status" value="ACTIVE" checked="checked"/> Active
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="status"  value="Deactive"/> Deactive
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6" style="color: red; margin-left: 10%;">
                                Note: (*) is required.
                            </div>
                            <div class="col-sm-12">
                                <div class="col-sm-2">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-danger center-block col-sm-10"
                                            onclick="location.href = 'classlist'">
                                        Discard
                                    </button>
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" onclick="checkAuthor()" class="btn btn-info center-block col-sm-10">Add</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${requestScope.done == true}">
                            <p>Add new class successful</p>
                        </c:if>
                    </div>
                </section>
            </aside>
        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
    <script>
        function checkAuthor() {
            var authorID = document.getElementById("author").value;
            if (authorID == -1) {
                document.getElementById("author_alert").innerHTML = "<p>Please choose author</p>";
            }
            if (authorID > 0) {
                document.getElementById("author_alert").innerHTML = "";
                document.getElementById("form").onsubmit = function () {
                    return true;
                }
            }
        }
    </script>
</html>

