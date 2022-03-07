<%-- 
    Document   : register
    Created on : Feb 9, 2022, 11:46:49 PM
    Author     : Vu Duc Tien
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
                        <form id="register_form" action="${pageContext.request.contextPath}/register" method="post">
                            <div class="form-group">
                                <input name="register_username" type="text" class="form-control border-0 p-4" placeholder="Username" required="required" />
                            </div>
                            <div class="form-group">
                                <input id="password" name="register_password" type="password" class="form-control border-0 p-4" placeholder="Password" required="required" />
                            </div>
                            <div class="form-group">
                                <input id="re_password" name="re_password" type="password" class="form-control border-0 p-4" placeholder="Re-password" required="required" />
                            </div>
                            <div id="password_alert" style="color: white;"></div>
                            <div class="form-group">
                                <input name="register_fullname" type="text" class="form-control border-0 p-4" placeholder="Fullname" required="required"
                                       pattern="^[a-zA-ZaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴ\s]+$"
                                       title="Name must't contain number and special characters"/>
                            </div>
                            <div class="form-group" style="color: white;">
                                <input name="gender" type="radio" value="male" required="required"/>Male
                                <span style="margin: 0 5%;"></span>
                                <input name="gender" type="radio" value="female"/>Female
                            </div>
                            <div class="form-group">
                                <input name="register_email" type="email" class="form-control border-0 p-4" placeholder="Email" required="required" />
                            </div>
                            <div class="form-group">
                                <input name="register_phone" type="tel" class="form-control border-0 p-4" placeholder="Phone number" required="required" />
                            </div>
                            <div class="form-group">
                                <input name="register_address" type="text" class="form-control border-0 p-4" placeholder="Address" required="required" />
                            </div>
                            <div>
                                <button class="btn btn-dark btn-block border-0 py-3" type="button" onclick="checkRePassword()">Register</button>
                            </div>
                            <c:if test="${requestScope.isFail == true}" >
                                <p style="color: white;">Username, email or phone number already in use</p>
                            </c:if>
                        </form>
                            <script>
                                function checkRePassword(){
                                    var password = document.getElementById("password").value;
                                    var re_password = document.getElementById("re_password").value;
                                    if(password === re_password){
                                        document.getElementById("register_form").onsubmit = function(){
                                            return true;
                                        }
                                    }else{
                                        document.getElementById("password_alert").innerHTML+="<p>Password and Re-password not match</p>";
                                        document.getElementById("register_form").onsubmit = function(){
                                            return false;
                                        }
                                    }
                                }
                            </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="header_footer/footer.jsp"></jsp:include>

