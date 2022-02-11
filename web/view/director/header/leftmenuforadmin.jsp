<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Left side column. contains the logo and sidebar -->
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
                <a href="${pageContext.request.contextPath}/director/setting/settinglist">
                    <i class="glyphicon glyphicon-cog"></i> <span>Settings List</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'userlist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/user/userlist">
                    <i class="glyphicon glyphicon-cog"></i> <span>Users List</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'subject'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/subject/subjectlist">
                    <i class="far fa-list-alt"></i> <span>Subject List</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>