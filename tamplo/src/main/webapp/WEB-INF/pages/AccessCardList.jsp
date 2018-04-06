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
    <title>AccessCard Management Screen</title>
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
            <a class="navbar-brand">Access Card</a>
            <ul class="navbar-nav">
                <li><a href="/"><input type="button" class="btn" style="background-color: #343a40" value="Home"></a></li>
                <li ><a href="allEmployees"><input type="button" class="btn" style="background-color: #343a40" value="Employees"></a></li>
                <li class="active"><a href="allAccessCards"><input type="button" class="btn" style="background-color: #343a40" value="Access Cards"></a></li>
            </ul>
        </nav>
    </div>--%>
    <jsp:include page="home.jsp" />
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