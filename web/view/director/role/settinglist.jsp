<%-- 
    Document   : settinglist
    Created on : Jan 12, 2022, 7:42:08 PM
    Author     : conmu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Settings List</title>
        <jsp:include page="linkcss.jsp"></jsp:include>
        </head>

        <body class="skin-black">
        <jsp:include page="headerforadmin.jsp"></jsp:include>

            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel">
                                    <div class="panel-heading" style="display: flex;">
                                        <div style="margin-right: 74%;"> List of settings</div>
                                        <div>
                                            <a href="#">Add new setting </a>
                                        </div>
                                    </div>

                                    <div class="panel-body table-responsive">

                                        <div class="box-tools m-b-15" style="display: flex;">
                                            <div style="margin-right: 65%;">
                                                Sort by:
                                                <select name="sort" class=" input-sm">
                                                    <option value="null">Choose option</option>
                                                    <option value="ID">Setting ID</option>
                                                    <option value="type">Type</option>
                                                    <option value="status">Status</option>
                                                </select>
                                                <button class="btn btn-sm btn-default" style="color: black;font-weight: bolder;">Apply</button>
                                            </div>
                                            <div class="input-group">
                                                <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search" />
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-hover">
                                            <tr>
                                                <th>ID</th>
                                                <th>Setting Name</th>
                                                <th>Description</th>
                                                <th>View</th>
                                                <th>Type</th>
                                                <th>Status</th>
                                                <th>Change status</th>
                                            </tr>
                                            <tr>
                                                <td>183</td>
                                                <td>Jane Doe</td>
                                                <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                                <td>
                                                    <Button onclick="showDetail()">Details</Button>
                                                </td>
                                                <td>11-7-2014</td>
                                                <td><button class="label label-success">Active</button></td>
                                                <td><button class="label label-danger">De-active</button></td>
                                            </tr>
                                            <tr>
                                                <td>219</td>
                                                <td>Jane Doe</td>
                                                <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                                <td>
                                                    <Button>Details</Button>
                                                </td>
                                                <td>11-7-2014</td>
                                                <td><button class="label label-success">Active</button></td>
                                                <td><button class="label label-danger">De-active</button></td>
                                            </tr>
                                            <tr>
                                                <td>657</td>
                                                <td>Bob Doe</td>
                                                <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                                <td>
                                                    <Button>Details</Button>
                                                </td>
                                                <td>11-7-2014</td>
                                                <td><button class="label label-success">Active</button></td>
                                                <td><button class="label label-danger">De-active</button></td>
                                            </tr>
                                            <tr>
                                                <td>175</td>
                                                <td>Mike Doe</td>
                                                <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                                <td>
                                                    <Button>Details</Button>
                                                </td>
                                                <td>11-7-2014</td>
                                                <td><button class="label label-danger">De-active</button></td>
                                                <td><button class="label label-success">Active</button></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <!-- /.box -->
                            </div>
                        </div>
                    </section>
                </aside>

            </div>
        <jsp:include page="linkjavascript.jsp"></jsp:include>
    </body>

</html>
