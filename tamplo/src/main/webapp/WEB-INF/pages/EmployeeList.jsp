<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Screen</title>
</head>
<body>
    <div align="center">
            <h3>
    <table border="1"> 
        <th><a href="/">Home</a></th>
        <th><a href="newEmployee">New Employee</a></th>
    </table>    
</h3>
    <h1>Employee List</h1>
            <table border="1">
     
                <th>Name</th>
                <th>Email</th>
                <th>Telephone</th>
                <th>Salary</th>
     
                <c:forEach var="employee" items="${listEmployee}">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.telephone}</td>
    <td>${employee.salary}</td>
                        <td>

    <a href="editEmployee?id=${employee.id}"><input type="button" value="Edit"></a>
    <a href="deleteEmployee?id=${employee.id}"><input type="button" value="Delete"></a>
    <a href="generateAccessCard?id=${employee.id}"><input type="button" value="Generate Access Card"></a>
    </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>