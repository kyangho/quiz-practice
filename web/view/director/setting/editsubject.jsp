<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/setting/setting.js?v=3" type="text/javascript"></script>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizz</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>
        </head>
        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel"><div class="col-md-12">
                                        <section class="panel">
                                            <header class="panel-heading">
                                                Edit subject
                                            </header>
                                            <div class="panel-body">
                                                <form action="editsubject" method="POST" class="form-horizontal tasi-form">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 col-sm-2 control-label">Subject ID:</label>
                                                        <div class="col-sm-10" style="padding-top: 7px;">
                                                        ${requestScope.subject.subject_id}
                                                        <input type="hidden" name="subject_id" class="form-control" 
                                                               value="${requestScope.subject.subject_id}" required placeholder="Subject ID">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 col-sm-2 control-label">Subject name:</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" name="subjectName" class="form-control" 
                                                               value="${requestScope.subject.subject_title}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 col-sm-2 control-label">Subject author</label>
                                                    <div class="col-sm-10">
                                                        <select name="subjectAuthor">
                                                            <c:forEach items="${requestScope.subject_author}" var="au">
                                                                <option 
                                                                    <c:if test="${au.fullname eq requestScope.subject.subject_Author.fullname}">
                                                                        selected="selected"
                                                                    </c:if> value="${au.id}">
                                                                    ${au.fullname}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                                    <div class="col-sm-10">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" required name="subjectStatus" id="setting-status" value="Published" 
                                                                       ${requestScope.subject.subject_status == "Published" ? "checked" : ""}>
                                                                Published
                                                            </label>
                                                        </div>
                                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" required name="subjectStatus" id="setting-status" value="Unpublished" 
                                                                       ${(requestScope.subject.subject_status == "Unpublished") ? "checked" : ""}>
                                                                Unpublished
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12">
                                                    <div class="notification-add col-sm-6 center-block">
                                                    </div>
                                                </div>
                                                <div class="col-sm-12">
                                                    <div class="col-sm-4">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-danger center-block col-sm-10"
                                                                onclick="location.href = 'subjectlist'">
                                                            Discard
                                                        </button>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="submit" class="btn btn-info center-block col-sm-10">Update subject</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </section>
                                </div>
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>





