<%--
  Created by IntelliJ IDEA.
  User: sematicbits
  Date: 4/4/18
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Employee</title>
    <style type="text/css">
        .panel-success > .bc-color {
            background: #f5cf8c;
        }
    </style>
</head>
<body>
    <div align="center">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">Employee Registration</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="allEmployees">Employees</a></li>
                <li class="active"><a href="newEmployee">New Employee</a></li>
            </ul>
        </div>
    </nav>
        </div>
<div class="container">
    <div class="col-md-12 col-sm-12">
        <form:form action="saveEmployee" method="post" modelAttribute="employee">
            <div class="panel panel-primary">
                <div class="panel-heading">Registration Form</div>
                <div class="panel-body">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                        <div class="panel panel-success">
                                            <div class="panel-heading panel-body bc-color"><%--<input type="button" class="btn btn-success" value="Basic Details">--%>Basic Details</div>
                                        </div>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <form:hidden path="id"/>
                                    <label for="name">Name</label>
                                    <form:input path="name" class="form-control input-sm"/>

                                    <label for="email">Email</label>
                                    <form:input path="email"  class="form-control input-sm" />

                                    <label for="email">Salary</label>
                                    <form:input path="salary"  class="form-control input-sm" />

                                    <label for="email">Telephone</label>
                                    <form:input path="telephone"  class="form-control input-sm" />

                                    <label for="email">Department</label>
                                    <form:input path="department"  class="form-control input-sm" />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                        <div class="panel panel-success">
                                            <div class="panel-heading panel-body bc-color">Access Card Details</div>
                                        </div>

                                    </a>
                                </h4>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <form:hidden path="accessCard.id"/>
                                    <label for="email">Card Holder Name</label>
                                    <form:input path="accessCard.card_holder_name"  class="form-control input-sm" />
                                    <label for="email">Card Holder Department</label>
                                    <form:input path="accessCard.department"  class="form-control input-sm" />
                                    <label for="email">Card Holder Department</label>
                                    <form:input path="accessCard.organization"  class="form-control input-sm" />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                                        <div class="panel panel-success">
                                            <div class="panel-heading panel-body bc-color">Task Details</div>
                                        </div>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse3" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <c:forEach items="${employee.tasks}" varStatus="eStatus">

                                        <div class="panel panel-success">
                                            <div class="panel-heading">Enter Task ${eStatus.index+1} Details</div>
                                            <div class="panel-body">
                                                <form:hidden path="tasks[${eStatus.index}].id"/>
                                                <label >Task Priority</label>
                                                <form:input path="tasks[${eStatus.index}].task_priority"  class="form-control input-sm" />
                                                <label >Task Description</label>
                                                <form:input path="tasks[${eStatus.index}].task_desc"  class="form-control input-sm" />
                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>
                            </div>
                            <input type="submit" class="btn btn-primary" style="float: right" value="Save">
                        </div>
                    </div>
                        <%--<p>
                                <form:hidden path="id"/>
                                <label for="name">Name</label>
                                <form:input path="name" class="form-control input-sm"/>

                                <label for="email">Email</label>
                                <form:input path="email"  class="form-control input-sm" />

                                <label for="email">Salary</label>
                                <form:input path="salary"  class="form-control input-sm" />

                                <label for="email">Telephone</label>
                                <form:input path="telephone"  class="form-control input-sm" />

                                <label for="email">Department</label>
                                <form:input path="department"  class="form-control input-sm" />
                            </p>--%>
                        <%--<div class="panel panel-info ">
                            <div class="panel-heading">Access Card Details</div>
                            <div class="panel-body">
                                <form:hidden path="accessCard.id"/>
                                <label for="email">Card Holder Name</label>
                                <form:input path="accessCard.card_holder_name"  class="form-control input-sm" />
                                <label for="email">Card Holder Department</label>
                                <form:input path="accessCard.department"  class="form-control input-sm" />
                                <label for="email">Card Holder Department</label>
                                <form:input path="accessCard.organization"  class="form-control input-sm" />
                            </div>
                        </div>--%>
                        <%--<div class="panel panel-info ">
                            <div class="panel-heading">Task Details</div>
                            <c:forEach items="${employee.tasks}" varStatus="eStatus">
                                <div class="panel-body">
                                    <div class="panel panel-success">
                                        <div class="panel-heading">Enter Task ${eStatus.index+1} Details</div>
                                        <div class="panel-body">
                                            <form:hidden path="tasks[${eStatus.index}].id"/>
                                            <label >Task Priority</label>
                                            <form:input path="tasks[${eStatus.index}].task_priority"  class="form-control input-sm" />
                                            <label >Task Description</label>
                                            <form:input path="tasks[${eStatus.index}].task_desc"  class="form-control input-sm" />
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>--%>

                </div>

            </div>

        </form:form>
    </div>
</div>
</body>
</html>