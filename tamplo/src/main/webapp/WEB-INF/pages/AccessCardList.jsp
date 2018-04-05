<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AccessCard Management Screen</title>
    <style type="text/css">
        .table { border: 1px solid #2980B9; }
        .table thead > tr > th { border-bottom: none; }
        .table thead > tr > th, .table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid #2980B9; }
    </style>
</head>
<body>
<div align="center">
    <%--        <h3>
       <table border="1">
           <th><a href="/">Home</a></th>
           <th><a href="allEmployees">Employees</a></th>
       </table>
       </h3>--%>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">AccessCard</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="allEmployees">Employees</a></li>
                <li class="active"><a href="allAccessCards">Access Cards</a></li>
            </ul>
        </div>
    </nav>
    <%--<h1>AccessCard List</h1>--%>
    <table class="table">
        <thead>
        <tr>
            <th>CardHolder Name</th>
            <th>Department</th>
            <th>Organization</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accesscard" items="${accessCards}">
            <tr>
                <td>${accesscard.card_holder_name}</td>
                <td>${accesscard.department}</td>
                <td>${accesscard.organization}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>