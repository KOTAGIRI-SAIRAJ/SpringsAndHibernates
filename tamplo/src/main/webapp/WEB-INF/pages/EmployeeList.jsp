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
</head>
<body>
<div class="container">
    <%--<div class="container">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand">Employee List</a>
            <ul class="navbar-nav">
                <li><a href="/"><input type="button" class="btn" style="background-color: #343a40" value="Home"></a></li>
                <li class="active"><a href="allEmployees"><input type="button" class="btn" style="background-color: #343a40" value="Employees"></a></li>
                <li ><a href="newEmployee"><input type="button" class="btn" style="background-color: #343a40" value="New Employee"></a></li>
            </ul>
        </nav>
    </div>--%>
    <jsp:include page="home.jsp"></jsp:include>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Telephone</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Operations</th>
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
                        <%--<a href="accessCard?id=${employee.accessCard.id}"><input type="button" class="btn btn-info" value="Access Card Details"></a>
                           &lt;%&ndash;<a href="employee-accessCard?id=${employee.accessCard.id}"><input type="button" data-toggle="modal" data-target="#myModal" class="btn btn-info" value="Access Card Details"></a>&ndash;%&gt;
                           <a href="tasksById?id=${employee.id}"><input type="button" class="btn btn-info" value="Show Tasks List"></a>
                           <a href="meetings?id=${employee.id}"><input type="button" class="btn btn-info" value="ShowMeetings"></a>--%>
                    <div class="panel-group" id="accordion${estatus.index}">
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
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>