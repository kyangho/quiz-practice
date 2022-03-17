<%-- 
    Document   : changprofile
    Created on : Feb 16, 2022, 11:13:31 AM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header_footer/header.jsp"></jsp:include>
    <div class="container-fluid bg-registration py-5" style="margin: 3rem 0;">
        <div class="container py-5">
            <div class="row align-items-center">
                <div class="col-lg-9" style="margin: 0 auto;">
                    <div class="card border-0">
                        <div class="card-header bg-light text-center p-4" style="display: flex">
                            <div class="form-header active"><h1 class="m-0">User profile</h1></div>
                        </div>
                        <div class="card-body rounded-bottom bg-primary p-5">
                            <form id="register_form" action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <div style="width: 50%; float: left">
                                    <div class="col-sm-10">
                                        <c:if test="${sessionScope.account.avatar != null}">
                                            <img id="avatar" src="${pageContext.servletContext.contextPath}/profile/avatar?accountID=${sessionScope.account.id}" class="img-fluid" >
                                        </c:if>
                                        <c:if test="${sessionScope.account.avatar == null}">
                                            <img id="avatar" src="https://www.pngitem.com/pimgs/m/455-4552929_profile-icon-png-transparent-png.png" class="img-fluid" >
                                        </c:if>
                                        <img id="image" style="padding-bottom: 10px" class="img-fluid" >
                                        <div style="display: flex; margin: 5px 0 10px 0">
                                            <span style="color:white; margin-right: 10px;">Change avatar</span>
                                        <input type="file" name="avatar" id="files" style="color: transparent; width: 40%;"/>
                                        </div>
                                        <style>
                                            img{
                                                width: 360px;
                                                height: 250px;
                                            }
                                        </style>
                                    </div>
                                </div>
                                <div style="width: 50%; float: right">
                                    <div class="form-group">
                                        <input name="username" type="text" class="form-control border-0 p-4" placeholder="Username" value="${sessionScope.account.username}" disabled required="required" />
                                    </div>
                                    <div class="form-group">
                                        <input name="fullname" type="text" class="form-control border-0 p-4" placeholder="Fullname" value="${sessionScope.account.fullname}" required
                                               pattern="^[a-zA-ZaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴ\s]+$"
                                               title="Name must't contain number and special characters"/>
                                    </div>
                                    <div class="form-group" style="color: white;">
                                        <input name="gender" type="radio" ${sessionScope.account.gender ? "checked":""} value="male" required="required"/>Male
                                        <span style="margin: 0 5%;"></span>
                                        <input name="gender" type="radio" ${sessionScope.account.gender ? "":"checked"} value="female"/>Female
                                    </div>
                                    <div class="form-group">
                                        <input name="email" type="email" class="form-control border-0 p-4" placeholder="Email" value="${sessionScope.account.email}" disabled required />
                                    </div>
                                    <div class="form-group">
                                        <input name="phone" type="tel" class="form-control border-0 p-4" placeholder="Phone number" value="${sessionScope.account.phone}" required
                                               pattern="^[0-9]+$" title="Phone number must't contain letter character"/>
                                    </div>
                                </div>
                                <div>
                                    <button class="btn btn-dark btn-block border-0 py-3" type="submit">Save</button>
                                </div>
                                <c:if test="${requestScope.isFail == true}" >
                                    <p style="color: white;">Username, email or phone number already in use</p>
                                </c:if>
                        </form>
                        <script>
                            document.getElementById("files").onchange = function () {
                                var reader = new FileReader();
                                reader.onload = function (e) {
                                    document.getElementById("image").src = e.target.result;
                                };
                                reader.readAsDataURL(this.files[0]);
                                var media = document.getElementById('avatar');
                                if (media != null) {
                                    media.remove();
                                }
                            };
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="header_footer/footer.jsp"></jsp:include>

