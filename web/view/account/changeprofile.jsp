<%-- 
    Document   : changprofile
    Created on : Feb 16, 2022, 11:13:31 AM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="container-fluid bg-registration py-5" style="margin: 3rem 0;">
        <div class="container py-5">
            <div class="row align-items-center">
                <div class="col-lg-5" style="margin: 0 auto;">
                    <div class="card border-0">
                        <div class="card-header bg-light text-center p-4" style="display: flex">
                            <div class="form-header active"><h1 class="m-0">User profile</h1></div>
                        </div>
                        <div class="card-body rounded-bottom bg-primary p-5">
                            <form id="register_form" action="${pageContext.request.contextPath}/changeprofile" method="post">
                            <div class="form-group">
                                <input name="username" type="text" class="form-control border-0 p-4" placeholder="Username" value="${sessionScope.account.username}" disabled required="required" />
                            </div>
                            <div class="form-group">
                                <input name="fullname" type="text" class="form-control border-0 p-4" placeholder="Fullname" value="${sessionScope.account.fullname}" required="required"
                                       pattern="^[a-zA-ZaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴ\s]+$"
                                       title="Name must't contain number and special characters"/>
                            </div>
                            <div class="form-group" style="color: white;">
                                <input name="gender" type="radio" ${sessionScope.account.gender ? "checked":""} value="male" required="required"/>Male
                                <span style="margin: 0 5%;"></span>
                                <input name="gender" type="radio" ${sessionScope.account.gender ? "":"checked"} value="female"/>Female
                            </div>
                            <div class="form-group">
                                <input name="email" type="email" class="form-control border-0 p-4" placeholder="Email" value="${sessionScope.account.email}" disabled required="required" />
                            </div>
                            <div class="form-group">
                                <input name="phone" type="tel" class="form-control border-0 p-4" placeholder="Phone number" value="${sessionScope.account.phone}" required="required" />
                            </div>
                            <div class="form-group">
                                <input name="address" type="text" class="form-control border-0 p-4" placeholder="Address" value="${sessionScope.account.address}" required="required" />
                            </div>
                            <div>
                                <button class="btn btn-dark btn-block border-0 py-3" type="submit">Save</button>
                            </div>
                            <c:if test="${requestScope.isFail == true}" >
                                <p style="color: white;">Username, email or phone number already in use</p>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>

