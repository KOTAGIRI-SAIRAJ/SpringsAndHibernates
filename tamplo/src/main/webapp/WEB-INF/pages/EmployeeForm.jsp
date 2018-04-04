<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
    <div align="center">
    <h3>
        <table border="1"> 
            <th><a href="/">Home</a></th>
            <th><a href="allEmployees">View Employees</a></th>
        </table>    
    </h3>
        <h1>${FormName} Employee</h1>
    <form:form action="saveEmployee" method="post" modelAttribute="employee">
                <table>
                    <form:hidden path="id"/>
                    <tr>
                        <td>Name:</td>
                        <td><form:input path="name" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email" /></td>
                    </tr>
        <tr>
                        <td>Salary:</td>
                        <td><form:input path="salary" /></td>
                    </tr>

                    <tr>
                        <td>Telephone:</td>
                        <td><form:input path="telephone" /></td>
                    </tr>
        <tr>
                <td>Department:</td>
                <td><form:input path="department" /></td>
        </tr>
        <tr>
             <td>AccessCard Details:</td>
             
        </tr>
        <tr>
            <tr>
            <td>Card Holder Name</td>
            <td><form:input path="accessCard.card_holder_name" /></td>
            </tr>
        <tr>
            <td>Department</td>
            <td><form:input path="accessCard.department" /></td>
        </tr>
        <tr>
            <td>Organisation</td>
            <td><form:input path="accessCard.organization" /></td>
        </tr>
        </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save"></td>
                    </tr>
                </table>
                </form:form>
        </div>
</body>
</html>