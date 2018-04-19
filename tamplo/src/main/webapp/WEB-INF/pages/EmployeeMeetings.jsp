<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Employee</title>
</head>
<body>
<div class="container">
    <jsp:include page="Header.jsp"></jsp:include>
    <div class="col-md-12 col-sm-12" style="margin-top: 25px">
        <div class="form-group col-md-3 col-sm-3">
        </div>
        <div class="form-group col-md-6 col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">Registration Form</div>
                <div class="panel-body">
                    <form:form action="saveEmployee" method="post" modelAttribute="employee">
                        <p>
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
                        </p>
                        <div class="panel panel-info border border-danger">
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
                        </div>
                        <input type="submit" class="btn btn-primary" value="Save">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>