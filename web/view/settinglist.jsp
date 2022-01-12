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
    <title>Header</title>
    <link href="../bootstrap_for_admin/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="../bootstrap_for_admin/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="../bootstrap_for_admin/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="../bootstrap_for_admin/css/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="../bootstrap_for_admin/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="../bootstrap_for_admin/css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- fullCalendar -->
    <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
    <!-- Daterange picker -->
    <link href="../bootstrap_for_admin/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- iCheck for checkboxes and radio inputs -->
    <link href="../bootstrap_for_admin/css/iCheck/all.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- CSS -->
    <link rel="stylesheet" href="../css/style.css">
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
    <!--<script src="../JS/headerCode.js"></script>-->

    <!-- jQuery 2.0.2 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <script src="../bootstrap_for_admin/js/jquery.min.js" type="text/javascript"></script>

    <!-- jQuery UI 1.10.3 -->
    <script src="../bootstrap_for_admin/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="../bootstrap_for_admin/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="../bootstrap_for_admin/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

    <script src="../bootstrap_for_admin/js/plugins/chart.js" type="text/javascript"></script>

    <!-- datepicker
    <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>-->
    <!-- Bootstrap WYSIHTML5
    <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
    <!-- iCheck -->
    <script src="../bootstrap_for_admin/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <!-- calendar -->
    <script src="../bootstrap_for_admin/js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

    <!-- Director App -->
    <script src="../bootstrap_for_admin/js/Director/app.js" type="text/javascript"></script>

    <!-- Director dashboard demo (This is only for demo purposes) -->
    <script src="../bootstrap_for_admin/js/Director/dashboard.js" type="text/javascript"></script>

    <!-- Director for demo purposes -->
    <script type="text/javascript">
        $('input').on('ifChecked', function(event) {
            // var element = $(this).parent().find('input:checkbox:first');
            // element.parent().parent().parent().addClass('highlight');
            $(this).parents('li').addClass("task-done");
            console.log('ok');
        });
        $('input').on('ifUnchecked', function(event) {
            // var element = $(this).parent().find('input:checkbox:first');
            // element.parent().parent().parent().removeClass('highlight');
            $(this).parents('li').removeClass("task-done");
            console.log('not');
        });
    </script>
    <script>
        $('#noti-box').slimScroll({
            height: '400px',
            size: '5px',
            BorderRadius: '5px'
        });

        $('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
            checkboxClass: 'icheckbox_flat-grey',
            radioClass: 'iradio_flat-grey'
        });
    </script>
    <script type="text/javascript">
        $(function() {
            "use strict";
            //BAR CHART
            var data = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [{
                    label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                }, {
                    label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 86, 27, 90]
                }]
            };
            new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
                responsive: true,
                maintainAspectRatio: false,
            });

        });
        // Chart.defaults.global.responsive = true;
    </script>
</body>

</html>
