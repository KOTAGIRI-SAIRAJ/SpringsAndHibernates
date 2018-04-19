<%--
   Created by IntelliJ IDEA.
   User: sematicbits
   Date: 6/4/18
   Time: 7:49 PM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand">Employee Management System</a>
        <ul class="navbar-nav">
            <li><a href="/"><input type="button" class="btn" style="background-color: #343a40; padding: 15px" value="Home"></a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="employeesdrop" data-toggle="dropdown">
                    <input type="button" class="btn" style="background-color: #343a40" value="Employee">
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="newEmployee">Employee Registration</a>
                    <a class="dropdown-item" href="allEmployees">Registered Employees</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="meetingsdrop" data-toggle="dropdown">
                    <input type="button" class="btn" style="background-color: #343a40" value="Meeting">
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="newMeeting">Create Meeting</a>
                    <a class="dropdown-item" href="allMeetings">Meetings List</a>
                </div>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>