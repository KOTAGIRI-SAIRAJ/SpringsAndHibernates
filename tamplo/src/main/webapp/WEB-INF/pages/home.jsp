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
    <title>Employee Management System</title>
</head>
<body>
    <div class="container">

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand">Employee Management System</a>
            <ul class="navbar-nav">
                <li class="active"><a href="/"><input type="button" class="btn" style="background-color: #343a40" value="Home"></a></li>
                <li ><a href="allEmployees"><input type="button" class="btn" style="background-color: #343a40" value="Employees"></a></li>
                <li ><a href="allTasks"><input type="button" class="btn" style="background-color: #343a40" value="Tasks"></a></li>
                <li ><a href="allMeetings"><input type="button" class="btn" style="background-color: #343a40" value="Meetings"></a></li>
                <li ><a href="allAccessCards"><input type="button" class="btn" style="background-color: #343a40" value="Access Cards"></a></li>
            </ul>
        </nav>


    <%--<nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">Employee Management System</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="allEmployees">Employees</a></li>
                <li><a href="allTasks">Tasks</a></li>
                <li><a href="allMeetings">Meetings</a></li>
                <li><a href="allAccessCards">Access Cards</a></li>
            </ul>
        </div>
    </nav>--%>
        </div>
</body>
</html>