<%-- 
    Document   : header
    Created on : Oct 15, 2021, 3:50:58 PM
    Author     : Tebellum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header id="header">
       <div class="header-middle">
              <div class="container">
                     <div class="row">
                            <div class="col-sm-5">
                                   <div class="logo pull-left">
                                          <a href="#"><img id="content_img"
                                                           src="https://cf.quizizz.com/img/quizizz_logos/purple-brandmark-600x164.png"
                                                           alt="Loading ..." /></a>
                                   </div>
                                   <div class="shop-menu pull-left">
                                          <ul class="nav navbar-nav">
                                                 <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
                                                 <li><a href="#">For work</a></li>
                                                 <li><a href="#">For Teacher</a></li>
                                          </ul>
                                   </div>
                            </div>
                            <div class="col-sm-7">
                                   <div class="shop-menu pull-right">
                                          <ul class="nav navbar-nav">            
                                                 <li>
                                                        <div class="btn-group pull-right">
                                                               <div class="btn-group">
                                                                      <button type="button" class="btn btn-default dropdown-toggle usa"
                                                                              data-toggle="dropdown" style="border: none;
                                                                              color: #696763;margin-right: 0px;
                                                                              padding-right: 0px;"><i class="fa fa-star"></i>
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

                                                        <nav style="
                                                             width: 290px; " class="main-nav">
                                                               <ul>
                                                                      <li>
                                                                             <a class="cd-signin" href="#0">Login
                                                                             </a>
                                                                      </li>
                                                                      <li>
                                                                             <a class="cd-signup" href="#0">Register
                                                                             </a>
                                                                      </li>
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
<div class="cd-user-modal"> 
       <!-- this is the entire modal form, including the background -->
       <div class="cd-user-modal-container"> 
              <!-- this is the container wrapper -->
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
                     <form class="cd-form" action="" method="">
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="signin-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="signin-email" type="email" placeholder="E-mail">
                                   <span class="cd-error-message">Email is not vacant!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-password" for="signin-password">Password
                                   </label>
                                   <input class="full-width has-padding has-border" id="signin-password" type="text"  placeholder="Password">
                                   <a href="#0" class="hide-password">Hide
                                   </a>
                                   <span class="cd-error-message">Password  is not vacant!
                                   </span>
                            </p>

                            <!--                            <p class="fieldset">
                                                               <input type="checkbox" id="remember-me" checked>
                                                               <label for="remember-me">Remember to login
                                                               </label>
                                                        </p>-->
                            <p class="fieldset">
                                   <input class="full-width" type="submit" value="Sign in">
                            </p>
                     </form>
                     <!--                     <p class="cd-form-bottom-message">
                                                 <a href="#0">Forgot password?
                                                 </a>
                                          </p>-->
                     <!-- <a href="#0" class="cd-close-form">Close</a> -->
              </div> 
              <!-- cd-login -->
              <div id="cd-signup"> 
                     <!-- sign up form -->
                     <form class="cd-form" action="" method="">

                            <!---------------------------------------------------------------------------thêm input here-------------------------------------------->

                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            







                            <p class="fieldset">
                                   <label class="image-replace cd-username" for="signup-username">Full name
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-username" name="fullname" type="text" placeholder="Fullname">
                                   <span class="cd-error-message">Full name is not null!
                                   </span>
                            </p>

                            <p class="fieldset">
                                   <label class="image-replace cd-username" for="signup-username">Username
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Username">
                                   <span class="cd-error-message">Username is not vacant!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="signup-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail">
                                   <span class="cd-error-message">Email  is not vacant!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-password" for="signup-password">Password
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="Password">
                                   <a href="#0" class="hide-password">Hide
                                   </a>
                                   <span class="cd-error-message">Password  is not vacant!
                                   </span>
                            </p>
                            <!--                            <p class="fieldset">
                                                               <input type="checkbox" id="accept-terms">
                                                               <label for="accept-terms">Tôi đồng ý với 
                                                                      <a href="#0">Điều khoản & Chính sách
                                                                      </a>
                                                               </label>
                                                        </p>-->
                            <p class="fieldset">
                                   <input class="full-width has-padding" type="submit" value="Sign up">
                            </p>
                     </form>
                     <!-- <a href="#0" class="cd-close-form">Close</a> -->
              </div> 
              <!-- cd-signup -->
              <div id="cd-reset-password"> 
                     <!-- reset password form -->
                     <p class="cd-form-message">Bạn quên mật khẩu? Vui lòng nhập email của bạn và chúng tôi sẽ gửi cho bạn một email có đường dẫn để thay đổi mật khẩu.
                     </p>
                     <form class="cd-form">
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="reset-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                                   <span class="cd-error-message">Email không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <input class="full-width has-padding" type="submit" value="Reset password">
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


<!--                                                        <div class="modal-box">
                                                                                                                      <button type="button" style="margin-top: 2px; border-radius: 50px;" class="btn btn-primary btn-lg show-modal"
                                                                                                                              data-toggle="modal" data-target="#myModal">
                                                                                                                             Login Form
                                                                                                                      </button>
                                                                                                                       model pop-up 
                                                                                                                      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                                                                                                           aria-labelledby="myModalLabel">
                                                                                                                             <div class="modal-dialog" role="document">
                                                                                                                                    <div class="modal-content clearfix">
                                                                                                                                           <button type="button" class="close" data-dismiss="modal"
                                                                                                                                                   aria-label="Close"><span aria-hidden="true">×</span></button>
                                                                                                                                           <div class="modal-body">
                                                                                                                                                  <h3 class="title">Login Form</h3>
                                                                                                                                                  <p class="description">Login here Using Email & Password</p>
                                                                                                                                                  <div class="form-group">
                                                                                                                                                         <span class="input-icon"><i class="fa fa-user"></i></span>
                                                                                                                                                         <input type="email" class="form-control"
                                                                                                                                                                placeholder="Enter email">
                                                                                                                                                  </div>
                                                                                                                                                  <div class="form-group">
                                                                                                                                                         <span class="input-icon"><i class="fas fa-key"></i></span>
                                                                                                                                                         <input type="password" class="form-control"
                                                                                                                                                                placeholder="Password">
                                                                                                                                                  </div>
                                                                                                                                                  <div class="form-group checkbox">
                                                                                                                                                         <input type="checkbox">
                                                                                                                                                         <label>Remember me</label>
                                                                                                                                                  </div>
                                                                                                                                                  <a href="" class="forgot-pass">Forgot Password?</a>
                                                                                                                                                  <button class="btn">Login</button>
                                                                                                                                           </div>
                                                                                                                                    </div>
                                                                                                                             </div>
                                                                                                                      </div>
                                                                                                               </div>-->