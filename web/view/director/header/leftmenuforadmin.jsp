<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Left side column. contains the logo and sidebar -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/setting.css">

<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel" style="    display: flex;">
            <div class="pull-left image">
                <img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1" class="img-circle" alt="User Image" />
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
            <li ${pageContext.request.requestURI.contains("post") ? "class=\"active\"" : ""}>
                <a  href="${pageContext.request.contextPath}/director/post/list">
                    <i class="fa fa-book"></i> <span>Posts</span>
                </a>
            </li>
            <li ${pageContext.request.requestURI.contains("settinglist") ? "class=\"active\"" : ""}>
                <a  href="${pageContext.request.contextPath}/director/setting/settinglist">
                    <i class="fa fa-wrench"></i> <span>Setting</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'userlist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/user/userlist">
                    <i class="fa fa-user"></i> <span>Users</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'subjectlist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/subject/subjectlist">
                    <i class="far fa-list-alt"></i> <span style="padding-left: 5px;">Subject</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'quizlist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/quiz/listquiz">
                    <i class="far fa-list-ul"></i> <span style="padding-left: 5px;">Quiz</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'question'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/question/list">
                    <i class="far fa-question-circle"></i> <span style="padding-left: 5px;">Question</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'classeslist'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/class/classeslist">
                    <i class="far fa-notes-medical"></i> <span style="padding-left: 5px;">Class</span>
                </a>
            </li>
            <li
                <c:if test="${tag eq 'slider'}">class="active"</c:if>  >
                <a href="${pageContext.request.contextPath}/director/slider/list">
                    <i class="far fa-shield"></i> <span style="padding-left: 5px;">Slider</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
