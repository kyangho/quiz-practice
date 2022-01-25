<%-- 
    Document   : header
    Created on : Oct 15, 2021, 3:50:58 PM
    Author     : Tebellum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header id="header">
       <div class="header-middle">
              <div class="container" style="width: 1238px;">
                     <div class="row">
                            <div class="col-sm-7">
                                   <div class="logo pull-left">
                                          <a href="#"><img id="content_img"
                                                           src="https://cf.quizizz.com/img/quizizz_logos/purple-brandmark-600x164.png"
                                                           alt="Loading ..." /></a>
                                   </div>
                                   <div class="shop-menu pull-left">
                                          <ul class="nav navbar-nav">
                                                 <li class="left_home"><a href="#"><i class="fa fa-home"></i> Home</a></li>
                                                 <li class="left_home"><a href="#"><i class="fa fa-concierge-bell"></i>Services</a></li>
                                                 <li class="left_home"><a href="#"><i class="fa fa-address-book"></i>Contacts</a></li>
                                                 <li class="left_home"><a href="#"><i class="fa fa-user-graduate"></i>For work</a></li>
                                                 <li class="left_home"><a href="#"><i class="fa fa-chalkboard-teacher"></i>For Teacher</a></li>
                                                        <c:if test="${sessionScope.account.username == 'admin'}">
                                                        <li><a href="${pageContext.request.contextPath}/director">For Director</a></li>
                                                        </c:if>
                                          </ul>
                                   </div>
                            </div>
                            <div class="col-sm-5">
                                   <div class="shop-menu pull-right">
                                          <ul class="nav navbar-nav">            
                                                 <li>
                                                        <div class="btn-group pull-right">
                                                               <div class="btn-group">
                                                                      <button type="button" class="btn btn-default dropdown-toggle usa"
                                                                              data-toggle="dropdown" style="border: none;
                                                                              color: #696763;margin-right: 0px;padding-right: 0px; padding-top: 0px; padding-bottom: 0px "><i class="fa fa-star"></i>
                                                                             Wishlist
                                                                             <span class="caret"></span>
                                                                      </button>
                                                                      <ul class="dropdown-menu" style="border: none;">
                                                                             <li style="display: block;"><a href="">acccc</a></li>
                                                                             <li style="display: block;"><a href="">baaaaa</a></li>
                                                                      </ul>
                                                               </div>
                                                        </div>
                                                 </li>
                                                 <li>
                                                        <nav style="width: 300px;padding-top: 0px; padding-bottom: 0px " class="main-nav">
                                                               <ul style="padding: 0px">
                                                                      <c:if test="${sessionScope.account == null}"> 
                                                                             <li>
                                                                                    <a  class="cd-signin" href="#0">Login</a>
                                                                             </li>
                                                                             <li>
                                                                                    <a class="cd-signup" href="#0">Register </a>
                                                                             </li>            
                                                                      </c:if>
                                                               </ul>
                                                        </nav>
                                                 </li> 
                                                 <li>
                                                        <nav style="width: 300px;padding-top: 0px; padding-bottom: 0px " class="main-nav">
                                                               <ul style="padding: 0px">
                                                                      <c:if test="${sessionScope.account != null}">                                                                       
                                                                             <li>
                                                                                    <button onclick="togglePopup()" style="border-style: none; border: none; background: white;" >
                                                                                           <i class="fa fa-user"></i>${sessionScope.account.fullname}
                                                                                    </button>
                                                                             </li>                    
                                                                             <li><a style=" border-top: none;" class="cd-user" href="logout"><i class="fa fa-sign-out-alt"></i>Logout</a></li>
                                                                             </c:if>
                                                               </ul>
                                                        </nav>
                                                 </li>
                                          </ul>
                                   </div>
                            </div>
                     </div>
              </div>
       </div>
       <!--/header-middle-->
       <div class="header-bottom">
              <!--header-bottom-->
              <div class="container">
                     <div class="row">
                            <div class="col-sm-9">
                                   <div class="navbar-header">
                                          <button type="button" class="navbar-toggle" data-toggle="collapse"
                                                  data-target=".navbar-collapse">
                                                 <span class="sr-only">Toggle navigation</span>
                                                 <span class="icon-bar"></span>
                                                 <span class="icon-bar"></span>
                                                 <span class="icon-bar"></span>
                                          </button>
                                   </div>

                            </div>

                     </div>
              </div>
       </div>
       <!--/header-bottom-->
