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

</head>
<jsp:include page="headerhomepage.jsp"></jsp:include>
    <body>
        <div class="body_inf">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Fullname</td>
                    <td>Username</td>
                    <td>Email</td>
                    <td>Phone</td>
                    <td>Address</td>
                </tr>

            <c:forEach items="${requestScope.information}" var="inf">
                <tr>
                    <td>${inf.id} </td>
                    <td>${inf.fullname} </td>
                    <td>${inf.username}</td>
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
    <!--</div>-->
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
            <li role="menuitem"><a href="#" target="_BLANK">The Quizizz Blog</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Teacher Resources</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Help Center</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Accessibility and Inclusion</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Terms of Service</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Privacy Policy</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">Careers</a></li>
            <li role="menuitem"><a href="#" target="_BLANK">About Us</a></li>
        </ul>
    </div>
</footer>
</html>

