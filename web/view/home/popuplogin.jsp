<%-- 
    Document   : popuplogin
    Created on : Jan 13, 2022, 4:38:47 PM
    Author     : Tebellum
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="cd-user-modal"> 
       <!-- this is the entire modal form, including the background -->
       <div class="cd-user-modal-container"> 
              <!-- this is the container wrapper -->
              <ul class="cd-switcher">
                     <li>
                            <a href="#0">Đăng nhập
                            </a>
                     </li>
                     <li>
                            <a href="#0">Tạo tài khoản
                            </a>
                     </li>
              </ul>
              <div id="cd-login"> 
                     <!-- log in form -->
                     <form class="cd-form">
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="signin-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="signin-email" type="email" placeholder="E-mail">
                                   <span class="cd-error-message">Email không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-password" for="signin-password">Password
                                   </label>
                                   <input class="full-width has-padding has-border" id="signin-password" type="text"  placeholder="Password">
                                   <a href="#0" class="hide-password">Hide
                                   </a>
                                   <span class="cd-error-message">Password không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <input type="checkbox" id="remember-me" checked>
                                   <label for="remember-me">Nhớ đăng nhập
                                   </label>
                            </p>
                            <p class="fieldset">
                                   <input class="full-width" type="submit" value="Đăng nhập">
                            </p>
                     </form>
                     <p class="cd-form-bottom-message">
                            <a href="#0">Quên mật khẩu?
                            </a>
                     </p>
                     <!-- <a href="#0" class="cd-close-form">Close</a> -->
              </div> 
              <!-- cd-login -->
              <div id="cd-signup"> 
                     <!-- sign up form -->
                     <form class="cd-form">
                            <p class="fieldset">
                                   <label class="image-replace cd-username" for="signup-username">Username
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Username">
                                   <span class="cd-error-message">Username không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-email" for="signup-email">Email
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail">
                                   <span class="cd-error-message">Email không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <label class="image-replace cd-password" for="signup-password">Password
                                   </label>
                                   <input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="Password">
                                   <a href="#0" class="hide-password">Hide
                                   </a>
                                   <span class="cd-error-message">Password không được bỏ trống!
                                   </span>
                            </p>
                            <p class="fieldset">
                                   <input type="checkbox" id="accept-terms">
                                   <label for="accept-terms">Tôi đồng ý với 
                                          <a href="#0">Điều khoản & Chính sách
                                          </a>
                                   </label>
                            </p>
                            <p class="fieldset">
                                   <input class="full-width has-padding" type="submit" value="Tạo tài khoản">
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
