<%-- 
    Document   : Homepage
    Created on : Jan 12, 2022, 2:52:19 PM
    Author     : Tebellum
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
              <!DOCTYPE html>
       <meta charset="utf-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <meta name="description" content="">
       <meta name="author" content="">
       <title>Quzizz</title>
       <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/prettyPhoto.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/price-range.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
       <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet">
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_popup.css">
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_popup.css">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
             integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


</style>

</head>
<body>
       <jsp:include page="headerhomepage.jsp"></jsp:include>
              <div class="body_inf">
                     <div class="popup" id="popup-1">
                            <div class="overplay"></div>
                            <div class="content">
                                   <div class="close-btn" onclick="togglePopup()">&times;</div>
                                   <h3>Infomation</h3>
                                   <ul>
                                          <li>${requestScope.acc.id} </li>
                                   <li>${requestScope.acc.fullname} </li>
                                   <li>${requestScope.acc.username}</li>
                                   <li>${requestScope.acc.password}</li>
                                   <li>${requestScope.acc.email}</li>
                                   <li>${requestScope.acc.phone}</li>
                                   <li>${requestScope.acc.address}</li>
                                          <c:if test="${sessionScope.account.username == 'admin'}">
                                          <li><a href="director/setting/settinglist">Setting list</a></li>
                                          </c:if>
                            </ul>
                     </div>
              </div>
              <table>
                     <tr>
                            <td>ID</td>
                            <td>Fullname</td>
                            <td>Username</td>
                            <td>Password</td>
                            <td>Email</td>
                            <td>Phone</td>
                            <td>Address</td>
                     </tr>

                     <c:forEach items="${requestScope.information}" var="inf">
                            <tr>
                                   <td>${inf.id} </td>
                                   <td>${inf.fullname} </td>
                                   <td>${inf.username}</td>
                                   <td>${inf.password}</td>
                                   <td>${inf.email}</td>
                                   <td>${inf.phone}</td>
                                   <td>${inf.address}</td>

                            </tr>
                     </c:forEach>
              </table>
       </div>

       <section id="slider">
              <!--slider-->
              <div class="container">
                     <div class="row">
                            <div class="col-sm-12">
                                   <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                                          <div class="carousel-inner">
                                                 <div class="item active">
                                                        <div class="col-sm-6">
                                                               <h1 class="platform">The 100% engagement platform</h1>
                                                               <h2>Free E-Commerce Template</h2>
                                                               <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                                                      incididunt ut labore et dolore magna aliqua. </p>
                                                               <button type="button" class="btn btn-default get">Get it now</button>
                                                        </div>
                                                        <div class="col-sm-6">
                                                               <img src="${pageContext.request.contextPath}/images/book.png"
                                                                    class="girl img-responsive" alt="" />
                                                               <!-- <img src="images/home/pricing.png" class="pricing" alt="" /> -->
                                                        </div>
                                                 </div>
                                                 <div class="item">
                                                        <div class="col-sm-6">
                                                               <h1 class="platform">Find step-by-step textbook solutions.</h1>
                                                               <h2>100% Responsive Design</h2>
                                                               <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                                                      incididunt ut labore et dolore magna aliqua. </p>
                                                               <button type="button" class="btn btn-default get">Get it now</button>
                                                        </div>
                                                        <div class="col-sm-6">
                                                               <img src="${pageContext.request.contextPath}/images/book.png" class="girl img-responsive" alt="" />
                                                        </div>
                                                 </div>
                                          </div>

                                          <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                                 <i class="fa fa-angle-left"></i>
                                          </a>
                                          <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                                 <i class="fa fa-angle-right"></i>
                                          </a>
                                   </div>

                            </div>
                     </div>
              </div>
       </section>
       <!--/slider-->

       <jsp:include page="footerhomepage.jsp"></jsp:include>
              <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
              <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
              <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                      integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
              crossorigin="anonymous"></script>
              <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
       <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/jquery.scrollUp.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/price-range.js"></script>
       <script src="${pageContext.request.contextPath}/js/jquery.prettyPhoto.js"></script>
       <script src="${pageContext.request.contextPath}/js/main.js"></script>
       <script src="${pageContext.request.contextPath}/js/popuplogin.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
       <script>
                                          function togglePopup() {
                                                 document.getElementById("popup-1").classList.toggle("active");
                                          }
       </script>
</body>
</html>