</header>
<!--<div class="cd-user-modal">--> 
<!--<div class="cd-user-modal-container">--> 
<c:if test="${sessionScope.account != null}">                                                                       
       <div class="popup" id="popup-1">
              <div class="overplay"></div>
              <div class="content" >
                     <div class="close-btn" onclick="togglePopup()">&times;</div>
                     <h3>Infomation</h3>
                     <ul>
                            <li>${sessionScope.account.id} </li>
                            <li>${sessionScope.account.fullname} </li>
                            <li>${sessionScope.account.username}</li>
                            <li>${sessionScope.account.password}</li>
                            <li>${sessionScope.account.email}</li>
                            <li>${sessionScope.account.phone}</li>
                            <li>${sessionScope.account.address}</li>
                     </ul>
              </div>
       </div>
</c:if>
<!--</div>-->
<!--</div>-->
<div class="cd-user-modal"> 
       <div class="cd-user-modal-container"> 
              <c:if test="${sessionScope.account == null}">                                                                       
                     <ul class="cd-switcher">
                            <li>
                                   <a href="#0">Login
                                   </a>
                            </li>
                            <li>
                                   <a href="#0">Register
                                   </a>
                            </li>
                     </ul>
                     <div id="cd-login"> 
                            <!-- log in form -->
                            <form class="cd-form" id="myForm1" action="login" method="post">
                                   <p class="fieldset">
                                          <label class="image-replace cd-email" for="signin-username">Username
                                          </label>
                                          <input class="full-width has-padding has-border" id="signin-email" type="text" placeholder="Username/Email" required name="username"/>
                                          <span class="cd-error-message">Username is not vacant!
                                          </span>
                                   </p>
                                   <p class="fieldset">
                                          <label class="image-replace cd-password" for="signin-password">Password
                                          </label>
                                          <input class="full-width has-padding has-border" id="signin-password" type="text" placeholder="Password" required name="password"/>
                                          <a href="#0" class="hide-password">Hide
                                          </a>
                                          <span class="cd-error-message">Password is not vacant!
                                          </span>
                                   </p>
                                   <div id="alert1"></div>
                                   <div class="fieldset">
                                          <input id="signin" class="full-width" type="submit" value="Sign in">
                                          <script>
                                                 var signin = document.getElementById("signin");
                                                 signin.addEventListener("click", checkAccount);
                                                 function checkAccount(e) {
                                                        e.preventDefault();
                                                        var signinEmail = document.getElementById("signin-email").value;
                                                        var signinPassword = document.getElementById("signin-password").value;
                                                        var link = "http://localhost:8080/QuizPractice/login?username=" + signinEmail + "&password=" + signinPassword;
                                                        var xhttp = new XMLHttpRequest();
                                                        xhttp.onreadystatechange = function () {
                                                               if (this.readyState == 4 && this.status == 200) {
                                                                      if (this.responseText == "fail") {
                                                                             document.getElementById("alert1").innerHTML = "Wrong username or password";
                                                                      } else {
                                                                             //                                        document.getElementById("demo").innerHTML = this.responseText;
                                                                             document.getElementById("myForm1").submit();
                                                                      }
                                                               }
                                                        };
                                                        xhttp.open("GET", link, false);
                                                        xhttp.send();
                                                 }
                                          </script>
                                   </div>
                            </form>

                     </div> 
                     <!-- cd-login -->
                     <div id="cd-signup"> 
                            <!-- sign up form -->
                            <form class="cd-form" id="myForm2" action="register" method="post">
                                   <p class="fieldset">
                                          <label class="image-replace cd-username" for="signup-username">Username
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-username" type="text" name="register_username" required placeholder="Username">
                                          <span class="cd-error-message">Username is not vacant!
                                          </span>
                                   </p>

                                   <p class="fieldset">
                                          <label class="image-replace cd-password" for="signup-password">Password
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-password" type="text" name="register_password" required placeholder="Password">
                                          <a href="#0" class="hide-password">Hide
                                          </a>
                                          <span class="cd-error-message">Password is not vacant!
                                          </span>
                                   </p>
                                   <p class="fieldset">
                                          <label class="image-replace cd-username" for="signup-fullname">Full name
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-username" name="register_fullname" type="text" required placeholder="Fullname">
                                          <span class="cd-error-message">Full name is not null!
                                          </span>
                                   </p>
                                   <p class="fieldset">
                                          <label class="image-replace cd-email" for="signup-email">Email
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-email" type="email" name="register_email" required placeholder="E-mail">
                                          <span class="cd-error-message">Email is not vacant!
                                          </span>
                                   </p>
                                   <p class="fieldset">
                                          <label class="image-replace cd-email" for="signup-phone">Phone
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-phone" type="tel" name="register_phone" placeholder="Phone number" required>
                                          <span class="cd-error-message">Phone is not vacant!
                                          </span>
                                   </p>
                                   <p class="fieldset">
                                          <label class="image-replace cd-email" for="signup-address">Address
                                          </label>
                                          <input class="full-width has-padding has-border" id="signup-address" type="text" name="register_address" placeholder="Address" required>
                                          <span class="cd-error-message">Address is not vacant!
                                          </span>
                                   </p>
                                   <div id="alert2"></div>
                                   <div class="fieldset">
                                          <input id="signup" class="full-width has-padding" type="submit" value="Sign up">
                                          <script>
                                                 var signup = document.getElementById("signup");
                                                 signup.addEventListener("click", checkRegister);
                                                 function checkRegister(e) {
                                                        e.preventDefault();
                                                        var signupUsername = document.getElementById("signup-username").value;
                                                        var signupEmail = document.getElementById("signup-email").value;
                                                        var signupPhone = document.getElementById("signup-password").value;
                                                        var link = "http://localhost:8080/QuizPractice/register?username=" + signupUsername + "&email=" + signupEmail + "&phone=" + signupPhone;
                                                        var xhttp = new XMLHttpRequest();
                                                        xhttp.onreadystatechange = function () {
                                                               if (this.readyState == 4 && this.status == 200) {
                                                                      if (this.responseText == "fail") {
                                                                             document.getElementById("alert2").innerHTML = this.responseText;
                                                                      } else {
                                                                             //                                        document.getElementById("demo").innerHTML = this.responseText;
                                                                             document.getElementById("myForm2").submit();
                                                                      }
                                                               }
                                                        };
                                                        xhttp.open("GET", link, false);
                                                        xhttp.send();
                                                 }
                                          </script>
                                   </div>
                            </form>
                            <!-- <a href="#0" class="cd-close-form">Close</a> -->
                     </div> 
              </c:if>

              <!-- cd-signup -->
              <div id="cd-reset-password"> 
                     <!-- reset password form -->
                     <p class="cd-form-message">Bạn quên mật khẩu? Vui lòng nhập email của bạn và chúng tôi sẽ gửi cho bạn một email có đường dẫn để thay đổi mật khẩu.
                     </p>
                     <form class="cd-form">
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="reset-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail" required>
                                   <span class="cd-error-message">Email không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <input class="full-width has-padding" type="submit" value="Reset password" required>
                            </p>
                     </form>
                     <p class="cd-form-bottom-message">
                            <a href="#0">Quay lại đăng nhập
                            </a>
                     </p>
              </div> 
              <!-- cd-reset-password -->
              <a href="#0" class="cd-close-form">Close
              </a>
       </div> 
       <!-- cd-user-modal-container -->
</div> 
