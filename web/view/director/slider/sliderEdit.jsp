<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizz</title>
        <jsp:include page="../header/linkcss.jsp"></jsp:include>

        </head>

        <body class="skin-black">
        <jsp:include page="../header/headerforadmin.jsp"></jsp:include>

            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="../header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                        <div class="row">
                            <div class="col-md-12">
                                <section class="panel">
                                <c:if test="${requestScope.edit eq 'edit'}" >
                                    <header class="panel-heading">
                                        Edit Slider
                                    </header>
                                </c:if>
                                <c:if test="${requestScope.add eq 'add'}" >
                                    <header class="panel-heading">
                                        Add Slider
                                    </header>
                                </c:if>

                                <div class="panel-body">
                                    <form class="form-horizontal tasi-form" method="POST" 
                                          <c:if test="${requestScope.add eq 'add'}" >
                                              action="add"</c:if>
                                          <c:if test="${requestScope.edit eq 'edit'}" >
                                              action="edit"
                                          </c:if>
                                          enctype="multipart/form-data">

                                        <c:if test="${requestScope.edit eq 'edit'}" >
                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">ID</label>
                                                <div class="col-sm-10">
                                                    <input value="${slider.id}" name="id" hidden="">
                                                    <input disabled value="${slider.id}" name ="id" type="text" class="form-control">
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="title" class="form-control" 
                                                       value="${slider.title}" required placeholder="title title">
                                            </div>
                                        </div>
                                        <c:if test="${requestScope.edit eq 'edit'}" >
                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">ThumbNail</label>
                                                <div class="col-sm-10">
                                                    <img src="${pageContext.servletContext.contextPath}/slider/imagin?id=${slider.id}" width="300px" class="img-fluid" >
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Upload Image</label>
                                            <div class="col-sm-10">
                                                <img id="image" width="300px" style="padding-bottom: 10px" class="img-fluid" >
                                                <input type="file" name="thumbnail" id="files" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Backlink</label>
                                            <div class="col-sm-10">
                                                <input value="${slider.backlink}" required name="backlink" type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                            <div class="col-sm-10">
                                                <input type="radio" id="status" name="status" value="Show"
                                                       ${slider.status eq 'Show' ? "checked=\"checked\"" : ""} checked="checked">
                                                <label for="html">Show</label><br>
                                                <input type="radio" id="status" name="status" value="Hide"
                                                       ${slider.status eq 'Hide' ? "checked=\"checked\"" : ""}>
                                                <label for="css">Hide</label><br>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Note</label>
                                            <div class="col-sm-10">
                                                <input value="${slider.note}" required name ="note"type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div style="margin-left: 40%; margin-top: -30px; padding-bottom: 30px">
                                            <!--<button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Edit</button>-->

                                            <c:if test="${requestScope.edit eq 'edit'}">
                                                <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Edit</button>
                                            </c:if>
                                            <c:if test="${requestScope.add eq 'add'}">
                                                <button style="float: left" type="submit"  id="add" class="btn btn-success btn-lg ">Add Slider</button>
                                            </c:if>
                                            <a href="list"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                                        </div>
                                    </form>
                                </div>
                            </section>
                        </div>
                    </div>
                </section><!-- /.content -->
            </aside>
        </div>
    </body>
    <script>
        document.getElementById("files").onchange = function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById("image").src = e.target.result;
            };
            reader.readAsDataURL(this.files[0]);
        };
    </script>

</html>







