<%-- 
    Document   : header
    Created on : Feb 1, 2022, 9:59:18 PM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="utf-8">
    <title>Quizz</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=5" rel="stylesheet">
    
    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/popup.css" rel="stylesheet">
</head>

<body>
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
        <a href="${pageContext.request.contextPath}/home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Quizz</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <a href="${pageContext.request.contextPath}/home" class="nav-item nav-link active">Home</a>
                <a href="about.html" class="nav-item nav-link">About</a>
                <a href="${pageContext.request.contextPath}/post/list" class="nav-item nav-link">Posts</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                    <div class="dropdown-menu fade-down m-0">
                        <a href="team.html" class="dropdown-item">Our Team</a>
                        <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                        <a href="404.html" class="dropdown-item">404 Page</a>
                    </div>
                </div>
                <a href="contact.html" class="nav-item nav-link">Contact</a>
                <c:forEach items="${sessionScope.account.role}" var="r">
                    <c:if test="${r.roleName == 'Admin'}">
                        <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">List User</a>
                    </c:if>
                </c:forEach>
                <c:forEach items="${sessionScope.account.role}" var="r">
                    <c:if test="${r.roleName == 'Admin'}">
                        <a href="${pageContext.request.contextPath}/director/setting/settinglist" class="nav-item nav-link">For Director</a>
                    </c:if>
                </c:forEach>
                <c:if test="${sessionScope.account == null}"> 
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">             
                    <div class="inf button nav-item nav-link" style="padding: 18px 0 0 0;"><img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1"
                                                                                                style="width: 35px; height: 35px ; margin-right: 4px; border-radius: 50%;">
                        <a href="#popup1" style="    padding-top: 7px;">${sessionScope.account.fullname}</a>
                    </div>

                    <a style=" border-top: none;" class="nav-item nav-link" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i>Logout</a>
                </c:if>
            </div>
            <div id="popup1" class="overlay">
                <div class="popup">
                    <h2 style="border-bottom: 1px solid black;">Information</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="flex">
                        <div class="content">
                            <ul style="font-weight: 600; padding-left: 0rem;">
                                <li>ID: ${sessionScope.account.id}</li>
                                <li>Full name: ${sessionScope.account.fullname} </li>
                                <li>Gender: <c:if test="${sessionScope.account.gender == true}">Male</c:if>
                                    <c:if test="${sessionScope.account.gender == false}">Female</c:if></li>
                                <li>Username:  ${sessionScope.account.username}</li>
                                <li>Email:  ${sessionScope.account.email}</li>
                                <li>Phone number: ${sessionScope.account.phone}</li>
                                <li>Address: ${sessionScope.account.address}</li>

                            </ul>
                        </div>
                        <div class="img">
                            <!--<img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1">-->
                            <img src="${sessionScope.account.avatar}"> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
