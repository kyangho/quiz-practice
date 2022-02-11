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
        <title>Add New User</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>
        </head>
        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="panel">
                        <header class="panel-heading">
                            Add new user
                        </header>
                        <div class="panel-body">
                            <form class="form-horizontal tasi-form add-form" action="adduser" method="POST">
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Username *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="username" class="form-control" required placeholder="username">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Full Name *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="fullname" class="form-control" required placeholder="Full Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Email *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="email" class="form-control" required placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Phone *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="phone" class="form-control" required placeholder="Phone Number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Address: </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="address" class="form-control" required placeholder="Address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Gender *:</label>
                                    <div class="col-sm-10" style="display: flex;">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="gender" value="male" checked="checked"> Male
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="Gender" value="femail"> Female
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Status *:</label>
                                    <div class="col-sm-10" style="display: flex;">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="status" value="Active" checked="checked"> Active
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="status" value="deactive"> Deactive
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Role *: </label>
                                    <div class="col-sm-10" style="display: flex;">
                                    <c:forEach items="${requestScope.roles}" var="r">
                                        <div class="radio" style="margin-left: -19px;">
                                            <input type="checkbox" name="roleID" value="${r.id}"
                                                   <c:if test="${r.roleName eq 'Student'}">checked="checked"</c:if>>${r.roleName}
                                            </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-sm-12" style="color: red; margin-left: 10%;">
                                Note: (*) is required.
                            </div>
                            <div class="col-sm-12">
                                <div class="col-sm-4">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-danger center-block col-sm-10"
                                            onclick="location.href = 'userlist'">
                                        Discard
                                    </button>
                                </div>
                                <div class="col-sm-2">
                                    <button type="submit" class="btn btn-info center-block col-sm-10">Add</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </aside>
        </div>
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>
