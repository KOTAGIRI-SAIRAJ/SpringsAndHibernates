<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Screen</title>
    <style type="text/css">
        .table { border: 1px solid #2980B9; }
        .table thead > tr > th { border-bottom: none; }
        .table thead > tr > th, .table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid #2980B9; }
    </style>
</head>
<body>
    <div align="center">
            <h3>
    <table border="1"> 
        <th><a href="/">Home</a></th>
        <th><a href="newEmployee">New Employee</a></th>
    </table>    
</h3>
    <h1>Employee List</h1>
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
        <c:forEach var="employee" items="${listEmployee}">
                            <tr>
                                <td>${employee.name}</td>
                                <td>${employee.email}</td>
                                <td>${employee.telephone}</td>
            <td>${employee.salary}</td>
            <td>${employee.department}</td>
                                <td>
            <a href="editEmployee?id=${employee.id}"><input type="button" value="Edit"></a>
            <a href="deleteEmployee?id=${employee.id}"><input type="button" value="Delete"></a>
            <a href="accessCard?id=${employee.accessCard.id}"><input type="button" value="Access Card Details"></a>
            <a href="tasksById?id=${employee.id}"><input type="button" value="Show Tasks List"></a>
            <a href="meetings?id=${employee.id}"><input type="button" value="ShowMeetings"></a>
            </td>
                            </tr>
                        </c:forEach>
        </tbody>
    </table>
            <%--<table border="1">
     
                <th>Name</th>
                <th>Email</th>
                <th>Telephone</th>
                <th>Salary</th>
                <th>Department</th>
                <th>Operations</th>
     
                <c:forEach var="employee" items="${listEmployee}">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.telephone}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.department}</td>
                        <td>
                            <a href="editEmployee?id=${employee.id}"><input type="button" value="Edit"></a>
                            <a href="deleteEmployee?id=${employee.id}"><input type="button" value="Delete"></a>
                            <a href="accessCard?id=${employee.accessCard.id}"><input type="button" value="Access Card Details"></a>
                            <a href="tasksById?id=${employee.id}"><input type="button" value="Show Tasks List"></a>
                            <a href="meetings?id=${employee.id}"><input type="button" value="ShowMeetings"></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>--%>
        </div>
</body>
</html>