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
