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
    <form:form action="saveTask" method="post" modelAttribute="task">
                <table>
                    <form:hidden path="id"/>
                    <tr>
                        <td>Task Description:</td>
                        <td><form:input path="task_desc" /></td>
                    </tr>
                    <tr>
                        <td>Task Priority:</td>
                        <td><form:input path="task_priority" /></td>
                    </tr>
                    <tr>
                        <td>Employee: </td>
                        <td>
                            <form:select items="${employees}"  itemLabel="name" itemValue="id" path="employee.id"></form:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save"></td>
                    </tr>
                </table>
                </form:form>
        </div>
</body>
</html>