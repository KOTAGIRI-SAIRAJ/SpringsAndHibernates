<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Screen</title>
</head>
<body>
    <div align="center">
            <h1>Employee List</h1>
            <h3>
                <%--<a href="newEmployee">New Employee</a>--%>
            </h3>
            <table border="1">
     
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Telephone</th>
                <th>Organization</th>
                <th>Action</th>
     
                <%--<c:forEach var="employee" items="${listEmployee}">
                    <tr>
     
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>Street: ${employee.address!=null?employee.address.street:''},
                            City: ${employee.address!=null?employee.address.city:''}</td>
                        <td>${employee.telephone}</td>
                        <td>${employee.organization!=null?(employee.organization.firstName):''}</td>
                        <td><a href="editEmployee?id=${employee.id}">Edit</a>
                                 <a href="deleteEmployee?id=${employee.id}">Delete</a></td>
     
                    </tr>
                </c:forEach>--%>
            </table>
        </div>
</body>
</html>