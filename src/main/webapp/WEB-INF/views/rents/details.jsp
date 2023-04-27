<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">

                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#users" data-toggle="tab">Client</a></li>
                            <li><a href="#cars" data-toggle="tab">Voiture</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="users">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Email</th>
                                            <th>Date de naissance</th>
                                        </tr>
                                        <tr>
                                            <td>${user.findById(reservation.client_id).id}</td>
                                            <td>${user.findById(reservation.client_id).nom}</td>
                                            <td>${user.findById(reservation.client_id).prenom}</td>
                                            <td>${user.findById(reservation.client_id).email}</td>
                                            <td>${user.findById(reservation.client_id).naissance}</td>
                                         </tr>

                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="cars">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Constructeur</th>
                                            <th style=>Nombre de places</th>
                                        </tr>
                                        <tr>
                                            <td>${vehicle.findById(reservation.vehicle_id).id}</td>
                                         	<td>${vehicle.findById(reservation.vehicle_id).constructeur}</td>
                                         	<td>${vehicle.findById(reservation.vehicle_id).nb_places}</td>
                                        /tr>

                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
