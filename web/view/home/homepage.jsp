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






