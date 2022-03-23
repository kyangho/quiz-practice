<%-- 
    Document   : changepassword
    Created on : Feb 17, 2022, 9:40:56 AM
    Author     : kienct
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header_footer/header.jsp"></jsp:include>
    <div class="container-fluid bg-registration py-5" style="margin: 3rem 0;">
        <div class="container py-5">
            <div class="row align-items-center">
                <div class="col-lg-3 mb-5 mb-lg-0">
                </div>
                <div class="col-lg-6">
                    <div class="card border-0">
                        <div class="card-header bg-light text-center p-4" style="display: flex">
                        <div class="form-header"><h1 class="m-0">Change password</h1></div>
                    </div>
                    <div class="card-body rounded-bottom bg-primary p-5">
                        <form action="${pageContext.request.contextPath}/changepassword" method="POST">
                            <div class="form-group">
                                <input name="oldpass" type="password" class="form-control border-0 p-4" placeholder="Old Password" required="required" />
                            </div>
                            <div class="form-group">
                                <input name="newpass" type="password" class="form-control border-0 p-4" placeholder="New Password" required="required" />
                            </div>
                            <div class="form-group">
                                <input name="repeatpass" type="password" class="form-control border-0 p-4" placeholder="Re-enter Password" required="required" />
                            </div>
                            <div>
                                <button class="btn btn-dark btn-block border-0 py-3" type="submit">Reset password</button>
                            </div>
                            <c:if test="${requestScope.wrongRepeat == true}" >
                                <p>Password repeat does not match!</p>
                            </c:if>
                            <c:if test="${requestScope.wrongPassword == true}" >
                                <p>Wrong password!</p>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="header_footer/footer.jsp"></jsp:include>
