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
                <div class="col-lg-7 mb-5 mb-lg-0">
                    <div class="mb-4">
                        <h5 class="text-primary text-uppercase mb-3" style="letter-spacing: 5px;">Need Any Courses</h5>
                        <h1 class="text-white">30% Off For New Students</h1>
                    </div>
                    <p class="text-white">Invidunt lorem justo sanctus clita. Erat lorem labore ea, justo dolor lorem ipsum ut sed eos,
                        ipsum et dolor kasd sit ea justo. Erat justo sed sed diam. Ea et erat ut sed diam sea ipsum est
                        dolor</p>
                    <ul class="list-inline text-white m-0">
                        <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Labore eos amet dolor amet diam</li>
                        <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Etsea et sit dolor amet ipsum</li>
                        <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Diam dolor diam elitripsum vero.</li>
                    </ul>
                </div>
                <div class="col-lg-5">
                    <div class="card border-0">
                        <div class="card-header bg-light text-center p-4" style="display: flex">
                            <div class="form-header active"><a href="${pageContext.request.contextPath}/login"><h1 class="m-0">Sign In</h1></a></div>
                        <div class="form-header"><a href="${pageContext.request.contextPath}/register"><h1 class="m-0">Sign Up</h1></a></div>
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
