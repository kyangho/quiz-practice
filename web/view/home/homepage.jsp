<%-- 
    Document   : Homepage
    Created on : Jan 12, 2022, 2:52:19 PM
    Author     : Tebellum
--%>

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
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
             integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


</head>
<body>
       <jsp:include page="headerhomepage.jsp"></jsp:include>
              <section id="slider">
                     <!--slider-->
                     <div class="container">
                            <div class="row">
                                   <div class="col-sm-12">
                                          <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                                                 <!--                                          <ol class="carousel-indicators">
                                                                                                  <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                                                                                  <li data-target="#slider-carousel" data-slide-to="1"></li>
                                                                                                  <li data-target="#slider-carousel" data-slide-to="2"></li>
                                                                                                  <li data-target="#slider-carousel" data-slide-to="3"></li>
                                                                                                  <li data-target="#slider-carousel" data-slide-to="4"></li>
                                                                                           </ol>-->

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
                                                                      <img src="https://cf.quizizz.com/img/mkt/1-HERO-Digital_Collage.png"
                                                                           class="girl img-responsive" style=" height: 441px;
                                                                           " alt="" />
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
                                                                      <img src="images/book.png" class="girl img-responsive" alt="" />
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
</body>
</html>







<!--    <header id="header">
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
                                      <li><a href="#"><i class="fa fa-user"></i> Account</a></li> 
                                      <li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li> 
                                     <li>
                                         <div class="btn-group pull-right">
                                             <div class="btn-group">
                                                 <button type="button" class="btn btn-default dropdown-toggle usa"
                                                         data-toggle="dropdown" style="border: none;
                                                         color: #696763;"><i class="fa fa-star"></i>
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
                                         <div class="modal-box">
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
                                         </div>
                                     </li>
                                 </ul>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
             /header-middle
             <div class="header-bottom">
                 header-bottom
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
             /header-bottom
         </header>
         /header-->