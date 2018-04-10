
<%--
  Created by IntelliJ IDEA.
  User: sematicbits
  Date: 6/4/18
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="p" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Enrollment Page</title>
    <style type="text/css">
        /*.table { border: 1px solid #2980B9; }
        .table thead > tr > th { border-bottom: none; }
        .table thead > tr > th, .table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid #2980B9; }*/
        html {
            font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
            font-size: 14px;
        }

        .table {
            border: none;
        }

        .table-definition thead th:first-child {
            pointer-events: none;
            background: white;
            border: none;
        }

        .table td {
            vertical-align: middle;
        }

        .page-item > * {
            border: none;
        }

        .custom-checkbox {
            min-height: 1rem;
            padding-left: 0;
            margin-right: 0;
            cursor: pointer;
        }
        .custom-checkbox .custom-control-indicator {
            content: "";
            display: inline-block;
            position: relative;
            width: 30px;
            height: 10px;
            background-color: #818181;
            border-radius: 15px;
            margin-right: 10px;
            -webkit-transition: background .3s ease;
            transition: background .3s ease;
            vertical-align: middle;
            margin: 0 16px;
            box-shadow: none;
        }
        .custom-checkbox .custom-control-indicator:after {
            content: "";
            position: absolute;
            display: inline-block;
            width: 18px;
            height: 18px;
            background-color: #f1f1f1;
            border-radius: 21px;
            box-shadow: 0 1px 3px 1px rgba(0, 0, 0, 0.4);
            left: -2px;
            top: -4px;
            -webkit-transition: left .3s ease, background .3s ease, box-shadow .1s ease;
            transition: left .3s ease, background .3s ease, box-shadow .1s ease;
        }
        .custom-checkbox .custom-control-input:checked ~ .custom-control-indicator {
            background-color: #84c7c1;
            background-image: none;
            box-shadow: none !important;
        }
        .custom-checkbox .custom-control-input:checked ~ .custom-control-indicator:after {
            background-color: #84c7c1;
            left: 15px;
        }
        .custom-checkbox .custom-control-input:focus ~ .custom-control-indicator {
            box-shadow: none !important;
        }
    </style>
</head>
<body>
<%--<div class="container">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand">Enroll Employees To ${meeting.meeting_title} meeting</a>
        <ul class="navbar-nav">
            <li><a href="/"><input type="button" class="btn" style="background-color: #343a40" value="Home"></a></li>
            <li class="active"><a href="allMeetings"><input type="button" class="btn" style="background-color: #343a40" value="Meetings"></a></li>
        </ul>
    </nav>
</div>--%>
<%--<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Enroll Employees To ${meeting.meeting_title} meeting</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="allMeeting">Meetings</a></li>
        </ul>
    </div>
</nav>--%>
<div class="container">
    <jsp:include page="Header.jsp"></jsp:include>
    <div style="margin-top: 25px">
        <form:form action="enrollSelectedEmployees" method="post" modelAttribute="meeting" >
            <h1>Enrolled Employees</h1>
            <table class="table table-bordered table-definition mb-5">
                <thead class="table-warning ">
                <tr>
                    <th>S NO</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Telephone</th>
                    <th>Salary</th>
                    <th>Department</th>
                    <%--<th>UnEnroll</th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="emp" items="${employees}" varStatus="estatus">
                    <tr>
                        <td>${estatus.index+1}</td>
                        <td>${emp.name}</td>
                        <td>${emp.email}</td>
                        <td>${emp.telephone}</td>
                        <td>${emp.salary}</td>
                        <td>${emp.department}</td>
                        <%--<td>
                            <label class="custom-control custom-checkbox" >
                                <input type="checkbox" name="unenroll" value="${emp.id}"  class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                            </label>
                        </td>--%>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <%--<a colspan="4" href="unEnrollSelectedEmployees?id=${meeting.id}"><button type="button" class="btn btn-primary" style="float:right;">UnEnroll Employees</button></a>--%>
            <h1>Not Enrolled</h1>
            <table class="table table-bordered table-definition mb-5">
                <thead class="table-warning ">
                <tr>
                    <th>S NO</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Telephone</th>
                    <th>Salary</th>
                    <th>Department</th>
                    <th>Enroll</th>
                </tr>
                </thead>
                <tbody>
                <form:hidden path="id"/>
                <c:forEach var="employee" items="${employeeList}" varStatus="estatus">
                    <tr>
                        <td>${estatus.index+1}</td>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.telephone}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.department}</td>
                        <td>
                            <label class="custom-control custom-checkbox" >
                                <input type="checkbox" name="enroll" value="${employee.id}"  class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a colspan="4" ><button type="submit" class="btn btn-primary" style="float:right;">Enroll Employees</button></a>
        </form:form>
    </div>
</div>

</body>
</html>
