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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Employee</title>
    <style type="text/css">
        .panel-success > .bc-color {
            background: #f5cf8c;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="Header.jsp"></jsp:include>
    <div class="col-md-12 col-sm-12" style="margin-top: 25px">
        <form:form action="saveEmployee" method="post" modelAttribute="employee">
            <div class="card bg-secondary text-white">
                <div class="card-body">${FormName}</div>
            </div>
            <div class="card border-dark mb-3">
                <div class="card-body text-dark">
                    <p class="card-text">
                    <div id="accordion">
                        <div class="card border-dark mb-3">
                            <div class="card">
                                <div class="card-header" id="headingOne">
                                    <h5 class="mb-0">
                                        <a class="btn" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            Basic Details
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                    <div class="card-body">
                                        <form:hidden path="id"/>
                                        <label for="name">Name</label>
                                        <form:input path="name" class="form-control input-sm" placeholder="Enter your Name"/>
                                        <label for="email">Email</label>
                                        <form:input path="email"  class="form-control input-sm" placeholder="Enter your Email-Id"/>
                                        <label for="email">Salary</label>
                                        <form:input path="salary"  class="form-control input-sm" placeholder="Enter your Salary"/>
                                        <label for="email">Telephone</label>
                                        <form:input path="telephone"  class="form-control input-sm" placeholder="Enter your Mobile Number"/>
                                        <label for="email">Department</label>
                                        <form:input path="department"  class="form-control input-sm" placeholder="Enter your Department"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card border-dark mb-3">
                            <div class="card">
                                <div class="card-header" id="headingTwo">
                                    <h5 class="mb-0">
                                        <a class="btn collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            Access Card Details
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                    <div class="card-body text-dark">
                                        <form:hidden path="accessCard.id"/>
                                        <label >Card Holder Name</label>
                                        <form:input path="accessCard.card_holder_name"  class="form-control input-sm" placeholder="Enter your AccessCard Name"/>
                                        <label >Card Holder Department</label>
                                        <form:input path="accessCard.department"  class="form-control input-sm" placeholder="Enter your Department"/>
                                        <label >Organisation</label>
                                        <form:input path="accessCard.organization"  class="form-control input-sm" placeholder="Enter your Organisation"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card border-dark mb-3">
                            <div class="card">
                                <div class="card-header" id="headingThree">
                                    <h5 class="mb-0">
                                        <a class="btn collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            Task Details
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                    <div class="card-body text-dark" >
                                        <c:forEach items="${employee.tasks}" varStatus="eStatus">
                                            <div class="card border-dark mb-3" >
                                                <div class="card-header bg-transparent border-dark">Enter Task ${eStatus.index+1} Details</div>
                                                <div class="card-body text-dark">
                                                    <form:hidden path="tasks[${eStatus.index}].id"/>
                                                    <label >Task Priority</label>
                                                    <form:input path="tasks[${eStatus.index}].task_priority"  class="form-control input-sm" placeholder="Enter Task Priority"/>
                                                    <label >Task Description</label>
                                                    <form:input path="tasks[${eStatus.index}].task_desc"  class="form-control input-sm" placeholder="Enter Task Description"/>
                                                </div>
                                                <%--<div class="form-group">
                                                    <label for="gender1" class="col-sm-2 control-label">With Bootstrap:</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" id="gender1">
                                                            <option>Male</option>
                                                            <option>Female</option>
                                                        </select>
                                                    </div>
                                                </div>--%>
                                            </div>
                                            <%--<div class="panel panel-success">
                                               <div class="panel-heading">Enter Task ${eStatus.index+1} Details</div>
                                               <div class="panel-body">
                                                   <form:hidden path="tasks[${eStatus.index}].id"/>
                                                   <label >Task Priority</label>
                                                   <form:input path="tasks[${eStatus.index}].task_priority"  class="form-control input-sm" placeholder="Enter Task Priority"/>
                                                   <label >Task Description</label>
                                                   <form:input path="tasks[${eStatus.index}].task_desc"  class="form-control input-sm" placeholder="Enter Task Description"/>
                                               </div>
                                               </div>--%>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </p>
                    <input type="submit" class="btn btn-primary" style="float: right" value="Save">
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>