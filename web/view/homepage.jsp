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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="css/login_popup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

</head>
<body>
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
                                <!-- <li><a href="#"><i class="fa fa-user"></i> Account</a></li> -->
                                <!-- <li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li> -->
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
                                        <!-- model pop-up -->
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
    <!--/header-->

    <section id="slider">
        <!--slider-->
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                            <li data-target="#slider-carousel" data-slide-to="1"></li>
                            <li data-target="#slider-carousel" data-slide-to="2"></li>
                            <li data-target="#slider-carousel" data-slide-to="3"></li>
                            <li data-target="#slider-carousel" data-slide-to="4"></li>
                        </ol>

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



    <footer class="resource-footer">
        <div class="footer-inner">
            <div class="col-1">
                <div class="footer-brand-logo" aria-label="Quizizz logo"><img
                        src="https://cf.quizizz.com/img/quizizz_logos/white-brandmark-600x164.png" label=""
                        aria-hidden="true"></div>
                <div class="copyright">© 2022 Quizizz Inc.</div>
                <div class="social-links"><span class="label">Follow us</span>
                    <a href="https://twitter.com/quizizz" target="_BLANK" aria-label="Twitter"><i
                            style="margin-left: 5px;" class="fa fa-twitter"></i></a>
                    <a href="https://facebook.com/quizizz" target="_BLANK" aria-label="Facebook"><i
                            style="margin-left: 5px;" class="fa fa-facebook"></i></a>
                    <a href="https://instagram.com/quizizz" target="_BLANK" aria-label="Instagram"><i
                            style="margin-left: 5px;" class="fa fa-instagram"></i>
                    </a>
                </div>
            </div>
            <ul class="col-2-and-3" role="menu">
                <li role="menuitem"><a href="https://blog.quizizz.com" target="_BLANK">The Quizizz Blog</a></li>
                <li role="menuitem"><a href="https://quizizz.com/resources" target="_BLANK">Teacher Resources</a></li>
                <li role="menuitem"><a href="https://support.quizizz.com/hc/en-us" target="_BLANK">Help Center</a></li>
                <li role="menuitem"><a href="https://www.essentialaccessibility.com/quizizz"
                                       target="_BLANK">Accessibility and Inclusion</a></li>
                <li role="menuitem"><a href="https://quizizz.com/tos" target="_BLANK">Terms of Service</a></li>
                <li role="menuitem"><a href="https://quizizz.com/privacy" target="_BLANK">Privacy Policy</a></li>
                <li role="menuitem"><a href="https://quizizz.com/careers" target="_BLANK">Careers</a></li>
                <li role="menuitem"><a href="https://quizizz.com/about" target="_BLANK">About Us</a></li>
            </ul>
        </div>
    </footer>

    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.scrollUp.min.js"></script>
    <script src="../js/price-range.js"></script>
    <script src="../js/jquery.prettyPhoto.js"></script>
    <script src="../js/main.js"></script>


</body>
</html>
