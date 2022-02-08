<!-- header logo: style can be found in header.less -->
<header class="header">
    <a href="#" class="logo">
        <!-- Add the class icon to your logo image or logo icon to add the margining -->
        Management
    </a>
    
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user"></i>
                        <span>${sessionScope.account.fullname} <i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                        <li class="dropdown-header text-center">Account</li>

                        <li>
                            <a href="#">
                                <i class="fa fa-clock-o fa-fw pull-right"></i>
                                <span class="badge badge-success pull-right">10</span> Updates</a>
                            <a href="#">
                                <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                <span class="badge badge-danger pull-right">5</span> Messages</a>
                            <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                            <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class=
                                                                                              "badge pull-right">11</span> FAQ</a>
                        </li>

                        <li class="divider"></li>

                        <!-- <li>
                            <a href="#">
                                <i class="fa fa-user fa-fw pull-right"></i> Profile
                            </a>
                            <a data-toggle="modal" href="#modal-user-settings">
                                <i class="fa fa-cog fa-fw pull-right"></i> Settings
                            </a>
                        </li> -->

                        <li class="divider"></li>

                        <li>
                            <a href="../../logout"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                        </li>
                    </ul>

                </li>
            </ul>
        </div>
    </nav>
</header>