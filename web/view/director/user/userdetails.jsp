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
                            User Details
                        </header>
                        <div style="display: flex;">
                            <div class="col-lg-4">
                                <div class="avatar" style="box-shadow: 0 0 5px 2px;margin-top: 12%;">
                                <c:if test="${account.avatar eq null && account.gender}">
                                    <img style="    border-radius: 50%; width: 73%; margin: 3% 11%;" src="https://anhdep123.com/wp-content/uploads/2021/05/avatar-trang-dep.jpg" alt=""/>
                                </c:if>
                                <c:if test="${account.avatar eq null && !account.gender}">
                                    <img style="    border-radius: 50%; width: 73%; margin: 3% 11%;" src="https://anhdep123.com/wp-content/uploads/2021/05/hinh-avatar-trang.jpg" alt=""/>
                                </c:if>
                                <c:if test="${account.avatar != null}">
                                    <img style="    border-radius: 50%; width: 73%; margin: 3% 11%" src="${account.avatar}" alt=""/>
                                </c:if>
                            </div>
                        </div>
                        <div class="panel-body col-lg-8">
                            <form class="form-horizontal tasi-form add-form" action="userdetails" method="POST">
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Username *:</label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="id" value="${requestScope.account.id}">
                                        <input type="hidden" name="username" value="${requestScope.account.username}">
                                        <span class="form-control" style="font-weight: bolder;">${requestScope.account.username}</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Full Name *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="fullname" class="form-control" 
                                               pattern="^[a-zA-ZaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴ\s]+$" 
                                               title="Fullname cannot contain special characters!" 
                                               value="${requestScope.account.fullname}"
                                               required placeholder="Full Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Email *:</label>
                                    <div class="col-sm-10">
                                        <input type="email" name="email" class="form-control" 
                                               value="${requestScope.account.email}"
                                               required placeholder="Email">
                                        <c:if test="${requestScope.tag eq 'email'}">
                                            <p style="color: red;">Email has been registered by another person. Please select another email!</p>
                                        </c:if>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Phone *:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="phone" class="form-control"
                                               pattern="^[0-9]{9,20}$" title="Phone number must be number and have length from 9 to 20 characters!" 
                                               value="${requestScope.account.phone}"
                                               required placeholder="Phone Number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Address: </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="address" class="form-control"
                                               value="${requestScope.account.address}"
                                               placeholder="Address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Gender *:</label>
                                    <div class="col-sm-10" style="display: flex;">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="gender" value="male" 
                                                       <c:if test="${requestScope.account.gender}">checked="checked"</c:if> > Male
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="gender"  <c:if test="${!requestScope.account.gender}">checked="checked"</c:if> value="femail"> Female
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Status *:</label>
                                        <div class="col-sm-10" style="display: flex;">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="status" value="Active" 
                                                    <c:if test="${requestScope.account.status eq 'ACTIVE'}">checked="checked"</c:if>> Active
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="status" 
                                                    <c:if test="${requestScope.account.status eq 'DEACTIVE'}">checked="checked"</c:if> value="Deactive"> Deactive
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Role *: </label>
                                        <div class="col-sm-10" style="display: flex;">
                                        <c:if test="${requestScope.account.role[0].id == 0}">
                                            <c:forEach items="${requestScope.roles}" var="r">
                                                <div class="radio" style="margin-left: -19px;">
                                                    <input type="checkbox" name="roleID" value="${r.id}">${r.roleName}
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${requestScope.account.role[0].id != 0}">
                                            <c:forEach items="${requestScope.roles}" var="r">
                                                <div class="radio" style="margin-left: -19px;">
                                                    <input type="checkbox" name="roleID" value="${r.id}"
                                                           <c:if test="${requestScope.account.checkRoleEqual(r)}"> checked="checked"</c:if>>${r.roleName}
                                                    </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="color: red; margin-left: 10%;">
                                    Note: (*) is required.
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
                                                onclick="location.href = 'userlist'">
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
        <jsp:include page="../header/linkjavascript.jsp"></jsp:include>
    </body>
</html>
