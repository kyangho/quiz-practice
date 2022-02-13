<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/setting.css">

<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/bootstrap_for_admin/img/avatar3.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p>Hello, ${sessionScope.account.fullname}</p>

                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li>
                <a href="${pageContext.request.contextPath}/home">
                    <i class="fa fa-home"></i> <span>Home</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-gavel"></i> <span>General</span>
                </a>
            </li>

            <li <c:if test="${tag eq 'settinglist'}">class="active"</c:if>  >
                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        <i class="glyphicon glyphicon-cog"></i><span>Setting</span>
                    </a>
                    <!--<a href="${pageContext.request.contextPath}/director/setting/settinglist">-->

                <div class="collapse" id="collapseExample">
                    <a href="${pageContext.request.contextPath}/director/setting/settinglist">
                        <i class="fa fa-gavel"></i> <span>Setting List</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/director/setting/post">
                        <i class="fa fa-gavel"></i> <span>Post Categories</span>
                    </a>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">

            </li>

            <li
                <c:if test="${tag eq 'userlist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/user/userlist">
                    <i class="glyphicon glyphicon-cog"></i> <span>Users List</span>
                </a>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>