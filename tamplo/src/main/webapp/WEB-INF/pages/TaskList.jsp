<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Task Management Screen</title>
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
        <th><a href="createTask">New Task</a></th>
    </table>    
</h3>
    <h1>Task List</h1>
            <table border="1">
     
                <th>Task Description</th>
                <th>Priority</th>
                <th>Employee Name</th>
     
                <c:forEach var="task" items="${taskList}">
                    <tr>
                        <td>${task.task_desc}</td>
                        <td>${task.task_priority}</td>
                        <td>${task.employee.name}</td>
                        <td>
    <a href="editTask?id=${task.id}"><input type="button" value="Edit"></a>
    <a href="deleteTask?id=${task.id}"><input type="button" value="Delete"></a>
    </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>