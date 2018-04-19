<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Screen</title>
    <style type="text/css">
        .table { border: 1px solid #2980B9; }
        .table thead > tr > th { border-bottom: none; }
        .table thead > tr > th, .table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid #2980B9; }
    </style>
    <script>
        $(document).ready(function(){
            $("#searchbutton").click(function() {
                var keyword = $("#searchBar").val();
                window.location.href = "?search=" + keyword;
            })
        });
    </script>
</head>
<body>
<div class="container">
    <jsp:include page="Header.jsp"></jsp:include>
    <table class="table" style="margin-top: 25px">

        <thead>
        <div class="row" style="margin-top: 25px">
            <div class="col-lg-6"></div>
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" id="searchBar" class="form-control" placeholder="Search for any...">
                        <span class="input-group-btn">
                            <button class="btn btn-secondary" type="button" id="searchbutton">Go!</button>
                        </span>
                </div>
            </div>
        </div>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Telephone</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Actions</th>
            <th>Mapping Relations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${listEmployee}" varStatus="estatus">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.telephone}</td>
                <td>${employee.salary}</td>
                <td>${employee.department}</td>
                <td>
                    <a href="editEmployee?id=${employee.id}"><input type="button" class="btn btn-success" value="Edit"></a>
                    <a href="deleteEmployee?id=${employee.id}"><input type="button" class="btn btn-danger"  value="Delete"></a>
                </td>
                <td>
                        <%--<div class="panel-group" id="accordion${estatus.index}">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion${estatus.index}" href="#collapse1${estatus.index}">
                                            <input type="button" class="btn btn-info" value="Access Card Details"></a>
                                    </h4>
                                </div>
                                <div id="collapse1${estatus.index}" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="panel panel-primary">
                                            <div class="panel-body">
                                                <p>Access Card Name :${employee.accessCard.card_holder_name}</p>
                                                <p>Department :${employee.accessCard.department}</p>
                                                <p>Organisation :${employee.accessCard.organization}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion${estatus.index}" href="#collapse2${estatus.index}">
                                            <input type="button" class="btn btn-info" value="Show Tasks List"></a>
                                    </h4>
                                </div>
                                <div id="collapse2${estatus.index}" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <c:forEach var="eachTask" items="${employee.tasks}">
                                            <div class="panel panel-primary">
                                                <div class="panel-body">
                                                    <p>Task Description :${eachTask.task_desc}</p>
                                                    <p>Priority :${eachTask.task_priority}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>--%>


                    <div id="accordion${estatus.index}">
                        <div class="card">
                            <div class="card-header" id="headingOne${estatus.index}">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne${estatus.index}" aria-expanded="true" aria-controls="collapseOne${estatus.index}">
                                        <input type="button" class="btn btn-info" value="Access Card Details">
                                    </button>
                                </h5>
                            </div>

                            <div id="collapseOne${estatus.index}" class="collapse" aria-labelledby="headingOne${estatus.index}" data-parent="#accordion${estatus.index}">
                                    <%--<div class="card-body">--%>
                                <div class="panel-body">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <p>Access Card Name :${employee.accessCard.card_holder_name}</p>
                                            <p>Department :${employee.accessCard.department}</p>
                                            <p>Organisation :${employee.accessCard.organization}</p>
                                        </div>
                                    </div>
                                </div>
                                    <%--</div>--%>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header" id="headingTwo${estatus.index}">
                                <h5 class="mb-0">
                                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo${estatus.index}" aria-expanded="false" aria-controls="collapseTwo${estatus.index}">
                                        <input type="button" class="btn btn-info" value="Show Tasks List">
                                    </button>
                                </h5>
                            </div>
                            <div id="collapseTwo${estatus.index}" class="collapse" aria-labelledby="headingTwo${estatus.index}" data-parent="#accordion${estatus.index}">
                                <div class="card-body">
                                    <div class="panel-body">
                                        <c:forEach var="eachTask" items="${employee.tasks}">
                                            <div class="panel panel-primary">
                                                <div class="panel-body">
                                                    <p>Task Description :${eachTask.task_desc}</p>
                                                    <p>Priority :${eachTask.task_priority}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <%--<div class="card">
                                <div class="card-header" id="headingThree${estatus.index}">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree${estatus.index}" aria-expanded="false" aria-controls="collapseThree${estatus.index}">
                                            Collapsible Group Item #3
                                        </button>
                                    </h5>
                                </div>
                                <div id="collapseThree${estatus.index}" class="collapse" aria-labelledby="headingThree${estatus.index}" data-parent="#accordion${estatus.index}">
                                    <div class="card-body">
                                        Anim pariatur cliche
                                    </div>
                                </div>
                            </div>--%>
                    </div>


                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>